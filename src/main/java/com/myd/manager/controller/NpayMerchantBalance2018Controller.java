package com.myd.manager.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.visitor.functions.If;
import com.github.pagehelper.PageInfo;
import com.myd.entity.Freeze;
import com.myd.entity.NpayMerchantBalance2018;
import com.myd.manager.service.FreezeService;
import com.myd.manager.service.NpayMerchantBalance2018Service;
import com.myd.util.Msg;
import com.myd.util.OfTime;

@Controller
@RequestMapping("/balance")
public class NpayMerchantBalance2018Controller {
	
	@Autowired
	private NpayMerchantBalance2018Service npayMerchantBalance2018Service;
	
	@Autowired
	private FreezeService freezeService;
	
	
	/**
	 * 冻结资金
	 * @return
	 */
	@RequestMapping(value = "/freeze", method = RequestMethod.GET)
	public String freeze(HttpServletRequest request){
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
	 * 冻结资金列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/freezelist", method = RequestMethod.GET)
	public String freezelist(HttpServletRequest request,Integer page){
		if(page==null){
			page = 0;
		}
		Freeze freeze = new Freeze();
		//NpayMerchantBalance2018 balance = new NpayMerchantBalance2018();
		List<Freeze> list = freezeService.selectByFreeze(freeze,page);
		request.setAttribute("list", list);
		PageInfo<Freeze> pageInfo = new PageInfo<>(list);
		request.setAttribute("pageInfo", pageInfo);
		return "freeze_list";
	}
	
	
	/**
	 * 解冻资金列表
	 * @return
	 */
	@RequestMapping(value = "/thawlist", method = RequestMethod.GET)
	public String thawlist(HttpServletRequest request){
		Freeze freeze = new Freeze();
		//NpayMerchantBalance2018 balance = new NpayMerchantBalance2018();
		//List<Freeze> list = freezeService.selectByBalanceList(freeze);
		//request.setAttribute("list", list);
		return "thaw_list";
	}
	
	@RequestMapping(value = "/selectByContent", method = RequestMethod.POST)
	@ResponseBody
	public Msg selectByContent(String merchantid,HttpServletRequest request){
		NpayMerchantBalance2018 balance = npayMerchantBalance2018Service.selectByPrimaryKey(merchantid);
		request.setAttribute("balance", balance.getMerchantname());
		return Msg.success().addObject(balance);
		
	}
	
	@RequestMapping(value = "/saveFreeze", method = RequestMethod.POST)
	@ResponseBody
	public Msg saveFreeze(String balance,String ydjzj ,String dongjie,String id,String name){
		NpayMerchantBalance2018 balance2018 = new NpayMerchantBalance2018();
		balance2018.setStatus(0);
		balance2018.setBalance(24120L);
		balance2018.setFreezeBalance(5812L);
		balance2018.setMerchantid(id);
		balance2018.setMerchantname(name);
		
		BigDecimal n1 = new BigDecimal(Double.toString(Double.parseDouble(balance)));
		BigDecimal n2 = new BigDecimal(Double.toString(100));
		String l1 = n1.multiply(n2).stripTrailingZeros().toPlainString();
		
		BigDecimal n3 = new BigDecimal(Double.toString(Double.parseDouble(ydjzj)));
		BigDecimal n4 = new BigDecimal(Double.toString(100));
		String l2 = n3.multiply(n4).stripTrailingZeros().toPlainString();
		
		BigDecimal n5 = new BigDecimal(Double.toString(Double.parseDouble(dongjie)));
		BigDecimal n6 = new BigDecimal(Double.toString(100));
		String l3 = n5.multiply(n6).stripTrailingZeros().toPlainString();
		
		balance2018.setBlockBalance(Long.parseLong(l2) + Long.parseLong(l3));
		balance2018.setBalanceAvailable(Long.parseLong(l1) - Long.parseLong(l3));
		int update = npayMerchantBalance2018Service.updateByPrimaryKey(balance2018);
		
		Freeze freeze = new Freeze();
		freeze.setFreezeMerchantId(id);
		freeze.setFreezemerchantName(name);
		freeze.setFreezeBalance(balance);
		freeze.setFreezeBlockBalance(ydjzj);
		freeze.setFreezeAmount(dongjie);
		freeze.setFreezeTime(OfTime.getShortTime());
		freeze.setFreezeState("1");
		freezeService.insert(freeze);
		return Msg.success();
		
	}
	
	@RequestMapping(value = "/saveThaw", method = RequestMethod.POST)
	@ResponseBody
	public Msg saveThaw(String balance,String ydjzj,String jiedong,String id,String name){
		NpayMerchantBalance2018 balance2018 = new NpayMerchantBalance2018();
		balance2018.setStatus(0);
		balance2018.setBalance(24120L);
		balance2018.setFreezeBalance(5812L);
		balance2018.setMerchantid(id);
		balance2018.setMerchantname(name);
		
		BigDecimal n1 = new BigDecimal(Double.toString(Double.parseDouble(balance)));
		BigDecimal n2 = new BigDecimal(Double.toString(100));
		String l1 = n1.multiply(n2).stripTrailingZeros().toPlainString();
		
		BigDecimal n3 = new BigDecimal(Double.toString(Double.parseDouble(ydjzj)));
		BigDecimal n4 = new BigDecimal(Double.toString(100));
		String l2 = n3.multiply(n4).stripTrailingZeros().toPlainString();
		
		BigDecimal n5 = new BigDecimal(Double.toString(Double.parseDouble(jiedong)));
		BigDecimal n6 = new BigDecimal(Double.toString(100));
		String l3 = n5.multiply(n6).stripTrailingZeros().toPlainString();
		
		
		balance2018.setBlockBalance(Long.parseLong(l2) - Long.parseLong(l3));
		balance2018.setBalanceAvailable(Long.parseLong(l1) + Long.parseLong(l3));
		int update = npayMerchantBalance2018Service.updateByPrimaryKey(balance2018);
		
		
		Freeze freeze = new Freeze();
		freeze.setFreezeMerchantId(id);
		freeze.setFreezemerchantName(name);
		freeze.setFreezeBalance(balance);
		freeze.setFreezeBlockBalance(ydjzj);
		freeze.setFreezeAmount(jiedong);
		freeze.setFreezeTime(OfTime.getShortTime());
		freeze.setFreezeState("2");
		freezeService.insert(freeze);
		return Msg.success();
		
	}
	

}
