package com.myd.controller;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myd.config.PayConfig;
import com.myd.entity.OrdersBank;
import com.myd.entity.OrdersDaifu;
import com.myd.util.DateUtil;
import com.myd.util.PayUtil;
import com.myd.util.WriteJsonUtil;
import com.myd.util_wx.VirtualUtil;

@Controller
@RequestMapping("/juhe")
public class JuheTestController {
	

	
	

	/*
	 * 
	 * 数据处理
	 * 
	 */
	//模拟用户传的参数
	@RequestMapping("/juhe")
	public String  juhe(){
		
		return "juhe/test";
	}
	
	
	//虚拟币支付链接
	@RequestMapping("/virtual")
	public String  virtual(){
		
		return "juhe/virtual";
	}
	
	
	
	//模拟我们要传的参数
	@RequestMapping("/juhetest")
	public String  juhe222(HttpServletRequest request){
		
		
		SortedMap<String, Object> pay = (SortedMap<String, Object>) VirtualUtil.PayParams(request.getParameter("txnAmt"));
		request.setAttribute("juheOrder", pay);
		
		
		
		return "juhe/juhe";
	}
	

	@RequestMapping("/juheBank")
	public String test(Model model ,OrdersBank obank,String key,HttpServletResponse response) throws Exception {
		String money = obank.getTxnAmt();
		try{
			Integer.parseInt(money);
		}catch (Exception e) {
			WriteJsonUtil.writeJson(response, "金额只能为整数");
			return "";
		}
		if(money.length()>=10){
			WriteJsonUtil.writeJson(response, "金额最大为9位整数");
			return "";
		}
		
		SortedMap<String, Object> parameters = new TreeMap<String, Object>();
		parameters = objectToSortedMap(obank);
		String sign = PayUtil.signMethod(parameters,key);// 得到方法签名
		if(obank.getSubject()!=null && !"".equals(obank.getSubject())){
			parameters.put("subject", new String(Base64.encodeBase64(obank.getSubject().getBytes("UTF-8")), "UTF-8"));
			
		}
		if(obank.getBody()!=null && !"".equals(obank.getBody())){
			parameters.put("body", new String(Base64.encodeBase64(obank.getBody().getBytes("UTF-8")), "UTF-8"));
			
		}
		
		parameters.put("signMethod", "MD5");
		parameters.put("signature", sign);
		model.addAttribute("map", parameters);
		
		return "juhe/juhesubmit";

	}
	


 
	public static void writeJson(HttpServletResponse response,String obj){
	  PrintWriter out  = null;
	  response.setContentType("application/json;charset=UTF-8");
      response.setHeader("Pragma", "No-cache");
      response.setHeader("Cache-Control", "no-cache");
      response.setDateHeader("Expires", 0);
        try {
			out = response.getWriter();
	        out.write(obj);
	        out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			out.close();
		}
    	
		
	
		
	}
 
 
		
	@RequestMapping("/juheResult")
	public String juheResult(){
		
		return "juhe/juheResult";
	}
	
	
	
	@RequestMapping("/juheResult10")
	public String juheResult10(){
		
		return "juhe/Result";
	}
	
	
	@RequestMapping("/juheDaifuTest")
	public String  juheDaifuTest(){
		
		return "juhe/juheDaifuTest";
	}
	
	
	@RequestMapping("/juheDaifu")
	public String  juheDaifu(){
		
		System.out.println("----------------daifu");
		return "juhe/juheDaifu";
	}

