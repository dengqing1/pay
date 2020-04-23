package com.myd.util_wx;

import java.io.IOException;
import java.io.PrintWriter;
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

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myd.entity.NpayChannels;
import com.myd.entity.NpayKJ;
import com.myd.entity.NpayOrder;
import com.myd.entity.NpayTf56Bank;
import com.myd.entity.OrdersBank;
import com.myd.entity.OrdersDaifu;
import com.myd.util.PayUtil;

public class Pay10088Util {

	public static SortedMap<String, Object>  Pay10088(String gateway, NpayOrder order, NpayTf56Bank nBank,NpayChannels channels) throws Exception {
	
		if("bank".equals(gateway)){
			//网银支付
			 
			 
			 SortedMap<String, Object> map = new TreeMap<String, Object>();
				
				String  bankcode = "907" ;
				if("10061".equals(channels.getChannelMerId()))
					bankcode = "919" ;
			
			  	map.put("pay_memberid",  channels.getChannelMerId());   //商户id
				map.put("pay_orderid", order.getOrderid());   //商户订单号(我们自己生成的id)
				map.put("pay_applydate", PayUtil.generateTime());     //时间
				map.put("pay_bankcode", bankcode);
				map.put("pay_notifyurl", channels.getNotifyurl());  //后台地址地址      自己填写的地址
				map.put("pay_callbackurl", order.getFronturl());  
				//把分改成元     除以100
				map.put("pay_amount", new BigDecimal(order.getTxnamt()).divide(new BigDecimal(100)).stripTrailingZeros().toPlainString());  //金额
				map.put("pay_productname", order.getSubject());
				map.put("bank_number", "1004");  //建设
				JSONObject o = JSON.parseObject(order.getCustomerinfo());
				String name = o.getString("customerNm");
				map.put("acct_name", name);     //姓名
				
				map.put("id_no", "431121199507282512");   //身份证信息
//				order.getPayeridno()
				// 签名
				String sign = createSign(map,channels.getChannelSecretKey());
				map.put("pay_md5sign", sign);      
				
				return map;
			 
			 
			 
			 
			 
			 
	
		}else if("daifu".equals(gateway)){
			//代付
			 SortedMap<String, Object> map = new TreeMap<String, Object>();
				
			  	
				map.put("bankname", nBank.getBankName());     //时间
				JSONObject o = JSON.parseObject(order.getCustomerinfo());
				String name = o.getString("customerNm");
				map.put("accountname", name);  //后台地址地址      自己填写的地址
				map.put("cardnumber", order.getAccno());  
				map.put("city", "上海"); 
				
				
//				SortedMap<String, Object> data = new TreeMap<String, Object>();
				
				
				map.put("mchid",  channels.getChannelMerId());   //商户id
				map.put("out_trade_no", order.getOrderid());   //商户订单号(我们自己生成的id)
				map.put("money", new BigDecimal(order.getTxnamt()).divide(new BigDecimal(100)).stripTrailingZeros().toPlainString());  //金额
				map.put("subbranch", nBank.getBankName());
				map.put("province", "上海");  
				
				
				
//				JSONObject json =new JSONObject(data);
//				String str=	new String(Base64.encodeBase64(json.toString().getBytes("UTF-8")),"UTF-8");
//				map.put("extends",str);  
//				
				
				
				// 签名
				String sign = createSign(map,channels.getChannelSecretKey());
				map.put("pay_md5sign", sign); 
				
				
				
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
