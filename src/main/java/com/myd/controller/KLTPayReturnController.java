package com.myd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.myd.entity.DaifuAnsyDaifuReturn;
import com.myd.entity.KLTAnsyPayReturn;
import com.myd.entity.KLTDaifuReturn;
import com.myd.entity.MerchantAsynchronousResult;
import com.myd.entity.NpayMerInfo;
import com.myd.entity.NpayOrder;
import com.myd.service.NewOrderSettleService;
import com.myd.service.NpayMerInfoService;
import com.myd.service.NpayOrderService;
import com.myd.util.DateUtil;
import com.myd.util.KLTUtil;
import com.myd.util.PayUtil;

/**
 * 开联通支付回调
 * 
 * @author xiaoqiang lu
 *
 *         2019/01/22 14:04
 */
@Controller
public class KLTPayReturnController {
	private static Logger logger = Logger.getLogger(KLTPayReturnController.class);
	@Autowired
	private NpayOrderService npayOrderService;
	@Autowired
	private NpayMerInfoService npayMerInfoService;
	@Autowired
	private NewOrderSettleService newOrderSettleService;

	/* 支付 */
	@RequestMapping("/kltPayReturn")
	public void kltpayReturn(KLTAnsyPayReturn pay,HttpServletResponse response) {
		logger.info("支付回调"+pay);
		write("SUCCESS",response);

		// 验签
		boolean flag = KLTUtil.getVerfySign(pay, "742fa3ffd050fb441763bf8fb6c0594f", pay.getSign());
		if(!flag){
			logger.info("支付回调验签不通过"+pay);
			//验签不通过
			return;
		}
		
		
		// 验签通过后取商户订单号
		String oid = pay.getOrderNo();
		// 拿商户订单好取订单信息
		NpayOrder order = npayOrderService.getOrderByOurOrderId(oid);
		if (order == null) {
			// 没有该订单
			return;
		}
		// payResult 处理结果 0：处理中 1：支付成功 2：失败
		// 商户可以通过查询接口查询订单状态。(此字段用于判断业务执行状态)
		int res = pay.getPayResult();// 状态
		String msg = "支付成功";
		if (res == 0) {
			msg = "处理中";
		} else if (res == 1) {
			msg = "支付成功";
		} else if (res == 2) {
			msg = "支付失败";

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
		if (res == 1) {
			// 成功
			orderUpdate.setStatus(1001);
			orderUpdate.setTxnamt(pay.getOrderAmount());
			merRes.setRespCode("1001");
			merRes.setTxnAmt(String.valueOf(pay.getOrderAmount()));

		} else if (res == 0) {
			orderUpdate.setStatus(1000);
			orderUpdate.setTxnamt(pay.getOrderAmount());
			merRes.setRespCode("1000");
			merRes.setTxnAmt(String.valueOf(pay.getOrderAmount()));
		} else if (res == 2) {
			// 失败
			orderUpdate.setStatus(1002);// 失败
			merRes.setRespCode("1002");

		}
		logger.info("修改的信息："+orderUpdate);
		// 去修改订单信息，并且告诉商户信息
		npayOrderService.updateOrder(orderUpdate);// 修改订单信息

		if (res == 1) {
			String sql = "call new_order_settle(" + oid + ",0,@ret_code)";
			Map<Object, Object> map = newOrderSettleService.callProcedure(sql);
			int staCode = (int) map.get("ret_code");

			String sql1 = "call new_order_settle(" + oid + ",1,@ret_code)";
			Map<Object, Object> map1 = newOrderSettleService.callProcedure(sql1);
			int staCode1 = (int) map.get("ret_code");

			logger.info(staCode +"订单号："+oid+ "--------->调用函数返回的结果");
			logger.info(staCode1 + "--------->余额计算返回的结果");

		}
		// 去修改订单信息，并且告诉商户信息
		String backUrl = order.getBackurl();
		sendGet(backUrl, merRes, key);// 向商户提供的地址发送异步的get请求

	}
	
	
	/**
	 * 代付回调
	 */
	@RequestMapping("/kltDaifuReturn")
	public void kltDaifuReturn(DaifuAnsyDaifuReturn pay){
		logger.info("代付回调"+pay);
		// 验签
		boolean flag = KLTUtil.getVerfySign(pay, "742fa3ffd050fb441763bf8fb6c0594f", pay.getSign());
		if(!flag){
			//验签失败
			logger.info("代付回调验签不通过"+pay);
			return ;
		}
		// 验签通过后取商户订单号
		String oid = pay.getMerchantOrderId();
		// 拿商户订单号取订单信息
		NpayOrder order = npayOrderService.getOrderByOurOrderId(oid);
		if (order == null) {
			// 没有该订单
			return;
		}
		int res = pay.getOrderStatus();
		// payResult 处理结果 0：处理中 1：支付成功 2：失败
		// 商户可以通过查询接口查询订单状态。(此字段用于判断业务执行状态)
		String msg = "支付成功";
		if (res == 0) {
			msg = "处理中";
		} else if (res == 1) {
			msg = "支付成功";
		} else if (res == 2) {
			msg = "支付失败";

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
		if (res == 1) {
			// 成功
			orderUpdate.setStatus(1001);
			orderUpdate.setTxnamt(pay.getAmount());
			merRes.setRespCode("1001");
			merRes.setTxnAmt(String.valueOf(pay.getAmount()));

		} else if (res == 0) {
			orderUpdate.setStatus(1000);
			orderUpdate.setTxnamt(pay.getAmount());
			merRes.setRespCode("1000");
			merRes.setTxnAmt(String.valueOf(pay.getAmount()));
		} else if (res == 2) {
			// 失败
			orderUpdate.setStatus(1002);// 失败
			merRes.setRespCode("1002");

		}

		

		if (res == 1) {
			String sql = "call new_order_settle(" + oid + ",0,@ret_code)";
			Map<Object, Object> map = newOrderSettleService.callProcedure(sql);
			int staCode = (int) map.get("ret_code");
			// 去修改订单信息，并且告诉商户信息
			npayOrderService.updateOrder(orderUpdate);// 修改订单信息
			String sql1 = "call new_order_settle(" + oid + ",1,@ret_code)";
			Map<Object, Object> map1 = newOrderSettleService.callProcedure(sql1);
			int staCode1 = (int) map.get("ret_code");

			logger.info(staCode +"订单号："+oid+"--------->调用函数返回的结果");
			logger.info(staCode1 + "--------->余额计算返回的结果");

		}
		// 去修改订单信息，并且告诉商户信息
		String backUrl = order.getBackurl();
		sendGet(backUrl, merRes, key);// 向商户提供的地址发送异步的get请求
		
	}

	/**
	 * 向商户提供的地址发送异步的get请求
	 * 
	 * @param url
	 * @param merRes
	 * @param key
	 */
	public void sendGet(String url, MerchantAsynchronousResult merRes, String key) {
		SortedMap<String, Object> map = null;
		try {
			map = PayUtil.thirdobjectToSortedMap(merRes);// 去除了空的字符串即singnature不参与签名
			String sign = PayUtil.signMethod(map, key);
			 logger.info("向商户发送信息时候的签名："+sign);
			merRes.setSignature(sign);
			logger.info(JSON.toJSONString(merRes) + "--------->向商户后台发送的消息");
			String param = PayUtil.signature(PayUtil.objectToSortedMap(merRes));
			PayUtil.sendGet(url, param);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	public void write(String msg,HttpServletResponse response){
		PrintWriter out  = null;
		response.setContentType("application/json;charset=UTF-8");
	    response.setHeader("Pragma", "No-cache");
	    response.setHeader("Cache-Control", "no-cache");
	    response.setDateHeader("Expires", 0);
        try {
			out = response.getWriter();
			out.write(msg);
		    out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.close();
		}

	}
	
	
	
	
	
	
	
	

}
