package com.myd.manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myd.entity.NpayChannels;
import com.myd.entity.NpayDaifuRoutersHistory;
import com.myd.entity.NpayDaifuRoutes;
import com.myd.entity.NpayInRoutersHistory;
import com.myd.entity.NpayInRoutes;
import com.myd.entity.NpayMerFeeRates;
import com.myd.entity.NpayMerInfo;
import com.myd.manager.service.ChannelService;
import com.myd.manager.service.NpayDaifuRoutersHistoryService;
import com.myd.manager.service.NpayDaifuRoutesService;
import com.myd.manager.service.NpayInRoutesService;
import com.myd.service.NpayMerFeeRatesService;
import com.myd.service.NpayMerInfoService;
import com.myd.util.DateUtil;
import com.myd.util.Msg;
import com.myd.util.UUIDUtils;

@Controller
@RequestMapping("/daifuhistory")
public class NpayDaifuRoutersHistoryController {
	
	@Autowired
	private NpayDaifuRoutersHistoryService npayDaifuRoutersHistoryService;
	
	@Autowired
	private NpayDaifuRoutesService npayDaifuRoutesService;
	
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
		NpayDaifuRoutersHistory npayDaifuRoutersHistory = new NpayDaifuRoutersHistory();
		List<NpayDaifuRoutersHistory> list = npayDaifuRoutersHistoryService.selectByExamplehistory(npayDaifuRoutersHistory);
		request.setAttribute("list", list);
		return "daifu_history_list";
		
	}
	
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deletehistory", method = RequestMethod.POST)
	@ResponseBody
	public Msg deletehistory(Integer id){
		int daifuhistory = npayDaifuRoutersHistoryService.deleteByPrimaryKey(id);
		return Msg.success();
		
	}
	
	/**
	 * 保存
	 * @return
	 */
	@RequestMapping(value = "/saveRoutes", method = RequestMethod.POST)
	@ResponseBody
	public Msg saveRoutes(NpayDaifuRoutes npayDaifuRoutes,NpayMerFeeRates npayMerFeeRates){
		npayDaifuRoutes.setMerchantid(npayDaifuRoutes.getMerchantid());
		npayDaifuRoutes.setChannelabbr(npayDaifuRoutes.getChannelabbr());
		npayDaifuRoutes.setChannelmerid(npayDaifuRoutes.getChannelmerid());
		npayDaifuRoutes.setRoutesGateway(npayMerFeeRates.getGateway());
		npayDaifuRoutes.setRoutesCardType(npayMerFeeRates.getCardType());
		NpayDaifuRoutes npayDaifuR =npayDaifuRoutesService.selectByPrimary(npayDaifuRoutes);
		if(npayDaifuR!=null){
			return Msg.fail();
		}else{
			//代付路由
			npayDaifuRoutes.setBankid("all");
			npayDaifuRoutes.setChannelabbr(npayDaifuRoutes.getChannelabbr());
			npayDaifuRoutes.setChannelmerid(npayDaifuRoutes.getChannelmerid());
			npayDaifuRoutes.setGt(npayDaifuRoutes.getGt());
			npayDaifuRoutes.setLt(npayDaifuRoutes.getLt());
			npayDaifuRoutes.setMerchantid(npayDaifuRoutes.getMerchantid());
			npayDaifuRoutes.setRoutesGateway(npayMerFeeRates.getGateway());
			npayDaifuRoutes.setRoutesCardType(npayMerFeeRates.getCardType());
			npayDaifuRoutesService.insertSelective(npayDaifuRoutes);
			//费率表
			npayMerFeeRates.setMerId(npayDaifuRoutes.getMerchantid());
			npayMerFeeRates.setGateway(npayMerFeeRates.getGateway());
			npayMerFeeRates.setCardType(npayMerFeeRates.getCardType());
			npayMerFeeRates.setFeeType(npayMerFeeRates.getFeeType());
			npayMerFeeRates.setFeeAmount(npayMerFeeRates.getFeeAmount());
			npayMerFeeRates.setMaxFee(0);
			npayMerFeeRates.setMinFee(0);
			npayMerFeeRates.setCrateTime(new Long(System.currentTimeMillis() / 1000).intValue());
			npayMerFeeRates.setUpdateTime(new Long(System.currentTimeMillis() / 1000).intValue());
			npayMerFeeRates.setChannelAbbr(npayDaifuRoutes.getChannelabbr());
			npayMerFeeRates.setChannelMerId(npayDaifuRoutes.getChannelmerid());
			npayMerFeeRatesService.insertSelective(npayMerFeeRates);
		}
		return Msg.success();
	}
	
	
	@RequestMapping(value = "/updateRoutes", method = RequestMethod.POST)
	@ResponseBody
	public Msg updateRoutes(HttpServletRequest request,NpayDaifuRoutes npayDaifuRoutes,NpayMerFeeRates npayMerFeeRates){
//		npayDaifuRoutes.setMerchantid(npayDaifuRoutes.getMerchantid());
//		npayDaifuRoutes.setChannelabbr(npayDaifuRoutes.getChannelabbr());
//		npayDaifuRoutes.setChannelmerid(npayDaifuRoutes.getChannelmerid());
//		npayDaifuRoutes.setRoutesGateway(npayMerFeeRates.getGateway());
//		npayDaifuRoutes.setRoutesCardType(npayMerFeeRates.getCardType());
//		NpayDaifuRoutes npayDaifuR =npayDaifuRoutesService.selectByPrimary(npayDaifuRoutes);
//		if(npayDaifuR!=null){
//			return Msg.fail();
//		}else{
//		
			//路由表
			npayDaifuRoutes.setId(npayDaifuRoutes.getId());
			npayDaifuRoutes.setBankid("all");
			npayDaifuRoutes.setChannelabbr(npayDaifuRoutes.getChannelabbr());
			npayDaifuRoutes.setChannelmerid(npayDaifuRoutes.getChannelmerid());
			npayDaifuRoutes.setGt(npayDaifuRoutes.getGt());
			npayDaifuRoutes.setLt(npayDaifuRoutes.getLt());
			npayDaifuRoutes.setMerchantid(npayDaifuRoutes.getMerchantid());
			npayDaifuRoutes.setRoutesGateway(npayMerFeeRates.getGateway());
			npayDaifuRoutes.setRoutesCardType(npayMerFeeRates.getCardType());
			npayDaifuRoutesService.updateByPrimaryKeySelective(npayDaifuRoutes);
			//费率表
			npayMerFeeRates.setId(Integer.parseInt(request.getParameter("fid")));
			npayMerFeeRates.setMerId(npayDaifuRoutes.getMerchantid());
			npayMerFeeRates.setGateway(npayMerFeeRates.getGateway());
			npayMerFeeRates.setCardType(npayMerFeeRates.getCardType());
			npayMerFeeRates.setFeeType(npayMerFeeRates.getFeeType());
			npayMerFeeRates.setFeeAmount(npayMerFeeRates.getFeeAmount());
			npayMerFeeRates.setMaxFee(0);
			npayMerFeeRates.setMinFee(0);
			npayMerFeeRates.setCrateTime(new Long(System.currentTimeMillis() / 1000).intValue());
			npayMerFeeRates.setUpdateTime(new Long(System.currentTimeMillis() / 1000).intValue());
			npayMerFeeRates.setChannelAbbr(npayDaifuRoutes.getChannelabbr());
			npayMerFeeRates.setChannelMerId(npayDaifuRoutes.getChannelmerid());
			npayMerFeeRatesService.updateByPrimaryKeySelective(npayMerFeeRates);
			return Msg.success();
//		}
	}

}