	@RequestMapping("/testDaifu")
	public String testDaifu(Model model,OrdersDaifu daifu,String key,HttpServletResponse response)throws Exception{
		String money = daifu.getTxnAmt();
		try{
			Integer.parseInt(money);
		}catch (Exception e) {
			WriteJsonUtil.writeJson(response, "金额只能为整数");
			return "";
		}
		if(money.length()>=10){
			WriteJsonUtil.writeJson(response, "金额最大为9位整数");
			return "";
		}
		SortedMap<String, Object> parameters = new TreeMap<String, Object>();
			parameters = objectToSortedMap(daifu);
			String sign = PayUtil.signMethod(parameters,key);// 得到方法签名
			
			if(daifu.getSubject()!=null && !"".equals(daifu.getSubject())){
				parameters.put("subject", new String(Base64.encodeBase64(daifu.getSubject().getBytes("UTF-8")), "UTF-8"));
				
			}
			if(daifu.getBody()!=null && !"".equals(daifu.getBody())){
				parameters.put("body", new String(Base64.encodeBase64(daifu.getBody().getBytes("UTF-8")), "UTF-8"));
				
			}
			parameters.put("signMethod", "MD5");
			parameters.put("signature",sign );
			model.addAttribute("map", parameters);
			return "juhe/juhedaifusubmit";
			
	}
	
	
	 public  SortedMap<String, Object> objectToSortedMap(Object obj) throws Exception {
	        if (obj == null)
	            return null;

	        SortedMap<String, Object> sortedMap = new TreeMap<String, Object>();
	        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
	        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
	        for (PropertyDescriptor property : propertyDescriptors) {
	            String key = property.getName();
	            if (key.compareToIgnoreCase("class") == 0) {
	                continue;
	            }
	            Method getter = property.getReadMethod();
	            Object value = getter != null ? getter.invoke(obj) : null;
	            if(value == null || "".equals(value)){
	            	
	            }else{
	            	 sortedMap.put(key, value);
	            }
	           
	        }
	        return sortedMap;
	    }

	
	
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 //充值       recharge
	 
	 	 
	 @RequestMapping("/toRecharge")
	 public  String  toRecharge(){
		 
		 
		 return "juhe/recharge";
	 }

	 @RequestMapping("/toRecharge3")
	 public  String  toRecharge3(){
		 
		 
		 return "juhe/recharge2";
	 }
	 
	 
	 @RequestMapping("/toRechargeList")
	 public  String  toRechargeList(){
		 
		 
		 return "juhe/rechargeList";
	 }
	 
	 
	 
	 @RequestMapping("/recharge")
	 public  void    recharge(OrdersBank orderInfo,HttpServletResponse response,HttpServletRequest request) throws Exception{
		 
		 
		 OrdersBank bank = new OrdersBank();
			bank.setMerchantId(orderInfo.getMerchantId());
			bank.setMerOrderId(DateUtil.getOrderId(new Date()));
//			bank.setTxnAmt(String.valueOf(txnAmt));
			bank.setTxnAmt(Integer.parseInt(orderInfo.getTxnAmt())*100+"");//把钱转为分
			bank.setFrontUrl("http://154.223.71.4:85/klt/KltKuaijieCallBack/backend");
			bank.setBackUrl("http://www.catchtrader.com/controller/payReturn");
			bank.setBankId(orderInfo.getBankId());
			bank.setDcType((byte)0);
			bank.setSubject("标题");
			bank.setAccNo("6216261000000000018");
			bank.setCustomerNm("账户名");
			bank.setPhoneNo("13552535506");
			bank.setBody("内容");
			bank.setGateway("bank");
			SortedMap<String, Object> parameters = new TreeMap<String, Object>();
			parameters = objectToSortedMap(bank);
			String temp = PayUtil.signMethod(parameters,request.getParameter("key"));//得到方法签名
			parameters.put("subject",  new String(Base64.encodeBase64("标题".getBytes("UTF-8")), "UTF-8")); //商品标题
			parameters.put("body", new String(Base64.encodeBase64("内容".getBytes("UTF-8")), "UTF-8"));//商品描述
			String signnature = PayUtil.signature(parameters);//得到排好序的字符串
			String param =signnature+"&signMethod=MD5"+"&signature="+temp;
			
			System.out.println(request.getParameter("key")+">>>>>>参数 :"+param);
			String res = PayUtil.sendPost(PayConfig.PAYURL,param); 
		 
			PayUtil.writeString(res,response);
			
			
			
//			JSONObject o = JSON.parseObject(res);
//			if("success".equals(o.getString("status"))){
//				
//				request.setAttribute("url", o.getString("data"));
//				request.getRequestDispatcher("../juhe/tozhufu").forward(request, response);
//			}
//			
			
		 
	 }
	 
//	  收银台
	 @RequestMapping("/checkoutCounter")
	 public  String  checkoutCounter(){
		 return "checkout/checkout_counter";
	 }
	 
	 
	 
	 
	
}
