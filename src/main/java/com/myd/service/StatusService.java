package com.myd.service;


import com.myd.entity.NpayMerInfo;
import com.myd.entity.NpayMerchantBalance2018;
import com.myd.entity.NpayOrder;

public interface StatusService {
	
	public NpayOrder getOrderById(String merOrderId,String merchantId);
	
	public NpayMerInfo getMerInfoByMerId(String merchantId);
	
	public NpayMerchantBalance2018 getBanlanceByMerId(String merId) ;
}
