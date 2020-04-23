package com.myd.manager.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.myd.entity.NpayBalanceOplogs;
import com.myd.entity.NpayChannels;
import com.myd.entity.NpayMerchantBalance2018;
import com.myd.entity.NpayOrder;
import com.myd.manager.service.ChannelService;
import com.myd.manager.service.NpayBalanceOplogsService;
import com.myd.manager.service.NpayMerchantBalance2018Service;
import com.myd.service.NpayOrderService;
import com.myd.util.Base64CodeUtil;
import com.myd.util.DateUtil;
import com.myd.util.Msg;
import com.myd.util_wx.Pay10088Util;

@Controller
@RequestMapping("/Oplogs")
public class NpayBalanceOplogsController {
	
	@Autowired
	private NpayOrderService nPayOrderService;
	
	@Autowired
	private NpayBalanceOplogsService npayBalanceOplogsService;
	
	@Autowired
	private NpayMerchantBalance2018Service npayMerchantBalance2018Service;
	
	@Autowired
	private ChannelService channelService;
	/**
	 * 调帐列表显示
	 * @return
	 */
	@RequestMapping(value = "/Oplogslist", method = RequestMethod.GET)
	public String Oplogslist(HttpServletRequest request){
		NpayBalanceOplogs npayBalanceOplogs = new NpayBalanceOplogs();
		List<NpayBalanceOplogs> list = npayBalanceOplogsService.selectByExampleoplogs(npayBalanceOplogs);
		request.setAttribute("list", list);
		
		
		//查询渠道表
		NpayChannels npayChannels = new NpayChannels();
		List<NpayChannels> npayChannelslist = channelService.selectByPrimary(npayChannels);
		request.setAttribute("npayChannelslist", npayChannelslist);
		
		
		return "account";
	}
	
	@RequestMapping(value = "/saveOplogs", method = RequestMethod.POST)
	@ResponseBody
	public Msg saveOplogs(NpayBalanceOplogs npayBalanceOplogs,HttpServletRequest request){
		npayBalanceOplogs.setCreateAt(DateUtil.getDate());
		npayBalanceOplogs.setUpdateAt(DateUtil.getDate());
		npayBalanceOplogs.setSettleAmount(0);
		String str = new BigDecimal(npayBalanceOplogs.getTxnamt()).multiply(new BigDecimal("100")).stripTrailingZeros().toPlainString();
		npayBalanceOplogs.setTxnamt(str);
		
		NpayMerchantBalance2018 balance =  npayMerchantBalance2018Service.selectByPrimaryKey(npayBalanceOplogs.getMerId());
		//判断输入的金额是正数还是负数
		String gateway = "" ;
		int txnamt = 0 ;
		if(Integer.parseInt(npayBalanceOplogs.getTxnamt()) > 0){
			gateway = "bank" ; 
			txnamt = Integer.parseInt(npayBalanceOplogs.getTxnamt());
			long longValue = new BigDecimal(balance.getBalance()).add(new BigDecimal(npayBalanceOplogs.getTxnamt())).stripTrailingZeros().longValue();
			long Value = new BigDecimal(balance.getBalanceAvailable()).add(new BigDecimal(npayBalanceOplogs.getTxnamt())).stripTrailingZeros().longValue();
			balance.setBalance(longValue);
			balance.setBalanceAvailable(Value );
			npayMerchantBalance2018Service.updateByPrimaryKey(balance);
		}else{
			gateway = "daifu" ; 
			txnamt = Math.abs(Integer.parseInt(npayBalanceOplogs.getTxnamt()));
			long longValue = new BigDecimal(balance.getBalance()).add(new BigDecimal(npayBalanceOplogs.getTxnamt())).stripTrailingZeros().longValue();
			long Value = new BigDecimal(balance.getBalanceAvailable()).add(new BigDecimal(npayBalanceOplogs.getTxnamt())).stripTrailingZeros().longValue();
			balance.setBalance(longValue);
			balance.setBalanceAvailable(Value);
			npayMerchantBalance2018Service.updateByPrimaryKey(balance);
		}
		
		npayBalanceOplogsService.insertSelective(npayBalanceOplogs);
		
		
//		插入order表
		NpayOrder order =new NpayOrder();
		
		order.setMerchantid(npayBalanceOplogs.getMerId());
		order.setMerorderid(Pay10088Util.generateOrderId());
		order.setTxnamt(txnamt);
		order.setGateway(gateway);
		// order.setOrderid(DateUtil.getOrderId(date));
		order.setOrderid(Pay10088Util.generateOrderId()); // 20为随机字符串
		order.setStatus(1001);
		order.setStatusdesc("调账成功");
		order.setNotifytimes(123);// 通知时间
		order.setRefundtimes((byte) 0);// 退款时间
		
		order.setOrderid(Pay10088Util.generateOrderId());
		order.setStatus(1001);// 待支付
		order.setStatusdesc("支付成功");
		order.setNotifytimes(123);// 通知时间
		order.setRefundtimes((byte) 0);// 退款时间
		order.setInFee("0");// 下家手续费金额(需要根据金额及下家的手续费计算)
		order.setInFeeAmount("0");// 下家费率(根据下家提供的进行查询)
		order.setInFeeType("0");// 下家手续费类型
		order.setOutFee("0");// 上家手续费金额(根据金额及费率进行计算)
		order.setOutFeeAmount("0");// 上家费率(在表里面查询)
		order.setOutFeeType("0");// 上家手续费类型
		order.setChannelMerAbbr(request.getParameter("channelabbr"));// 渠道商户名缩写(根据商户号去inrote表里面查询)
		order.setChannelId(request.getParameter("channelabbr"));// 渠道号
		order.setChannelMerId(request.getParameter("channelmerid"));// 渠道商户号
		order.setCheckStatus(0);// 0:没有对账, 1:对账成功, 默认0
		order.setCstatus(" ");
		order.setLastUpdate(DateUtil.getDateFormart(new Date()));

		
		nPayOrderService.addOrder(order);
		
		return Msg.success();
		
	}
	
	/**
	 * 异常订单列表
	 * @param orderId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public String findAll(String orderId,HttpServletRequest request,Integer page){
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("orderId", orderId);
    	List<Map<String,Object>> list =npayBalanceOplogsService.findByAbnormal(map,page);
    	request.setAttribute("list", list);
    	PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		request.setAttribute("pageInfo", pageInfo);
    	return "abnormal_list";
    }

}
