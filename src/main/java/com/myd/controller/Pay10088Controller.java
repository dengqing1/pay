package com.myd.controller;


import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myd.entity.MerchantAsynchronousResult;
import com.myd.entity.NpayMerInfo;
import com.myd.entity.NpayMerchantBalance2018;
import com.myd.entity.NpayOrder;
import com.myd.entity.NpayTf56Bank;
import com.myd.entity.OrdersBank;
import com.myd.entity.OrdersDaifu;
import com.myd.entity.Pay10088Result;
import com.myd.entity.RspMsg;
import com.myd.manager.service.NpayMerchantBalance2018Service;
import com.myd.service.JuhePayService;
import com.myd.service.NewOrderSettleService;
import com.myd.service.NpayMerInfoService;
import com.myd.service.NpayMerchantBalanceService;
import com.myd.service.NpayOrderService;
import com.myd.service.NpayTf56BankService;
import com.myd.service.PayService;
import com.myd.util.DateUtil;
import com.myd.util.EntityIsNullUtil;
import com.myd.util.KLTUtil;
import com.myd.util.PayUtil;
import com.myd.util_wx.MD5Util;

/**
 * 聚合下单
 * 
 */
@Controller
public class Pay10088Controller {
	private static Logger logger = Logger.getLogger(Pay10088Controller.class);
	@Autowired
	private NpayMerInfoService npayMerInfoService;
	@Autowired
	private PayService payService;
	@Autowired
	private NpayTf56BankService napyTf56BankService;
	@Autowired
	private NpayOrderService npayOrderService ;
	@Autowired
	private NpayMerchantBalanceService npayMerchantBalanceService ;
	
	
	
	@Autowired
	private JuhePayService juhePayService;
	
	@Autowired
	private NewOrderSettleService newOrderSettleService;
	
	@Autowired
	private NpayMerchantBalance2018Service    npayMerchantBalance2018Service;
	
	
	
