package com.myd.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.myd.entity.MerchantAsynchronousResult;


public class PayUtil {
	
	private static Logger logger = Logger.getLogger(PayUtil.class);
    SortedMap<String, Object> parameters = new TreeMap<String, Object>();

    //格式转换 按照键名A-Z-a-z（ASCII码从小到大排序）
    //例如：d=dd, c=, aa=aa1aa2, ab=ab1ab2, subject=张大爷的秋裤blue
    //转后为：aa=aa1aa2&ab=ab1ab2&c=&d=dd&subject=张大爷的秋裤blue
    public static String signature(SortedMap<String, Object> parameters) {
        StringBuffer buffer = new StringBuffer();
        List<String> sb = new ArrayList<String>();
        Set<Entry<String, Object>> es = parameters.entrySet();
        Iterator<Entry<String, Object>> it = es.iterator();
        while (it.hasNext()) {
            Entry<String, Object> entry = it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"signMethod".equals(k) ) {
            	buffer.append(k).append("=");
            	buffer.append(v).append("&");
            		
            		sb.add(k + "=" + v);
            }
        }
        buffer.deleteCharAt(buffer.length() - 1);
        return buffer.toString();
    }
    
    
    
    public static String signature2(SortedMap<String, Object> parameters) {
    	StringBuffer buffer = new StringBuffer();
        Set<Entry<String, Object>> es = parameters.entrySet();
        Iterator<Entry<String, Object>> it = es.iterator();
        while (it.hasNext()) {
            Entry<String, Object> entry = it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
//            buffer.append(k).append("=");
//            buffer.append(v).append("&");
            if (null != v && !"signMethod".equals(k)) {
            	if("respMsg".equals(k)){
					try {
						 buffer.append(k).append("=");
				         buffer.append(URLEncoder.encode(v.toString(), "UTF-8")).append("&");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}else {
            		buffer.append(k).append("=");
            		buffer.append(v).append("&");
            	}
            }
        }
        buffer.deleteCharAt(buffer.length() - 1);
        return buffer.toString();
    }
    
    

    public static String signMethodMD5(SortedMap<String, Object> parameters, String key) {

        List<String> sb = new ArrayList<String>();
        Set<Entry<String, Object>> es = parameters.entrySet();
        Iterator<Entry<String, Object>> it = es.iterator();
        while (it.hasNext()) {
            Entry<String, Object> entry = it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"signMethod".equals(k) && !"signature".equals(k)) {
                sb.add(k + "=" + v);
            }
        }
        byte[] digest = null;
        try {
            byte[] data = (StringUtils.join(sb, "&") + key).getBytes("UTF-8");
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(data);
            digest = messageDigest.digest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return digest.toString();
    }

    
    	/**
    	 * 传入map跟秘钥，会生成相应的signnature
    	 * @param parameters
    	 * @param key
    	 * @return
    	 */
    public static String signMethod(SortedMap<String, Object> parameters, String key) {
        StringBuffer buffer = new StringBuffer();
        List<String> sb = new ArrayList<String>();
        Set<Entry<String, Object>> es = parameters.entrySet();
        Iterator<Entry<String, Object>> it = es.iterator();
        while (it.hasNext()) {
            Entry<String, Object> entry = it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"signMethod".equals(k) && !"signature".equals(k)) {
                sb.add(k + "=" + v);
                buffer.append(k).append("=");
                buffer.append(v).append("&");
            }
        }
        byte[] digest = null;
        try {
        	System.out.println(buffer+"buffer");
            logger.info("生成对比明文为：" + buffer.deleteCharAt(buffer.length() - 1).append(key));
            byte[] data = (StringUtils.join(sb, "&") + key).getBytes("UTF-8");
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(data);
            digest = messageDigest.digest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(digest));
    }

    
    
    
    public static SortedMap<String, Object> objectToSortedMap(Object obj) throws Exception {
        if (obj == null)
            return null;

        SortedMap<String, Object> sortedMap = new TreeMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter != null ? getter.invoke(obj) : null;
            sortedMap.put(key, value);
        }
        return sortedMap;
    }
    
    
    
    
    
    public static SortedMap<String, Object> mapToSortedMap(HttpServletRequest request){
    	Map<String, Object> map = request.getParameterMap();
    	 if (map == null)
             return null;
    	 SortedMap<String, Object> sortedMap = new TreeMap<String, Object>();
    	 Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
    	 while (it.hasNext()) {
    		 Entry<String, Object> entry = it.next();
    		 sortedMap.put(entry.getKey(), request.getParameter(entry.getKey()));
    	 }
    	 return sortedMap;
    }

    
    
    /**
     * 支持contentType的请求
     * @param url
     * @param data
     * @return
     * @throws Exception
     */
    public static String post(String url, String data, String contentType) throws Exception {
        org.apache.http.client.methods.CloseableHttpResponse response = null;
        org.apache.http.impl.client.CloseableHttpClient httpclient = null;
        String result = "";
        try {
            org.apache.http.impl.conn.PoolingHttpClientConnectionManager cm = new org.apache.http.impl.conn.PoolingHttpClientConnectionManager();
//            cm.setDefaultMaxPerRoute(MAX_CONN_PERROUTE);
//            cm.setMaxTotal(MAX_CONN_TOTAL);
            httpclient = HttpClients.custom().setConnectionManager(cm).build();
            org.apache.http.client.methods.HttpPost httppost = new org.apache.http.client.methods.HttpPost(url);
            // 设置参数
            org.apache.http.entity.StringEntity reqEntity = new org.apache.http.entity.StringEntity(data);
            reqEntity.setContentType(contentType);

            // 设置超时时间
            org.apache.http.client.config.RequestConfig requestConfig = org.apache.http.client.config.RequestConfig.custom()
                    .setConnectionRequestTimeout(30000)
                    .setConnectTimeout(30000)
                    .setSocketTimeout(30000).build();
            httppost.setConfig(requestConfig);

            httppost.setEntity(reqEntity);
            response = httpclient.execute(httppost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                org.apache.http.HttpEntity responseEntity = response.getEntity();
                result = EntityUtils.toString(responseEntity, "UTF-8");
            } else {
                throw new RuntimeException("Response status:" + statusCode);
            }
        } finally {
            if (response != null)
                response.close();
            if (httpclient != null)
                httpclient.close();
        }
        return result;
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
            HttpURLConnection conn =(HttpURLConnection) realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("contentType", "UTF-8");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("referer", "154.223.71.4:8081");
//            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setConnectTimeout(60000);
            conn.setReadTimeout(60000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)  
	        {
	            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
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

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
        	 String urlNameString = null;
        	if(param!=null)
        		urlNameString = url + "?" + param;
        	else
        		urlNameString = url ;
            System.out.println("回调请求>>>>>"+urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            //connection.setRequestProperty("contentType", "UTF-8");
            connection.setRequestProperty("connection", "Keep-Alive");
            
            connection.setRequestProperty("Accept-Language", "zh-CN");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            
            System.out.println(urlNameString);
            
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
            	
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    
   
    
    
    /**
     * 向指定URL发送POST方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendPostToken(String url, String param,String token) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("X-Amz-Date", "20190408T100512Z");
            conn.setRequestProperty("Authorization", "AWS4-HMAC-SHA256 Credential=/20190408/us-east-1/execute-api/aws4_request, SignedHeaders=access_token;content-type;host;x-amz-date, Signature=a4e2c376443bd8ba03a45414be95fdc3bcf0b9d568199a0d1297f563e8bde96e");
//            conn.setRequestProperty("Accept-Charset", "UTF-8");
//            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Access_token", token);
            conn.setConnectTimeout(60000);
            conn.setReadTimeout(60000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
//            System.out.println(conn.getResponseCode);
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
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
    
    
    
    
    
    
    
    
    
    
    
    
    
	
	/**
	 * 向商户提供的地址发送异步的get请求
	 * 
	 * @param url
	 * @param merRes
	 * @param key
	 */
	public static void sendGet(String url, MerchantAsynchronousResult merRes, String key) {
		SortedMap<String, Object> map = null;
		try {
			map = PayUtil.thirdobjectToSortedMap(merRes);// 去除了空的字符串即singnature不参与签名
			
			String sign = PayUtil.signMethod(map, key);
			//转意 特殊字符+
			sign = URLEncoder.encode(sign, "UTF-8");
			logger.info("向商户发送信息时候的签名："+sign);
			merRes.setSignature(sign);
			String param = PayUtil.signature2(PayUtil.objectToSortedMap(merRes));
			logger.info(param + "--------->向商户后台发送的消息");
			String str = PayUtil.sendGet(url, param);
			if(str != null)
				logger.info("下游返回的信息>>>>>"+str);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
    
    
    
    /**
     * 接三方支付的时候用到
     * @param parameters
     * @return
     */
    public static String thirdSignature(SortedMap<String, Object> parameters) {
        StringBuffer buffer = new StringBuffer();
        List<String> sb = new ArrayList<String>();
        Set<Entry<String, Object>> es = parameters.entrySet();
        Iterator<Entry<String, Object>> it = es.iterator();
        while (it.hasNext()) {
            Entry<String, Object> entry = it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
           
            if (null != v && !"sign".equals(k)  && !"".equals(v)) {
            	 buffer.append(k).append("=");
                 buffer.append(v).append("&");
               
            }else{
            	
            	
            }
        }
        buffer.deleteCharAt(buffer.length() - 1);
        return buffer.toString();
    }
    
    
    /**
     * 三方支付的时候用到，过滤掉不是必须的字段
     * @param obj
     * @return
     * @throws Exception
     */
    public static SortedMap<String, Object> thirdobjectToSortedMap(Object obj) throws Exception {
        if (obj == null)
            return null;

        SortedMap<String, Object> sortedMap = new TreeMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter != null ? getter.invoke(obj) : null;
            if(value != null && !"".equals(value)){
            	sortedMap.put(key, value);
            }
            
        }
       
        return sortedMap;
    }

// 	拼接请求参数
    public static String getParam(SortedMap<String, Object> parameters) {
        StringBuffer buffer = new StringBuffer();
//        List<String> sb = new ArrayList<String>();
        Set<Entry<String, Object>> es = parameters.entrySet();
        Iterator<Entry<String, Object>> it = es.iterator();
        while (it.hasNext()) {
            Entry<String, Object> entry = it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            buffer.append(k).append("=");
            buffer.append(v).append("&");
//            sb.add(k + "=" + v);

        }
        buffer.deleteCharAt(buffer.length() - 1);
        return buffer.toString();
    }
    
    
    
    
	 //获取当前时间     ("yyyy-MM-dd HH:mm:ss")
	 public static String generateTime(){
	        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	    }
	
	 //获取当前时间     ("yyyyMMddHHmmss")
	 public static String getTime(){
	        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	    }  
	 /**
	  * 获取当前时间     ("yyyyMMdd")
	  * @return
	  */
	 public static String getTimeDD(){
	        return new SimpleDateFormat("yyyyMMdd").format(new Date());
	    } 
    
	 //打印到界面
	 public static void writeString(String str,HttpServletResponse response){
			PrintWriter out  = null;
			response.setCharacterEncoding("utf-8");
	  		response.setContentType("text/html;charset=utf-8");
	  		response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
	  		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
	  		response.setHeader("Pragma", "no-cache");
	        try {
				out = response.getWriter();
				out.write(str);
			    out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				out.close();
			}
	    	
			
			
			
			
		}
    
    
	 public static String generateOrderId(){
	        String keyup_prefix=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	        String keyup_append=String.valueOf(new Random().nextInt(899999)+100000);
	        String pay_orderid=keyup_prefix+keyup_append;//订单号
	        return pay_orderid;
	    }
	 
	 
	 
	 
    
}

