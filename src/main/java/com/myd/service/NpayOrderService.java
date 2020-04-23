package com.myd.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;

import com.myd.entity.NpayOrder;
import com.myd.entity.NpayTf56Bank;

public interface NpayOrderService {

	int addOrder(NpayOrder payOrder);
	
	int insert(NpayOrder payOrder);
	
	List<NpayOrder> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
    NpayOrder selectByOrderId(NpayOrder record);
	
	NpayOrder getOrderByOurOrderId(String orderId);
	
	NpayOrder getMerorderid(String merorderid);
	
	int updateOrder(NpayOrder npayOrder);
	
	List<NpayTf56Bank> selectByBank();
	
	NpayTf56Bank getBank(String bankName);
	
	void export(HttpServletResponse response,Map<String, Object> params) throws Exception;

	void exportWithdraw(HttpServletResponse response, Map<String, Object> params) throws Exception;

	NpayOrder getOrderByMerChantId(String merOrderId);

	NpayOrder getOrderByMerChantIdandMerOId(String merId, String merOid);
	
//	@Param(value="chartId")
	Integer selectSumTxnAmt(String channelMerAbbr, String merchantid);
	
}
