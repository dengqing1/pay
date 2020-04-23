package com.myd.controller;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.util.HSSFColor.BLACK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myd.entity.JuheResult;
import com.myd.entity.MerchantAsynchronousResult;
import com.myd.entity.NpayChannels;
import com.myd.entity.NpayMerInfo;
import com.myd.entity.NpayOrder;
import com.myd.entity.Pay10088Result;
import com.myd.entity.juheDaifuReturn;
import com.myd.service.NewOrderSettleService;
import com.myd.service.NpayChannelsService;
import com.myd.service.NpayMerInfoService;
import com.myd.service.NpayOrderService;
import com.myd.util.DateUtil;
import com.myd.util.HttpUtils;
import com.myd.util.KLTUtil;
import com.myd.util.PayUtil;
import com.myd.util_wx.APIUtil;
import com.myd.util_wx.JinYiUtil;
import com.myd.util_wx.MD5Util;
import com.myd.util_wx.ThreeUtil;
import com.myd.util_wx.TokenPayUtil;
import com.myd.util_wx.VirtualUtil;
import com.myd.util_wx.ZjxPayUtil;

/**
 * 
 * 网银回调
 * 
 * @author admin
 *
 */
@Controller
public class JuhePayReturnController {


	private static Logger logger = Logger.getLogger(JuhePayReturnController.class);
	
	@Autowired
	private NpayMerInfoService npayMerInfoService;
	@Autowired
	private NpayOrderService npayOrderService ;

