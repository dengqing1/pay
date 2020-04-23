package com.myd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myd.entity.DaifuAnsyDaifuReturn;
import com.myd.entity.Gather;
import com.myd.entity.KJDuanXinReturn;
import com.myd.entity.KLTKJDuanXinReturn;
import com.myd.entity.MerchantAsynchronousResult;
import com.myd.entity.MsgOrder;
import com.myd.entity.NpayBfInfo;
import com.myd.entity.NpayChannels;
import com.myd.entity.NpayInRoutes;
import com.myd.entity.NpayKJ;
import com.myd.entity.NpayMerInfo;
import com.myd.entity.NpayMerchantBalance2018;
import com.myd.entity.NpayOrder;
import com.myd.entity.NpayTf56Bank;
import com.myd.entity.OrdersBank;
import com.myd.entity.OrdersDaifu;
import com.myd.entity.RspMsg;
import com.myd.entity.juheDaifuReturn;
import com.myd.manager.service.GatherService;
import com.myd.service.JuhePayService;
import com.myd.service.NewOrderSettleService;
import com.myd.service.NpayBfInfoService;
import com.myd.service.NpayChannelsService;
import com.myd.service.NpayInRoutesService;
import com.myd.service.NpayMerInfoService;
import com.myd.service.NpayMerchantBalanceService;
import com.myd.service.NpayOrderService;
import com.myd.service.NpayTf56BankService;
import com.myd.service.PayService;
import com.myd.service.StatusService;
import com.myd.util.DateUtil;
import com.myd.util.EntityIsNullUtil;
import com.myd.util.KLTUtil;
import com.myd.util.OfTime;
import com.myd.util.POIUtil;
import com.myd.util.PayUtil;
import com.myd.util.PhoneUtil;
import com.myd.util.R;
import com.myd.util_wx.APIUtil;
import com.myd.util_wx.JinYiUtil;
import com.myd.util_wx.Pay10088Util;
import com.myd.util_wx.PayYiBaoUtil;
import com.myd.util_wx.PayzlyUtil;
import com.myd.util_wx.QuPayUtil;
import com.myd.util_wx.ThreeUtil;
import com.myd.util_wx.TokenPayUtil;
import com.myd.util_wx.VirtualUtil;
import com.myd.util_wx.Wxpay;
import com.myd.util_wx.YMDUtil;
import com.myd.util_wx.YiBaoUtil;
import com.myd.util_wx.ZjxPayUtil;

/**
 * 聚合下单
 * 
 */
@Controller
public class JuhePayController {
	private static Logger logger = Logger.getLogger(JuhePayController.class);
	@Autowired
	private NpayMerInfoService npayMerInfoService;
	@Autowired
	private PayService payService;
	@Autowired
	private NpayTf56BankService napyTf56BankService;
	@Autowired
	private NpayOrderService npayOrderService;
	@Autowired
	private NpayMerchantBalanceService npayMerchantBalanceService;
	@Autowired
	private NpayBfInfoService npayBfInfoService ;
	

	@Autowired
	private NpayChannelsService npayChannelsService;
	@Autowired
	private JuhePayService juhePayService;
	@Autowired
	private NpayInRoutesService npayInRoutesService;
	@Autowired
	private NewOrderSettleService newOrderSettleService;
	
    @Autowired
    private StatusService statusService;
	@Autowired
	private GatherService gatherService;

