package com.myd.util_wx;

import java.io.UnsupportedEncodingException;
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

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myd.entity.NpayChannels;
import com.myd.entity.NpayOrder;
import com.myd.entity.NpayTf56Bank;
import com.myd.entity.OrdersBank;
import com.myd.entity.OrdersDaifu;
import com.myd.util.PayUtil;

public class TokenPayUtil {

	
	public static SortedMap<String, Object>  PayParams(String gateway, NpayOrder order, NpayTf56Bank nBank,NpayChannels channels) throws UnsupportedEncodingException {
		
		String param=null;
		if("bank".equals(gateway)){
			//网银支付
			 
			 
			 SortedMap<String, Object> map = new TreeMap<String, Object>();
				
			
			  	map.put("storeId",  channels.getChannelMerId());   //商户id
				map.put("payChannel", "3");   //商户订单号(我们自己生成的id)
				map.put("totalFee", new BigDecimal(order.getTxnamt()).divide(new BigDecimal(100)).stripTrailingZeros().toPlainString());     //时间
				map.put("orderTime", PayUtil.getTime());
				map.put("sellerOrderNo", order.getOrderid());
				map.put("ip", "10.202.131.56");
				map.put("notifyUrl", channels.getNotifyurl());  //后台地址地址      自己填写的地址
				map.put("returnUrl", order.getFronturl());  
				
//				order.getPayeridno()
				// 签名
				String sign = createSign(map,channels.getChannelSecretKey());
				map.put("sign", sign);      
				
				return map;
			 
			 
			 
	
		}else if("daifu".equals(gateway)){
			
			
			 	SortedMap<String, Object> map = new TreeMap<String, Object>();
			 	
			 	
				return map;
			 
		
		}
		
		return new TreeMap<String, Object>() ;
		
		
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
			if (null != v && !"".equals(v)  && !"sign".equals(k) ) {
				sb.append(k + "=" + v + "&");
			}
		}
		
		sb.deleteCharAt(sb.length() - 1);
		sb.append(API_KEY);
		
		System.out.println("--------签名前数据-----》"+sb);
		String sign = MD5Util.md5(sb.toString());
				
		System.out.println(sign);
		return sign;
	}
	
	
	
	public static String  getBankCode(String bankCode){
		String str = null;
		//我们的银行编码
		switch (bankCode) {
		
		case "01050000":  //建设银行
			str = "10004" ;
			break;
		case "01030000":  //农业银行
			str = "10002" ;
			break;
		case "01020000":  //工商银行
			str = "10001" ;
			break;
		case "01040000":  //中国银行
			str = "10003" ;
			break;
//		case "03100000":  //浦发银行
//			str = "SPDB" ;
//			break;
		case "03030000":  //光大银行
			str = "10010" ;
			break;
		case "04100000":  //平安银行
			str = "10011" ;
			break;	
			
		case "03090000":  //兴业银行
			str = "10015" ;
			break;
		case "01000000":  //邮政储蓄银行
			str = "10013" ;
			break;
		case "03020000":  //中信银行
			str = "10008" ;
			break;	
			
			
		case "03040000":  //华夏银行
			str = "10014" ;
			break;	
		case "03080000":  //招商银行
			str = "10006" ;
			break;	
		case "03060000":  //广发银行
			str = "10007" ;
			break;	
		case "04031000":  //北京银行
			str = "10016" ;
			break;	
		case "04012900":  //上海银行
			str = "10012" ;
			break;	
		case "03050000":  //民生银行
			str = "10009" ;
			break;	
			
		case "03010000":  //交通银行
			str = "10005" ;
			break;
			
		default:
			str = "10004" ;
			break;
		}
		
		return str;
	}
	
	
	
	
	
	
}
