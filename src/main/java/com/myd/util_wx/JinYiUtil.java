package com.myd.util_wx;

import java.math.BigDecimal;
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

public class JinYiUtil {
	
	private static Logger logger = Logger.getLogger(JinYiUtil.class);
	
	public static SortedMap<String, Object>  PayParams(String gateway, NpayOrder order, NpayTf56Bank nBank,NpayChannels channels) {
		
			//网银支付
			SortedMap<String, Object> map = new TreeMap<String, Object>();
			
//			map.put("customer_code", channels.getChannelMerId()); 
			map.put("customer_code", "JHYF54984899"); 
			map.put("detail", "test"); 
			map.put("order_no", order.getOrderid());
			map.put("remark", "test");
			map.put("create_ip", "154.223.71.5");
//			map.put("create_ip", "192.168.1.103");
			map.put("request_time", PayUtil.generateTime());
			map.put("total_fee", new BigDecimal(order.getTxnamt()).divide(new BigDecimal(100)).stripTrailingZeros().toPlainString());     //时间
			
			map.put("pay_code", "wg");  
			map.put("version", "v1");  
			map.put("nonce", PayUtil.generateOrderId());  
			map.put("return_url", order.getFronturl());  
			map.put("notify_url", channels.getNotifyurl());  //后台地址地址      自己填写的地址
			
			String sign = createSign(map,"de1da1f432946db6ef0b8e2e0586af8d");
//			String sign = createSign(map,channels.getChannelSecretKey());
			map.put("sign", sign); 
			
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
			if (null != v && !"".equals(v)  && !"sign".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
//		System.out.println("第一次加密参数>>>>>>"+sb.toString());
		String md5 = md5(sb.toString(),"");
//		System.out.println("第二次加密参数>>>>>>"+md5 + API_KEY);
		String sign = md5(md5 + API_KEY,"");
		
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



