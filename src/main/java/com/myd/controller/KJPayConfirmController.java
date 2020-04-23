package com.myd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.myd.entity.KLTKJConfirmReturn;
import com.myd.entity.NpayBfInfo;
import com.myd.entity.NpayKJConfirm;
import com.myd.entity.NpayMerInfo;
import com.myd.entity.NpayOrder;
import com.myd.entity.RspMsg;
import com.myd.service.NpayBfInfoService;
import com.myd.service.NpayMerInfoService;
import com.myd.service.NpayOrderService;
import com.myd.service.ThirdPayService;
import com.myd.util.DateUtil;
import com.myd.util.EntityIsNullUtil;
import com.myd.util.KLTUtil;
import com.myd.util.PayUtil;

/**
 * 快捷支付确认
 *@author xiaoqiang lu
 *
 *2019/01/21 18:58
 */
@Controller
public class KJPayConfirmController {
	
	private static Logger logger = Logger.getLogger(KJPayConfirmController.class);
	@Autowired
	private NpayOrderService npayOrderService ;
	@Autowired
	private NpayBfInfoService npayBfInfoService ;
	@Autowired
	private ThirdPayService thirdService ; 
	@Autowired
	private NpayMerInfoService npayMerInfoService;
 	/**
 	 * 快捷支付确认支付
 	 * @param confirm
 	 * @param response
 	 * @param request
 	 */
	@RequestMapping("/kjpayConfirm")
	public void  kjPayConfirm(NpayKJConfirm confirm,HttpServletResponse response,HttpServletRequest request){
		logger.info("确认支付发来的信息"+confirm);
		RspMsg pesMsg = new RspMsg();
		pesMsg.setTimestamp(DateUtil.getNowTimeStamp());
		//拿传过来的订单号查询订单信息跟商户号去查询订单信息
		String merId = confirm.getMerchantId();
		String merOid = confirm.getMerOrderId();
		NpayMerInfo merInfo = npayMerInfoService.getMerInfoById(merId);
		if(merInfo == null){
			//订单或商户号有误
			logger.info("确认支付商户号不存在");
			pesMsg.setMsg("商户号不存在");
			jsonString(pesMsg, null, response);
			return;
		}
		List<String> list = EntityIsNullUtil.checkObjFieldIsNull(confirm);
		if(list.size()>0){
			//不能有空字符串
			logger.info("确认支付有的字段是空值");
			pesMsg.setMsg("所有字段都必须填写");
			jsonString(pesMsg, null, response);
			return;
		}
		String key = merInfo.getMerSecretKey();
		SortedMap<String, Object> sortedMap = PayUtil.mapToSortedMap(request);
		String signature = PayUtil.signMethod(sortedMap,key);
		if(!confirm.getSignature().equals(signature)){
			logger.info("确认支付验签不通过");
			pesMsg.setMsg("签名失败");
			jsonString(pesMsg, null, response);
			return;
			
		}
		
		
		NpayOrder order = npayOrderService.getOrderByMerChantIdandMerOId(merId,merOid);
		if(order == null ){
			//订单或商户号有误
			logger.info("确认支付商户号或订单号错误");
			pesMsg.setMsg("订单号或商户号错误");
			jsonString(pesMsg, null, response);
			return;
		}
		//查询原来的流水号
		NpayBfInfo info = npayBfInfoService.getInfoByMerOid(merOid);
		if(info == null ){
			logger.info("确认支付订单号不存在");
			//订单或商户号有误
			pesMsg.setMsg("订单号错误");
			jsonString(pesMsg, null, response);
			return;
		}
		String smsCode = confirm.getSmsCode();//短信验证码
		String reqId = info.getRequestid();
		String res = thirdService.kjConfirm(order,smsCode,reqId);//去向开联通发确认支付信息
		
		KLTKJConfirmReturn payReturn = KLTUtil.stringToEntity(res, KLTKJConfirmReturn.class);
		String status = payReturn.getOrderState();
		if("SUCCESS".equals(status)){
			pesMsg.setMsg("支付成功");
			pesMsg.setSuccess(1);
			pesMsg.setCode("1001");
			jsonString(pesMsg, key, response);
			return;
			
		}else{
			pesMsg.setMsg(payReturn.getResponseMsg());
			pesMsg.setSuccess(0);
			pesMsg.setCode("1002");
			jsonString(pesMsg, key, response);
			return;
			
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

}
