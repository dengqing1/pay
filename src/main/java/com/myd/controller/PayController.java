package com.myd.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.alibaba.fastjson.JSON;
import com.myd.entity.KJDuanXinReturn;
import com.myd.entity.KLTKJDuanXinReturn;
import com.myd.entity.NpayBfInfo;
import com.myd.entity.NpayKJ;
import com.myd.entity.NpayMerInfo;
import com.myd.entity.NpayMerchantBalance2018;
import com.myd.entity.NpayOrder;
import com.myd.entity.NpayTf56Bank;
import com.myd.entity.OrdersBank;
import com.myd.entity.OrdersDaifu;
import com.myd.entity.RspMsg;
import com.myd.service.NpayBfInfoService;
import com.myd.service.NpayMerInfoService;
import com.myd.service.NpayMerchantBalanceService;
import com.myd.service.NpayOrderService;
import com.myd.service.NpayTf56BankService;
import com.myd.service.PayService;
import com.myd.service.ThirdPayService;
import com.myd.util.DateUtil;
import com.myd.util.EntityIsNullUtil;
import com.myd.util.KLTUtil;
import com.myd.util.PayUtil;

/**
 * 下单
 * 
 * @author xiaoqiang lu
 *
 * 2018/12/21 17:56
 */
@Controller
public class PayController {
	private static Logger logger = Logger.getLogger(PayController.class);
	@Autowired
	private NpayMerInfoService npayMerInfoService;
	@Autowired
	private PayService payService;
	@Autowired
	private ThirdPayService thirdPayService;
	@Autowired
	private NpayTf56BankService napyTf56BankService;
	@Autowired
	private NpayOrderService npayOrderService ;
	@Autowired
	private NpayMerchantBalanceService npayMerchantBalanceService ;
	@Autowired
	private NpayBfInfoService npayBfInfoService ;
	/*
	 * 实际上是post请求（后期改过来）
	 */
//	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	public void pay(OrdersBank orderInfo, OrdersDaifu orderInfoDaifu, NpayKJ paykj ,HttpServletResponse response) {
		logger.info("传来的信息>>"+JSON.toJSONString(orderInfoDaifu));
		RspMsg pesMsg = new RspMsg();
		pesMsg.setTimestamp(DateUtil.getNowTimeStamp());
		String getway = orderInfo.getGateway();// 得到网关是bank还是daifu
		String merId = orderInfo.getMerchantId();// 商户的id
		NpayMerInfo npayMerInfo  = npayMerInfoService.getMerInfoById(merId);
		String key = null;
		if(npayMerInfo!=null){
			key = npayMerInfo.getMerSecretKey();
		}
		
		
		
