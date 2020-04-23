package com.myd.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
/**
 * 判断有几个空字段的工具类
 *@author xiaoqiang lu
 *
 *2018/12/21 18:56
 */
public class EntityIsNullUtil {
	/**
	 * 返回实体类的空字符串字段(有的话就返回对应的字段名字，没有的话就返回一个长度为0的集合)
	 * @param obj
	 * @return
	 * @throws IllegalAccessException
	 */
	public static  List<String> checkObjFieldIsNull(Object obj){
		 List<String> list = new ArrayList<>();
		 for(Field f : obj.getClass().getDeclaredFields()){
	        f.setAccessible(true);
	        try{
	        	 if((f.get(obj) == null) || ("".equals(f.get(obj))) ||("null").equalsIgnoreCase((String) f.get(obj))){
	        		 
	 	            list.add(f.getName());
	 	        }
	        }catch (Exception e) {
	        	
			}
	       
	    }
	    return list;
	}

}


















