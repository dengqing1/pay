package com.myd.manager.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.myd.controller.JuhePayReturnController;
import com.myd.entity.MerchantAsynchronousResult;
import com.myd.entity.NpayChannels;
import com.myd.entity.NpayMerInfo;
import com.myd.entity.NpayOrder;
import com.myd.entity.NpayTf56Bank;
import com.myd.manager.service.ChannelService;
import com.myd.manager.service.NpayOrderService;
import com.myd.service.NpayMerInfoService;
import com.myd.util.CommonExcel;
import com.myd.util.Msg;
import com.myd.util.PayUtil;
import com.myd.util.R;
import com.myd.util.UUIDUtils;

@Controller
@RequestMapping("/channel")
public class ChannelController {
	

	private static Logger logger = Logger.getLogger(ChannelController.class);
	
	@Autowired
	private ChannelService channelService;
	@Autowired
	private NpayMerInfoService npayMerInfoService;
	
	@Autowired
	private NpayOrderService npayOrderService;
	
	@Autowired
	private com.myd.service.NpayOrderService npayOrderServiceImpl ;
	
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String login(String channelId,HttpServletRequest request){
		if(channelId!=null){
			NpayChannels channel = channelService.selectByPrimaryKey(channelId);
			request.setAttribute("channel", channel);
		}
		return "channel";
		
	}
	
	
	@RequestMapping(value = "/findChannelAll", method = {RequestMethod.POST,RequestMethod.GET})
	public String findChannelAll(String gateway,String channelMerAbbr,HttpServletRequest request,HttpServletResponse response,Integer page) throws UnsupportedEncodingException{
		Map<String,Object> map = new HashMap<String,Object>();	
		map.put("gateway", gateway);
		map.put("channelMerAbbr", channelMerAbbr);
		List<Map<String,Object>> list = channelService.selectByExamplechannel(map,page);
		request.setAttribute("list", list);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("gateway", gateway);
		request.setAttribute("channelMerAbbr", channelMerAbbr);
		return "channel_list";
		
	}
	