		//根据网关判断有没有不能为空的字符串
		if(getway == null){
			pesMsg.setMsg("网关错误");
			jsonString(pesMsg, key, response);
			return;
			
		}else if("bank".equals(getway)){
			
			List<String> list = EntityIsNullUtil.checkObjFieldIsNull(orderInfo);
			if(!isRightFiled(list,"bank")){
				pesMsg.setMsg("必须传的字符串不能为空");
				jsonString(pesMsg, key, response);
				return;
				
			}
			
			
		}else if("daifu".equals(getway)){
			List<String> list = EntityIsNullUtil.checkObjFieldIsNull(orderInfoDaifu);
			if(!isRightFiled(list,"daifu")){
				pesMsg.setMsg("必须传的字符串不能为空");
				jsonString(pesMsg, key, response);
				return;
				
			}
			
			
		}else if("kuaijie".equals(getway)){
			String type = paykj.getDcType();
			int dctyp = 0;
			try{
				dctyp = Integer.parseInt(type);
			}catch (Exception e) {
				pesMsg.setMsg("只能为借记卡或借贷卡");
				jsonString(pesMsg, key, response);
				return;
			}
			
			if(dctyp !=0 && dctyp!=1){
				pesMsg.setMsg("只能为借记卡或借贷卡");
				jsonString(pesMsg, key, response);
				return;
				
			}
			List<String> list = EntityIsNullUtil.checkObjFieldIsNull(paykj);
			if(dctyp == 1){
				//信用卡
				if(list.size() > 0 ){
					pesMsg.setMsg("必须传的字符串不能为空");
					jsonString(pesMsg, key, response);
					return;
				}else{
					//判断日期跟安全码
					String date = paykj.getAcctValidDate();
					
					if(date.trim().length() !=4){
						pesMsg.setMsg("信用卡有效日期只能是4位整数");
						jsonString(pesMsg, key, response);
						return;
					}
					try{
						dctyp = Integer.parseInt(date);
					}catch (Exception e) {
						pesMsg.setMsg("信用卡有效日期只能是4位整数");
						jsonString(pesMsg, key, response);
						return;
					}
					
					String anquanma = paykj.getCvv2();
					if(anquanma.trim().length() !=3){
						pesMsg.setMsg("信用卡安全码只能是3位整数");
						jsonString(pesMsg, key, response);
						return;
					}
					try{
						Integer.parseInt(anquanma);
					}catch (Exception e) {
						pesMsg.setMsg("信用卡安全码只能是3位整数");
						jsonString(pesMsg, key, response);
						return;
					}
	
				}
				
			}
			//快捷支付要验证的东西
			//对应的字符串是否为空
			
			if(!isRightFiledKuaijie(list,dctyp)){
				pesMsg.setMsg("必须传的字符串不能为空");
				jsonString(pesMsg, key, response);
				return;
			}
			
			
			
			
			
		}else{
			
			pesMsg.setMsg("网关错误");
			jsonString(pesMsg, key, response);
			return;
		}
		
		
		
		
		String money = orderInfo.getTxnAmt();
		try{
			Integer.parseInt(money);
		}catch (Exception e) {
			pesMsg.setMsg("金额只能为整数");
			jsonString(pesMsg, key, response);
			return;
		}
		if(money.length()>=10){
			pesMsg.setMsg("金额最大为9位整数");
			jsonString(pesMsg, key, response);
			return;
		}
		
		
		
		
		//查找订单号存不存在
		NpayOrder orderTemp = npayOrderService.getOrderByMerChantId(orderInfo.getMerOrderId());
		if(orderTemp!=null){
			pesMsg.setMsg("该订单号已存在");
			jsonString(pesMsg, key, response);
			return;
		}
		NpayTf56Bank nBank = null ;
		if(!"kuaijie".equals(getway)){
			 nBank = napyTf56BankService.getBankByBankId(orderInfo.getBankId());//得到银行
			if(nBank == null){
				//没有该银行
				pesMsg.setMsg("没有可用银行");
				jsonString(pesMsg, key, response);
				return;
			}
		}
		
		
		// 商户存在
		// 有该商户，正常判断
		
