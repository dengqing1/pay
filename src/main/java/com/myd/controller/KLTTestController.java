package com.myd.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.myd.entity.KLTDuanXin;
import com.myd.entity.KLTGatewayContent;
import com.myd.entity.KLTKJConfirmPay;
import com.myd.entity.KLTKJDuanXinReturn;
import com.myd.entity.KLTKJHead;
import com.myd.util.DateUtil;
import com.myd.util.KLTUtil;
import com.myd.util.PayUtil;


@Controller
@RequestMapping("klt")
public class KLTTestController {
	/**
	 * 网关支付的界面
	 * @return
	 */
	@RequestMapping("/gatewaytest")
	public  String  gatewaypay(Model model){
		String id  = DateUtil.getOrderId(new Date());
		model.addAttribute("orderid", id);
		model.addAttribute("time", DateUtil.getNowTimeWithyyyyMMddHHmmss());
		return "kltpage/gateway"; 
	 }

	
	
	/*
	 * 网关确认支付
	 */
	@RequestMapping("/gatewayConfirm")
	public String sendGateWay(KLTGatewayContent cont){
		Map<Object,Object> map = new HashMap<>();
		KLTKJHead head = new KLTKJHead();
		head.setMerchantId("903110153110001");
		String sign = KLTUtil.getSign(cont, head, "742fa3ffd050fb441763bf8fb6c0594f");
		head.setSign(sign);
		map.put("content", cont);
		map.put("head", head);
		String parm = JSON.toJSON(map).toString();
		String res = PayUtil.sendPost("https://ipay.chinasmartpay.cn/openapi/merchantPayment/order",parm);
		System.out.println("网关支付的返回信息："+res);
		//KLTKJDuanXinReturn passport =KLTUtil.stringToEntity(res, KLTKJDuanXinReturn.class);
		
		return "";
	}
	
	
	
	/**
	 * 发送短信的输入界面
	 * @return
	 */
	@RequestMapping("/duanXinTest")
	public String KjDuanxin(Model model){
		String id = DateUtil.getOrderId(new Date());
		model.addAttribute("time",id );
		return "kltpage/duanxintest";
		
	}
	
	/**
	 * 发送短信并且跳到填写确认支付界面
	 * @return
	 */
	@RequestMapping("/sendDuanxin")
	public String KjConfirm(Model model,KLTDuanXin duanxin){
		Map<Object,Object> map = new HashMap<>();
		KLTKJHead head = new KLTKJHead();
		head.setMerchantId("903110153110001");
		String sign = KLTUtil.getSign(duanxin, head, "742fa3ffd050fb441763bf8fb6c0594f");
		head.setSign(sign);
		map.put("content", duanxin);
		map.put("head", head);
		String parm = JSON.toJSON(map).toString();
		System.out.println("参数："+parm);
		String res = PayUtil.sendPost("http://47.99.165.66/pay/kltkjmessagezhuanfacs",parm);
		KLTKJDuanXinReturn passport =KLTUtil.stringToEntity(res, KLTKJDuanXinReturn.class);
		if("000000".equals(passport.getResponseCode())){
			model.addAttribute("stau", "SUCCESS");	
			model.addAttribute("duanxin", passport);
			model.addAttribute("time", DateUtil.getNowTimeWithyyyyMMddHHmmss());
		}else{
			
			
			model.addAttribute("msg", passport.getResponseMsg());
	
		}
		return "kltpage/kjconfirm";
		
	}
	
	@RequestMapping("/confirmPay")
	public String  KjSendConfirm(Model model ,KLTKJConfirmPay pay){
		
		Map<Object,Object> map = new HashMap<>();
		KLTKJHead head = new KLTKJHead();
		head.setMerchantId("903110153110001");
		String sign = KLTUtil.getSign(pay, head, "742fa3ffd050fb441763bf8fb6c0594f");
		head.setSign(sign);
		map.put("content", pay);
		map.put("head", head);
		String parm = JSON.toJSON(map).toString();
		System.out.println("确认支付："+parm);
		String res = PayUtil.sendPost("http://47.99.165.66/pay/kltkuaijiezhuanfacs",parm);
		System.out.println("确认支付返回的信息："+res);
		model.addAttribute("msg", res);
		return "kltpage/kjconfirmpayreturn";
	}
	

	
	
		
	
	
	
}
