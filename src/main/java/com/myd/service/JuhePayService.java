package com.myd.service;

import java.util.SortedMap;

import com.myd.entity.NpayChannels;
import com.myd.entity.NpayOrder;
import com.myd.entity.NpayTf56Bank;

public interface JuhePayService{
	
	
	SortedMap<String, Object> juhePay(String gateway,NpayOrder order,NpayTf56Bank nBank,NpayChannels channels);
	
	
}
