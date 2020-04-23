package com.myd.manager.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;
import com.myd.entity.NpayDaifuRoutersTemp;
import com.myd.entity.NpayInRoutersTemp;
import com.myd.manager.service.NpayInRoutersTempService;

@Controller
@RequestMapping("/temp")
public class NpayInRoutersTempController {
	
	@Autowired
	private NpayInRoutersTempService npayInRoutersTempService;
	
	
	@RequestMapping(value = "/templist", method = RequestMethod.GET)
	public String templist(HttpServletRequest request,Integer page){
		NpayInRoutersTemp npayInRoutersTemp = new  NpayInRoutersTemp();
		List<Map<String,Object>> list = npayInRoutersTempService.selectByRoutersTemp(npayInRoutersTemp,page);
		request.setAttribute("list", list);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		request.setAttribute("pageInfo", pageInfo);
		return "routerstemp_list";
		
	}

}
