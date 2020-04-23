package com.myd.controller;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myd.entity.NpayKJ;
import com.myd.entity.NpayKJConfirm;
import com.myd.util.DateUtil;
import com.myd.util.HttpUtils;
import com.myd.util.PayUtil;
/**
 * 自己的测试
 *@author xiaoqiang lu
 *
 *2019/01/21 18:09
 */
@Controller
@RequestMapping("/mechrtklt")
public class MechartKltController {
	private static Logger logger = Logger.getLogger(MechartKltController.class);
	
	@RequestMapping("duanxinTest")
	public String  duanxinTest(Model model){
		model.addAttribute("time", DateUtil.getOrderId(new Date()));
		return "mechartklt/duanxintest";
	}
	
	/**
	 * 短信提交
	 * @param npkj
	 * @param key
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sendduanxin")
	public String sendduanxin(NpayKJ npkj,String key,Model model) throws Exception{
		SortedMap<String, Object> parameters = new TreeMap<String, Object>();
		parameters = objectToSortedMap(npkj);
		String sign = PayUtil.signMethod(parameters,key);// 得到方法签名
		parameters.put("signMethod", "MD5");
		parameters.put("signature",sign );
		model.addAttribute("map", parameters);
		return "mechartklt/duanxinsubmit";
	}
	
	
	
	/**
	 * 确认支付
	 * @return
	 */
	@RequestMapping("/confirm")
	public String confirmDuanxin(){
		
		return "mechartklt/kjconfirm";
		
	}
	
	/**
	 * 确认支付提交
	 * @param npaykj
	 * @param key
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/comfirmSubmit")
	public String confirmSubmin(NpayKJConfirm npaykj,String key,Model model) throws Exception{
		SortedMap<String, Object> parameters = new TreeMap<String, Object>();
		System.out.println("签名集合："+parameters);
		parameters = objectToSortedMap(npaykj);
		String sign = PayUtil.signMethod(parameters,key);// 得到方法签名
		parameters.put("signMethod", "MD5");
		parameters.put("signature",sign );
		model.addAttribute("map", parameters);
		return "mechartklt/kjconfirmsubmit";
		
		
	}
	
	@RequestMapping("/callback")
	public void KltKjDuanxin(HttpServletRequest request,HttpServletResponse response) {
		 SortedMap<String, Object> sortedMap = PayUtil.mapToSortedMap(request);
		logger.info("epay向我异步发来的信息："+sortedMap);
		String remsg = "success";
		write(remsg,response);
		return;
	}
	
	
	
	
	
	
	
	 public  SortedMap<String, Object> objectToSortedMap(Object obj) throws Exception {
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
	            if(value == null || "".equals(value)){
	            	
	            }else{
	            	 sortedMap.put(key, value);
	            }
	           
	        }
	        return sortedMap;
	    }

	 
	 public void write(String msg,HttpServletResponse response){
			PrintWriter out  = null;
			response.setContentType("application/json;charset=UTF-8");
		    response.setHeader("Pragma", "No-cache");
		    response.setHeader("Cache-Control", "no-cache");
		    response.setDateHeader("Expires", 0);
	        try {
				out = response.getWriter();
				out.write(msg);
			    out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				out.close();
			}

		}
	

}