	/**
	 * 删除
	 * @param channelId
	 * @return
	 */
	@RequestMapping(value = "/deleteChannel", method = {RequestMethod.POST})
	@ResponseBody
	public Msg deleteChannel(String channelId){
		NpayChannels npayChannels = new NpayChannels();
		npayChannels.setChannelId(channelId);
		npayChannels.setIsDelete(1);
		int channels =  channelService.updateByIsDelete(npayChannels);
		return Msg.success();
		
	}
	
	
	/**
	 * 交易查询
	 * @param createStateAt
	 * @param createEndAt
	 * @param merchantId
	 * @param orderId
	 * @param merOrderId
	 * @param abbr
	 * @param txnAmt
	 * @param bank
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/orderQuery", method = RequestMethod.GET)
	public String orderQuery(String createStateAt,String createEndAt,String merchantId,
			String orderId,String merOrderId,String abbr,String txnAmt,String bank,String status,
			String gateway,@RequestParam(value="page",defaultValue="1") String page,HttpServletRequest request){
		String newTxnAmt = null;
//		if(txnAmt != null && txnAmt!= "")
//			newTxnAmt = (Integer.parseInt(txnAmt) * 100 )+ "" ;
//			newTxnAmt =  new BigDecimal(txnAmt).multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString();
			newTxnAmt = txnAmt ;
		int a=Integer.parseInt(page);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("merchantId", merchantId);
		map.put("orderId", orderId);
		map.put("merOrderId", merOrderId);
		map.put("channelMerAbbr", abbr);
		map.put("txnAmt", newTxnAmt);
		map.put("bankName", bank);
		map.put("createStateAt", createStateAt);
		map.put("createEndAt", createEndAt);
		map.put("gateway", gateway);
		map.put("status", status);
		List<Map<String,Object>> map1 = npayOrderService.selectByExampleOrder(map,a);
		request.setAttribute("list", map1);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(map1);
		request.setAttribute("pageInfo", pageInfo);
		
		request.setAttribute("merchantId", merchantId);
		request.setAttribute("orderId", orderId);
		request.setAttribute("merOrderId", merOrderId);
		request.setAttribute("abbr", abbr);
		request.setAttribute("txnAmt", txnAmt);
		request.setAttribute("bank", bank);
		request.setAttribute("createStateAt", createStateAt);
		request.setAttribute("createEndAt", createEndAt);
		request.setAttribute("gateway", gateway);
		request.setAttribute("status", status);
		
		//页面显示所有银行
		NpayTf56Bank npayTf56Bank = new NpayTf56Bank();
		List<NpayTf56Bank> Banklist = npayOrderService.selectByBank(npayTf56Bank);
		request.setAttribute("Banklist", Banklist);
		
		//查询渠道表
		NpayChannels npayChannels = new NpayChannels();
		List<NpayChannels> npayChannelslist = channelService.selectByPrimary(npayChannels);
		request.setAttribute("npayChannelslist", npayChannelslist);
		return "orderQuery";
		
	}
	
	
	
	/**
	 * 交易查询导出
	 * @param request
	 * @param response
	 */
	/*@RequestMapping(value = "/export", method = {RequestMethod.POST,RequestMethod.GET})
	public void export(HttpServletRequest request, HttpServletResponse response){
		
		String title = "交易列表";
		String[] rowsName = new String[]{"订单日期","平台订单号","商户号","商户名称","商户订单号","订单金额(元)","手续费额(元)","产品类型","银行",    
		"通道简称","通道名","状态","通道状态码","状态详情"};
		List<Object[]> dataList = new ArrayList<Object[]>();
		NpayChannels npayChannels = new NpayChannels();
		List<Map<String,Object>> list = npayOrderService.selectByPrimary(npayChannels);
		for (int i = 0; i < list.size(); i++) {
	     	Object[] objects = new Object[rowsName.length];
	     	Map<String, Object> channels = list.get(i);
	     	
	     	objects[0] = StringUtils.isBlank(channels.create_at) ? "/" : merInfo.getMerId();
	     	objects[1] = StringUtils.isBlank(merInfo.getMerShortName()) ? "/" : merInfo.getMerShortName();
	     	objects[2] = StringUtils.isBlank(merInfo.getOpenProductIds()) ? "/" : merInfo.getOpenProductIds();
	     	objects[3] = StringUtils.isBlank(String.valueOf(merInfo.getMerInfoUpdateTime()) ) ? "/" : String.valueOf(merInfo.getMerInfoUpdateTime());
	     	objects[4] = StringUtils.isBlank(String.valueOf(merInfo.getMerRiskLevel()) ) ? "/" : String.valueOf(merInfo.getMerRiskLevel());
	     	objects[5] = StringUtils.isBlank(String.valueOf(merInfo.getMerCheckStatus()) ) ? "/" : String.valueOf(merInfo.getMerCheckStatus());
	     	objects[6] = StringUtils.isBlank(String.valueOf(merInfo.getMerInfoCheckTime()) ) ? "/" : String.valueOf(merInfo.getMerInfoCheckTime());
	     	objects[7] = StringUtils.isBlank(String.valueOf(merInfo.getMerOpenStatus()) ) ? "/" : String.valueOf(merInfo.getMerOpenStatus());
	     	objects[8] = StringUtils.isBlank(String.valueOf(merInfo.getMerOpenTime()) ) ? "/" : String.valueOf(merInfo.getMerOpenTime());
	    	dataList.add(objects);
	    }
		String fileName="交易报表-"+ new SimpleDateFormat("yyyy-MM-dd HH:mm").format((new Date()))+ ".xlsx";
		CommonExcel ex = new CommonExcel(title, rowsName, dataList, response, fileName);
	  	ex.downloadExcel();
		
		
	}*/
	
	
	