		 if ("bank".equals(getway.trim())) {
			 
			 if(0!= orderInfo.getDcType()){
				pesMsg.setMsg("借贷类型必须为0");
				jsonString(pesMsg, key, response);
				return;
			 }
			NpayOrder order = new NpayOrder();
			int res = payService.addOrder(order, orderInfo);//添加订单
			if(res ==  200){
				String payThird = thirdPayService.thirdPay("bank",orderInfo,order,nBank);//第三方的支付
				if(payThird == null){
					//请求成功
					pesMsg.setSuccess(1);
					pesMsg.setCode("1000");
					pesMsg.setMsg("支付成功");
					jsonString(pesMsg, key, response);
					return;	
					
				} else{
					//直接把第三方的错误信息发给商户
					pesMsg.setMsg(payThird);
					jsonString(pesMsg, key, response);
					return;
				}
			}else{
				//生成订单的时候有错误
				
				pesMsg.setMsg("金额不正确");
				jsonString(pesMsg, key, response);
				return;
			}
			
			
			

		} else if ("daifu".equals(getway)) {
			if(!"CNY".equals(orderInfoDaifu.getCurrency())){
				
				pesMsg.setMsg("货币类型错误,只支持(CNY)");
				jsonString(pesMsg, key, response);
				return;
			}
			
			
			int txnmon = Integer.parseInt(orderInfoDaifu.getTxnAmt());
			NpayMerchantBalance2018  balance = npayMerchantBalanceService.getBanlaceById(orderInfoDaifu.getMerchantId());
			if(balance == null || (balance.getBalanceAvailable()<txnmon )){
				pesMsg.setMsg("余额不足,请充值");
				jsonString(pesMsg, key, response);
				return;
				
			}
			
			
			
			
			NpayOrder order = new NpayOrder();
			int res = payService.addOrder(order, orderInfoDaifu);//添加订单
			if(res == 200){//生成订单成功
				String payThird = thirdPayService.thirdPay("daifu",orderInfoDaifu,order,nBank);
				if(payThird == null){

					//请求成功
					pesMsg.setSuccess(1);
					pesMsg.setCode("1000");
					pesMsg.setMsg("支付成功");
					jsonString(pesMsg, key, response);
					return;
					
				} else{
					//直接把第三方的错误信息发给商户
					pesMsg.setMsg(payThird);
					jsonString(pesMsg, key, response);
					return;
				}
				
				
				
				
			}else{
				//生成订单的时候有错误
				pesMsg.setMsg("金额不正确");
				jsonString(pesMsg, key, response);
				return;
				
			}
			

		}else if("kuaijie".equals(getway)){
			
			
			
			//快捷支付
			NpayOrder order = new NpayOrder();
			int res = payService.addOrder(order, paykj);//添加订单
			if(res == 200){//生成订单成功
				String payThird = thirdPayService.KLTKJPay(order);
				logger.info("快捷支付发送短信返回的信息>>>>"+payThird);
				KLTKJDuanXinReturn msg = KLTUtil.stringToEntity(payThird, KLTKJDuanXinReturn.class);
				if("000000".equals(msg.getResponseCode())){
					//短信发送成功
					//1.保存信息
					NpayBfInfo info = new NpayBfInfo();
					info.setRequestid(msg.getRequestId());
					info.setOrderid(msg.getOrderNo());
					npayBfInfoService.updateBfinfo(info);
					//2.返回商户信息(订单id)
					KJDuanXinReturn duanxinReturn = new KJDuanXinReturn();
					duanxinReturn.setMerOrderId(order.getMerorderid());
					duanxinReturn.setTimestamp(DateUtil.getNowTimeStamp());
					duanxinReturn.setMsg(msg.getResponseMsg());
					jsonStringDuanxin(duanxinReturn, key, response);
					return;
				}else{
					//短信发送失败
					//2.返回商户信息(订单id)
					KJDuanXinReturn duanxinReturn = new KJDuanXinReturn();
					duanxinReturn.setSuccess(0);
					duanxinReturn.setMerOrderId(msg.getOrderNo());
					duanxinReturn.setTimestamp(DateUtil.getNowTimeStamp());
					duanxinReturn.setMsg(msg.getResponseMsg());
					jsonStringDuanxin(duanxinReturn, key, response);
					return;	
				}
				
			}else{
				//生成订单的时候有错误
				pesMsg.setMsg("金额不正确");
				jsonString(pesMsg, key, response);
				return;
				
			}
			
			
		}else{
			//其他支付
			
			
		}

	}
	
	
	
	
	
	/**
	 * 短信发送成功的结果
	 * @param resMsg
	 * @param key
	 * @param response
	 */
	public void jsonStringDuanxin(KJDuanXinReturn resMsg,String key,HttpServletResponse response){
		
		if(key == null){
			
			
		}else{
			SortedMap<String, Object> map = null ;
			try {
				map = PayUtil.objectToSortedMap(resMsg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String str = PayUtil.signMethod(map, key);
			resMsg.setSignature(str);
			
		}
		
		PrintWriter out  = null;
		response.setContentType("application/json;charset=UTF-8");
	    response.setHeader("Pragma", "No-cache");
	    response.setHeader("Cache-Control", "no-cache");
	    response.setDateHeader("Expires", 0);
	    String res = JSON.toJSONString(resMsg);
	       
        try {
			out = response.getWriter();
			out.write(res);
		    out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.close();
		}
    	
		
		
		
		
	}
	
	
	
	public void jsonString(RspMsg resMsg,String key,HttpServletResponse response){
		
		if(key == null){
			
			
		}else{
			SortedMap<String, Object> map = null ;
			try {
				map = PayUtil.objectToSortedMap(resMsg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String str = PayUtil.signMethod(map, key);
			resMsg.setSignature(str);
			
		}
		
		PrintWriter out  = null;
		response.setContentType("application/json;charset=UTF-8");
	    response.setHeader("Pragma", "No-cache");
	    response.setHeader("Cache-Control", "no-cache");
	    response.setDateHeader("Expires", 0);
	    String res = JSON.toJSONString(resMsg);
	       
        try {
			out = response.getWriter();
			out.write(res);
		    out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.close();
		}
    	
		
		
		
		
	}
	
	
	public void returnRes(int res,String key,HttpServletResponse response,RspMsg pesMsg){
		
		if (res == 0) {
			// 必传的字符串为空了
			pesMsg.setMsg("必须传的签名信息不能为空");
			jsonString(pesMsg,key,response);

		} else if (res == 1) {
			// url有非法字符
			pesMsg.setMsg("url有非法字符");
			jsonString(pesMsg,key,response);

		} else if (res == 2) {
			// 签名信息失败
			pesMsg.setMsg("签名验证失败");
			jsonString(pesMsg,key,response);

		} else if (res == 3) {

			// 没有该商户
			pesMsg.setMsg("无效商户");
			jsonString(pesMsg,null,response);

		} else {
			// 未知错误
			pesMsg.setMsg("未知错误");
			jsonString(pesMsg,key,response);

		}
		
		
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
	
	
	
	public  String getdaifuurl(NpayOrder order){
		SortedMap<String, Object> map = new TreeMap<String, Object>();
		map.put("ret_code", "1001");
		map.put("ret_msg", "SUCCESS");
		map.put("sign", "Hiuhsidfhiuushdfihsdiof");
		map.put("merch_id", "PAY10100090000033");
		map.put("out_order_no", order.getOrderid());
		map.put("trade_no", "11111111111");
		map.put("amount", order.getTxnamt());
		map.put("fee", 10000);
		return PayUtil.signature(map);
	
	}
	
	
	
	
	/**
	 * 判断是不是指定可以不传的字符串
	 * @param list
	 * @return
	 */
	public boolean isRightFiled(List<String> list,String getway){
		if(list == null){
			//没有直接返回true
			return true ;
		} 
		if("bank".equals(getway.trim())){

			if(list.size() >= 0 && list.size() <= 2){
				list.remove("userId") ;
				list.remove("customerIp");
				//如果还有的话就返回false
				if(list.size() == 0){
					return true ;
				}
			}
		}else if("daifu".equals(getway.trim())){
			
			if(list.size() >= 0 && list.size() <= 1){
				list.remove("purpose") ;
				//如果还有的话就返回false
				if(list.size() == 0){
					return true ;
				}
			}
			
			
		}
		
		
		return false;
		
	}
	
	
	
	/**
	 * 判断是不是指定可以不传的字符串
	 * @param list
	 * @return
	 */
	public boolean isRightFiledKuaijie(List<String> list,int type){
		if(list == null){
			//没有直接返回true
			return true ;
		} 
		if(0 == type){
			//信用卡
			if(list.size() >= 0 && list.size() <= 2){
				list.remove("cvv2") ;
				list.remove("acctValidDate");
				//如果还有的话就返回false
				if(list.size() == 0){
					return true ;
				}
			}
		}else {
			
			if(list.size() == 0){
				return true ;
			}
			
			
		}
		
		
		return false;
		
	}
	
	
	
	
	
	

}
