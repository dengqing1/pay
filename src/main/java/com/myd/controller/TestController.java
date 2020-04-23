package com.myd.controller;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.myd.entity.OrdersBank;
import com.myd.entity.OrdersDaifu;
import com.myd.util.DateUtil;
import com.myd.util.PayUtil;
import com.myd.util.WriteJsonUtil;

@Controller
public class TestController {
	@RequestMapping("/test")
	public  String testPage(){

		
		
		return "testpay";
		
		
	}
	
	
	@RequestMapping("/testdaifu")
	public  String testDaifuPage(Model model){

		String time = DateUtil.getNowTimeWithyyyyMMddHHmmss();
		model.addAttribute("time", time);
		
		return "testdaifu";
		
		
	}
	
	
	
	

	@RequestMapping("/testBank")
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
		
		return "banksubmit";

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
			return "daifusubmit";
			
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
	 
	 
	
	

}
