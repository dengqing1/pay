package com.myd.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.myd.entity.NpayMerchantBalance2018;
import com.myd.entity.NpayOrder;
import com.myd.entity.NpayTf56Bank;
import com.myd.service.BatchDaifuDataService;
import com.myd.service.NpayMerchantBalanceService;
import com.myd.service.NpayTf56BankService;
import com.myd.util.PayUtil;

/**
 * 批量代付
 *@author xiaoqiang lu
 *
 *2019/01/17 14:53
 */
@Controller
public class BatchDaiFuController {
	@Autowired
	private BatchDaifuDataService batchDaifuDataService ;
	
	@Autowired
	private NpayTf56BankService napyTf56BankService;
	
	@Autowired
	private NpayMerchantBalanceService npayMerchantBalanceService ;
	
	@RequestMapping(value = "/batchDaifu", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody()
	public String batchDaifu(NpayOrder order){
		Map<String,String> map = new HashMap<>();
		NpayMerchantBalance2018  balance = npayMerchantBalanceService.getBanlaceById(order.getMerchantid());
		if(balance == null || (balance.getBalanceAvailable()<order.getTxnamt())){
			map.put("msg", "余额不足");
			return JSON.toJSONString(map);
			
		}
		
		NpayTf56Bank nBank = napyTf56BankService.getBankByBankId(order.getBankid());//得到银行
		if(nBank == null){
			//没有该银行
			map.put("msg", "没有可用银行");
			return JSON.toJSONString(map);
		}
		String  res = batchDaifuDataService.BatchDaifu(order, nBank);
		if(res == null){
			//成功
			map.put("msg", "代付成功");
		}else{
			//模拟成功
			map.put("msg", "代付成功");
			
			//map.put("msg", "代付失败");
		}
		
		
		//模拟回调
		String param = getBankurl(order);
		PayUtil.sendPost("http://127.0.0.1:8080/fengmai/daifuReturn", param);
		
		return JSON.toJSONString(map);
		
	}
	
	
	
	public  String getBankurl(NpayOrder order){
		SortedMap<String, Object> map = new TreeMap<String, Object>();
		map.put("ret_code", "1001");
		map.put("ret_msg", "SUCCESS");
		map.put("sign", "Hiuhsidfhiuushdfihsdiof");
		map.put("merch_id", "PAY10100090000033");
		map.put("out_order_no", order.getOrderid());
		map.put("trade_no", "11111111111");
		map.put("amount", order.getTxnamt());
		return PayUtil.signature(map);
		
	}
}