	@Autowired
	private NewOrderSettleService newOrderSettleService;
	@Autowired
	private NpayChannelsService npayChannelsService ;
	
	
	/**
	 *  信通网银回调
	 *   
	 * 
	 * 
	 */
	@RequestMapping("/juheBanks")
	public void  juheBankNotify(JuheResult pay,HttpServletResponse response){
		logger.info("聚合支付回调"+pay);
		
		
		// 取商户订单号
		String oid = pay.getOutTradeNo();
		// 拿商户订单好取订单信息
		NpayOrder order = npayOrderService.getOrderByOurOrderId(oid);
		if (order == null) {
			// 没有该订单
			return;
		}
//		获取渠道中的商户秘钥   进行验签
		NpayChannels channels = npayChannelsService.selectByPrimaryKey(order.getChannelId());
		
		if(channels==null){
			logger.info("在channels表查不到对应的信息>>>>"+JSON.toJSONString(channels));
			return ;
		}
		
		// 验签
		boolean flag = getVerfySign(pay , channels.getChannelSecretKey(),pay.getSign());
		if(!flag){
			logger.info("支付回调验签不通过"+pay);
			//验签不通过
			return;
		}
		// 验签通过后    返回信息给第三方
		write("SUCCESS",response);
		
		
//		// payResult 处理结果 0：处理中 1：支付成功 2：失败
		//订单状态：needpay- 待支付；paid- 支付成功；failture- 支付失败；overtime - 订单超时；close - 订单已关闭；back - 已退款。
		// 商户可以通过查询接口查询订单状态。(此字段用于判断业务执行状态)
		String res = pay.getStatus();  // 状态
		String msg = "支付成功";
		if ("needpay".equals(res)) 
			msg = "处理中";
		else if("paid".equals(res))
			msg = "支付成功";
		else if("failture".equals(res))
			msg = "支付失败";
		else if("overtime".equals(res))
			msg = "订单超时";
		else
			msg = "订单已关闭";
			
		
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
		if ("paid".equals(res)) {
			// 成功
			orderUpdate.setStatus(1001);
			
			
			orderUpdate.setTxnamt(Integer.parseInt(order.getTxnamt()+""));
			merRes.setRespCode("1001");
			merRes.setTxnAmt(String.valueOf(pay.getTotalFee()));

		} else if ("needpay".equals(res)) {//处理中
			orderUpdate.setStatus(1000);
			 //计算本次交易余额(除去费率)   订单金额-费率金额
		//	String l1 = new BigDecimal(order.getTxnamt()).subtract(new BigDecimal(order.getInFee())).stripTrailingZeros().toPlainString();
			
			orderUpdate.setTxnamt(Integer.parseInt(order.getTxnamt()+""));
			merRes.setRespCode("1000");
			merRes.setTxnAmt(String.valueOf(pay.getTotalFee()));
		} else {
			// 其他失败
			orderUpdate.setStatus(1002);// 失败
			merRes.setRespCode("1002");
			merRes.setTxnAmt(String.valueOf(pay.getTotalFee()));

		}
		logger.info("修改的信息："+orderUpdate);
		// 去修改订单信息，并且告诉商户信息
		npayOrderService.updateOrder(orderUpdate);// 修改订单信息

		if ("paid".equals(res)) {
			String sql = "call new_order_settle(" + oid + ",0,@ret_code)";
			Map<Object, Object> map = newOrderSettleService.callProcedure(sql);
			int staCode = (int) map.get("ret_code");

			String sql1 = "call new_order_settle(" + oid + ",1,@ret_code)";
			Map<Object, Object> map1 = newOrderSettleService.callProcedure(sql1);
			int staCode1 = (int) map1.get("ret_code");

			logger.info(staCode +"订单号："+oid+ "--------->调用函数返回的结果");
			logger.info(staCode1 + "--------->余额计算返回的结果");
			
			
		}
		
		// 去修改订单信息，并且告诉商户信息
		String backUrl = order.getBackurl();
		sendGet(backUrl, merRes, key);// 向商户提供的地址发送异步的get请求
		
		
	}
	

	
	
	
	
	
	
	
	
	
	/**
	 * 10088  网银回调
	 *   
	 * 
	 * 
	 */
	@RequestMapping("/juheBanks10")
	public void  juheBankNotify(Pay10088Result pay,HttpServletResponse response){
		logger.info("聚合支付回调"+pay);
	
		// 取商户订单号
		String oid = pay.getOrderid();
		// 拿商户订单好取订单信息
		NpayOrder order = npayOrderService.getOrderByOurOrderId(oid);
		if (order == null) {
			// 没有该订单
			return;
		}
		
//		获取渠道中的商户秘钥   进行验签
		NpayChannels channels = npayChannelsService.selectByPrimaryKey(order.getChannelId());
		
		if(channels==null){
			logger.info("在channels表查不到对应的信息>>>>"+JSON.toJSONString(channels));
			return ;
		}
		
		// 验签
		boolean flag = getVerfySign(pay , channels.getChannelSecretKey() ,pay.getSign());
		if(!flag){
			logger.info("支付回调验签不通过"+pay);
			//验签不通过
			return;
		}
//		验签通过后   响应给第三方信息
		write("OK",response);
		
		
		
		// 商户可以通过查询接口查询订单状态。(此字段用于判断业务执行状态)
		String res = pay.getReturncode();  // 状态
		String msg = "支付成功";
		if ("00".equals(res)) 
			msg = "支付成功";
		else
			msg = "支付失败";
			
		
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
		if ("00".equals(res)) {
			// 成功
			orderUpdate.setStatus(1001);
//			orderUpdate.setTxnamt(Integer.parseInt(new BigDecimal(pay.getAmount()).multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString()));
			merRes.setRespCode("1001");
			merRes.setTxnAmt(new BigDecimal(pay.getAmount()).multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString());
		
		} else {
			// 其他失败
			orderUpdate.setStatus(1002);// 失败
			merRes.setRespCode("1002");
			merRes.setTxnAmt(new BigDecimal(pay.getAmount()).multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString());

		}
		logger.info("修改的信息："+orderUpdate);
		// 去修改订单信息，并且告诉商户信息
		npayOrderService.updateOrder(orderUpdate);// 修改订单信息

		if ("00".equals(res)) {
			String sql = "call new_order_settle(" + oid + ",0,@ret_code)";
			Map<Object, Object> map = newOrderSettleService.callProcedure(sql);
			int staCode = (int) map.get("ret_code");

			String sql1 = "call new_order_settle(" + oid + ",1,@ret_code)";
			Map<Object, Object> map1 = newOrderSettleService.callProcedure(sql1);
			int staCode1 = (int) map1.get("ret_code");

			logger.info(staCode +"订单号："+oid+ "--------->调用函数返回的结果");
			logger.info(staCode1 + "--------->余额计算返回的结果");
			
			 
		}
		
		
		// 去修改订单信息，并且告诉商户信息
		String backUrl = order.getBackurl();
		sendGet(backUrl, merRes, key);// 向商户提供的地址发送异步的get请求
		
		
	}
	

	
	
	
	/**
	 * UsdtPay  网银回调
	 *   
	 * 
	 * 
	 */
	@RequestMapping("/UsdtPayBankNotify")
	public void  UsdtPayBankNotify(HttpServletRequest request,HttpServletResponse response){
		
		SortedMap<String, Object> sortedMap = PayUtil.mapToSortedMap(request);
		
		logger.info("聚合支付回调"+sortedMap);
		String res = sortedMap.get("status").toString();  // 状态
		//取订单金额    元转换为分
		String amount =  new BigDecimal(sortedMap.get("orderAmount").toString()).multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString()  ;
		// 取商户订单号
		String oid = sortedMap.get("orderNo").toString();
		// 拿商户订单好取订单信息
		NpayOrder order = npayOrderService.getOrderByOurOrderId(oid);
		if (order == null) {
			// 没有该订单
			return;
		}
		
//		获取渠道中的商户秘钥   进行验签
		NpayChannels channels = npayChannelsService.selectByPrimaryKey(order.getChannelId());
		
		if(channels==null){
			logger.info("在channels表查不到对应的信息>>>>"+JSON.toJSONString(channels));
			return ;
		}
		
		
		StringBuffer sb = new StringBuffer();
		
		sb.append( sortedMap.get("signType"));
		sb.append( sortedMap.get("orderNo"));
		sb.append( sortedMap.get("orderAmount"));
		sb.append( sortedMap.get("orderCurrency"));
		sb.append( sortedMap.get("transactionId"));
		sb.append( sortedMap.get("status"));
		sb.append(channels.getChannelSecretKey());
		
		String sign =VirtualUtil.md5(sb.toString(),"");
		
		
		
		logger.info("自己生成的签名"+sign);
		if(!sign.equals(sortedMap.get("sign").toString())){
			logger.info("支付回调验签不通过"+sortedMap);
			//验签不通过
			return;
		}
//		验签通过后   响应给第三方信息
		write("success",response);
		
		
		
		// 商户可以通过查询接口查询订单状态。(此字段用于判断业务执行状态)

		String msg = "支付成功";
		if ("success".equals(res)) 
			msg = "支付成功";
		else
			msg = "支付失败";
			
		
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
		if ("success".equals(res)) {
			// 成功
			orderUpdate.setStatus(1001);
			merRes.setRespCode("1001");
			merRes.setTxnAmt(amount);
		
		} else {
			// 其他失败
			orderUpdate.setStatus(1002);// 失败
			merRes.setRespCode("1002");
			merRes.setTxnAmt(amount);

		}
		logger.info("修改的信息："+orderUpdate);
		// 去修改订单信息，并且告诉商户信息
		npayOrderService.updateOrder(orderUpdate);// 修改订单信息

		if ("success".equals(res)) {
			String sql = "call new_order_settle(" + oid + ",0,@ret_code)";
			Map<Object, Object> map = newOrderSettleService.callProcedure(sql);
			int staCode = (int) map.get("ret_code");

			String sql1 = "call new_order_settle(" + oid + ",1,@ret_code)";
			Map<Object, Object> map1 = newOrderSettleService.callProcedure(sql1);
			int staCode1 = (int) map1.get("ret_code");

			logger.info(staCode +"订单号："+oid+ "--------->调用函数返回的结果");
			logger.info(staCode1 + "--------->余额计算返回的结果");
			
			 
		}
		
		
		// 去修改订单信息，并且告诉商户信息
//		String backUrl = order.getBackurl();
//		sendGet(backUrl, merRes, key);// 向商户提供的地址发送异步的get请求
		
		
	}
	

	
	
	
	
