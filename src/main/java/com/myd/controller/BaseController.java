package com.myd.controller;

import java.util.Enumeration;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import com.myd.dto.BodyResponse;
import com.myd.entity.User;
import com.myd.util.PayUtil;
@SuppressWarnings("rawtypes")
public abstract class BaseController {

//	private static Logger logger = (Logger) LogFactory.getLog(BaseController.class);
    @SuppressWarnings("finally")
	public BodyResponse checkRequest(Object object) {
    	BodyResponse bodyResponse = new BodyResponse();
    	try {
			SortedMap<String, Object> sortedMap = PayUtil.objectToSortedMap(object);
			
			String signature = PayUtil.signMethod(sortedMap,"682807c82e2716452bd069aaf72d48dc");
			
		} catch (Exception e) {
//			logger.error(">>>>>>签名方法出错",e);
			bodyResponse.setCode("0002");
			bodyResponse.setMsg("签名错误");
			
		}finally {
			
			return bodyResponse;
		}
    }
    
    public static <T> T requestProperty(HttpServletRequest request,Class<T> beanClass) {
		try {
			T bean = beanClass.newInstance();   
			Enumeration en = request.getParameterNames();   
			while (en.hasMoreElements()) {         
				String name = (String) en.nextElement();
				String value = request.getParameter(name);
				BeanUtils.setProperty(bean, name, value);   
			}
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);  
		}
    }
    
    
    
    public static User getUser(HttpServletRequest request) {
		try {
			User user  = (User) request.getSession().getAttribute("user");
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);  
		}
    }
}
