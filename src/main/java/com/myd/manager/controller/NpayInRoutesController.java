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
import com.myd.entity.NpayInRoutes;
import com.myd.entity.NpayMerFeeRates;
import com.myd.entity.NpayTf56Bank;
import com.myd.manager.service.ChannelService;
import com.myd.manager.service.NpayInRoutesService;
import com.myd.service.NpayChannelsService;
import com.myd.util.Msg;

@Controller
@RequestMapping("/routes")
public class NpayInRoutesController {

	@Autowired
	private NpayInRoutesService NpayInRoutesService;
	
	@Autowired
	private ChannelService channelService;
	
	
	@RequestMapping(value = "/routeslist", method = RequestMethod.GET)
	public String routeslist(HttpServletRequest request,Integer page){
		NpayInRoutes npayInRoutes = new NpayInRoutes();
		List<NpayMerFeeRates> list = NpayInRoutesService.selectByRoutes(npayInRoutes,page);
		request.setAttribute("list", list);
		PageInfo<NpayMerFeeRates> pageInfo = new PageInfo<>(list);
		request.setAttribute("pageInfo", pageInfo);
		//页面显示所有银行
		NpayTf56Bank npayTf56Bank = new NpayTf56Bank();
		List<NpayTf56Bank> Banklist = NpayInRoutesService.selectByBank(npayTf56Bank);
		request.setAttribute("Banklist", Banklist);
		
		//查询渠道表
		NpayChannels npayChannels = new NpayChannels();
		List<NpayChannels> npayChannelslist = channelService.selectByPrimary(npayChannels);
		request.setAttribute("npayChannelslist", npayChannelslist);
		
		//查询渠道表网关
		List<NpayChannels> gatewayList = channelService.selectByGateway(npayChannels);
		request.setAttribute("gatewayList", gatewayList);
		
		return "routes_list";
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updateRouter", method = RequestMethod.GET)
	@ResponseBody
	public Msg updateRouter(String merchantId,String channelAbbr,String channelMerId,String routesGateway,String routesCardType){
		Map<String,Object> list  = NpayInRoutesService.selectRoutesByRates(merchantId,channelAbbr,channelMerId,routesGateway,routesCardType);
		return Msg.success().addObject(list);
		
	}
	
	/**
	 * 
	 * 新增页面
	 * @param request
	 * @param channelAbbr
	 * @return
	 */
	@RequestMapping(value = "/routesEdit", method = RequestMethod.GET)
	@ResponseBody
	public Msg routesEdit(HttpServletRequest request,String channelAbbr){
		List<NpayChannels> npayInRoutes = NpayInRoutesService.selectNpayInRoutesById(channelAbbr);
		return Msg.success().addObject(npayInRoutes);
		
	}
}
