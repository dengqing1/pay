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

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		回调下游
		MerchantAsynchronousResult merRes =new MerchantAsynchronousResult();
		merRes.setMerchantId("600000000000007");
		merRes.setMerOrderId("JNG70106220190327114314");
		merRes.setRespCode("1001");
		merRes.setRespMsg("支付成功");
		//merRes.setRespMsg("SUCCESS");
		merRes.setTxnAmt("50000");
		
		
//		http://47.99.165.66/pay/mytest
//		http://47.52.208.15:8080/EAmall/controller/payReturn
//		send("http://yamcgc.cn/vendor/success/return.html", merRes, "42ZhVBQCqdMxgWEZl9atYb1of6gLreda");// 向商户提供的地址发送异步的get请求
//		 double result = new BigDecimal((float)2000 / 10000*2000).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();

		
//		 	BigDecimal n1 = new BigDecimal(Double.toString(3.14));
//			BigDecimal n2 = new BigDecimal(Double.toString(100));
//			String l1 = n1.multiply(n2).stripTrailingZeros().toPlainString();
			
//		 String l1 = new BigDecimal("2000").subtract(new BigDecimal("400")).stripTrailingZeros().toPlainString();
			
			
			
//		System.out.println(l1);
		
		
		
		
		
/*		
//		20190329141121264227
		juheDaifuReturn d=new juheDaifuReturn();
		d.setOutBatchNo("20190329141121264227");
		d.setStatus("success");
		d.setTotalAmount("736000");
		
		JuhePayReturnController pay=new JuhePayReturnController();
		pay.xtDaifuReturn(d);
		
		*/
		
	    // 首先最外层是{}，是创建一个对象  
	    JSONObject person = new JSONObject();  
	    person.put("phone", "2132431");  
	    person.put("name", "yuanzhifei89");  
	    person.put("age", 100);  
	    person.put("ageaaa", "http://106.14.47.193/xpay/h5/pay/xyunShanFu-loading?orderNo=X189046920190410144639899189");  
		
		
	    SortedMap<String, Object> itemMap = JSONObject.toJavaObject(person, SortedMap.class);
//	    SortedMap<String, Object> itemMap=(SortedMap<String, Object>) person;
	    
		System.out.println(itemMap);
		
		itemMap.entrySet();
		
		
		
		
		StringBuffer sb = new StringBuffer();
		Set es = itemMap.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v)  && !"sign".equals(k) ) {
				sb.append(k + "=" + v + "&");
			}
		}
		
		sb.deleteCharAt(sb.length() - 1);
		sb.append("aaaaaaaaaaaaa");
		
		System.out.println("--------签名前数据-----》"+sb);
		String sign = MD5Util.md5(sb.toString());
				
		System.out.println(sign);
		
		
		
//		TokenPayUtil.createSign(itemMap , "aaaaaaaaaaaaaa");
		
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
