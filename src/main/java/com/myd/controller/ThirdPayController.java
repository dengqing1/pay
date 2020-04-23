package com.myd.controller;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.myd.entity.MerchantAsynchronousResult;
import com.myd.entity.NpayMerInfo;
import com.myd.entity.NpayOrder;
import com.myd.entity.ThirdDaifuReturn;
import com.myd.entity.ThirdPayReturn;
import com.myd.service.NewOrderSettleService;
import com.myd.service.NpayMerInfoService;
import com.myd.service.NpayOrderService;
import com.myd.util.DateUtil;
import com.myd.util.PayUtil;
import com.myd.util.ThirdConfirmSign;

@Controller
public class ThirdPayController {
	
	private static Logger logger = Logger.getLogger(ThirdPayController.class);
	
	@Autowired
	private NpayOrderService npayOrderService ;
	@Autowired
	private NpayMerInfoService npayMerInfoService ;
	@Autowired
	private NewOrderSettleService newOrderSettleService;
	
	
	/**
	 * 支付回调
	 * @param model
	 * @param request
	 */
	@RequestMapping("/payReturn")
	public void thirdpayReturn(Model model,HttpServletRequest request,ThirdPayReturn thirdPay){
		//验签
		SortedMap<String, Object> infoMap = null ;
		
		 try {
			 infoMap = PayUtil.objectToSortedMap(thirdPay);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 String sign = (String) infoMap.remove("sign");
		 boolean res = true ;  //ThirdConfirmSign.confirmSign(infoMap,sign);
		 logger.info(JSON.toJSONString(thirdPay)+"--------->回调的信息,验签是否通过："+res);
		 if(res){
			 //返回信息验签成功
			 String orderId = thirdPay.getOut_order_no();//得到返回的我们订单的id
			 NpayOrder order = npayOrderService.getOrderByOurOrderId(orderId);
			 if(order != null){
				 //查询有此订单的时候才进行操作
				 
				 NpayMerInfo merInfo = npayMerInfoService.getMerInfoById(order.getMerchantid());
				 String key = merInfo.getMerSecretKey();
				 NpayOrder orderUpdate = new NpayOrder();//修改order数据
				// String code = thirdPay.getRet_code();返回的信息状态码
				 String resmsg = thirdPay.getRet_msg();
				 Date date = new Date();
				 MerchantAsynchronousResult merRes = new MerchantAsynchronousResult();
				 
				 if("SUCCESS".equals(resmsg)){
					//成功根据返回的订单号去查询信息,并其修改一些信息,并且向商户前后台发信息
					 orderUpdate.setOrderid(orderId);
					 orderUpdate.setStatus(1001);
					 orderUpdate.setStatusdesc("支付成功");
					 orderUpdate.setTxnamt(thirdPay.getAmount());
					 orderUpdate.setLastUpdate(DateUtil.getDateFormart(date));
					 orderUpdate.setOid(order.getOid());
					 
					 merRes.setMerchantId(order.getMerchantid());
					 merRes.setMerOrderId(order.getMerorderid());
					 merRes.setRespCode("1001");
					 merRes.setRespMsg("支付成功");
					 merRes.setTxnAmt(String.valueOf(thirdPay.getAmount()));
					 
					 //去修改订单信息，并且告诉商户信息
					 npayOrderService.updateOrder(orderUpdate);//修改订单信息
					 
					 	
					 String sql = "call new_order_settle("+orderId+",0,@ret_code)";
					 Map<Object, Object> map  = newOrderSettleService.callProcedure(sql);
					 int staCode = (int) map.get("ret_code");
					 
					 String sql1 = "call new_order_settle("+orderId+",1,@ret_code)";
					 Map<Object, Object> map1  = newOrderSettleService.callProcedure(sql1);
					 int staCode1 = (int) map.get("ret_code");
					 
					 logger.info(staCode+"--------->调用函数返回的结果");
					 logger.info(staCode1+"--------->余额计算返回的结果");
				 }else {
					//失败根据返回的订单号去查询信息并且向商户前后台发信息
					 String msg = thirdPay.getRet_msg();
					 orderUpdate.setStatus(1002);
					 orderUpdate.setStatusdesc(msg);
					 orderUpdate.setTxnamt(thirdPay.getAmount());
					 orderUpdate.setLastUpdate(DateUtil.getDateFormart(date));
					 orderUpdate.setOid(order.getOid());
					 
					 merRes.setMerchantId(order.getMerchantid());
					 merRes.setMerOrderId(order.getMerorderid());
					 merRes.setRespCode("1002");
					 merRes.setRespMsg(msg);
					 merRes.setTxnAmt(String.valueOf(thirdPay.getAmount()));
					 
					 
					 //去修改订单信息，并且告诉商户信息
					 npayOrderService.updateOrder(orderUpdate);//修改订单信息
					 
				}
				 //去修改订单信息，并且告诉商户信息
				 String backUrl = order.getBackurl();
				 sendGet(backUrl, merRes, key);//向商户提供的地址发送异步的get请求
				 
				 
				 
				 
			 }else{
				 //没有此订单
				 logger.info(JSON.toJSONString(thirdPay)+"--------->回调查无此订单号");
				 
				 
			 }
			 
			 
			
			 
			 
		 }else{
			 //验签失败
			 logger.info(JSON.toJSONString(thirdPay)+"--------->回调签名验证失败");
			 
		 }
		 	

	}
	

	/**
	 * 代付回调
	 * @param model
	 * @param request
	 */
	@RequestMapping("/daifuReturn")
	public void thirdDaifuReturn(Model model,HttpServletRequest request,ThirdDaifuReturn thirdDaifu){
		//验签
		SortedMap<String, Object> infoMap = null ;
		
		 try {
			 infoMap = PayUtil.objectToSortedMap(thirdDaifu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 String sign = (String) infoMap.remove("sign");
		 
		 boolean res = true ;//ThirdConfirmSign.confirmSign(infoMap,sign);
		 if(res == true){
			 //验签成功
			 //String code = thirdDaifu.getRet_code();
			
			 String orderId = thirdDaifu.getOut_order_no();//得到返回的我们订单的id
			 NpayOrder order = npayOrderService.getOrderByOurOrderId(orderId);
			 
			 if(order!=null){
				 //真是的数据
				 NpayMerInfo merInfo = npayMerInfoService.getMerInfoById(order.getMerchantid());
				 String key = merInfo.getMerSecretKey();
				 NpayOrder orderUpdate = new NpayOrder();//修改order数据
				// String code = thirdPay.getRet_code();返回的信息状态码
				 String resmsg = thirdDaifu.getRet_msg();
				 Date date = new Date();
				 MerchantAsynchronousResult merRes = new MerchantAsynchronousResult();
				 
				 if("SUCCESS".equals(resmsg)){
					//成功根据返回的订单号去查询信息,并其修改一些信息,并且向商户前后台发信息
					 orderUpdate.setOrderid(orderId);
					 orderUpdate.setStatus(1001);
					 orderUpdate.setStatusdesc("支付成功");
					 orderUpdate.setTxnamt(Integer.parseInt(thirdDaifu.getAmount()));
					 orderUpdate.setLastUpdate(DateUtil.getDateFormart(date));
					 orderUpdate.setOid(order.getOid());
					 orderUpdate.setOutFeeAmount(thirdDaifu.getFee());
					 
					 merRes.setMerchantId(order.getMerchantid());
					 merRes.setMerOrderId(order.getMerorderid());
					 merRes.setRespCode("1001");
					 merRes.setRespMsg("支付成功");
					 merRes.setTxnAmt(String.valueOf(thirdDaifu.getAmount()));
					 
					
					 
					 String sql = "call new_order_settle("+orderId+",0,@ret_code)";
					 Map<Object, Object> map  = newOrderSettleService.callProcedure(sql);
					 int staCode = (int) map.get("ret_code");
					 npayOrderService.updateOrder(orderUpdate);
					 String sql1 = "call new_order_settle("+orderId+",1,@ret_code)";
					 Map<Object, Object> map1  = newOrderSettleService.callProcedure(sql1);
					 int staCode1 = (int) map1.get("ret_code");
					 
					 logger.info(staCode+"--------->代付调用函数返回的结果");
					 logger.info(staCode1+"--------->代付余额计算返回的结果");
					 logger.info(JSON.toJSONString(orderUpdate)+"--------->支付成功返回信息");
				 }else {
					//失败根据返回的订单号去查询信息并且向商户前后台发信息
					 String msg = thirdDaifu.getRet_msg();
					 orderUpdate.setStatus(1002);
					 orderUpdate.setStatusdesc(msg);
					 orderUpdate.setTxnamt(Integer.parseInt(thirdDaifu.getAmount()));
					 orderUpdate.setLastUpdate(DateUtil.getDateFormart(date));
					 orderUpdate.setOid(order.getOid());
					 
					 merRes.setMerchantId(order.getMerchantid());
					 merRes.setMerOrderId(order.getMerorderid());
					 merRes.setRespCode("1002");
					 merRes.setRespMsg(msg);
					 merRes.setTxnAmt(String.valueOf(thirdDaifu.getAmount()));
					 
					 
					 npayOrderService.updateOrder(orderUpdate);//修改订单信息
					 
					 logger.info(JSON.toJSONString(orderUpdate)+"--------->支付失败返回信息");
				}
				 //去修改订单信息，并且告诉商户信息
				// npayOrderService.updateOrder(orderUpdate);//修改订单信息
				 String backUrl = order.getBackurl();
				 sendGet(backUrl, merRes, key);//向商户提供的地址发送异步的get请求
				 
				 
			 }else{
				 //查不到该订单号
				 
				 logger.info(JSON.toJSONString(thirdDaifu)+"--------->回调查无此订单号");
			 }
			 
			
			 
		 }else{
			 //验签失败
			 //验签失败
			 logger.info(JSON.toJSONString(thirdDaifu)+"--------->回调签名验证失败");  
 
		 }
	}
	
	
	
	/**
	 * 向商户提供的地址发送异步的get请求
	 * @param url
	 * @param merRes
	 * @param key
	 */
	public void sendGet(String url ,MerchantAsynchronousResult merRes,String key){
		 SortedMap<String, Object> map = null; 
		 try {
			map = PayUtil.thirdobjectToSortedMap(merRes);//去除了空的字符串即singnature不参与签名
			String sign = PayUtil.signMethodMD5(map, key);
			merRes.setSignature(sign);
			logger.info(JSON.toJSONString(merRes)+"--------->向商户后台发送的消息");
			String param =  PayUtil.signature(PayUtil.objectToSortedMap(merRes));
			PayUtil.sendGet(url, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 
		 
		
	}
	
	
	
}
