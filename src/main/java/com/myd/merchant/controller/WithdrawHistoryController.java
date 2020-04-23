package com.myd.merchant.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myd.config.DynamicDataSource;
import com.myd.controller.BaseController;
import com.myd.entity.NpayOrder;
import com.myd.entity.User;
import com.myd.service.NpayOrderService;
import com.myd.service.UserService;
import com.myd.util.PageInfo;
import com.myd.util.Query;


/**
 * 账务 --> 账务查询
 */
@Controller
public class WithdrawHistoryController extends BaseController{

	@Autowired
	private NpayOrderService npayOrderService;
	
	
	@RequestMapping("withdrawHistory")
	public String order(HttpServletRequest request,
			Query query,Model model,@RequestParam Map<String,Object> params){
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		User user = super.getUser(request);
		String merchantid = "";
		if (user != null ) {
			merchantid = user.getMerchantid();
		}
		params.put("merchantId", merchantid);
		
		params.put("offset", query.getPageNum());
		params.put("limit", query.getPageSize());
		query.setPages(npayOrderService.count(params));
		PageInfo<NpayOrder> pageInfo = new PageInfo<>(npayOrderService.list(params));		
		model.addAttribute("list", query.getDataTable(pageInfo));
		return "merchant/withdrawHistory";
	}
	
	@RequestMapping("withdrawHistory/search")
	@ResponseBody
	Map<String, Object> search(HttpServletRequest request,
			Query query, @RequestParam Map<String, Object> params)
			throws Exception {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		User user = super.getUser(request);
		String merchantid = "";
		if (user != null ) {
			merchantid = user.getMerchantid();
		}
		params.put("merchantId", merchantid);

		params.put("offset", query.getPageNum());
		params.put("limit", query.getPageSize());
		query.setPages(npayOrderService.count(params));
		PageInfo<NpayOrder> pageInfo = new PageInfo<>(npayOrderService.list(params));
		return query.getDataTable(pageInfo);
	}
	
	 @RequestMapping(value = "withdrawHistory/export", method = {RequestMethod.POST,RequestMethod.GET})
	   	public void export(HttpServletResponse response,@RequestParam Map<String, Object> params){	
		 DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
	       	try {
	       		npayOrderService.exportWithdraw(response,params);
	   		} catch (Exception e) {
	   			e.printStackTrace();
	   		} 
	   	}	
}