	/*
	 * 实际上是post请求（后期改过来）
	 */
	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	public void pay(OrdersBank orderInfo, OrdersDaifu orderInfoDaifu, NpayKJ paykj, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("传来的信息>>" + JSON.toJSONString(orderInfoDaifu));
		RspMsg pesMsg = new RspMsg();
		pesMsg.setTimestamp(DateUtil.getNowTimeStamp());
		String getway = orderInfo.getGateway();// 得到网关是bank还是daifu
		String merId = orderInfo.getMerchantId();// 商户的id

		NpayMerInfo npayMerInfo = npayMerInfoService.getMerInfoById(merId);
		String key = null;
		if (npayMerInfo != null) {
			key = npayMerInfo.getMerSecretKey();
		}

		// 根据网关判断有没有不能为空的字符串
		if (getway == null) {
			pesMsg.setMsg("网关错误");
			jsonString(pesMsg, key, response);
			return;

		} else if ("bank".equals(getway)) {

			List<String> list = EntityIsNullUtil.checkObjFieldIsNull(orderInfo);
			logger.info(">>>bank传来的空的list" + list);
			if (!isRightFiled(list, "bank")) {
				pesMsg.setMsg("必须传的字符串不能为空");
				jsonString(pesMsg, key, response);
				return;

			}

		} else if ("daifu".equals(getway)) {
			List<String> list = EntityIsNullUtil.checkObjFieldIsNull(orderInfoDaifu);
			logger.info(">>>daifu传来的空的list" + list);
			if (!isRightFiled(list, "daifu")) {
				pesMsg.setMsg("必须传的字符串不能为空");
				jsonString(pesMsg, key, response);
				return;

			}

		}else if("kuaijie".equals(getway)){
			String type = paykj.getDcType();
			int dctyp = 0;
			try{
				dctyp = Integer.parseInt(type);
			}catch (Exception e) {
				pesMsg.setMsg("只能为借记卡或借贷卡");
				jsonString(pesMsg, key, response);
				return;
			}
			
			if(dctyp !=0 && dctyp!=1){
				pesMsg.setMsg("只能为借记卡或借贷卡");
				jsonString(pesMsg, key, response);
				return;
				
			}
			List<String> list = EntityIsNullUtil.checkObjFieldIsNull(paykj);
			if(dctyp == 1){
				//信用卡
				if(list.size() > 0 ){
					pesMsg.setMsg("必须传的字符串不能为空");
					jsonString(pesMsg, key, response);
					return;
				}else{
					//判断日期跟安全码
					String date = paykj.getAcctValidDate();
					
					if(date.trim().length() !=4){
						pesMsg.setMsg("信用卡有效日期只能是4位整数");
						jsonString(pesMsg, key, response);
						return;
					}
					try{
						dctyp = Integer.parseInt(date);
					}catch (Exception e) {
						pesMsg.setMsg("信用卡有效日期只能是4位整数");
						jsonString(pesMsg, key, response);
						return;
					}
					
					String anquanma = paykj.getCvv2();
					if(anquanma.trim().length() !=3){
						pesMsg.setMsg("信用卡安全码只能是3位整数");
						jsonString(pesMsg, key, response);
						return;
					}
					try{
						Integer.parseInt(anquanma);
					}catch (Exception e) {
						pesMsg.setMsg("信用卡安全码只能是3位整数");
						jsonString(pesMsg, key, response);
						return;
					}
	
				}
				
			}
			//快捷支付要验证的东西
			//对应的字符串是否为空
			logger.info(">>>kuaijie传来的空的list" + list);
			if(!isRightFiledKuaijie(list,dctyp)){
				pesMsg.setMsg("必须传的字符串不能为空");
				jsonString(pesMsg, key, response);
				return;
			}
			
		} else {

			pesMsg.setMsg("网关错误");
			jsonString(pesMsg, key, response);
			return;
		}

		// 参数验证

		String money = orderInfo.getTxnAmt();
		try {
			Integer.parseInt(money);
		} catch (Exception e) {
			pesMsg.setMsg("金额只能为整数");
			jsonString(pesMsg, key, response);
			return;
		}
		if (money.length() >= 10) {
			pesMsg.setMsg("金额最大为9位整数");
			jsonString(pesMsg, key, response);
			return;
		}

		// 查找订单号存不存在
		NpayOrder orderTemp = npayOrderService.getOrderByMerChantId(orderInfo.getMerOrderId());
		if (orderTemp != null) {
			pesMsg.setMsg("该订单号已存在");
			jsonString(pesMsg, key, response);
			return;
		}
		NpayTf56Bank nBank = null;
		if (!"kuaijie".equals(getway)) {
			nBank = napyTf56BankService.getBankByBankId(orderInfo.getBankId());// 得到银行
			if (nBank == null) {
				// 没有该银行
				pesMsg.setMsg("没有可用银行");
				jsonString(pesMsg, key, response);
				return;
			}
		}

		// 商户存在
		// 有该商户，正常判断

		if ("bank".equals(getway.trim())) {

			if (0 != orderInfo.getDcType()) {
				pesMsg.setMsg("借贷类型必须为0");
				jsonString(pesMsg, key, response);
				return;
			}
			NpayOrder order = new NpayOrder();
			int res = payService.addOrder(order, orderInfo);// 添加订单
			if (res == 200) {


				NpayChannels channels = npayChannelsService.selectByPrimaryKey(order.getChannelId());

				if (channels == null) {
					logger.info("在channels表查不到对应的信息>>>>" + JSON.toJSONString(channels));
				}

				// 按订单表里面的路由(ChannelMerAbbr)判断走哪个渠道
				if ("jr10".equals(order.getChannelMerAbbr()) || "cm".equals(order.getChannelMerAbbr()) || "cmsm".equals(order.getChannelMerAbbr()) ) {

					SortedMap<String, Object> pay = Pay10088Util.Pay10088("bank", order, nBank, channels);

					logger.info("请求信息>>>>" +PayUtil.getParam(pay));
					String str = PayUtil.sendPost(channels.getProxyurl(), PayUtil.getParam(pay));
					
					logger.info("请求返回信息>>>>" + str);
					
					writeurl(str, response);

				}else if ("th".equals(order.getChannelMerAbbr())) {

					SortedMap<String, Object> pay = ThreeUtil.PayThree("bank", order, nBank, channels);

					logger.info("请求信息>>>>" +PayUtil.getParam(pay));
					
					String str = PayUtil.sendPost(channels.getProxyurl(), PayUtil.getParam(pay));
					
					logger.info("请求返回信息>>>>" + str);
					JSONObject o = JSON.parseObject(str);
					if("success".equals(o.getString("status"))){
						str =	PayUtil.sendPost(o.getString("data"),null);
//						str = o.getString("data");
					}
					
					
					
					writeurl(str, response);

				 }else if ("qu".equals(order.getChannelMerAbbr())) {
					SortedMap<String, Object> pay = QuPayUtil.PayParams("bank", order, nBank, channels);

					System.out.println( PayUtil.getParam(pay));
					
					String str = PayUtil.sendPost(channels.getProxyurl(), PayUtil.getParam(pay));
					
					JSONObject o = JSON.parseObject(str);
					if("success".equals(o.getString("msg"))){
						JSONObject json = JSON.parseObject(o.getString("data"));
						System.out.println("返回的二维码>>>>" +o.getString("qr_code"));
						if(o.getString("qr_code").contains("http"))
							PayUtil.sendPost("",o.getString("qr_code"));
					}
					
					writeurl(str, response);


				} else if ("zjx".equals(order.getChannelMerAbbr()) || "qy".equals(order.getChannelMerAbbr()) ) {

					SortedMap<String, Object> pay = ZjxPayUtil.PayConfig("bank", order, nBank, channels);

					logger.info("请求信息>>>>" +PayUtil.getParam(pay));
					
					String str = PayUtil.sendPost(channels.getProxyurl(), PayUtil.getParam(pay));
					
					logger.info("请求返回信息>>>>" + str);
					writeurl(str, response);

				}else if ("token".equals(order.getChannelMerAbbr())) {
					
//					先获取到token
					String token = PayUtil.sendGet("http://106.14.47.193/xpay/tokens/"+channels.getChannelChannelId(), null);
					JSONObject o = JSON.parseObject(token);
					logger.info("请求信息>>>>" +o.getString("token"));
					SortedMap<String, Object> pay = TokenPayUtil.PayParams("bank", order, nBank, channels);
					logger.info("请求信息>>>>" +PayUtil.getParam(pay));
					String str = PayUtil.sendPostToken(channels.getProxyurl()+"?"+PayUtil.getParam(pay),"",o.getString("token"));
					logger.info("请求返回信息>>>>" + str);
					JSONObject json = JSON.parseObject(str);
					if("200".equals(json.getString("status"))){
						
						JSONObject data = JSON.parseObject(json.getString("data"));
						
						str="<a href='"+data.getString("codeUrl")+"'><span id='sp'></span></a><script>document.getElementById('sp').click();</script>" ;
					}
					
					
					writeurl(str, response);


				}else if ("jy".equals(order.getChannelMerAbbr())) {
					SortedMap<String, Object> pay = JinYiUtil.PayParams("bank", order, nBank, channels);

					logger.info("请求信息>>>>" +PayUtil.getParam(pay));
					
					String str = PayUtil.sendPost(channels.getProxyurl(),PayUtil.getParam(pay));
//					JSONObject json = JSON.parseObject(str);
//					if("200".equals(json.getString("code"))){
//						
//						JSONObject data = JSON.parseObject(json.getString("data"));
//						
//						str="<a href='"+data.getString("pay_url")+"'><span id='sp'></span></a><script>document.getElementById('sp').click();</script>" ;
//					}
					logger.info("请求返回信息>>>>" + str);
					writeurl(str, response);


				}else if ("apipay".equals(order.getChannelMerAbbr())) {
					SortedMap<String, Object> pay = APIUtil.PayParams("bank", order, nBank, channels);

					System.out.println( PayUtil.getParam(pay));
					String json = JSON.toJSONString(pay);
//					String str = PayUtil.sendPost(channels.getProxyurl(), PayUtil.getParam(pay));
					String str = PayUtil.sendPost(channels.getProxyurl(), json);
					
					JSONObject o = JSON.parseObject(str);
					if("200".equals(o.getString("result"))){
//						if(o.getString("CodeUrl").contains("http"))
//							PayUtil.sendPost(o.getString("CodeUrl"),"");
						str="<a href='"+o.getString("codeUrl")+"'><span id='sp'></span></a><script>document.getElementById('sp').click();</script>" ;
					}
					
					writeurl(str, response);


				} else if ("test".equals(order.getChannelMerAbbr())) {

					// 测试渠道 1

					test(order, "paid", response);

				} else if ("test_jr".equals(order.getChannelMerAbbr())) {

					// 测试渠道
					test1(order, "00", response);

				} else {

					// 生成订单的时候有错误

					// pesMsg.setMsg("路由配置错误！");
					// jsonString(pesMsg, key, response);
					logger.info("订单表路由错误！");
					return;

				}

				// request.setAttribute("juheOrder", payJuhe);
				// request.getRequestDispatcher("/juhe/juhetest").forward(request,
				// response);
				//
			}else if (res == 1) {

				pesMsg.setMsg("金额不正确！");
				jsonString(pesMsg, key, response);
				return;

			} else if (res == 20 || res == 21) {

				pesMsg.setMsg("没有可用的渠道");
				jsonString(pesMsg, key, response);
				return;

			} else {
				// 生成订单的时候有错误
				logger.info("订单表参数不正确！" + res);
				pesMsg.setMsg("参数不正确");
				jsonString(pesMsg, key, response);
				return;
			}

		} else if ("daifu".equals(getway)) {
			if (!"CNY".equals(orderInfoDaifu.getCurrency())) {

				pesMsg.setMsg("货币类型错误,只支持(CNY)");
				jsonString(pesMsg, key, response);
				return;
			}



//			单笔限额   500 - 50000  元
			if(Integer.parseInt(orderInfoDaifu.getTxnAmt()) < Integer.parseInt("50000")){
				pesMsg.setMsg("代付请求失败,金额最小为  500 元");
				jsonString(pesMsg, key, response);
				return;
			}
			
			
			if(Integer.parseInt(orderInfoDaifu.getTxnAmt()) > Integer.parseInt("5000000")){
				pesMsg.setMsg("代付请求失败,金额最大为 50000 元");
				jsonString(pesMsg, key, response);
				return;
			}
			
			int txnmon = Integer.parseInt(orderInfoDaifu.getTxnAmt()) + 500;
			NpayMerchantBalance2018 balance = npayMerchantBalanceService.getBanlaceById(orderInfoDaifu.getMerchantId());
			logger.info("代付金额为>>>>>>>"+txnmon);
			logger.info("余额为>>>>>>>"+balance.getBalanceAvailable());
			if (balance == null || (balance.getBalanceAvailable() < txnmon)) {
				pesMsg.setMsg("余额不足!");
				jsonString(pesMsg, key, response);
				return;

			}
			
			
			
			
//			添加接口参数到审核表
			Gather gather = new Gather();
			gather.setGatherMerchantId(orderInfoDaifu.getMerchantId());
			gather.setGatherMerOrderId(orderInfoDaifu.getMerOrderId());
			gather.setGatherTxnAmt(orderInfoDaifu.getTxnAmt());
			gather.setGatherAccno(orderInfoDaifu.getAccNo());
			gather.setGatherPpflag(orderInfoDaifu.getPpFlag());
			gather.setGatherCustomerName(orderInfoDaifu.getCustomerNm());
			gather.setGatherBankId(orderInfoDaifu.getBankId());
			gather.setGatherTime(OfTime.getLongTime());//创建时间
			gather.setGatherUpdateTime(OfTime.getLongTime());//审核时间
			gather.setGatherState("1");//状态
			gather.setGatherKey("api");
			gather.setGatherPhoneNo(orderInfoDaifu.getPhoneNo());
			
			gatherService.insert(gather);
			
			
//			给指定手机发送信息
			String code = String.valueOf((int)((Math.random()*9+1)*1000));
//			String code = "有新的DF订单";
			System.out.println("手机验证码为:"+code);
			boolean sendPhone = PhoneUtil.sendPhone("18721167150", code);
			if (sendPhone) {
				request.getSession().setAttribute("phoneCode", code);
			}
			
			pesMsg.setMsg("代付添加成功,待审核！");
			jsonString(pesMsg, key, response);
			return;
			
			

		}else if("kuaijie".equals(getway)){
			
			
			
			//快捷支付
			NpayOrder order = new NpayOrder();
			int res = payService.addOrder(order, paykj);//添加订单
			if(res == 200){//生成订单成功
				
				NpayChannels channels = npayChannelsService.selectByPrimaryKey(order.getChannelId());

				if (channels == null) {
					logger.info("在channels表查不到对应的信息>>>>" + JSON.toJSONString(channels));
				}
				
				if ("zly".equals(order.getChannelMerAbbr())) {

					SortedMap<String, Object> pay = PayzlyUtil.Payzly("kuaijie", order, nBank, channels);

					String str = PayUtil.sendPost(channels.getProxyurl(), PayUtil.getParam(pay));
					
					System.out.println(str);
//					writeurl(str, response);
					PayUtil.writeString(str,response);

				}else if ("zjx".equals(order.getChannelMerAbbr())) {
					
					SortedMap<String, Object> pay = ZjxPayUtil.PayConfig("kuaijie", order, nBank, channels);
					
					String str = PayUtil.sendPost(channels.getProxyurl(), PayUtil.getParam(pay));
					logger.info("快捷返回数据" + str);
//					
//					JSONObject o = JSON.parseObject(str);
//					if("success".equals(o.getString("status"))){
//						// 请求成功 改变订单数据
//						JuhePayReturnController c=new JuhePayReturnController();
//						c.thDaifuReturn(o.getString("transaction_id"));
//					}
//					
					
					
					writeurl(str, response);
					
					
					

				}else if ("ymd".equals(order.getChannelMerAbbr())) {
					
					SortedMap<String, Object> pay = YMDUtil.PayParams("kuaijie", order, nBank, channels);
					logger.info("快捷请求数据" + PayUtil.getParam(pay));
					String str = PayUtil.sendPost(channels.getProxyurl(), PayUtil.getParam(pay));
					logger.info("快捷返回数据" + str);
//					
//					JSONObject o = JSON.parseObject(str);
//					if("success".equals(o.getString("status"))){
//						// 请求成功 改变订单数据
//						JuhePayReturnController c=new JuhePayReturnController();
//						c.thDaifuReturn(o.getString("transaction_id"));
//					}
//					
					PayUtil.writeString(str, response);
					
//					writeurl(str, response);
					
					
					

				}
				
			} else if (res == 20 || res == 21 || res == 1) {

				pesMsg.setMsg("没有可用的渠道");
				jsonString(pesMsg, key, response);
				return;	
				
			}else{
				//生成订单的时候有错误
				logger.info("订单表参数不正确！" + res);
				pesMsg.setMsg("参数不正确");
				jsonString(pesMsg, key, response);
				return;
				
			}
			
			
		}else if("wxpay".equals(getway)){
			
			//微信支付
			NpayOrder order = new NpayOrder();
			int res = payService.addOrder(order, orderInfo);//添加订单
			if(res == 200){//生成订单成功
				
				NpayChannels channels = npayChannelsService.selectByPrimaryKey(order.getChannelId());

				if (channels == null) {
					logger.info("在channels表查不到对应的信息>>>>" + JSON.toJSONString(channels));
				}
				
				if ("wxpay".equals(order.getChannelMerAbbr())) {

					SortedMap<String, Object> pay = Wxpay.WXPay( order, nBank, channels);

					String str = PayUtil.sendPost(channels.getProxyurl(), PayUtil.getParam(pay));
					
					System.out.println(str);
//					writeurl(str, response);
					PayUtil.writeString(str,response);

				}
				
				
				
			}else{
				//生成订单的时候有错误
				logger.info("订单表参数不正确！" + res);
				pesMsg.setMsg("参数不正确");
				jsonString(pesMsg, key, response);
				return;
				
			}
			
			
		} else {
			// 其他支付

		}

	}

	
	
	
	
	
	
	
	

