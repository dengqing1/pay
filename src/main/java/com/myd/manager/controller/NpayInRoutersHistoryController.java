package com.myd.manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myd.entity.NpayChannels;
import com.myd.entity.NpayInRoutersHistory;
import com.myd.entity.NpayInRoutes;
import com.myd.entity.NpayMerFeeRates;
import com.myd.entity.NpayMerInfo;
import com.myd.manager.service.ChannelService;
import com.myd.manager.service.NpayInRoutersHistoryService;
import com.myd.manager.service.NpayInRoutesService;
import com.myd.service.NpayChannelsService;
import com.myd.service.NpayMerFeeRatesService;
import com.myd.service.NpayMerInfoService;
import com.myd.util.DateUtil;
import com.myd.util.Msg;
import com.myd.util.UUIDUtils;

@Controller
@RequestMapping("/history")
public class NpayInRoutersHistoryController {
	
	@Autowired
	private NpayInRoutersHistoryService npayInRoutersHistoryService;
	
	@Autowired
	private NpayInRoutesService npayInRoutesService;
	
	@Autowired
	private NpayMerInfoService npayMerInfoService;
	
	@Autowired
	private ChannelService channelService;
	
	@Autowired
	private NpayMerFeeRatesService npayMerFeeRatesService;
	
	
	
	
	
	/**
	 * 路由历史列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/historylist", method = RequestMethod.GET)
	public String historylist(HttpServletRequest request){
		NpayInRoutersHistory npayInRoutersHistory = new NpayInRoutersHistory();
		List<NpayInRoutersHistory> list = npayInRoutersHistoryService.selectByExamplehistory(npayInRoutersHistory);
		request.setAttribute("list", list);
		return "history_list";
		
	}
	
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deletehistory", method = RequestMethod.POST)
	@ResponseBody
	public Msg deletehistory(Integer id){
		int history = npayInRoutersHistoryService.deleteByPrimaryKey(id);
		return Msg.success();
		
	}
	
	/**
	 * 保存
	 * @return
	 */
	@RequestMapping(value = "/saveRoutes", method = RequestMethod.POST)
	@ResponseBody
	public Msg saveRoutes(NpayInRoutes npayInRoutes,NpayMerFeeRates npayMerFeeRates){
		npayInRoutes.setMerchantid(npayInRoutes.getMerchantid());
		npayInRoutes.setChannelabbr(npayInRoutes.getChannelabbr());
		npayInRoutes.setChannelmerid(npayInRoutes.getChannelmerid());
		npayInRoutes.setRoutesGateway(npayInRoutes.getRoutesGateway());
		npayInRoutes.setRoutesCardType(npayInRoutes.getRoutesCardType());
		NpayInRoutes npayIn = npayInRoutesService.selectByPrimary(npayInRoutes);
		//判断同一个商户下不能有相同渠道
		if(npayIn!=null){
			return Msg.fail();
		}else{
			//路由表
			npayInRoutes.setBankid("all");
			npayInRoutes.setChannelabbr(npayInRoutes.getChannelabbr());
			npayInRoutes.setChannelmerid(npayInRoutes.getChannelmerid());
			npayInRoutes.setGt(npayInRoutes.getGt());
			npayInRoutes.setLt(npayInRoutes.getLt());
			npayInRoutes.setMerchantid(npayInRoutes.getMerchantid());
			npayInRoutes.setRoutesGateway(npayInRoutes.getRoutesGateway());
			npayInRoutes.setRoutesCardType(npayInRoutes.getRoutesCardType());
			npayInRoutesService.insertSelective(npayInRoutes);
			//费率表
			npayMerFeeRates.setMerId(npayInRoutes.getMerchantid());
			npayMerFeeRates.setGateway(npayInRoutes.getRoutesGateway());
			npayMerFeeRates.setCardType(npayInRoutes.getRoutesCardType());
			npayMerFeeRates.setFeeType(npayMerFeeRates.getFeeType());
			npayMerFeeRates.setFeeAmount(npayMerFeeRates.getFeeAmount());
			npayMerFeeRates.setMaxFee(0);
			npayMerFeeRates.setMinFee(0);
			npayMerFeeRates.setCrateTime(new Long(System.currentTimeMillis() / 1000).intValue());
			npayMerFeeRates.setUpdateTime(new Long(System.currentTimeMillis() / 1000).intValue());
			npayMerFeeRates.setChannelAbbr(npayInRoutes.getChannelabbr());
			npayMerFeeRates.setChannelMerId(npayInRoutes.getChannelmerid());
			npayMerFeeRatesService.insertSelective(npayMerFeeRates);
			
		}
		return Msg.success();
		
	}
	
