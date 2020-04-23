package com.myd.util_wx;

import java.io.IOException;
import java.io.PrintWriter;
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

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myd.entity.NpayChannels;
import com.myd.entity.NpayKJ;
import com.myd.entity.NpayOrder;
import com.myd.entity.NpayTf56Bank;
import com.myd.entity.OrdersBank;
import com.myd.entity.OrdersDaifu;
import com.myd.util.PayUtil;

public class PayzlyUtil {

	public static SortedMap<String, Object>  Payzly(String gateway, NpayOrder order, NpayTf56Bank nBank,NpayChannels channels) {
	
		if("bank".equals(gateway)){
			//网银支付
			 
				return null;
			 
	
		}else if("daifu".equals(gateway)){
			//代付
			 
				SortedMap<String, Object> map = new TreeMap<String, Object>();
				
			
				map.put("tenantOrderNo", order.getOrderid());   //商户订单号(我们自己生成的id)
				//把分改成元     除以100
				map.put("amount", new BigDecimal(order.getTxnamt()).divide(new BigDecimal(100)).stripTrailingZeros().toPlainString());  //金额
				JSONObject o = JSON.parseObject(order.getCustomerinfo());
				String name = o.getString("customerNm");
				map.put("name", name);
				map.put("bankNo", order.getAccno());
				map.put("bankName", nBank.getBankName());
//				map.put("notifyUrl", channels.getNotifyurl());  //后台地址地址      自己填写的地址
				// 签名
				String sign = createSign(map,channels.getChannelSecretKey());
				map.put("sign", sign);      
				
				return map;
		
		
		}else if("kuaijie".equals(gateway)){
			 
			 SortedMap<String, Object> map = new TreeMap<String, Object>();
				
			
				map.put("tenantOrderNo", order.getOrderid());   //商户订单号(我们自己生成的id)
				map.put("notifyUrl", channels.getNotifyurl());  //后台地址地址      自己填写的地址
				//把分改成元     除以100
				map.put("amount", new BigDecimal(order.getTxnamt()).divide(new BigDecimal(100)).stripTrailingZeros().toPlainString());  //金额
				map.put("payType", "chianpay");
				
				// 签名
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
			if (null != v && !"".equals(v) && !"bank_number".equals(k) && !"pay_productname".equals(k) && !"pay_md5sign".equals(k)
					&& !"acct_name".equals(k)&& !"id_no".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + API_KEY);
		
		System.out.println("--------签名前数据-----》"+sb);
		String sign = MD5Util.md5(sb.toString());
				
		System.out.println(sign);
		return sign;
	}
	
	
	


	 
	 
	 
	 public static String generateOrderId(){
	        String keyup_prefix=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	        String keyup_append=String.valueOf(new Random().nextInt(899999)+100000);
	        String pay_orderid=keyup_prefix+keyup_append;//订单号
	        return pay_orderid;
	    }
	 
	 
	 
	
	
}