	/*
	 * 代付post
	 */
	@RequestMapping(value = "/paydaifu", method = RequestMethod.POST)
	public void daifupay(OrdersBank orderInfo, OrdersDaifu orderInfoDaifu, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("传来的信息>>" + JSON.toJSONString(orderInfoDaifu));
		RspMsg pesMsg = new RspMsg();
		pesMsg.setTimestamp(DateUtil.getNowTimeStamp());
		String getway = orderInfo.getGateway();// 得到网关是bank还是daifu
		String merId = orderInfo.getMerchantId();// 商户的id

		NpayMerInfo npayMerInfo = npayMerInfoService.getMerInfoById(merId);
		String key = null;
		if (npayMerInfo != null) {
			key = npayMerInfo.getMerSecretKey();
		}

		// 根据网关判断有没有不能为空的字符串
		if (getway == null) {
			pesMsg.setMsg("网关错误");
			jsonString(pesMsg, key, response);
			return;

		} else if ("bank".equals(getway)) {

			List<String> list = EntityIsNullUtil.checkObjFieldIsNull(orderInfo);
			logger.info(">>>bank传来的空的list" + list);
			if (!isRightFiled(list, "bank")) {
				pesMsg.setMsg("必须传的字符串不能为空");
				jsonString(pesMsg, key, response);
				return;

			}

		} else if ("daifu".equals(getway)) {
			List<String> list = EntityIsNullUtil.checkObjFieldIsNull(orderInfoDaifu);
			logger.info(">>>daifu传来的空的list" + list);
			if (!isRightFiled(list, "daifu")) {
				pesMsg.setMsg("必须传的字符串不能为空");
				jsonString(pesMsg, key, response);
				return;

			}

		 
			
		} else {

			pesMsg.setMsg("网关错误");
			jsonString(pesMsg, key, response);
			return;
		}

		// 参数验证

		String money = orderInfo.getTxnAmt();
		try {
			Integer.parseInt(money);
		} catch (Exception e) {
			pesMsg.setMsg("金额只能为整数");
			jsonString(pesMsg, key, response);
			return;
		}
		if (money.length() >= 10) {
			pesMsg.setMsg("金额最大为9位整数");
			jsonString(pesMsg, key, response);
			return;
		}

		// 查找订单号存不存在
		NpayOrder orderTemp = npayOrderService.getOrderByMerChantId(orderInfo.getMerOrderId());
		if (orderTemp != null) {
			pesMsg.setMsg("该订单号已存在");
			jsonString(pesMsg, key, response);
			return;
		}
		NpayTf56Bank nBank = null;
		if (!"kuaijie".equals(getway)) {
			nBank = napyTf56BankService.getBankByBankId(orderInfo.getBankId());// 得到银行
			if (nBank == null) {
				// 没有该银行
				pesMsg.setMsg("没有可用银行");
				jsonString(pesMsg, key, response);
				return;
			}
		}

		// 商户存在
		// 有该商户，正常判断

		 if ("daifu".equals(getway)) {
			if (!"CNY".equals(orderInfoDaifu.getCurrency())) {

				pesMsg.setMsg("货币类型错误,只支持(CNY)");
				jsonString(pesMsg, key, response);
				return;
			}

			int txnmon = Integer.parseInt(orderInfoDaifu.getTxnAmt()) +  500;
			NpayMerchantBalance2018 balance = npayMerchantBalanceService.getBanlaceById(orderInfoDaifu.getMerchantId());
			logger.info("代付金额为>>>>>>>"+txnmon);
			logger.info("余额为>>>>>>>"+balance.getBalanceAvailable());
			if (balance == null || (balance.getBalanceAvailable() < txnmon)) {
				pesMsg.setMsg("余额不足!");
				jsonString(pesMsg, key, response);
				return;

			}

			NpayOrder order = new NpayOrder();
			int res = payService.addOrder(order, orderInfoDaifu);// 添加订单
			if (res == 200) {// 生成订单成功
				NpayChannels channels = npayChannelsService.selectByPrimaryKey(order.getChannelId());

				if ("cm".equals(order.getChannelMerAbbr()) || "cmsm".equals(order.getChannelMerAbbr())) {
	
					
//					SortedMap<String, Object> pay = Pay10088Util.Pay10088("daifu", order, nBank, channels);
//
//					logger.info("请求信息>>>>" +PayUtil.getParam(pay));
//					String str = PayUtil.sendPost(channels.getProxyurl(), PayUtil.getParam(pay));
//					
//					logger.info("请求返回信息>>>>" + str);
//					if(str.contains("success")){
						juheDaifuReturn d=new juheDaifuReturn();
						d.setStatus("success");
						d.setOutBatchNo(order.getOrderid());
						d.setTotalAmount(order.getTxnamt()+"");
						ThreeDaifuTest(d,response);
//					}
//					
//					
//					writeurl(str, response);
					

				}else if ("jy".equals(order.getChannelMerAbbr()) ) {
	
					
						juheDaifuReturn d=new juheDaifuReturn();
						d.setStatus("success");
						d.setOutBatchNo(order.getOrderid());
						d.setTotalAmount(order.getTxnamt()+"");
						ThreeDaifuTest(d,response);
//					
//					writeurl(str, response);
					

				}else if ("zjx".equals(order.getChannelMerAbbr())) {
					
					SortedMap<String, Object> pay = ZjxPayUtil.PayConfig("daifu", order, nBank, channels);
					
					String str = PayUtil.sendPost(channels.getProxyurl(), PayUtil.getParam(pay));
					logger.info("代付返回数据" + str);
					
					JSONObject o = JSON.parseObject(str);
					if("00".equals(o.getString("rsp_code"))){
						
						pesMsg.setSuccess(1);
						pesMsg.setCode("1001");
						pesMsg.setMsg("代付成功");
						jsonString(pesMsg, key, response);
						return;
						
					}else{
						writeurl(str, response);
					}
					
					
					

				}else if ("th".equals(order.getChannelMerAbbr())) {
					
					SortedMap<String, Object> pay = ThreeUtil.PayThree("daifu", order, nBank, channels);
					
					String str = PayUtil.sendPost(channels.getProxyurl(), PayUtil.getParam(pay));
					logger.info("代付返回数据" + str);
//					没有回调   实时响应数据
					JSONObject o = JSON.parseObject(str);
					if("success".equals(o.getString("status"))){
						
						
						juheDaifuReturn d=new juheDaifuReturn();
						d.setStatus(o.getString("status"));
						d.setOutBatchNo(order.getOrderid());
						d.setTotalAmount(order.getTxnamt()+"");
						
						ThreeDaifuTest(d,response);
						
					}else{
						writeurl(str, response);
					}
					
					
					
					

				}else if ("apipay".equals(order.getChannelMerAbbr()) ) {
					
					
					juheDaifuReturn d=new juheDaifuReturn();
					d.setStatus("success");
					d.setOutBatchNo(order.getOrderid());
					d.setTotalAmount(order.getTxnamt()+"");
					ThreeDaifuTest(d,response);
				

				} else if ("test_jr".equals(order.getChannelMerAbbr())) {

					// 测试渠道
					juheDaifuReturn d=new juheDaifuReturn();
					d.setOutBatchNo(order.getOrderid());
					d.setTotalAmount(order.getTxnamt()+"");
					
					ThreeDaifuTest(d,response);
					
					pesMsg.setSuccess(0);
					pesMsg.setCode("1001");
					pesMsg.setMsg("代付成功");

					jsonString(pesMsg, key, response);
					return;
					

				}

			} else if (res == 2 || res == 3 || res == 1) {

				pesMsg.setMsg("没有可用的渠道");
				jsonString(pesMsg, key, response);
				return;
			}else{
				// 生成订单的时候有错误
				logger.info("订单表参数不正确！" + res);
				pesMsg.setMsg("参数不正确");
				jsonString(pesMsg, key, response);
				return;

			}

		}

	}

	
	
	
	
	
	
	
	
	
//	test测试通道回调
	public void test(NpayOrder order, String res, HttpServletResponse response) {

		// 取商户订单号
		String oid = order.getOrderid();
		// 拿商户订单好取订单信息
		NpayOrder order1 = npayOrderService.getOrderByOurOrderId(oid);
		if (order1 == null) {
			// 没有该订单
			return;
		}
		// 获取渠道中的商户秘钥 进行验签
		NpayChannels channels = npayChannelsService.selectByPrimaryKey(order1.getChannelId());

		if (channels == null) {
			logger.info("在channels表查不到对应的信息>>>>" + JSON.toJSONString(channels));
			return;
		}

		// // payResult 处理结果 0：处理中 1：支付成功 2：失败
		// 订单状态：needpay- 待支付；paid- 支付成功；failture- 支付失败；overtime - 订单超时；close -
		// 订单已关闭；back - 已退款。
		// 商户可以通过查询接口查询订单状态。(此字段用于判断业务执行状态)
		// String res = "paid"; // 状态
		String msg = "支付成功";
		if ("needpay".equals(res))
			msg = "处理中";
		else if ("paid".equals(res))
			msg = "支付成功";
		else if ("failture".equals(res))
			msg = "支付失败";
		else if ("overtime".equals(res))
			msg = "订单超时";
		else
			msg = "订单已关闭";

		NpayMerInfo merInfo = npayMerInfoService.getMerInfoById(order1.getMerchantid());
		String key = merInfo.getMerSecretKey();
		NpayOrder orderUpdate = new NpayOrder();// 修改order数据
		Date date = new Date();
		MerchantAsynchronousResult merRes = new MerchantAsynchronousResult();
		// 成功根据返回的订单号去查询信息,并其修改一些信息,并且向商户前后台发信息
		orderUpdate.setOrderid(oid);

		orderUpdate.setStatusdesc(msg);

		orderUpdate.setLastUpdate(DateUtil.getDateFormart(date));
		orderUpdate.setOid(order1.getOid());

		merRes.setMerchantId(order1.getMerchantid());
		merRes.setMerOrderId(order1.getMerorderid());
		merRes.setRespMsg(msg);
		if ("paid".equals(res)) {
			// 成功
			orderUpdate.setStatus(1001);

			orderUpdate.setTxnamt(Integer.parseInt(order1.getTxnamt() + ""));
			merRes.setRespCode("1001");
			merRes.setTxnAmt(String.valueOf(order1.getTxnamt()));

		} else if ("needpay".equals(res)) {// 处理中
			orderUpdate.setStatus(1000);
			// 计算本次交易余额(除去费率) 订单金额-费率金额
			// String l1 = new BigDecimal(order.getTxnamt()).subtract(new
			// BigDecimal(order.getInFee())).stripTrailingZeros().toPlainString();

			orderUpdate.setTxnamt(Integer.parseInt(order1.getTxnamt() + ""));
			merRes.setRespCode("1000");
			merRes.setTxnAmt(String.valueOf(order1.getTxnamt()));
		} else {
			// 其他失败
			orderUpdate.setStatus(1002);// 失败
			merRes.setRespCode("1002");
			merRes.setTxnAmt(String.valueOf(order1.getTxnamt()));

		}
		logger.info("修改的信息：" + orderUpdate);
		// 去修改订单信息，并且告诉商户信息
		npayOrderService.updateOrder(orderUpdate);// 修改订单信息

		if ("paid".equals(res)) {
			String sql = "call new_order_settle(" + oid + ",0,@ret_code)";
			Map<Object, Object> map = newOrderSettleService.callProcedure(sql);
			int staCode = (int) map.get("ret_code");

			String sql1 = "call new_order_settle(" + oid + ",1,@ret_code)";
			Map<Object, Object> map1 = newOrderSettleService.callProcedure(sql1);
			int staCode1 = (int) map1.get("ret_code");

			logger.info(staCode + "订单号：" + oid + "--------->调用函数返回的结果");
			logger.info(staCode1 + "--------->余额计算返回的结果");

		}

		JuhePayReturnController a = new JuhePayReturnController();

//		writeurl("交易成功test", response);
		// 去修改订单信息，并且告诉商户信息
		 String backUrl = order1.getBackurl();
		 a.sendGet(backUrl, merRes, key);// 向商户提供的地址发送异步的get请求

	}
//	test_jr测试通道回调
	public void test1(NpayOrder order, String res, HttpServletResponse response) {

		// 取商户订单号
		String oid = order.getOrderid();
		// 拿商户订单好取订单信息
		NpayOrder order1 = npayOrderService.getOrderByOurOrderId(oid);
		if (order1 == null) {
			// 没有该订单
			return;
		}
		// 获取渠道中的商户秘钥 进行验签
		NpayChannels channels = npayChannelsService.selectByPrimaryKey(order1.getChannelId());

		if (channels == null) {
			logger.info("在channels表查不到对应的信息>>>>" + JSON.toJSONString(channels));
			return;
		}

		// // payResult 处理结果 0：处理中 1：支付成功 2：失败
		// 订单状态：needpay- 待支付；paid- 支付成功；failture- 支付失败；overtime - 订单超时；close -
		// 订单已关闭；back - 已退款。
		// 商户可以通过查询接口查询订单状态。(此字段用于判断业务执行状态)
		// String res = "paid"; // 状态
		String msg = "支付成功";
		if ("needpay".equals(res))
			msg = "处理中";
		else if ("00".equals(res))
			msg = "支付成功";
		else if ("failture".equals(res))
			msg = "支付失败";
		else if ("overtime".equals(res))
			msg = "订单超时";
		else
			msg = "订单已关闭";

		NpayMerInfo merInfo = npayMerInfoService.getMerInfoById(order1.getMerchantid());
		String key = merInfo.getMerSecretKey();
		NpayOrder orderUpdate = new NpayOrder();// 修改order数据
		Date date = new Date();
		MerchantAsynchronousResult merRes = new MerchantAsynchronousResult();
		// 成功根据返回的订单号去查询信息,并其修改一些信息,并且向商户前后台发信息
		orderUpdate.setOrderid(oid);

		orderUpdate.setStatusdesc(msg);

		orderUpdate.setLastUpdate(DateUtil.getDateFormart(date));
		orderUpdate.setOid(order1.getOid());

		merRes.setMerchantId(order1.getMerchantid());
		merRes.setMerOrderId(order1.getMerorderid());
		merRes.setRespMsg(msg);
		if ("00".equals(res)) {
			// 成功
			orderUpdate.setStatus(1001);

			orderUpdate.setTxnamt(Integer.parseInt(order1.getTxnamt() + ""));
			merRes.setRespCode("1001");
			merRes.setTxnAmt(String.valueOf(order1.getTxnamt()));

		} else if ("needpay".equals(res)) {// 处理中
			orderUpdate.setStatus(1000);
			// 计算本次交易余额(除去费率) 订单金额-费率金额
			// String l1 = new BigDecimal(order.getTxnamt()).subtract(new
			// BigDecimal(order.getInFee())).stripTrailingZeros().toPlainString();

			orderUpdate.setTxnamt(Integer.parseInt(order1.getTxnamt() + ""));
			merRes.setRespCode("1000");
			merRes.setTxnAmt(String.valueOf(order1.getTxnamt()));
		} else {
			// 其他失败
			orderUpdate.setStatus(1002);// 失败
			merRes.setRespCode("1002");
			merRes.setTxnAmt(String.valueOf(order1.getTxnamt()));

		}
		logger.info("修改的信息：" + orderUpdate);
		// 去修改订单信息，并且告诉商户信息
		npayOrderService.updateOrder(orderUpdate);// 修改订单信息

		if ("00".equals(res)) {
			String sql = "call new_order_settle(" + oid + ",0,@ret_code)";
			Map<Object, Object> map = newOrderSettleService.callProcedure(sql);
			int staCode = (int) map.get("ret_code");

			String sql1 = "call new_order_settle(" + oid + ",1,@ret_code)";
			Map<Object, Object> map1 = newOrderSettleService.callProcedure(sql1);
			int staCode1 = (int) map1.get("ret_code");

			logger.info(staCode + "订单号：" + oid + "--------->调用函数返回的结果");
			logger.info(staCode1 + "--------->余额计算返回的结果");

		}

		JuhePayReturnController a = new JuhePayReturnController();

//		writeurl("交易成功test", response);
		// 去修改订单信息，并且告诉商户信息
		 String backUrl = order1.getBackurl();
		 a.sendGet(backUrl, merRes, key);// 向商户提供的地址发送异步的get请求

	}

	
	
	
//	测试通道   代付回调   
	public  void ThreeDaifuTest(juheDaifuReturn pay,HttpServletResponse response) {
		logger.info("代付回调" + pay);

		// 取商户订单号
		String oid = pay.getOutBatchNo();
		//	状态
		String res = pay.getStatus();
		// 拿商户订单好取订单信息
		NpayOrder order = npayOrderService.getOrderByOurOrderId(oid);
		if (order == null) {
			// 没有该订单
			return;
		}
	

		// payResult 处理结果 0：处理中 1：支付成功 2：失败
		// 商户可以通过查询接口查询订单状态。(此字段用于判断业务执行状态)
		String msg = "代付成功";
		if ("success".equalsIgnoreCase(res)) {
			msg = "代付成功";

		} else {
			msg = "代付失败";

		}
		NpayMerInfo merInfo = npayMerInfoService.getMerInfoById(order.getMerchantid());
		String key = merInfo.getMerSecretKey();
		NpayOrder orderUpdate = new NpayOrder();// 修改order数据
		Date date = new Date();
		MerchantAsynchronousResult merRes = new MerchantAsynchronousResult();
		// 成功根据返回的订单号去查询信息,并其修改一些信息,并且向商户前后台发信息
		orderUpdate.setOrderid(oid);

		orderUpdate.setStatusdesc(msg);

		orderUpdate.setLastUpdate(DateUtil.getDateFormart(date));
		orderUpdate.setOid(order.getOid());

		merRes.setMerchantId(order.getMerchantid());
		merRes.setMerOrderId(order.getMerorderid());
		merRes.setRespMsg(msg);
		if ("success".equalsIgnoreCase(res)) {
			// 成功
			orderUpdate.setStatus(1001);
			merRes.setRespCode("1001");
			merRes.setTxnAmt(new BigDecimal(pay.getTotalAmount()).multiply(new BigDecimal("100")).stripTrailingZeros()
					.toPlainString());

		} else {
			// 失败
			orderUpdate.setStatus(1002);// 失败
			merRes.setRespCode("1002");

		}
		logger.info("修改的信息：" + orderUpdate);

		if ("success".equalsIgnoreCase(res)) {
			String sql = "call new_order_settle(" + oid + ",0,@ret_code)";
			Map<Object, Object> map = newOrderSettleService.callProcedure(sql);
			int staCode = (int) map.get("ret_code");
			// 去修改订单信息，并且告诉商户信息
			npayOrderService.updateOrder(orderUpdate);// 修改订单信息
			String sql1 = "call new_order_settle(" + oid + ",1,@ret_code)";
			Map<Object, Object> map1 = newOrderSettleService.callProcedure(sql1);
			int staCode1 = (int) map1.get("ret_code");

			logger.info(staCode + "订单号：" + oid + "--------->调用函数返回的结果");
			logger.info(staCode1 + "--------->余额计算返回的结果");

		}

		// 去修改订单信息，并且告诉商户信息
		RspMsg pesMsg = new RspMsg();
		pesMsg.setSuccess(1);
		pesMsg.setCode("1001");
		pesMsg.setMsg("代付成功");
		jsonString(pesMsg, key, response);
		return;
	}

	
	
	
	