	/**
	 * 金溢支付 网银回调
	 *   
	 * 
	 * 
	 */
	@RequestMapping("/JinYiPayBankNotify")
	public void  JinYiPayBankNotify(HttpServletRequest request,HttpServletResponse response){
		Enumeration e1 = request.getHeaderNames();  
        while (e1.hasMoreElements()) {  
            String headerName = (String) e1.nextElement();  
            String headValue = request.getHeader(headerName);  
            logger.info("请求头信息》》》》》》"+headerName + "=" + headValue);  
        }  
		
		SortedMap<String, Object> aaa = PayUtil.mapToSortedMap(request);
		logger.info("请求头信息>>>>>>>>"+aaa);  
		
		  StringBuffer sb = new StringBuffer();
	        try {
	            BufferedReader br = request.getReader();
	            String line = null;
	            while ((line = br.readLine()) != null) {
	                sb.append(line);
	            }
	        } catch (Exception e) {
	            // TODO: handle exception
	        }

	      JSONObject o = JSON.parseObject(sb.toString());
		
		
		
		
		
		
		logger.info("聚合支付回调"+o);
		String res = o.get("code").toString();  // 状态
		//取订单金额    元转换为分
		String amount =  new BigDecimal(o.get("total_fee").toString()).multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString()  ;
		// 取商户订单号
		String oid = o.get("customer_order_no").toString();
		// 拿商户订单好取订单信息
		NpayOrder order = npayOrderService.getOrderByOurOrderId(oid);
		if (order == null) {
			// 没有该订单
			return;
		}
		
//		获取渠道中的商户秘钥   进行验签
		NpayChannels channels = npayChannelsService.selectByPrimaryKey(order.getChannelId());
		
		if(channels==null){
			logger.info("在channels表查不到对应的信息>>>>"+JSON.toJSONString(channels));
			return ;
		}
		
		
		
		SortedMap<String, Object> sortedMap = new TreeMap<String, Object>();
		sortedMap.put("code", o.getString("code"));   //商户id
		sortedMap.put("message",  o.getString("message"));   //商户订单号(我们自己生成的id)
		sortedMap.put("status", o.getString("status"));
		sortedMap.put("customer_code", o.getString("customer_code"));     
		sortedMap.put("customer_order_no", o.getString("customer_order_no"));
		sortedMap.put("order_no", o.getString("order_no"));
		sortedMap.put("payment_fee", o.getString("payment_fee"));
		sortedMap.put("payment_time", o.getString("payment_time"));
		sortedMap.put("total_fee", o.getString("total_fee"));
		sortedMap.put("remark", o.getString("remark"));
		
		
		String sign =JinYiUtil.createSign(sortedMap,channels.getChannelSecretKey());
		logger.info("自己生成的签名"+sign);
		if(!sign.equals(sortedMap.get("sign").toString())){
			logger.info("支付回调验签不通过"+sortedMap);
			//验签不通过
			return;
		}
//		验签通过后   响应给第三方信息
		write("success",response);
		
		
		
		// 商户可以通过查询接口查询订单状态。(此字段用于判断业务执行状态)

		String msg = "支付成功";
		if ("200".equals(res)) 
			msg = "支付成功";
		else
			msg = "支付失败";
			
		
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
		if ("200".equals(res)) {
			// 成功
			orderUpdate.setStatus(1001);
			merRes.setRespCode("1001");
			merRes.setTxnAmt(amount);
		
		} else {
			// 其他失败
			orderUpdate.setStatus(1002);// 失败
			merRes.setRespCode("1002");
			merRes.setTxnAmt(amount);

		}
		logger.info("修改的信息："+orderUpdate);
		// 去修改订单信息，并且告诉商户信息
		npayOrderService.updateOrder(orderUpdate);// 修改订单信息

		if ("200".equals(res)) {
			String sql = "call new_order_settle(" + oid + ",0,@ret_code)";
			Map<Object, Object> map = newOrderSettleService.callProcedure(sql);
			int staCode = (int) map.get("ret_code");

			String sql1 = "call new_order_settle(" + oid + ",1,@ret_code)";
			Map<Object, Object> map1 = newOrderSettleService.callProcedure(sql1);
			int staCode1 = (int) map1.get("ret_code");

			logger.info(staCode +"订单号："+oid+ "--------->调用函数返回的结果");
			logger.info(staCode1 + "--------->余额计算返回的结果");
			
			 
		}
		
		
		// 去修改订单信息，并且告诉商户信息
		String backUrl = order.getBackurl();
		sendGet(backUrl, merRes, key);// 向商户提供的地址发送异步的get请求
		
		
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("textml");
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));  
	    String line = null;  
	    StringBuilder sb = new StringBuilder();  
	    while((line = br.readLine())!=null){  
	        sb.append(line);  
	    }  
	  
	    //      ?     
	    String reqBody = sb.toString();  
		
		PrintWriter out = response.getWriter();
		out.println("success");
		out.flush();
		out.close();
	}

	/**
	 * apiPay  网银回调
	 *   
	 * 
	 * 
	 */
	@RequestMapping("/ApiPayBankNotify")
	public void  ApiPayBankNotify(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		
//		SortedMap<String, Object> sortedMap = PayUtil.mapToSortedMap(request);
	
   	 
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));  
	    String line = null;  
	    StringBuilder sb = new StringBuilder();  
	    while((line = br.readLine())!=null){  
	        sb.append(line);  
	    }  
	    
	    
	    
	    logger.info("聚合支付回调>>>>"+ sb.toString());
	    
	    JSONObject o = JSON.parseObject(sb.toString());
	    
		
		logger.info("聚合支付回调>>>>"+o);
		String res = o.getString("state");  // 状态
		//取订单金额    元转换为分
		String amount =  new BigDecimal(o.getString("amount")).multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString()  ;
		// 取商户订单号
		String oid = o.getString("ordercode");
		// 拿商户订单好取订单信息
		NpayOrder order = npayOrderService.getOrderByOurOrderId(oid);
		if (order == null) {
			// 没有该订单
			return;
		}
		
//		获取渠道中的商户秘钥   进行验签
		NpayChannels channels = npayChannelsService.selectByPrimaryKey(order.getChannelId());
		
		if(channels==null){
			logger.info("在channels表查不到对应的信息>>>>"+JSON.toJSONString(channels));
			return ;
		}
		
		
		SortedMap<String, Object> sortedMap = new TreeMap<String, Object>();
		sortedMap.put("amount", o.getString("amount"));   //商户id
		sortedMap.put("ordercode",  o.getString("ordercode"));   //商户订单号(我们自己生成的id)
		sortedMap.put("state", o.getString("state"));
		sortedMap.put("callbackurl", o.getString("callbackurl"));     
		sortedMap.put("goodsId", o.getString("goodsId"));
		sortedMap.put("callbackMemo", o.getString("callbackMemo"));
		
		
		String sign =APIUtil.sign(sortedMap,channels.getChannelSecretKey());
		logger.info("自己生成的签名"+sign);
		if(!sign.equals(o.getString("sign"))){
			logger.info("支付回调验签不通过"+o);
			//验签不通过
			return;
		}
		
