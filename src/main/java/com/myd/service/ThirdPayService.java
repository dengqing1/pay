package com.myd.service;

import com.myd.entity.NpayOrder;
import com.myd.entity.NpayTf56Bank;
import com.myd.entity.OrdersBank;
import com.myd.entity.OrdersDaifu;
import com.myd.entity.ReqPars;

public interface ThirdPayService{
	String  thirdPay(String gateway,Object obj,NpayOrder order,NpayTf56Bank nBank);
	ReqPars getPayPar(OrdersBank obank,NpayOrder order,NpayTf56Bank oBank);
	ReqPars getDaifuPar(OrdersDaifu odaifu,NpayOrder order,NpayTf56Bank oBank);
	String KLTKJPay(NpayOrder order);
	String kjConfirm(NpayOrder order, String smsCode,String reqId);
	
}