	// -----------------------------------------------------------------------------------

	
	
	
	
//	订单查询
	@RequestMapping(value="/order/status", method = RequestMethod.POST)
	@ResponseBody
	public MsgOrder order(String signMethod,String signature,String merchantId,String merOrderId,HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.info("查询请求信息>>>>>>"+signature+"merchantId="+merchantId+">>>>>merOrderId="+merOrderId);
		MsgOrder msg=new MsgOrder();
		NpayOrder order = new NpayOrder();
		NpayMerInfo merInfo = statusService.getMerInfoByMerId(merchantId);
		 
		if(merInfo == null || merInfo.getMerOpenStatus() != 1) {
			
			msg.setSuccess("0");
			msg.setCode("2000");
			msg.setTimestamp(DateUtil.getDateFormart(new Date())+"");
			msg.setMsg("无效商户");
			return msg;
		}
		
		
		order.setMerchantid(merchantId);
		order.setMerorderid(merOrderId);
		NpayOrder npayOrder = npayOrderService.selectByOrderId(order);
		if(npayOrder == null){
			msg.setSuccess("0");
			msg.setCode("2002");
			msg.setTimestamp(DateUtil.getDateFormart(new Date())+"");
			msg.setMsg("没有该订单");
		}else{
			msg.setSuccess("1");
			msg.setCode("1001");
			msg.setTimestamp(DateUtil.getDateFormart(new Date())+"");
			msg.setMsg("");
			
			msg.setMerchantId(order.getMerchantid());
			msg.setMerOrderId(order.getMerorderid());
			msg.setTxnAmt(order.getTxnamt()+"");
			msg.setStatus(order.getStatus()+"");
			msg.setStatusDesc(order.getStatusdesc());
			
			SortedMap<String, Object> map = PayUtil.objectToSortedMap(msg);
			String sign = PayUtil.signMethod(map,merInfo.getMerSecretKey());
			msg.setSignature(sign);
			
			
		}
		
		
		return msg;
		
	}
	
	
	
	
	
	
	/**
	 * 短信发送成功的结果
	 * 
	 * @param resMsg
	 * @param key
	 * @param response
	 */
	public void jsonStringDuanxin(KJDuanXinReturn resMsg, String key, HttpServletResponse response) {

		if (key == null) {

		} else {
			SortedMap<String, Object> map = null;
			try {
				map = PayUtil.objectToSortedMap(resMsg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String str = PayUtil.signMethod(map, key);
			resMsg.setSignature(str);

		}

		PrintWriter out = null;
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		String res = JSON.toJSONString(resMsg);

		try {
			out = response.getWriter();
			out.write(res);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

	public void jsonString(RspMsg resMsg, String key, HttpServletResponse response) {

		if (key == null) {

		} else {
			SortedMap<String, Object> map = null;
			try {
				map = PayUtil.objectToSortedMap(resMsg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String str = PayUtil.signMethod(map, key);
			resMsg.setSignature(str);

		}

		PrintWriter out = null;
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		String res = JSON.toJSONString(resMsg);

		try {
			out = response.getWriter();
			out.write(res);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

	public void writeurl(String str, HttpServletResponse response) {

		PrintWriter out = null;
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		try {
			out = response.getWriter();
			out.write(str);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

	public void returnRes(int res, String key, HttpServletResponse response, RspMsg pesMsg) {

		if (res == 0) {
			// 必传的字符串为空了
			pesMsg.setMsg("必须传的签名信息不能为空");
			jsonString(pesMsg, key, response);

		} else if (res == 1) {
			// url有非法字符
			pesMsg.setMsg("url有非法字符");
			jsonString(pesMsg, key, response);

		} else if (res == 2) {
			// 签名信息失败
			pesMsg.setMsg("签名验证失败");
			jsonString(pesMsg, key, response);

		} else if (res == 3) {

			// 没有该商户
			pesMsg.setMsg("无效商户");
			jsonString(pesMsg, null, response);

		} else {
			// 未知错误
			pesMsg.setMsg("未知错误");
			jsonString(pesMsg, key, response);

		}

	}

	public String getBankurl(NpayOrder order) {
		SortedMap<String, Object> map = new TreeMap<String, Object>();
		map.put("ret_code", "1001");
		map.put("ret_msg", "SUCCESS");
		map.put("sign", "Hiuhsidfhiuushdfihsdiof");
		map.put("merch_id", "PAY10100090000033");
		map.put("out_order_no", order.getOrderid());
		map.put("trade_no", "11111111111");
		map.put("amount", order.getTxnamt());
		return PayUtil.signature(map);

	}

	public String getdaifuurl(NpayOrder order) {
		SortedMap<String, Object> map = new TreeMap<String, Object>();
		map.put("ret_code", "1001");
		map.put("ret_msg", "SUCCESS");
		map.put("sign", "Hiuhsidfhiuushdfihsdiof");
		map.put("merch_id", "PAY10100090000033");
		map.put("out_order_no", order.getOrderid());
		map.put("trade_no", "11111111111");
		map.put("amount", order.getTxnamt());
		map.put("fee", 10000);
		return PayUtil.signature(map);

	}

	/**
	 * 判断是不是指定可以不传的字符串
	 * 
	 * @param list
	 * @return
	 */
	public boolean isRightFiled(List<String> list, String getway) {
		if (list == null) {
			// 没有直接返回true
			return true;
		}
		if ("bank".equals(getway.trim())) {

			if (list.size() >= 0 && list.size() <= 2) {
				list.remove("userId");
				list.remove("customerIp");
				// 如果还有的话就返回false
				if (list.size() == 0) {
					return true;
				}
			}
		} else if ("daifu".equals(getway.trim())) {

			if (list.size() >= 0 && list.size() <= 1) {
				list.remove("purpose");
				// 如果还有的话就返回false
				if (list.size() == 0) {
					return true;
				}
			}

		}

		return false;

	}

	/**
	 * 判断是不是指定可以不传的字符串
	 * 
	 * @param list
	 * @return
	 */
	public boolean isRightFiledKuaijie(List<String> list, int type) {
		if (list == null) {
			// 没有直接返回true
			return true;
		}
		if (0 == type) {
			// 信用卡
			if (list.size() >= 0 && list.size() <= 3) {
				list.remove("cvv2");
				list.remove("acctValidDate");
				list.remove("payerIdNo");
				// 如果还有的话就返回false
				if (list.size() == 0) {
					return true;
				}
			}
		} else {

			if (list.size() == 0) {
				return true;
			}

		}

		return false;

	}

}