	/*
	 * 实际上是post请求（后期改过来）
	 */
	@RequestMapping(value = "/pay10", method = RequestMethod.POST)
	public void pay(OrdersBank orderInfo, OrdersDaifu orderInfoDaifu ,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		logger.info("传来的信息>>"+JSON.toJSONString(orderInfoDaifu));
		RspMsg pesMsg = new RspMsg();
		pesMsg.setTimestamp(DateUtil.getNowTimeStamp());
		String getway = orderInfo.getGateway();// 得到网关是bank还是daifu
		String merId = orderInfo.getMerchantId();// 商户的id
		
		NpayMerInfo npayMerInfo  = npayMerInfoService.getMerInfoById(merId);
		String key = null;
		if(npayMerInfo!=null){
			key = npayMerInfo.getMerSecretKey();
		}
		
		
		
		//根据网关判断有没有不能为空的字符串
		if(getway == null){
			pesMsg.setMsg("网关错误");
			jsonString(pesMsg, key, response);
			return;
			
		}else if("bank".equals(getway)){
			
			List<String> list = EntityIsNullUtil.checkObjFieldIsNull(orderInfo);
			if(!isRightFiled(list,"bank")){
				pesMsg.setMsg("必须传的字符串不能为空");
				jsonString(pesMsg, key, response);
				return;
				
			}
			
			
		}else if("daifu".equals(getway)){
			List<String> list = EntityIsNullUtil.checkObjFieldIsNull(orderInfoDaifu);
			logger.info(">>>daifu传来的空的list"+list);
			if(!isRightFiled(list,"daifu")){
				pesMsg.setMsg("必须传的字符串不能为空");
				jsonString(pesMsg, key, response);
				return;
				
			}
			
			
		}
		else{
			
			pesMsg.setMsg("网关错误");
			jsonString(pesMsg, key, response);
			return;
		}
		
		
//		参数验证     
		
		String money = orderInfo.getTxnAmt();
		try{
			Integer.parseInt(money);
		}catch (Exception e) {
			pesMsg.setMsg("金额只能为整数");
			jsonString(pesMsg, key, response);
			return;
		}
		if(money.length()>=10){
			pesMsg.setMsg("金额最大为9位整数");
			jsonString(pesMsg, key, response);
			return;
		}
		
		
		
		
		//查找订单号存不存在
		NpayOrder orderTemp = npayOrderService.getOrderByMerChantId(orderInfo.getMerOrderId());
		if(orderTemp!=null){
			pesMsg.setMsg("该订单号已存在");
			jsonString(pesMsg, key, response);
			return;
		}
		NpayTf56Bank nBank = null ;
		if(!"kuaijie".equals(getway)){
			 nBank = napyTf56BankService.getBankByBankId(orderInfo.getBankId());//得到银行
			if(nBank == null){
				//没有该银行
				pesMsg.setMsg("没有可用银行");
				jsonString(pesMsg, key, response);
				return;
			}
		}
		
		
		// 商户存在
		// 有该商户，正常判断
		
		 if ("bank".equals(getway.trim())) {
			 
			 if(0!= orderInfo.getDcType()){
				pesMsg.setMsg("借贷类型必须为0");
				jsonString(pesMsg, key, response);
				return;
			 }
			NpayOrder order = new NpayOrder();
			int res = payService.addOrder(order, orderInfo);//添加订单
			if(res ==  200){

//				
//				System.out.println(">>>>>>"+order.getChannelMerAbbr());
//				
//				Map pay =Pay10088Util.Pay10088("bank",orderInfo, order, nBank);
//				
//					
//					System.out.println(getContent(pay));
//					
//				String str=	PayUtil.sendPost("http://47.92.38.215/Pay_Index.html", getContent(pay));
////				writeurl(str, response);
//				  System.out.println(str);
//				Pay10088Util.jsonString(str, response);
//				
				
			}else{
				//生成订单的时候有错误
				
				pesMsg.setMsg("金额不正确");
				jsonString(pesMsg, key, response);
				return;
			}
			
			
			

		} else if ("daifu".equals(getway)) {
			if(!"CNY".equals(orderInfoDaifu.getCurrency())){
				
				pesMsg.setMsg("货币类型错误,只支持(CNY)");
				jsonString(pesMsg, key, response);
				return;
			}
			
			
			int txnmon = Integer.parseInt(orderInfoDaifu.getTxnAmt());
			NpayMerchantBalance2018  balance = npayMerchantBalanceService.getBanlaceById(orderInfoDaifu.getMerchantId());
			if(balance == null || (balance.getBalanceAvailable()<txnmon )){
				pesMsg.setMsg("余额不足,请充值");
				jsonString(pesMsg, key, response);
				return;
				
			}
			
			
			
			
			NpayOrder order = new NpayOrder();
			int res = payService.addOrder(order, orderInfoDaifu);//添加订单
			if(res == 200){//生成订单成功
				
				
				Map<String, Object> payJuheDaifu = juhePayService.juhePay("daifu", order, nBank,null);//第三方的支付
				
//				System.out.println(getContent(payJuheDaifu));
				
			String  json=PayUtil.sendPost("http://pay.100off.cn/substitute/batchWithdraw", getContent(payJuheDaifu));

			
			System.out.println("=======>代付响应信息"+json);
			
				if(json.contains("success")){
					
					 //计算本次交易余额(除去费率)   订单金额-费率金额     分 
					BigDecimal txnAmts = new BigDecimal(order.getTxnamt());
					BigDecimal infee = new BigDecimal(order.getInFee());
					BigDecimal l1 = txnAmts.subtract(infee).stripTrailingZeros();
						
					 
//					 merRes.setTxnAmt(l1.toPlainString());
					 
//					查询原来的金额
					 NpayMerchantBalance2018 balance2018 = npayMerchantBalance2018Service.selectByPrimaryKey(order.getMerchantid());
					//new BigDecimal( order.getTxnAmts(),order.getInFeeAmount());
					 
//					本次交易加  账户余额
					String l2 = new BigDecimal(balance2018.getBalance()).add(l1).stripTrailingZeros().toPlainString();
					 
//					本次交易加  可用余额
					String l3 = new BigDecimal(balance2018.getBalanceAvailable()).add(l1).stripTrailingZeros().toPlainString();
					
					
					NpayMerchantBalance2018   merchant2018=new NpayMerchantBalance2018();
					merchant2018.setMerchantid(order.getMerchantid());
					merchant2018.setBalance(Long.parseLong(l2));
					merchant2018.setBalanceAvailable(Long.parseLong(l3));
					
//					修改账户余额
					npayMerchantBalance2018Service.updateByPrimaryKeySelective(merchant2018);
					
					
					
					//请求成功
					pesMsg.setSuccess(1);
					pesMsg.setCode("1001");
					pesMsg.setMsg("支付成功");
					jsonString(pesMsg, key, response);
					return;
				}else{
					//请求失败
//					拿出返回json  中某个字段响应出去
					JSONObject o = JSONObject.parseObject(json);
					String msg = o.get("message").toString();
					pesMsg.setSuccess(0);
					pesMsg.setCode("1002");
					pesMsg.setMsg(msg);
					
					jsonString(pesMsg, key, response);
					return;
				}
					
				
			}else{
				//生成订单的时候有错误
				pesMsg.setMsg("金额不正确");
				jsonString(pesMsg, key, response);
				return;
				
			}
			

		}else{
			//其他支付
			
			
		}

	}
	
	
	

	
	