//		验签通过后   响应给第三方信息
		write("SUCCESS",response);
		
		
		
		// 商户可以通过查询接口查询订单状态。(此字段用于判断业务执行状态)

		String msg = "支付成功";
		if ("10Z".equals(res)) 
			msg = "支付成功";
		else
			msg = "支付失败";
			
		
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
		if ("10Z".equals(res)) {
			// 成功
			orderUpdate.setStatus(1001);
			merRes.setRespCode("1001");
			merRes.setTxnAmt(amount);
		
		} else {
			// 其他失败
			orderUpdate.setStatus(1002);// 失败
			merRes.setRespCode("1002");
			merRes.setTxnAmt(amount);

		}
		logger.info("修改的信息："+orderUpdate);
		// 去修改订单信息，并且告诉商户信息
		npayOrderService.updateOrder(orderUpdate);// 修改订单信息

		if ("10Z".equals(res)) {
			String sql = "call new_order_settle(" + oid + ",0,@ret_code)";
			Map<Object, Object> map = newOrderSettleService.callProcedure(sql);
			int staCode = (int) map.get("ret_code");

			String sql1 = "call new_order_settle(" + oid + ",1,@ret_code)";
			Map<Object, Object> map1 = newOrderSettleService.callProcedure(sql1);
			int staCode1 = (int) map1.get("ret_code");

			logger.info(staCode +"订单号："+oid+ "--------->调用函数返回的结果");
			logger.info(staCode1 + "--------->余额计算返回的结果");
			
			 
		}
		
		
		// 去修改订单信息，并且告诉商户信息
		String backUrl = order.getBackurl();
		sendGet(backUrl, merRes, key);// 向商户提供的地址发送异步的get请求
		
		
	}
	

	
	
	
	/**
	 * 一麻袋  网银回调
	 *   
	 * 
	 * 
	 */
	@RequestMapping("/YMDNotify")
	public void  YMDNotify(HttpServletRequest request,HttpServletResponse response){
		
		SortedMap<String, Object> sortedMap = PayUtil.mapToSortedMap(request);
		
		logger.info("聚合支付回调"+sortedMap);
		String res = sortedMap.get("payResult").toString();  // 状态
		//取订单金额    元转换为分
//		String amount =  new BigDecimal(sortedMap.get("amount").toString()).multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString()  ;
		// 取商户订单号
		String oid = sortedMap.get("merchantOutOrderNo").toString();
		// 拿商户订单好取订单信息
		NpayOrder order = npayOrderService.getOrderByOurOrderId(oid);
		if (order == null) {
			// 没有该订单
			return;
		}
		String amount = order.getTxnAmts();
//		获取渠道中的商户秘钥   进行验签
		NpayChannels channels = npayChannelsService.selectByPrimaryKey(order.getChannelId());
		
		if(channels==null){
			logger.info("在channels表查不到对应的信息>>>>"+JSON.toJSONString(channels));
			return ;
		}
		String sign =APIUtil.sign(sortedMap,channels.getChannelSecretKey());
		logger.info("自己生成的签名"+sign);
		if(!sign.equals(sortedMap.get("sign").toString())){
			logger.info("支付回调验签不通过"+sortedMap);
			//验签不通过
			return;
		}
		
//		验签通过后   响应给第三方信息
		write("success",response);
		
		
		
		// 商户可以通过查询接口查询订单状态。(此字段用于判断业务执行状态)

		String msg = "支付成功";
		if ("1".equals(res)) 
			msg = "支付成功";
		else
			msg = "支付失败";
			
		
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
		if ("1".equals(res)) {
			// 成功
			orderUpdate.setStatus(1001);
			merRes.setRespCode("1001");
			merRes.setTxnAmt(amount);
		
		} else {
			// 其他失败
			orderUpdate.setStatus(1002);// 失败
			merRes.setRespCode("1002");
			merRes.setTxnAmt(amount);

		}
		logger.info("修改的信息："+orderUpdate);
		// 去修改订单信息，并且告诉商户信息
		npayOrderService.updateOrder(orderUpdate);// 修改订单信息

		if ("1".equals(res)) {
			String sql = "call new_order_settle(" + oid + ",0,@ret_code)";
			Map<Object, Object> map = newOrderSettleService.callProcedure(sql);
			int staCode = (int) map.get("ret_code");

			String sql1 = "call new_order_settle(" + oid + ",1,@ret_code)";
			Map<Object, Object> map1 = newOrderSettleService.callProcedure(sql1);
			int staCode1 = (int) map1.get("ret_code");

			logger.info(staCode +"订单号："+oid+ "--------->调用函数返回的结果");
			logger.info(staCode1 + "--------->余额计算返回的结果");
			
			 
		}
		
		
		// 去修改订单信息，并且告诉商户信息
		String backUrl = order.getBackurl();
		sendGet(backUrl, merRes, key);// 向商户提供的地址发送异步的get请求
		
		
	}
	
	
	
	
	

	/**
	 * three  网银回调
	 *   
	 * 
	 * 
	 */
	@RequestMapping("/threeBankNotify")
	public void  threeBankNotify(HttpServletRequest request,HttpServletResponse response){
		
		SortedMap<String, Object> sortedMap = PayUtil.mapToSortedMap(request);
		
		logger.info("聚合支付回调"+sortedMap);
		String res = sortedMap.get("returncode").toString();  // 状态
		//取订单金额    元转换为分
		String amount =  new BigDecimal(sortedMap.get("amount").toString()).multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString()  ;
		// 取商户订单号
		String oid = sortedMap.get("orderid").toString();
		// 拿商户订单好取订单信息
		NpayOrder order = npayOrderService.getOrderByOurOrderId(oid);
		if (order == null) {
			// 没有该订单
			return;
		}
		
//		获取渠道中的商户秘钥   进行验签
		NpayChannels channels = npayChannelsService.selectByPrimaryKey(order.getChannelId());
		
		if(channels==null){
			logger.info("在channels表查不到对应的信息>>>>"+JSON.toJSONString(channels));
			return ;
		}
		
		// 验签
//		boolean flag = getVerfySign(sortedMap , channels.getChannelSecretKey() ,sortedMap.get("sign").toString());
		String sign = ThreeUtil.createSign(sortedMap , channels.getChannelSecretKey());
		logger.info("自己生成的签名"+sign);
		if(!sign.equals(sortedMap.get("sign").toString())){
			logger.info("支付回调验签不通过"+sortedMap);
			//验签不通过
			return;
		}
//		验签通过后   响应给第三方信息
		write("ok",response);
		
		
		
		// 商户可以通过查询接口查询订单状态。(此字段用于判断业务执行状态)

		String msg = "支付成功";
		if ("00".equals(res)) 
			msg = "支付成功";
		else
			msg = "支付失败";
			
		
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
		if ("00".equals(res)) {
			// 成功
			orderUpdate.setStatus(1001);
			merRes.setRespCode("1001");
			merRes.setTxnAmt(amount);
		
		} else {
			// 其他失败
			orderUpdate.setStatus(1002);// 失败
			merRes.setRespCode("1002");
			merRes.setTxnAmt(amount);

		}
		logger.info("修改的信息："+orderUpdate);
		// 去修改订单信息，并且告诉商户信息
		npayOrderService.updateOrder(orderUpdate);// 修改订单信息

		if ("00".equals(res)) {
			String sql = "call new_order_settle(" + oid + ",0,@ret_code)";
			Map<Object, Object> map = newOrderSettleService.callProcedure(sql);
			int staCode = (int) map.get("ret_code");

			String sql1 = "call new_order_settle(" + oid + ",1,@ret_code)";
			Map<Object, Object> map1 = newOrderSettleService.callProcedure(sql1);
			int staCode1 = (int) map1.get("ret_code");

			logger.info(staCode +"订单号："+oid+ "--------->调用函数返回的结果");
			logger.info(staCode1 + "--------->余额计算返回的结果");
			
			 
		}
		
		
		// 去修改订单信息，并且告诉商户信息
		String backUrl = order.getBackurl();
		sendGet(backUrl, merRes, key);// 向商户提供的地址发送异步的get请求
		
		
	}
	

	
	
	/**
	 * zjx  网银回调
	 * @throws Exception 
	 *   
	 * 
	 * 
	 */
	@RequestMapping("/ZjxBankNotify")
	public void  ZjxBankNotify(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
//		SortedMap<String, Object> sortedMap = PayUtil.objectToSortedMap(o);
		
		
		
		   StringBuffer sb = new StringBuffer();
	        try {
	            BufferedReader br = request.getReader();
	            String line = null;
	            while ((line = br.readLine()) != null) {
	                sb.append(line);
	            }
	        } catch (Exception e) {
	            // TODO: handle exception
	        }

	      JSONObject o = JSON.parseObject(sb.toString());
		
//	      Map mapTypes = JSON.parseObject(sb.toString());  
		
		logger.info("聚合支付回调"+o);
		
		String res = o.getString("status");  // 状态
		
		// 取商户订单号
		String oid = o.getString("order_id");  
		// 拿商户订单好取订单信息
		NpayOrder order = npayOrderService.getOrderByOurOrderId(oid);
		if (order == null) {
			// 没有该订单
			return;
		}
		
		//取订单金额    元转换为分
		String amount =  order.getTxnamt().toString();
		
		
//		获取渠道中的商户秘钥   进行验签
		NpayChannels channels = npayChannelsService.selectByPrimaryKey(order.getChannelId());
		
		if(channels==null){
			logger.info("在channels表查不到对应的信息>>>>"+JSON.toJSONString(channels));
			return ;
		}
		
		SortedMap<String, Object> sortedMap = new TreeMap<String, Object>();
		sortedMap.put("merchant_id", o.getString("merchant_id"));   //商户id
		sortedMap.put("order_id",  o.getString("order_id"));   //商户订单号(我们自己生成的id)
		sortedMap.put("status", o.getString("status"));
		sortedMap.put("order_amt", o.getString("order_amt"));     
		sortedMap.put("up_order_id", o.getString("up_order_id"));
//		sortedMap.put("sign", o.getString("sign")); 
		
		
		
		
		// 验签
//		boolean flag = getVerfySign(sortedMap , channels.getChannelSecretKey() ,o.getString("sign"));
		
		String sign = ZjxPayUtil.createSign(sortedMap , channels.getChannelSecretKey());
		logger.info("自己生成的签名"+sign);
		if(!sign.equals(o.getString("sign"))){
			logger.info("支付回调验签不通过"+o);
			//验签不通过
			return;
		}
//		验签通过后   响应给第三方信息
		write("ok",response);
		
		
		
		// 商户可以通过查询接口查询订单状态。(此字段用于判断业务执行状态)

		String msg = "支付成功";
		if ("00".equals(res)) 
			msg = "支付成功";
		else
			msg = "支付失败";
			
		
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
		if ("00".equals(res)) {
			// 成功
			orderUpdate.setStatus(1001);
			merRes.setRespCode("1001");
			merRes.setTxnAmt(amount);
		
		} else {
			// 其他失败
			orderUpdate.setStatus(1002);// 失败
			merRes.setRespCode("1002");
			merRes.setTxnAmt(amount);

		}
		logger.info("修改的信息："+orderUpdate);
		// 去修改订单信息，并且告诉商户信息
		npayOrderService.updateOrder(orderUpdate);// 修改订单信息

		if ("00".equals(res)) {
			String sql = "call new_order_settle(" + oid + ",0,@ret_code)";
			Map<Object, Object> map = newOrderSettleService.callProcedure(sql);
			int staCode = (int) map.get("ret_code");

			String sql1 = "call new_order_settle(" + oid + ",1,@ret_code)";
			Map<Object, Object> map1 = newOrderSettleService.callProcedure(sql1);
			int staCode1 = (int) map1.get("ret_code");

			logger.info(staCode +"订单号："+oid+ "--------->调用函数返回的结果");
			logger.info(staCode1 + "--------->余额计算返回的结果");
			
			 
		}
		
		
		// 去修改订单信息，并且告诉商户信息
		String backUrl = order.getBackurl();
		sendGet(backUrl, merRes, key);// 向商户提供的地址发送异步的get请求
		
		
	}
	

	
	
	
	
	
	/**
	 * token  网银回调
	 *   
	 * 
	 * 
	 */
	@RequestMapping("/tokenBankNotify")
	public void  tokenBankNotify(HttpServletRequest request,HttpServletResponse response){
//		logger.info("聚合支付回调>>>>>"+request);
//		logger.info("聚合支付回调json>>>>>>"+JSON.toJSONString(request));
//		SortedMap<String, Object> sortedMap = PayUtil.mapToSortedMap(request);
		
		  StringBuffer sb = new StringBuffer();
	        try {
	            BufferedReader br = request.getReader();
	            String line = null;
	            while ((line = br.readLine()) != null) {
	                sb.append(line);
	            }
	        } catch (Exception e) {
	            // TODO: handle exception
	        }

	      JSONObject o = JSON.parseObject(sb.toString());
	      
//	      SortedMap<String, Object> sortedmap = JSONObject.toJavaObject(o, SortedMap.class);
		
		
		logger.info("聚合支付回调"+o);
		String res = o.getString("orderStatus");  // 状态
		//取订单金额    元转换为分
		String amount =  new BigDecimal(o.getString("totalFee")).multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString()  ;
		// 取商户订单号
		String oid = o.getString("sellerOrderNo") ;
		// 拿商户订单好取订单信息
		NpayOrder order = npayOrderService.getOrderByOurOrderId(oid);
		if (order == null) {
			// 没有该订单
			return;
		}
		
//		获取渠道中的商户秘钥   进行验签
		NpayChannels channels = npayChannelsService.selectByPrimaryKey(order.getChannelId());
		
		if(channels==null){
			logger.info("在channels表查不到对应的信息>>>>"+JSON.toJSONString(channels));
			return ;
		}
		
		
		
		SortedMap<String, Object> sortedMap = new TreeMap<String, Object>();
		sortedMap.put("orderNo", o.getString("orderNo"));   //商户id
		sortedMap.put("sellerOrderNo",  o.getString("sellerOrderNo"));   //商户订单号(我们自己生成的id)
		sortedMap.put("codeUrl", o.getString("codeUrl"));
		sortedMap.put("orderStatus", o.getString("orderStatus"));     
		sortedMap.put("totalFee", o.getString("totalFee"));
		sortedMap.put("extOrderNo", o.getString("extOrderNo"));
		sortedMap.put("uid", o.getString("uid"));
		sortedMap.put("channelNo", o.getString("channelNo"));
		sortedMap.put("sign", o.getString("sign"));
		
		
		
		
		
		
		// 验签
		String sign = TokenPayUtil.createSign(sortedMap , channels.getChannelSecretKey());
		logger.info("自己生成的签名"+sign);
		if(!sign.equals(o.getString("sign"))){
			logger.info("支付回调验签不通过"+sortedMap);
			//验签不通过
			return;
		}
//		验签通过后   响应给第三方信息
		write("success",response);
		
		
		
		// 商户可以通过查询接口查询订单状态。(此字段用于判断业务执行状态)

		String msg = "支付成功";
		if ("0".equals(res)) 
			msg = "支付成功";
		else
			msg = "支付失败";
			
		
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
		if ("0".equals(res)) {
			// 成功
			orderUpdate.setStatus(1001);
			merRes.setRespCode("1001");
			merRes.setTxnAmt(amount);
		
		} else {
			// 其他失败
			orderUpdate.setStatus(1002);// 失败
			merRes.setRespCode("1002");
			merRes.setTxnAmt(amount);

		}
		logger.info("修改的信息："+orderUpdate);
		// 去修改订单信息，并且告诉商户信息
		npayOrderService.updateOrder(orderUpdate);// 修改订单信息

		if ("0".equals(res)) {
			String sql = "call new_order_settle(" + oid + ",0,@ret_code)";
			Map<Object, Object> map = newOrderSettleService.callProcedure(sql);
			int staCode = (int) map.get("ret_code");

			String sql1 = "call new_order_settle(" + oid + ",1,@ret_code)";
			Map<Object, Object> map1 = newOrderSettleService.callProcedure(sql1);
			int staCode1 = (int) map1.get("ret_code");

			logger.info(staCode +"订单号："+oid+ "--------->调用函数返回的结果");
			logger.info(staCode1 + "--------->余额计算返回的结果");
			
			 
		}
		
		
		// 去修改订单信息，并且告诉商户信息
		String backUrl = order.getBackurl();
		sendGet(backUrl, merRes, key);// 向商户提供的地址发送异步的get请求
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * zjx  代付回调
	 *   
	 * 
	 * 
	 */
	@RequestMapping("/ZjxDaifuNotify")
//	@ResponseBody
	public void  ZjxDaifuNotify(HttpServletRequest request,HttpServletResponse response){
		
		   StringBuffer sb = new StringBuffer();
	        try {
	            BufferedReader br = request.getReader();
	            String line = null;
	            while ((line = br.readLine()) != null) {
	                sb.append(line);
	            }
	        } catch (Exception e) {
	            // TODO: handle exception
	        }

	      JSONObject o = JSON.parseObject(sb.toString());
		
		
		logger.info("代付回调>>>>>>"+o);
		
		String res = o.getString("status");  // 状态
		
		// 取商户订单号
		String oid = o.getString("order_id");  
		// 拿商户订单好取订单信息
		NpayOrder order = npayOrderService.getOrderByOurOrderId(oid);
		if (order == null) {
			// 没有该订单
			logger.info("没有该订单为1000>>>>>>"+o);
			return;
		}
		
		//取订单金额    元转换为分
		String amount =  order.getTxnamt().toString();
		
		
//		获取渠道中的商户秘钥   进行验签
		NpayChannels channels = npayChannelsService.selectByPrimaryKey(order.getChannelId());
		
		if(channels==null){
			logger.info("在channels表查不到对应的信息>>>>"+JSON.toJSONString(channels));
			return ;
		}
		
		SortedMap<String, Object> sortedMap = new TreeMap<String, Object>();
		sortedMap.put("merchant_id", o.getString("merchant_id"));   //商户id
		sortedMap.put("order_id",  o.getString("order_id"));   //商户订单号(我们自己生成的id)
		sortedMap.put("status", o.getString("status"));
		sortedMap.put("order_amt", o.getString("order_amt"));     
		sortedMap.put("up_order_id", o.getString("up_order_id"));
		
		
		
		
		// 验签
		String sign = ZjxPayUtil.createSign(sortedMap , channels.getChannelSecretKey());
		if(!sign.equals(o.getString("sign"))){
			logger.info("代付回调验签不通过"+sortedMap);
			//验签不通过
			return;
		}
//		验签通过后   响应给第三方信息
		write("ok",response);
		
		
		
		// 商户可以通过查询接口查询订单状态。(此字段用于判断业务执行状态)

		String msg = "代付成功";
		if ("00".equals(res)) 
			msg = "代付成功";
		else
			msg = "代付失败";
			
		
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
		if ("00".equals(res)) {
			// 成功
			orderUpdate.setStatus(1001);
			merRes.setRespCode("1001");
			merRes.setTxnAmt(amount);
		
		} else {
			// 其他失败
			orderUpdate.setStatus(1002);// 失败
			merRes.setRespCode("1002");
			merRes.setTxnAmt(amount);

		}

		

		if ("00".equals(res)) {
			String sql = "call new_order_settle(" + oid + ",0,@ret_code)";
			Map<Object, Object> map = newOrderSettleService.callProcedure(sql);
			int staCode = (int) map.get("ret_code");
			
			logger.info("修改的信息："+orderUpdate);
			// 去修改订单信息，并且告诉商户信息
			npayOrderService.updateOrder(orderUpdate);// 修改订单信息
			String sql1 = "call new_order_settle(" + oid + ",1,@ret_code)";
			Map<Object, Object> map1 = newOrderSettleService.callProcedure(sql1);
			int staCode1 = (int) map1.get("ret_code");

			logger.info(staCode +"订单号："+oid+ "--------->调用函数返回的结果");
			logger.info(staCode1 + "--------->余额计算返回的结果");
			
			 
		}
		
		
		// 去修改订单信息，并且告诉商户信息
		String backUrl = order.getBackurl();
		sendGet(backUrl, merRes, key);// 向商户提供的地址发送异步的get请求
		
	}
	
	
	
	
	

	/**
	 * kuaijie回调
	 *   
	 * 
	 * 
	 */
	@RequestMapping("/kuaijieNotify")
	public void  kuaijieNotify(HttpServletRequest request,HttpServletResponse response){
		
		SortedMap<String, Object> sortedMap = PayUtil.mapToSortedMap(request);
		
		logger.info("聚合支付回调"+sortedMap);
		
		String res = sortedMap.get("status").toString();  // 状态
		//取订单金额    元转换为分
		String amount =  new BigDecimal(sortedMap.get("oldAmount").toString()).multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString()  ;
		// 取商户订单号
		String oid = sortedMap.get("tenantOrderNo").toString();
		// 拿商户订单好取订单信息
		NpayOrder order = npayOrderService.getOrderByOurOrderId(oid);
		if (order == null) {
			// 没有该订单
			logger.info("没有该订单>>>>>"+oid);
			return;
		}
		
//		获取渠道中的商户秘钥   进行验签
		NpayChannels channels = npayChannelsService.selectByPrimaryKey(order.getChannelId());
		
		if(channels==null){
			logger.info("在channels表查不到对应的信息>>>>"+JSON.toJSONString(channels));
			return ;
		}
		
		// 验签
		boolean flag = getVerfySign(sortedMap , channels.getChannelSecretKey() ,sortedMap.get("sign").toString());
		if(!flag){
			logger.info("支付回调验签不通过"+sortedMap);
			//验签不通过
			return;
		}
//		验签通过后   响应给第三方信息
		write("success",response);
		
		
		
		// 商户可以通过查询接口查询订单状态。(此字段用于判断业务执行状态)
		String msg = "支付成功";
		if ("200".equals(res)) 
			msg = "支付成功";
		else if("201".equals(res))
			msg = "处理中";
		else
			msg = "支付失败";
		
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
		if ("200".equals(res)) {
			// 成功
			orderUpdate.setStatus(1001);
			merRes.setRespCode("1001");
			merRes.setTxnAmt(amount);
		
		} else {
			// 其他失败
			orderUpdate.setStatus(1002);// 失败
			merRes.setRespCode("1002");
			merRes.setTxnAmt(amount);

		}
		logger.info("修改的信息："+orderUpdate);
		// 去修改订单信息，并且告诉商户信息
		npayOrderService.updateOrder(orderUpdate);// 修改订单信息

		if ("200".equals(res)) {
			String sql = "call new_order_settle(" + oid + ",0,@ret_code)";
			Map<Object, Object> map = newOrderSettleService.callProcedure(sql);
			int staCode = (int) map.get("ret_code");

			String sql1 = "call new_order_settle(" + oid + ",1,@ret_code)";
			Map<Object, Object> map1 = newOrderSettleService.callProcedure(sql1);
			int staCode1 = (int) map1.get("ret_code");

			logger.info(staCode +"订单号："+oid+ "--------->调用函数返回的结果");
			logger.info(staCode1 + "--------->余额计算返回的结果");
			
			 
		}
		
		
		// 去修改订单信息，并且告诉商户信息
		String backUrl = order.getBackurl();
		sendGet(backUrl, merRes, key);// 向商户提供的地址发送异步的get请求
		
		
	}
	

	
	
	
	
	
	/**
	 * 
	 * 
	 */
	@RequestMapping("/QUBankNotify")
	public void  QUBankNotify(HttpServletRequest request,HttpServletResponse response){
		
		SortedMap<String, Object> sortedMap = PayUtil.mapToSortedMap(request);
		
		logger.info("聚合支付回调"+sortedMap);
		String res = sortedMap.get("trade_status").toString();  // 状态
		//取订单金额    元转换为分
		String amount =  new BigDecimal(sortedMap.get("totalAmount").toString()).multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString()  ;
		// 取商户订单号
		String oid = sortedMap.get("out_trade_no").toString();
		// 拿商户订单好取订单信息
		NpayOrder order = npayOrderService.getOrderByOurOrderId(oid);
		if (order == null) {
			// 没有该订单
			return;
		}
		
//		获取渠道中的商户秘钥   进行验签
		NpayChannels channels = npayChannelsService.selectByPrimaryKey(order.getChannelId());
		
		if(channels==null){
			logger.info("在channels表查不到对应的信息>>>>"+JSON.toJSONString(channels));
			return ;
		}
		
		// 验签
		String newSign = sign(sortedMap , channels.getChannelSecretKey());
		if(!sortedMap.get("sign").toString().equals(newSign)){
			logger.info("支付回调验签不通过"+sortedMap);
			//验签不通过
			return;
		}
//		验签通过后   响应给第三方信息
		write("success",response);
		
		
		
		// 商户可以通过查询接口查询订单状态。(此字段用于判断业务执行状态)

		String msg = "支付成功";
		if ("TRADE_SUCCESS".equals(res)) 
			msg = "支付成功";
		else
			msg = "支付失败";
			
		
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
		if ("TRADE_SUCCESS".equals(res)) {
			// 成功
			orderUpdate.setStatus(1001);
			merRes.setRespCode("1001");
			merRes.setTxnAmt(amount);
		
		} else {
			// 其他失败
			orderUpdate.setStatus(1002);// 失败
			merRes.setRespCode("1002");
			merRes.setTxnAmt(amount);

		}
		logger.info("修改的信息："+orderUpdate);
		// 去修改订单信息，并且告诉商户信息
		npayOrderService.updateOrder(orderUpdate);// 修改订单信息

		if ("TRADE_SUCCESS".equals(res)) {
			String sql = "call new_order_settle(" + oid + ",0,@ret_code)";
			Map<Object, Object> map = newOrderSettleService.callProcedure(sql);
			int staCode = (int) map.get("ret_code");

			String sql1 = "call new_order_settle(" + oid + ",1,@ret_code)";
			Map<Object, Object> map1 = newOrderSettleService.callProcedure(sql1);
			int staCode1 = (int) map1.get("ret_code");

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
	public void xtDaifuReturn(juheDaifuReturn pay) {
		logger.info("代付回调" + pay);

		// 取商户订单号
		String oid = pay.getOutBatchNo();
		// 拿商户订单好取订单信息
		NpayOrder order = npayOrderService.getOrderByOurOrderId(oid);
		if (order == null) {
			// 没有该订单
			return;
		}
		// 获取渠道中的商户秘钥 进行验签
		// NpayChannels channels =
		// npayChannelsService.selectByPrimaryKey(order.getChannelId());
		//
		// if(channels==null){
		// logger.info("在channels表查不到对应的信息>>>>"+JSON.toJSONString(channels));
		// return ;
		// }
		//
		//
		// // 验签
		// boolean flag = KLTUtil.getVerfySign(pay,
		// channels.getChannelSecretKey(), pay.getSign());
		// if(!flag){
		// //验签失败
		// logger.info("代付回调验签不通过"+pay);
		// return ;
		// }
		//
		String res = pay.getStatus();
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
		 String backUrl = order.getBackurl();
		 if(backUrl != null || backUrl != "")
		 sendGet(backUrl, merRes, key);// 向商户提供的地址发送异步的get请求

	}

	
	
	
	
	
	
	/**
	 * 代付回调
	 */
	public void thDaifuReturn(String orderId) {
		logger.info("代付回调" + orderId);

		// 取商户订单号
		String oid = orderId;
		// 拿商户订单好取订单信息
		NpayOrder order = npayOrderService.getOrderByOurOrderId(oid);
		if (order == null) {
			// 没有该订单
			return;
		}
		String res = "success";
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
			merRes.setTxnAmt(order.getTxnamt()+"");

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
		 String backUrl = order.getBackurl();
		 sendGet(backUrl, merRes, key);// 向商户提供的地址发送异步的get请求

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void write(String str,HttpServletResponse response){
		PrintWriter out  = null;
		response.setContentType("application/json;charset=UTF-8");
	    response.setHeader("Pragma", "No-cache");
	    response.setHeader("Cache-Control", "no-cache");
	    response.setDateHeader("Expires", 0);
        try {
			out = response.getWriter();
//			out.write(JSON.toJSONString(str));
			out.write(str);
		    out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.close();
		}

	}

	/**
	 * 
	 * 验签
	 * 
	 * @param obj
	 * @param sercetKey
	 * @param sign
	 * @return
	 */
	public static  boolean  getVerfySign(Object obj,String sercetKey,String sign){
        if (obj == null)
            return false;
        SortedMap<String, Object> sortedMap = new TreeMap<String, Object>();
        BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(obj.getClass());
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = null;
			try {
				value = getter != null ? getter.invoke(obj) : null;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //过滤空字符串
            if(value == null || "".equals(value)||"sign".equals(key) || "null".equals(value)|| "attach".equals(key)){
            	
            }else{
            	 sortedMap.put(key, value);
            }
           
        }
        logger.info("验签的map："+sortedMap);
     
        String temp = KLTUtil.getContent(sortedMap,sercetKey);
        String tempsign = MD5Util.MD5Encode(temp.toString(), "UTF-8")
				.toUpperCase();
        logger.info("自己生成的签名"+tempsign);
        return sign.equals(tempsign);
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
			//转意 特殊字符+
			sign = URLEncoder.encode(sign, "UTF-8");
			logger.info("向商户发送信息时候的签名："+sign);
			merRes.setSignature(sign);
			String param = PayUtil.signature2(PayUtil.objectToSortedMap(merRes));
			logger.info(param + "--------->向商户后台发送的消息");
			String str = PayUtil.sendGet(url, param);
			if(str != null)
				logger.info("下游返回的信息>>>>>"+str);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	
	public static String sign(
			SortedMap<String, Object> packageParams, String API_KEY) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v)) {
				if(k.equals("orderAmount"))
					sb.append(k + "=" + v + "&");
				if(k.equals("out_trade_no"))
					sb.append("orderId=" + v + "&");
				
			}
		}
		sb.append("priKey=" + API_KEY);
		
		String sign = MD5Util.md5(sb.toString());
				
		System.out.println(sign);
		return sign;
	}
	
	
	
}
