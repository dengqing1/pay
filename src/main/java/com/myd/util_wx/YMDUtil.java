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

public class YMDUtil {
	
	private static Logger logger = Logger.getLogger(YMDUtil.class);
	
	public static SortedMap<String, Object>  PayParams(String gateway, NpayOrder order, NpayTf56Bank nBank,NpayChannels channels) {
		
			//网银支付
			SortedMap<String, Object> map = new TreeMap<String, Object>();
			
			map.put("merchantOutOrderNo",  order.getOrderid());
			map.put("merid", "yft2017082500005");
			map.put("noncestr", "12345asdfg");  
			map.put("notifyUrl", channels.getNotifyurl());
			
			map.put("orderMoney", new BigDecimal(order.getTxnamt()).divide(new BigDecimal(100)).stripTrailingZeros().toPlainString());  
			map.put("orderTime", PayUtil.getTime());
//			map.put("returnUrl", order.getFronturl());
			
			
//			String sign=sign(map, "gNociwieX1aCSkhvVemcXkaF9KVmkXm8");
			
			
			String sign = createSign(map,"gNociwieX1aCSkhvVemcXkaF9KVmkXm8");
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

		sb.append("key="+API_KEY);
		
		System.out.println("--------签名前数据-----》"+sb);
		String sign = MD5Util.md5(sb.toString()).toLowerCase();
		System.out.println(sign);
		
		return sign;
	}
	
    
    


}



