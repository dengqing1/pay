package com.myd.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.myd.entity.User;

// 拦截器  

public class LoginHandlerInterceptor implements HandlerInterceptor {

		
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		 response.setCharacterEncoding("utf-8");
         response.setContentType("text/html;charset=UTF-8");
	        User user =(User)request.getSession().getAttribute("user");
	        if(user == null){    //未登录,或者session过期
	            PrintWriter out = response.getWriter();
	            StringBuffer sb = new StringBuffer("<script type='text/javascript' charset='UTF-8'>");
	            sb.append("alert('当前未登录，或者页面已经过期，请重新登录');");
	            sb.append("window.location.href='./merchant/login.html';");
	            sb.append("</script>");
	            out.print(sb.toString());
	            out.close();
	            return false;
	        }
		return true;
	}
}
