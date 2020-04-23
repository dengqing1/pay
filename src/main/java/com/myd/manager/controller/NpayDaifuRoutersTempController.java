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
import com.myd.manager.service.NpayDaifuRoutersTempService;

@Controller
@RequestMapping("/daifutemp")
public class NpayDaifuRoutersTempController {
	
	@Autowired
	private NpayDaifuRoutersTempService npayDaifuRoutersTempService;

	@RequestMapping(value = "/templist", method = RequestMethod.GET)
	public String templist(HttpServletRequest request,Integer page){
		NpayDaifuRoutersTemp npayDaifuRoutersTemp = new  NpayDaifuRoutersTemp();
		List<NpayDaifuRoutersTemp> list = npayDaifuRoutersTempService.selectByRoutersTemp(npayDaifuRoutersTemp,page);
		request.setAttribute("list", list);
		PageInfo<NpayDaifuRoutersTemp> pageInfo = new PageInfo<>(list);
		request.setAttribute("pageInfo", pageInfo);
		return "daifu_routerstemp_list";
		
	}
}
