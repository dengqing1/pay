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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myd.entity.NpayChannels;
import com.myd.entity.NpayOrder;
import com.myd.entity.NpayTf56Bank;
import com.myd.entity.OrdersBank;
import com.myd.entity.OrdersDaifu;
import com.myd.util.PayUtil;

public class Wxpay {

	
	public static SortedMap<String, Object>  WXPay(NpayOrder order, NpayTf56Bank nBank,NpayChannels channels) {
	
			 
			 
			 SortedMap<String, Object> map = new TreeMap<String, Object>();
				
			 map.put("appid",channels.getChannelSecretKey());
			 map.put("mch_id",channels.getChannelMerId());         //商户号
			 map.put("trade_type","NATIVE");                         //支付场景 APP 微信app支付 JSAPI 公众号支付  NATIVE 扫码支付
			 map.put("notify_url",channels.getNotifyurl());                     //回调地址
			 map.put("spbill_create_ip","127.0.0.1");             //终端ip
			 map.put("total_fee",order.getTxnamt());       //订单总金额
			 map.put("fee_type","CNY");                           //默认人民币
			 map.put("out_trade_no",order.getOrderid());   //交易号
			 map.put("body",order.getBody());
			 map.put("nonce_str",PayUtil.generateOrderId());   // 随机字符串小于32位
			 String s = createSign(map,channels.getChannelSecretKey());  //签名
			 map.put("sign",s);
	        
	        
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
			SortedMap<String, Object> packageParams, String API_KEY) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v)  && !"pay_md5sign".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + API_KEY);
		
		System.out.println("--------签名前数据-----》"+sb);
		String sign = MD5Util.md5(sb.toString());
				
		System.out.println(sign);
		return sign;
	}
	
	
	
	
	
	
	
	
}