		/**
		 *  网银回调
		 *   
		 * 
		 * 
		 */
//		@RequestMapping("/juheBanks10")
		public void  juheBankNotify(Pay10088Result pay,HttpServletResponse response){
			logger.info("聚合支付回调"+pay);
			
			write("OK",response);
			
			// 验签
			boolean flag = getVerfySign(pay , "225h3hy5dn8hzqgm8lcpe8kajlngn0bi" ,pay.getSign());
			if(!flag){
				logger.info("支付回调验签不通过"+pay);
				//验签不通过
				return;
			}
			
			
			// 验签通过后取商户订单号
			String oid = pay.getOrderid();
			// 拿商户订单好取订单信息
			NpayOrder order = npayOrderService.getOrderByOurOrderId(oid);
			if (order == null) {
				// 没有该订单
				return;
			}
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
				
				 //计算本次交易余额(除去费率)   订单金额-费率金额
				String l1 = new BigDecimal(order.getTxnamt()).subtract(new BigDecimal(order.getInFee())).stripTrailingZeros().toPlainString();
				
				orderUpdate.setTxnamt(Integer.parseInt(l1));
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
		
	
		
		
		
		
	
		public void write(String str,HttpServletResponse response){
			PrintWriter out  = null;
			response.setContentType("application/json;charset=UTF-8");
		    response.setHeader("Pragma", "No-cache");
		    response.setHeader("Cache-Control", "no-cache");
		    response.setDateHeader("Expires", 0);
	        try {
				out = response.getWriter();
//				out.write(JSON.toJSONString(str));
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
	            if(value == null || "".equals(value)||"attach".equals(key) || "null".equals(value)|| "sign".equals(value)){
	            	
	            }else{
	            	 sortedMap.put(key, value);
	            }
	           
	        }
	        logger.info("验签的map"+sortedMap);
	     
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
				logger.info(JSON.toJSONString(merRes) + "--------->向商户后台发送的消息");
				String param = PayUtil.signature2(PayUtil.objectToSortedMap(merRes));
				PayUtil.sendGet(url, param);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		
		
		
		
//		Sorted
		 public static String getContent(Map<String, Object> parameters) {
		        StringBuffer buffer = new StringBuffer();
		        Set<Entry<String, Object>> es = parameters.entrySet();
		        Iterator<Entry<String, Object>> it = es.iterator();
		        while (it.hasNext()) {
		            Entry<String, Object> entry = it.next();
		            String k = (String) entry.getKey();
		            Object v = entry.getValue();
		            buffer.append(k).append("=");
		            if(it.hasNext())
		            	buffer.append(v).append("&");
		            else
		            	buffer.append(v);
		        }
		     //   buffer.append("key="+key);
		        return buffer.toString();
		    }
		
		
		
		
		
		//-----------------------------------------------------------------------------------
		
		
		
		public void jsonString(RspMsg resMsg,String key,HttpServletResponse response){
			
			if(key == null){
				
				
			}else{
				SortedMap<String, Object> map = null ;
				try {
					map = PayUtil.objectToSortedMap(resMsg);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String str = PayUtil.signMethod(map, key);
				resMsg.setSignature(str);
				
			}
			
			PrintWriter out  = null;
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
			}finally{
				out.close();
			}
	    	
			
			
			
			
		}
		
	public void writeurl(String str ,HttpServletResponse response){

			PrintWriter out  = null;
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
			}finally{
				out.close();
			}
	    	
			
			
			
			
		}
		
		
		public void returnRes(int res,String key,HttpServletResponse response,RspMsg pesMsg){
			
			if (res == 0) {
				// 必传的字符串为空了
				pesMsg.setMsg("必须传的签名信息不能为空");
				jsonString(pesMsg,key,response);

			} else if (res == 1) {
				// url有非法字符
				pesMsg.setMsg("url有非法字符");
				jsonString(pesMsg,key,response);

			} else if (res == 2) {
				// 签名信息失败
				pesMsg.setMsg("签名验证失败");
				jsonString(pesMsg,key,response);

			} else if (res == 3) {

				// 没有该商户
				pesMsg.setMsg("无效商户");
				jsonString(pesMsg,null,response);

			} else {
				// 未知错误
				pesMsg.setMsg("未知错误");
				jsonString(pesMsg,key,response);

			}
			
			
		}
		
		
		
		/**
		 * 判断是不是指定可以不传的字符串
		 * @param list
		 * @return
		 */
		public boolean isRightFiled(List<String> list,String getway){
			if(list == null){
				//没有直接返回true
				return true ;
			} 
			if("bank".equals(getway.trim())){

				if(list.size() >= 0 && list.size() <= 2){
					list.remove("userId") ;
					list.remove("customerIp");
					//如果还有的话就返回false
					if(list.size() == 0){
						return true ;
					}
				}
			}else if("daifu".equals(getway.trim())){
				
				if(list.size() >= 0 && list.size() <= 1){
					list.remove("purpose") ;
					//如果还有的话就返回false
					if(list.size() == 0){
						return true ;
					}
				}
				
				
			}
			
			
			return false;
			
		}
		
		
		
		/**
		 * 判断是不是指定可以不传的字符串
		 * @param list
		 * @return
		 */
		public boolean isRightFiledKuaijie(List<String> list,int type){
			if(list == null){
				//没有直接返回true
				return true ;
			} 
			if(0 == type){
				//信用卡
				if(list.size() >= 0 && list.size() <= 2){
					list.remove("cvv2") ;
					list.remove("acctValidDate");
					//如果还有的话就返回false
					if(list.size() == 0){
						return true ;
					}
				}
			}else {
				
				if(list.size() == 0){
					return true ;
				}
				
				
			}
			
			
			return false;
			
		}
		
		
		
		
		
	
	
	

}
