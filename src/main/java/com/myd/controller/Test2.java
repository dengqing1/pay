package com.myd.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myd.config.YiBaoPayConfig;
import com.myd.entity.MerchantAsynchronousResult;
import com.myd.entity.juheDaifuReturn;
import com.myd.util.KLTUtil;
import com.myd.util.PayUtil;
import com.myd.util_wx.DigestUtil;
import com.myd.util_wx.MD5Util;
import com.myd.util_wx.TokenPayUtil;

public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SortedMap<String, Object> map = new TreeMap<String, Object>();
		map.put("code", "200");
		map.put("customer_code", "JHYF54535554");
		map.put("customer_order_no", "1020938874");
		System.out.println("11");
		System.out.println(PayUtil.sendPost("http://localhost:8080/mavenTest/JinYiPayBankNotify", PayUtil.getParam(map)));
		
		
	}
	
	
	 
	
	/**
	 * 向商户提供的地址发送异步的get请求
	 * 
	 * @param url
	 * @param merRes
	 * @param key
	 */
	public static void send(String url, MerchantAsynchronousResult merRes, String key) {
		SortedMap<String, Object> map = null;
		
		try {
			map = PayUtil.thirdobjectToSortedMap(merRes);// 去除了空的字符串即singnature不参与签名
			String sign = PayUtil.signMethod(map, key);
			//转意 特殊字符+
			sign = URLEncoder.encode(sign, "UTF-8");
			System.out.println(sign);
//			 logger.info("向商户发送信息时候的签名："+sign);
			merRes.setSignature(sign);
//			logger.info(JSON.toJSONString(merRes) + "--------->向商户后台发送的消息");
//			拼接参数是处理中文乱码
			String param = PayUtil.signature2(PayUtil.objectToSortedMap(merRes));
			System.out.println(">>>>"+param);
			System.out.println(PayUtil.sendGet(url, param));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	

	
	
	
	
	

}
