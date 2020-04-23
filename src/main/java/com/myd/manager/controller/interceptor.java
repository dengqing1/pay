package com.myd.manager.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.myd.entity.NewUser;
import com.myd.entity.User;

public class interceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		 response.setCharacterEncoding("utf-8");
         response.setContentType("text/html;charset=UTF-8");
            NewUser newUser =(NewUser)request.getSession().getAttribute("newUser");
	        if(newUser == null){    //未登录,或者session过期
	            PrintWriter out = response.getWriter();
	            StringBuffer sb = new StringBuffer("<script type='text/javascript' charset='UTF-8'>");
	            sb.append("alert('当前未登录，或者页面已经过期，请重新登录');");
	            sb.append("window.location.href='login.htm';");
	            sb.append("</script>");
	            out.print(sb.toString());
	            out.close();
	            return false;
	        }
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
