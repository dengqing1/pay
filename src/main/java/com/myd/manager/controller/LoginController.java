package com.myd.manager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myd.entity.NewUser;
import com.myd.manager.service.NewUserService;
import com.myd.util.Md5Util;
import com.myd.util.Msg;

@Controller
@RequestMapping("/manager")
public class LoginController {
	
	
	@Autowired
	public NewUserService newUserService;
	
	
	@RequestMapping(value = "/api")
	public String api(){
		
		return "api/MySite";
		
	}
	@RequestMapping(value = "/apiphp")
	public String apiphp(){
		
		return "api/MySite_php";
		
	}
	
	
	/**
	 * 进入后台登录页面
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String to_login_admin(){
		
		return "login_admin";
		
	}
	
	
	
	/**
	 * 登录方法
	 * @return
	 */
	@RequestMapping(value = "/login_admin", method = RequestMethod.POST)
	@ResponseBody
	public String login_admin(HttpServletRequest request){
		String email = request.getParameter("email");
		String password = Md5Util.encryption(request.getParameter("password")+"@P#W$R%D^");	
		
		NewUser newUser = new NewUser();
		newUser.setEmail(email);
		NewUser nUser =newUserService.selectByPrimary(newUser);
		
		if(nUser==null)
			return "0";  //账户名不存在
		
		
		
		if(nUser.getPassword().equals(password)){
			
			System.out.println("登录成功");
			request.getSession().setAttribute("newUser",nUser);
			
			return "1";
			
		}
		
		
		
		
		return "0";
		
	}
	
	
	
	
	
	/**
	 * 主页显示
	 * @return
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(){
		
		return "admin";
		
	}
	
	
	@RequestMapping(value = "/bank", method = RequestMethod.GET)
	public String bank(){
		
		return "bank_list";
		
	}
	
	
	@RequestMapping(value = "/reconciliation", method = RequestMethod.GET)
	public String reconciliation(){
		
		return "reconciliation";
		
	}

	/**
	 * 冻结资金
	 * @return
	 */
	@RequestMapping(value = "/freeze", method = RequestMethod.GET)
	public String freeze(){
		
		return "freeze_edit";
		
	}
	
	
	/**
	 * 解冻资金
	 * @return
	 */
	@RequestMapping(value = "/thaw", method = RequestMethod.GET)
	public String thaw(){
		
		return "thaw_edit";
		
	}
	
	/**
	 * 资金冻结查询
	 * @return
	 */
	@RequestMapping(value = "/fund", method = RequestMethod.GET)
	public String fund(){
		
		return "fund_list";
		
	}
	
	/**
	 * 资金冻结查询-new
	 * @return
	 */
	@RequestMapping(value = "/fundNew", method = RequestMethod.GET)
	public String fundNew(){
		
		return "fund_list_new";
		
	}
	
	
	@RequestMapping(value = "/exit", method = {RequestMethod.POST,RequestMethod.GET})
	public String exit(HttpServletRequest request){
		//清空session值
		request.getSession().removeAttribute("NewUser");
		return "login_admin";
		
	}
	
	
}
