package com.myd.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myd.entity.User;
/**
 * 返回数据工具类
 *@author xiaoqiang lu
 *
 *2018/12/21 18:52
 */
public class WriteJsonUtil {
	/**
	 * 返回json数据
	 * @param response
	 * @param obj
	 */
	public static void writeJson(HttpServletResponse response,Object obj){
	  PrintWriter out  = null;
	  response.setContentType("application/json;charset=UTF-8");
      response.setHeader("Pragma", "No-cache");
      response.setHeader("Cache-Control", "no-cache");
      response.setDateHeader("Expires", 0);
        try {
			out = response.getWriter();
			String res = JSON.toJSONString(obj);
	        out.write(res);
	        out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			out.close();
		}
    	
		
	
		
	}
	
	
	

}
