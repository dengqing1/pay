package com.myd.util_wx;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import com.myd.entity.NpayChannels;
import com.myd.entity.NpayOrder;
import com.myd.entity.NpayTf56Bank;
import com.myd.util.PayUtil;

public class APIUtil {
	
	private static Logger logger = Logger.getLogger(APIUtil.class);
	
	public static SortedMap<String, Object>  PayParams(String gateway, NpayOrder order, NpayTf56Bank nBank,NpayChannels channels) {
		
			//网银支付
			SortedMap<String, Object> map = new TreeMap<String, Object>();
			
			map.put("amount",  new BigDecimal(order.getTxnamt()).divide(new BigDecimal(100)).stripTrailingZeros().toPlainString());
			map.put("ordercode", order.getOrderid());
			map.put("goodsId", "Kjpay");  
			map.put("statedate", PayUtil.getTimeDD());  
			map.put("merNo", channels.getChannelMerId());
//			map.put("callbackurl", order.getFronturl());
			map.put("callbackurl", channels.getNotifyurl());
			map.put("callbackMemo", "orderCallbackMemoTest001");
			map.put("notifyurl", channels.getNotifyurl());
			
			
			String sign=sign(map, channels.getChannelSecretKey());
			
			
//			String sign = createSign(map,"49EC5B9691BB48478FEE95F11F999E95");
//			String sign = createSign(map,channels.getChannelSecretKey());
			map.put("sign", sign); 
			
			return map;
			 
			 
			 
			 
			 
	}
	
	
	
	
	
	
	
	
	public static String md5(byte[] b) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(b);
			byte[] hash = md.digest();
			StringBuffer outStrBuf = new StringBuffer(32);
			for (int i = 0; i < hash.length; i++) {
				int v = hash[i] & 0xFF;
				if (v < 16) {
					outStrBuf.append('0');
				}
				outStrBuf.append(Integer.toString(v, 16).toLowerCase());
			}
			return outStrBuf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new String(b);
		}
	}
	
	public static String sign(SortedMap<String, Object> params, String appkey) {
		if (params.containsKey("sign"))// 签名明文组装不包含sign字段
			params.remove("sign");
		params.put("key", appkey);
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (entry.getValue() != null && ((String) entry.getValue()).length() > 0) {
				sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			}
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		String sign = null ;
		try {
			sign = md5(sb.toString().getBytes("UTF-8"));// 记得是md5编码的加签
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		params.remove("key");
		return sign;
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

		sb.append("key="+API_KEY);
		
		System.out.println("--------签名前数据-----》"+sb);
		String sign = MD5Util.md5(sb.toString());
		System.out.println(sign);
		
		return sign;
	}
	
	public static String md5(String text, String key){
        //加密后的字符串
        String encodeStr=DigestUtils.md5Hex(text);
        System.out.println("MD5加密后的字符串为:encodeStr="+encodeStr);
        return encodeStr;
     }
    
    


}



