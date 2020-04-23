package com.myd.merchant.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myd.config.DynamicDataSource;
import com.myd.controller.BaseController;
import com.myd.entity.NpayBalanceOplogs;
import com.myd.entity.User;
import com.myd.service.BalanceOplogsService;
import com.myd.util.PageInfo;
import com.myd.util.Query;


/**
 * 账目 --> 账目查询
 */
@Controller
public class BalanceOplogsController extends BaseController{

	@Autowired
	private BalanceOplogsService balanceOplogsService;
	
	@RequestMapping("balanceLogs")
	public String balanceLogs(HttpServletRequest request ,
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
		query.setPages(balanceOplogsService.count(params));
		PageInfo<NpayBalanceOplogs> pageInfo = new PageInfo<>(balanceOplogsService.list(params));		
		model.addAttribute("list", query.getDataTable(pageInfo));
		return "merchant/balanceLogs";
	}
	
	@RequestMapping("balanceLogs/search")
	@ResponseBody
	Map<String, Object> search(HttpServletRequest request ,Query query, @RequestParam Map<String, Object> params)
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
		query.setPages(balanceOplogsService.count(params));
		PageInfo<NpayBalanceOplogs> pageInfo = new PageInfo<>(balanceOplogsService.list(params));
		return query.getDataTable(pageInfo);
	}
	
	
}
