package com.myd.util_wx;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.beust.jcommander.internal.Maps;
import com.myd.controller.JuhePayReturnController;
import com.myd.entity.NpayChannels;
import com.myd.entity.NpayOrder;
import com.myd.entity.NpayTf56Bank;
import com.myd.entity.OrdersBank;
import com.myd.entity.OrdersDaifu;
import com.myd.util.PayUtil;

public class VirtualUtil {
	
	private static Logger logger = Logger.getLogger(VirtualUtil.class);
	
	public static SortedMap<String, Object>  PayParams(String txnAmt) {
		
			//网银支付
			SortedMap<String, Object> map = new TreeMap<String, Object>();
			
			map.put("pickupUrl", "http://www.baidu.com"); 
			map.put("receiveUrl", "http://154.223.71.4:8081/fengmai/UsdtPayBankNotify"); 
			map.put("signType", "MD5");
			map.put("orderNo", PayUtil.generateOrderId());
			map.put("orderAmount",  txnAmt);
			map.put("orderCurrency", "CNY");
			map.put("customerId", "002");
			
			
			String sign = createSign(map,"hks8wq9f5a6sv2f5q66we8");
			map.put("sign", sign); 
			map.put("crmNo", "10000009");
			
			return map;
			 
			 
			 
			 
			 
	}
	
	
	

	/**
	 * @author
	 * @date 2016-4-22
	 * @Description：sign签名
	 * @param characterEncoding
	 *            编码格式
	 * @param parameters
	 *            请求参数
	 * @return
	 */
	public static String createSign(
			Map<String, Object> packageParams, String API_KEY) {
		StringBuffer sb = new StringBuffer();
		
		
			sb.append( packageParams.get("pickupUrl"));
			sb.append( packageParams.get("receiveUrl"));
			sb.append( packageParams.get("signType"));
			sb.append( packageParams.get("orderNo"));
			sb.append( packageParams.get("orderAmount"));
			sb.append( packageParams.get("orderCurrency"));
			sb.append( packageParams.get("customerId"));
			sb.append(API_KEY);
		
		String sign = 	md5(sb.toString(),"UTF-8");
		return sign;
	}
	
	public static String md5(String text, String key){
        //加密后的字符串
        String encodeStr=DigestUtils.md5Hex(text);
        System.out.println("MD5加密后的字符串为:encodeStr="+encodeStr);
        return encodeStr;
     }
	
	
	
	
	
}
