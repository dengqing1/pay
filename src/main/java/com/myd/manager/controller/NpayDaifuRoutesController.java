package com.myd.manager.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.myd.entity.NpayChannels;
import com.myd.entity.NpayDaifuRoutersTemp;
import com.myd.entity.NpayDaifuRoutes;
import com.myd.entity.NpayTf56Bank;
import com.myd.manager.service.ChannelService;
import com.myd.manager.service.NpayDaifuRoutesService;
import com.myd.util.Msg;

@Controller
@RequestMapping("/daifuroutes")
public class NpayDaifuRoutesController {
	
	@Autowired
	private NpayDaifuRoutesService npayDaifuRoutesService;
	@Autowired
	private ChannelService channelService;
	
	@RequestMapping(value = "/routeslist", method = RequestMethod.GET)
	public String routeslist(HttpServletRequest request,Integer page){
		NpayDaifuRoutes npayDaifuRoutes = new NpayDaifuRoutes();
		List<NpayDaifuRoutes> list = npayDaifuRoutesService.selectByRoutes(npayDaifuRoutes,page);
		request.setAttribute("list", list);
		PageInfo<NpayDaifuRoutes> pageInfo = new PageInfo<>(list);
		request.setAttribute("pageInfo", pageInfo);
		//页面显示所有银行
		NpayTf56Bank npayTf56Bank = new NpayTf56Bank();
		List<NpayTf56Bank> Banklist = npayDaifuRoutesService.selectByBank(npayTf56Bank);
		request.setAttribute("Banklist", Banklist);
		
		//查询渠道表
		NpayChannels npayChannels = new NpayChannels();
		List<NpayChannels> npayChannelslist = channelService.selectByPrimary(npayChannels);
		request.setAttribute("npayChannelslist", npayChannelslist);
		return "daifu_routes_list";
	}

	@RequestMapping(value = "/daifuRoutesEdit", method = RequestMethod.GET)
	@ResponseBody
	public Msg daifuRoutesEdit(HttpServletRequest request,String channelAbbr){
		List<NpayChannels> npayDaifuRoutes = npayDaifuRoutesService.selectNpayDaifuRoutesById(channelAbbr);
		return Msg.success().addObject(npayDaifuRoutes);
	}
	
	/*
	@RequestMapping(value = "/updateDaifuRouter", method = RequestMethod.GET)
	@ResponseBody
	public Msg updateRouter(String merchantId,String channelAbbr,String channelMerId){
		Map<String,Object> list  = npayDaifuRoutesService.selectDaifuRoutesByRates(merchantId,channelAbbr,channelMerId);
		return Msg.success().addObject(list);
		
	}
	*/
	
	
	@RequestMapping(value = "/updateDaifuRouter", method = RequestMethod.GET)
	@ResponseBody
	public Msg updateRouter(NpayDaifuRoutes daifuRoutes){
		Map<String,Object> list  = npayDaifuRoutesService.selectDaifuRoutesByRates(daifuRoutes);
		return Msg.success().addObject(list);
		
	}
	
	
	
	
	
}
