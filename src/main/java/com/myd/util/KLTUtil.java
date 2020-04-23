package com.myd.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;

public class KLTUtil {
	private static Logger logger = Logger.getLogger(KLTUtil.class);
	/**
	 * 传入报文内容，报文头，及秘钥生成32位MD5加密信息
	 * @param obj
	 * @param obj1
	 * @param sercetKey
	 * @return
	 */
	 public static  String  getSign(Object obj,Object obj1,String sercetKey){
	        if (obj == null)
	            return null;
	        SortedMap<String, Object> sortedMap = new TreeMap<String, Object>();
	        BeanInfo beanInfo = null;
			try {
				beanInfo = Introspector.getBeanInfo(obj.getClass());
			} catch (IntrospectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
	        for (PropertyDescriptor property : propertyDescriptors) {
	            String key = property.getName();
	            if (key.compareToIgnoreCase("class") == 0) {
	                continue;
	            }
	            Method getter = property.getReadMethod();
	            Object value = null;
				try {
					value = getter != null ? getter.invoke(obj) : null;
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            //过滤空字符串
	            if(value == null || "".equals(value)){
	            	
	            }else{
	            	 sortedMap.put(key, value);
	            }
	           
	        }
	        
	        
	        
	        BeanInfo beanInfo1 = null;
			try {
				beanInfo1 = Introspector.getBeanInfo(obj1.getClass());
			} catch (IntrospectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        PropertyDescriptor[] propertyDescriptors1 = beanInfo1.getPropertyDescriptors();
	        for (PropertyDescriptor property : propertyDescriptors1) {
	            String key = property.getName();
	            if (key.compareToIgnoreCase("class") == 0) {
	                continue;
	            }
	            Method getter = property.getReadMethod();
	            Object value = null;
				try {
					value = getter != null ? getter.invoke(obj1) : null;
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            //过滤空字符串
	            if(value == null || "".equals(value)){
	            	
	            }else{
	            	 sortedMap.put(key,value);
	            }
	           
	        }
	        String temp = getContent(sortedMap,sercetKey);
	        String sign = getSignWithMD5(temp);
	        return sign;
	    }
	
	
	 
	 public static String getContent(SortedMap<String, Object> parameters,String key) {
	        StringBuffer buffer = new StringBuffer();
	        List<String> sb = new ArrayList<String>();
	        Set<Entry<String, Object>> es = parameters.entrySet();
	        Iterator<Entry<String, Object>> it = es.iterator();
	        while (it.hasNext()) {
	            Entry<String, Object> entry = it.next();
	            String k = (String) entry.getKey();
	            Object v = entry.getValue();
	            buffer.append(k).append("=");
	            buffer.append(v).append("&");
	            sb.add(k + "=" + v);
	        }
	        buffer.append("key="+key);
	        return buffer.toString();
	    }
	    
	 
	 
	 public static String getSignWithMD5(String str){
		
		 MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			md5.update(str.getBytes());
			byte by[]=md5.digest();
			int i;
			StringBuffer sbf = new StringBuffer();
			for (int j = 0; j < by.length; j++) {
				i=by[j];
				if (i<0) {
					i+=256;
				}else if(i<16){
					sbf.append("0");    //因为大于16的有两位，因此小于16需要补位，
				}
				sbf.append(Integer.toHexString(i));
				
			}
			
			return sbf.toString().toUpperCase(); 
	
	 }
	
	 
 	/**
	 *传入json字符串，跟对应的类信息得到一个实体类  
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	 public static <T> T stringToEntity(String jsonStr, Class<T> clazz) {
		 return (T)JSONArray.parseObject(jsonStr, clazz);
	}
	 
	 
	 
	 public static  boolean  getVerfySign(Object obj,String sercetKey,String sign){
	        if (obj == null)
	            return false;
	        SortedMap<String, Object> sortedMap = new TreeMap<String, Object>();
	        BeanInfo beanInfo = null;
			try {
				beanInfo = Introspector.getBeanInfo(obj.getClass());
			} catch (IntrospectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
	        for (PropertyDescriptor property : propertyDescriptors) {
	            String key = property.getName();
	            if (key.compareToIgnoreCase("class") == 0) {
	                continue;
	            }
	            Method getter = property.getReadMethod();
	            Object value = null;
				try {
					value = getter != null ? getter.invoke(obj) : null;
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            //过滤空字符串
	            if(value == null || "".equals(value)||"sign".equals(key) || "null".equals(value)){
	            	
	            }else{
	            	 sortedMap.put(key, value);
	            }
	           
	        }
	        logger.info("验签的map"+sortedMap);
	     
	        String temp = getContent(sortedMap,sercetKey);
	        String tempsign = getSignWithMD5(temp);
	        logger.info("自己生成的签名"+tempsign);
	        return sign.equals(tempsign);
	    }
	
	
}
