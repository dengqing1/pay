package com.myd.merchant.controller;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myd.config.DynamicDataSource;
import com.myd.controller.BaseController;
import com.myd.entity.NpayMerInfo;
import com.myd.entity.NpayMerchantBalance2018;
import com.myd.entity.User;
import com.myd.service.NpayMerInfoService;
import com.myd.service.StatusService;
import com.myd.service.UserService;
import com.myd.util.MailUtil;
import com.myd.util.Md5Util;
import com.myd.util.PhoneUtil;
import com.myd.util.R;

@Controller
public class AdminController extends BaseController{
	@Autowired
	private UserService userService;

	@Autowired
	private NpayMerInfoService npayMerInfoService;
	
    @Autowired
    private StatusService statusService;
	
	@RequestMapping("/merchant")
	public String index(Model moder,HttpServletRequest request){
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		User user = super.getUser(request);
		String merchantid = "";
		if (user != null ) {
			merchantid = user.getMerchantid();
		}
		NpayMerchantBalance2018 banlanceByMerId = statusService.getBanlanceByMerId(merchantid);
		
		
		if (banlanceByMerId != null) {
			DecimalFormat decimalFormat = new DecimalFormat("#0.00");
			//余额
			moder.addAttribute("banlance", decimalFormat.format(Double.parseDouble(banlanceByMerId.getBalance()+"")/100));
			//可提现余额
			moder.addAttribute("balanceAvailable", decimalFormat.format(Double.parseDouble(banlanceByMerId.getBalanceAvailable()+"")/100));
			//在途余额
			moder.addAttribute("freezeBalance", decimalFormat.format(Double.parseDouble(banlanceByMerId.getFreezeBalance()+"")/100));
			//冻结余额
			moder.addAttribute("blockBalance", decimalFormat.format(Double.parseDouble(banlanceByMerId.getBlockBalance()+"")/100));
		}
		
		NpayMerInfo obj = npayMerInfoService.selectById(merchantid);
		
		moder.addAttribute("obj", obj);
		moder.addAttribute("user", user);
		return "merchant/index";
	}
	
	
	
	@RequestMapping("/merchant/login")
	public String login(Model moder){
		return "merchant/login";
	}
	
	@RequestMapping("/login/toLogin")
	@ResponseBody
	public R user(User user,HttpServletRequest request){
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_B);
		/*String emailCode = (String) request.getSession().getAttribute("emailCode");
		String phoneCode = (String) request.getSession().getAttribute("phoneCode");
		if (!StringUtils.isBlank(user.getEmailCode())) {
			if (StringUtils.isBlank(emailCode) ) {
				return R.error("邮箱验证码获取失败！");
			}
			if (!emailCode.equals(user.getEmailCode())) {
				return R.error("邮箱验证码校验失败！");
			}
		}else if(!StringUtils.isBlank(user.getPhoneCode())){
			if (StringUtils.isBlank(phoneCode) ) {
				return R.error("短信验证码获取失败！");
			}
			if (!phoneCode.equals(user.getPhoneCode())) {
				return R.error("短信验证码校验失败！");
			}
		}else {
			return R.error("验证码获取失败！");
		}*/
		
		
		User login = userService.toLogin(user);
		if (login != null) {
			request.getSession().setAttribute("user", login);;
			/*if (!StringUtils.isBlank(emailCode)) {
				request.getSession().removeAttribute("emailCode");
			}
			if (!StringUtils.isBlank(phoneCode)) {
				request.getSession().removeAttribute("phoneCode");
			}*/
			return R.ok();
		}
		return R.error("商户号、账号或者密码错误!");
	}
	
	@RequestMapping("/login/logout")
	@ResponseBody
	public R out(HttpServletRequest request,Model moder){
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("emailCode");
		request.getSession().removeAttribute("phoneCode");
		return R.ok("退出成功!");
	}
	
	
	@RequestMapping("/login/sedEmail")
	@ResponseBody
	public R sedEmail(String email,HttpServletRequest request){
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_B);
		if (StringUtils.isBlank(email)) {
			return R.error("请输入邮箱！");
		}
		
		try {
			String code = String.valueOf((int)((Math.random()*9+1)*1000));
			System.out.println("邮箱验证码为:"+code);
			MailUtil.send_mail(email,"您的验证码为:"+code);
			request.getSession().setAttribute("emailCode", code);
			return R.ok("邮件发送成功！");
		} catch (Exception e) {
			e.printStackTrace();		}
		return R.error("邮件发送失败！");
	}
	
	@RequestMapping("/login/sedPhone")
	@ResponseBody
	public R sedPhone(String email,HttpServletRequest request){
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_B);
		
		if (StringUtils.isBlank(email)) {
			return R.error("请输入邮箱！");
		}
		
		User user = userService.getUserByEmail(email);
		if (user == null || StringUtils.isBlank(user.getPhone())) {
			return R.error("手机号获取失败！");
		}
		try {
			String code = String.valueOf((int)((Math.random()*9+1)*1000));
			System.out.println("手机验证码为:"+code);
			boolean sendPhone = PhoneUtil.sendPhone(user.getPhone(), code);
			if (sendPhone) {
				request.getSession().setAttribute("phoneCode", code);
				return R.ok("短信发送成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();		
		}
		return R.error("短信发送失败！");
	}
	
	
	@RequestMapping("/user")
	public String user(Model moder,HttpServletRequest request){
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		User user = super.getUser(request);
		String merchantid = "";
		if (user != null ) {
			merchantid = user.getMerchantid();
		}
		NpayMerInfo obj = npayMerInfoService.selectById(merchantid);
		moder.addAttribute("obj", obj);
		moder.addAttribute("user", user);
		return "merchant/user";
	}
	
	
	@RequestMapping("/user/updatePassword")
	@ResponseBody
	public R updatePassword(Model moder,User newUser,HttpServletRequest request){
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_B);
		User user = super.getUser(request);
		if (user != null ) {
			
			String emailCode = (String) request.getSession().getAttribute("emailCode");
			if (StringUtils.isBlank(emailCode)) {
				return R.error("邮箱验证码获取失败！");
			}
			
			if (!emailCode.equals(newUser.getEmailCode())) {
				return R.error("邮箱验证码校验失败！");
			}
			
			if (newUser.getOld_password() != null) {
				String oldPassword = Md5Util.encryption(newUser.getOld_password()+"@P#W$R%D^");
				if (user.getPassword().equals(oldPassword)) {
					//TODO 密码相同  开始修改新密码
					String newPassword = Md5Util.encryption(newUser.getNew_password()+"@P#W$R%D^");
					user.setPassword(newPassword);
					userService.upadte(user);
					request.getSession().removeAttribute("emailCode");
					return R.ok();
				}else{
					return R.error("原密码不正确！");
				}
			}
		}
		return  R.error("未登录！");
	} 
}