	/**
	 * 修改路由
	 * @param npayInRoutes
	 * @param npayMerFeeRates
	 * @return
	 */
	@RequestMapping(value = "/updateRoutes", method = RequestMethod.POST)
	@ResponseBody
	public Msg updateRoutes(HttpServletRequest request, NpayInRoutes npayInRoutes,NpayMerFeeRates npayMerFeeRates){
//		npayInRoutes.setMerchantid(npayInRoutes.getMerchantid());
//		npayInRoutes.setChannelabbr(npayInRoutes.getChannelabbr());
//		npayInRoutes.setChannelmerid(npayInRoutes.getChannelmerid());
//		npayInRoutes.setRoutesGateway(npayInRoutes.getRoutesGateway());
//		npayInRoutes.setRoutesCardType(npayInRoutes.getRoutesCardType());
//		NpayInRoutes npayIn = npayInRoutesService.selectByPrimary(npayInRoutes);
//		//判断同一个商户下不能有相同渠道
//		if(npayIn!=null){
//			return Msg.fail();
//		}else{
//		
			//路由表
			npayInRoutes.setId(npayInRoutes.getId());
			npayInRoutes.setBankid("all");
			npayInRoutes.setChannelabbr(npayInRoutes.getChannelabbr());
			npayInRoutes.setChannelmerid(npayInRoutes.getChannelmerid());
			npayInRoutes.setGt(npayInRoutes.getGt());
			npayInRoutes.setLt(npayInRoutes.getLt());
			npayInRoutes.setMerchantid(npayInRoutes.getMerchantid());
			npayInRoutes.setAccumulative(npayInRoutes.getAccumulative());
			npayInRoutes.setRoutesGateway(npayInRoutes.getRoutesGateway());
			npayInRoutes.setRoutesCardType(npayInRoutes.getRoutesCardType());
			npayInRoutesService.updateByPrimaryKeySelective(npayInRoutes);
			//费率表
			npayMerFeeRates.setId(Integer.parseInt(request.getParameter("fid")));
			npayMerFeeRates.setMerId(npayInRoutes.getMerchantid());
			npayMerFeeRates.setGateway(npayInRoutes.getRoutesGateway());
			npayMerFeeRates.setCardType(npayInRoutes.getRoutesCardType());
			npayMerFeeRates.setFeeType(npayMerFeeRates.getFeeType());
			npayMerFeeRates.setFeeAmount(npayMerFeeRates.getFeeAmount());
			npayMerFeeRates.setMaxFee(0);
			npayMerFeeRates.setMinFee(0);
			npayMerFeeRates.setCrateTime(new Long(System.currentTimeMillis() / 1000).intValue());
			npayMerFeeRates.setUpdateTime(new Long(System.currentTimeMillis() / 1000).intValue());
			npayMerFeeRates.setChannelAbbr(npayInRoutes.getChannelabbr());
			npayMerFeeRates.setChannelMerId(npayInRoutes.getChannelmerid());
			npayMerFeeRatesService.updateByPrimaryKeySelective(npayMerFeeRates);
			
			return Msg.success();
	}

}
