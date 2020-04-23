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

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myd.controller.JuhePayReturnController;
import com.myd.entity.NpayChannels;
import com.myd.entity.NpayOrder;
import com.myd.entity.NpayTf56Bank;
import com.myd.entity.OrdersBank;
import com.myd.entity.OrdersDaifu;
import com.myd.util.PayUtil;

public class ZjxPayUtil {
	
	private static Logger logger = Logger.getLogger(ZjxPayUtil.class);
	
	public static SortedMap<String, Object>  PayConfig(String gateway, NpayOrder order, NpayTf56Bank nBank,NpayChannels channels) {
		
		if("bank".equals(gateway)){
			//网银支付
			SortedMap<String, Object> map = new TreeMap<String, Object>();
			
			
		 	map.put("merchant_id", channels.getChannelMerId());   //商户id
		  	map.put("order_id",  order.getOrderid());   //商户订单号(我们自己生成的id)
			map.put("version", "0.0.1");
			map.put("order_amt", order.getTxnamt().toString());     
			map.put("biz_code", "1020");  
			map.put("bank_code", getBankCode(order.getBankid()));  //银行转换
			map.put("card_type", order.getDctype().toString());
			map.put("return_url", order.getFronturl()); 
			map.put("bg_url", channels.getNotifyurl()); 
			map.put("product_name", order.getSubject());
			map.put("product_des", order.getBody());
			
			
			String sign = createSign(map,channels.getChannelSecretKey());
			map.put("sign", sign);  
			
			return map;
			 
			 
			 
			 
			 
	
		}else if("daifu".equals(gateway)){
			
			
			 	SortedMap<String, Object> map = new TreeMap<String, Object>();
				
				
			 	map.put("merchant_id", channels.getChannelMerId());   //商户id
			  	map.put("order_id",  order.getOrderid());   //商户订单号(我们自己生成的id)
				map.put("version", "0.0.1");
				map.put("order_amt", order.getTxnamt().toString());    
				map.put("bank_name", nBank.getBankName());
				map.put("bank_code", getBankCode(order.getBankid()));  //后台地址地址      自己填写的地址
				map.put("biz_code", "2001");  
				map.put("account_no", order.getAccno());  
				
				JSONObject o = JSON.parseObject(order.getCustomerinfo());
				String name = o.getString("customerNm");
				String phoneNo = o.getString("phoneNo");
				
				map.put("account_name", name);  
				map.put("mobile", phoneNo);
				map.put("type", "0");
				map.put("province", "上海");  
				map.put("city", "上海");  
				map.put("bank_firm_no", "123");  
				map.put("bg_url", channels.getNotifyurl());  
				
				String sign = createSign(map,channels.getChannelSecretKey());
				map.put("sign", sign);  
				
				return map;
			 
		
		}else if("kuaijie".equals(gateway)){
			
			
		 	SortedMap<String, Object> map = new TreeMap<String, Object>();
			
			
		 	map.put("merchant_id", channels.getChannelMerId());   //商户id
		  	map.put("order_id",  order.getOrderid());   //商户订单号(我们自己生成的id)
			map.put("version", "0.0.1");
			map.put("order_amt", order.getTxnamt().toString());    
			map.put("biz_code", "5001"); 
			map.put("return_url", order.getFronturl()); 
			map.put("bg_url", channels.getNotifyurl()); 
			map.put("product_name", "标题");  // order.getSubject()
			map.put("product_des", "商品");	// order.getBody()
			
			
			String sign = createSign(map,channels.getChannelSecretKey());
			map.put("sign", sign);  
			
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
			if (null != v && !"".equals(v)  && !"sign".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + API_KEY);
		
		logger.info("--------签名前数据-----》"+sb);
		String sign = MD5Util.md5(sb.toString());
				
		return sign;
	}
	
	
	
	public static String  getBankCode(String bankCode){
		String str = null;
		//我们的银行编码
		switch (bankCode) {
		
		case "01050000":  //建设银行
			str = "CCB" ;
			break;
		case "01030000":  //农业银行
			str = "ABC" ;
			break;
		case "01020000":  //工商银行
			str = "ICBC" ;
			break;
		case "01040000":  //中国银行
			str = "BOC" ;
			break;
		case "03100000":  //浦发银行
			str = "SPDB" ;
			break;
		case "03030000":  //光大银行
			str = "CEB" ;
			break;
		case "04100000":  //平安银行
			str = "PINGAN" ;
			break;	
			
		case "03090000":  //兴业银行
			str = "CIB" ;
			break;
		case "01000000":  //邮政储蓄银行
			str = "POST" ;
			break;
		case "03020000":  //中信银行
			str = "CITIC" ;
			break;	
			
			
		case "03040000":  //华夏银行
			str = "HXB" ;
			break;	
		case "03080000":  //招商银行
			str = "CMB" ;
			break;	
		case "03060000":  //广发银行
			str = "CGB" ;
			break;	
		case "04031000":  //北京银行
			str = "BCCB" ;
			break;	
		case "04012900":  //上海银行
			str = "SHB" ;
			break;	
		case "03050000":  //民生银行
			str = "CMBC" ;
			break;	
			
		case "03010000":  //交通银行
			str = "BOCO" ;
			break;
//		case "03010000":  //北京农村商业银行
//			str = "BJRCB" ;
//			break;

			
		default:
			str = "YLBILL" ;
			break;
		}
		
		return str;
	}
	
	
	
	
}
