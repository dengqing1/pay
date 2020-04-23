package com.myd.util_wx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
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

public class QuPayUtil {

	
	public static SortedMap<String, Object>  PayParams(String gateway, NpayOrder order, NpayTf56Bank nBank,NpayChannels channels) {
		
		String param=null;
		if("bank".equals(gateway)){
			//网银支付
			 
			 SortedMap<String, Object> map = new TreeMap<String, Object>();
				
			
			 	map.put("payType",  "QrCode");   //商户id
			  	map.put("merchantId",  channels.getChannelMerId());   //商户id
				map.put("orderId", order.getOrderid());   //商户订单号(我们自己生成的id)
				map.put("productName", "name");
				map.put("orderAmount", new BigDecimal(order.getTxnamt()).divide(new BigDecimal(100)).stripTrailingZeros().toPlainString());     //时间
				map.put("version", "1.0");
				map.put("signType", "MD5");
				map.put("payMethod", "22");
				map.put("notifyUrl", channels.getNotifyurl());  //后台地址地址      自己填写的地址
				
//				order.getPayeridno()
				// 签名
				String sign = createSign(map,channels.getChannelSecretKey());
				map.put("sign", sign);      
				
				return map;
			 
			 
		}else if("daifu".equals(gateway)){
			//代付
			return  null;
		
		
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
			if (null != v && !"".equals(v)) {
				if(k.equals("merchantId"))
					sb.append(k + "=" + v + "&");
				if(k.equals("orderAmount"))
					sb.append(k + "=" + v + "&");
				if(k.equals("orderId"))
					sb.append(k + "=" + v + "&");
				if(k.equals("payMethod"))
					sb.append(k + "=" + v + "&");
				if(k.equals("payType"))
					sb.append(k + "=" + v + "&");
				if(k.equals("signType"))
					sb.append(k + "=" + v + "&");
				if(k.equals("version"))
					sb.append(k + "=" + v + "&");
				
			}
		}
		sb.append("priKey=" + API_KEY);
		
		System.out.println("--------签名前数据-----》"+sb);
		String sign = MD5Util.md5(sb.toString());
				
		System.out.println(sign);
		return sign;
	}
	
	
	
	
	 
    /**
     * 向指定URL发送POST方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
//            conn.setRequestProperty("contentType", "UTF-8");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setConnectTimeout(60000);
            conn.setReadTimeout(60000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

	
	
	
	
	
	
}
