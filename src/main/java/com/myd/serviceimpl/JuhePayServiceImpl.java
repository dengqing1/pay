package com.myd.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myd.entity.JuheDaifuDetailData;
import com.myd.entity.NpayChannels;
import com.myd.entity.NpayOrder;
import com.myd.entity.NpayTf56Bank;
import com.myd.entity.OrdersBank;
import com.myd.entity.OrdersDaifu;
import com.myd.service.JuhePayService;
import com.myd.service.NpayChannelsService;
import com.myd.util_wx.MD5Util;

import net.sf.json.JSONArray;

@Service
public class JuhePayServiceImpl implements JuhePayService {

	private static Logger logger = Logger.getLogger(JuhePayServiceImpl.class);
	@Autowired
	private NpayChannelsService npayChannelsService ;
	
	
	@Override
	public SortedMap<String, Object> juhePay(String gateway, NpayOrder order, NpayTf56Bank nBank,	NpayChannels channels) {

		if("bank".equals(gateway)){
			
			//网银支付
			 return getJuheParam(order,nBank,channels);

		}else if("daifu".equals(gateway)){

			//代付
			return  getJuheDaifuParam(order,nBank,channels);//银行名称
		
		
		}
		
		return new TreeMap<String, Object>() ;
		
		
	}
	
	
	

	
	public SortedMap<String, Object> getJuheParam(NpayOrder order, NpayTf56Bank bank,	NpayChannels channels){
	
		
		
		SortedMap<String, Object> map = new TreeMap<String, Object>();
		
		//支付渠道     网银支付：WY 		快捷支付：QUICK
		String payChannel=null;
		if("bank".equals(order.getGateway()))
			payChannel="WY";
		else
			payChannel="QUICK";
		
		//支付渠道     DC	借记卡		CC	贷记卡
		String cardType=null;
		if(0==order.getDctype())
			cardType="DC";
		else
			cardType="CC";
			
	
	  	map.put("merId", channels.getChannelMerId());   //商户id
		map.put("outTradeNo", order.getOrderid());   //商户订单号(我们自己生成的id)
		map.put("body", order.getBody());
		map.put("notifyUrl", channels.getNotifyurl());
		map.put("callbackUrl", order.getBackurl());
		map.put("totalFee", order.getTxnamt().toString());
		map.put("payChannel", payChannel);     // gateway   bank =wy
		map.put("bankNumber", bank.getzBankId());  //银行
		map.put("cardType", cardType);      //dctype    0  =   借记卡 DC
		map.put("nonceStr", System.currentTimeMillis()+"");
		
		// 签名
//		String sign = PayToolUtil.createSign("UTF-8", map, "020589C50A1E43EC86ECA40B6634CF82");
		String sign = createSign("UTF-8", map, channels.getChannelSecretKey());
		map.put("sign", sign);
		
		return map;
		
	}
	
	
	/**
	 * @author
	 * @date 2016-4-22
	 * @Description：sign签名
	 * @param characterEncoding
	 *            编码格式
	 * @param parameters
	 *            请求参数
	 * @return
	 */
	public static String createSign(String characterEncoding,
			SortedMap<String, Object> packageParams, String API_KEY) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + API_KEY);
		
		System.out.println("--------签名前数据-----》"+sb);
		String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding)
				.toUpperCase();
		System.out.println(sign);
		return sign;
	}
	
	
	
	
	
	
	public SortedMap<String, Object> getJuheDaifuParam(NpayOrder order ,NpayTf56Bank nBank,	NpayChannels channels){
		
		SortedMap<String, Object> map = new TreeMap<String, Object>();
		
		String receiverType=null;
		if("01".equals(order.getPpflag()))
			receiverType="PERSON";
		else//00
			receiverType="CORP";
		
		List<JuheDaifuDetailData> list = new ArrayList<JuheDaifuDetailData>();
		
		JuheDaifuDetailData data=new JuheDaifuDetailData();
		
		
		data.setOutOrderNo(order.getOrderid()); //
		data.setOrderName("提现");
		data.setReceiverCardNo(order.getAccno());   //银行卡号
			JSONObject o = JSON.parseObject(order.getCustomerinfo());
			String name = o.getString("customerNm");
		data.setReceiverName(name);            //姓名
		data.setAmount(order.getTxnamt()/100+"");
		data.setReceiverType(receiverType);
		data.setReceiverCurrency("CNY");
		data.setBankCode(nBank.getzBankId());
		data.setBankName(nBank.getBankName());
		
		
		
		list.add(data);
		

		
		//   商户号：   100660011       密钥：   B1C82F0A009E42399BCD81F0C35EC3E2
		map.put("merId", channels.getChannelMerId());  //由平台分配的商户号
		map.put("outBatchNo", System.currentTimeMillis()+"");   //批次号
		map.put("totalNum", "1");    //批量付款总笔数（至少1笔）   list.size()
		map.put("totalAmount", order.getTxnamt()/100+""); //单位：元   总金额
		map.put("detailData", getData(list));  //付款详细数据
		
		System.out.println("----"+getData(list));

		map.put("nonceStr",System.currentTimeMillis()+"");   //随机字符串
		
		String sign = createSign("UTF-8", map, channels.getChannelSecretKey());
		map.put("sign", sign);
		
		JSONArray json = JSONArray.fromObject(list);
		map.put("detailData", json );  //付款详细数据
//		map.put("detailData", "["+JSON.toJSONString(detailData)+"]" );  //付款详细数据
		
		
		
		return map;
	}
	
	
	
//	多笔代付      data参数拼接
	public String getData(List<JuheDaifuDetailData> params){
		
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < params.size(); i++) {
			sb.append( params.get(i) .getOutOrderNo()+ "|");
			sb.append( params.get(i) .getOrderName()+ "|");
			sb.append( params.get(i) .getReceiverCardNo()+ "|");
			sb.append( params.get(i) .getReceiverName()+ "|");
			sb.append( params.get(i) .getAmount()+ "|");
			sb.append( params.get(i) .getReceiverType()+ "|");
			sb.append( params.get(i) .getReceiverCurrency()+ "|");
			sb.append( params.get(i) .getBankCode()+ "|");
			sb.append( params.get(i) .getBankName());
			sb.append("#");
		}
		//便需要去除其最后一个字符
		sb.deleteCharAt(sb.length() - 1);
		
		return sb.toString();
	}
	
	
	
	
	
	
	

}