	/**
	 * 通道保存
	 * @param channel
	 * @return
	 */
	@RequestMapping(value = "/savechannelchants", method = {RequestMethod.POST})
	@ResponseBody
	public Msg savechannelchants(String channelId,NpayChannels channel){
		if(channelId == ""){
			channel.setIsDelete(0);
			channel.setCreateTime(new Long(System.currentTimeMillis() / 1000).intValue());
			channel.setUpdateTime(new Long(System.currentTimeMillis() / 1000).intValue());
			channel.setChannelId(UUIDUtils.getUUID());
			channelService.insertSelective(channel);
		}else{
			channelService.updateByPrimaryKeySelective(channel);
		}
		return Msg.success();
		
	}

	
	
	/**
	 * 对账上游余额查询列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/reconciliationlist", method = RequestMethod.GET)
	public String reconciliationlist(String merId,String merShortName,Integer page,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();	
		map.put("merId", request.getParameter("merId"));
		map.put("merShortName", request.getParameter("merShortName"));
		List<Map<String,Object>> list = channelService.selectByExampleReconciliation(map,page);
	
		
		request.setAttribute("list", list);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("merId", merId);
		request.setAttribute("merShortName", merShortName);
		
		return "reconciliation_list";
		
	}
	
	
	
	/**
	 * 补发回调
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/orderCallback", method = RequestMethod.GET)
	public String orderCallback(@RequestParam(value="page",defaultValue="1") String page,HttpServletRequest request){
		
		int a=Integer.parseInt(page);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("merchantId", request.getParameter("merchantId"));
		map.put("orderId", request.getParameter("orderId"));
		map.put("merOrderId", request.getParameter("merOrderId"));
		map.put("gateway", "bank");
		map.put("status", "1001");
		List<Map<String,Object>> map1 = npayOrderService.selectByExampleOrder(map,a);
		request.setAttribute("list", map1);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(map1);
		request.setAttribute("pageInfo", pageInfo);
		
		request.setAttribute("merchantId", request.getParameter("merchantId"));
		request.setAttribute("orderId", request.getParameter("orderId"));
		request.setAttribute("merOrderId", request.getParameter("merOrderId"));
		
		return "orderCallback";
		
	}
	
	
	/**
	 * 补发回调
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/callback")
	@ResponseBody
	public R callback(String merOrderId,HttpServletRequest request){
		
		
		// 拿商户订单好取订单信息
		NpayOrder order = npayOrderServiceImpl.getMerorderid(merOrderId);
		if (order == null) {
			// 没有该订单
			R.error("没有该订单");
		}
		
		
		
		NpayMerInfo merInfo = npayMerInfoService.getMerInfoById(order.getMerchantid());
		String key = merInfo.getMerSecretKey();
		
		
	
		
//		回调下游
		MerchantAsynchronousResult merRes =new MerchantAsynchronousResult();
		merRes.setMerchantId(order.getMerchantid());
		merRes.setMerOrderId(order.getMerorderid());
		merRes.setRespCode("1001");
		merRes.setRespMsg("支付成功");
		merRes.setTxnAmt(order.getTxnamt().toString());
		
		
		SortedMap<String, Object> map = null;
		
		try {
			map = PayUtil.thirdobjectToSortedMap(merRes);// 去除了空的字符串即singnature不参与签名
			String sign = PayUtil.signMethod(map, key);
			//转意 特殊字符+
			sign = URLEncoder.encode(sign, "UTF-8");
			 logger.info("向商户发送信息时候的签名："+sign);
			merRes.setSignature(sign);
//			拼接参数是处理中文乱码
			String param = PayUtil.signature2(PayUtil.objectToSortedMap(merRes));
			logger.info("下游返回的数据："+PayUtil.sendGet(order.getBackurl(), param));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		

		return R.ok("回调成功");
	}
	
	
}
