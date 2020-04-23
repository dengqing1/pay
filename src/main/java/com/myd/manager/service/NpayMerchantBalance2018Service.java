package com.myd.manager.service;

import java.util.List;

import com.myd.entity.NpayMerchantBalance2018;

public interface NpayMerchantBalance2018Service {

	NpayMerchantBalance2018 selectByPrimaryKey(String merchantid);

	NpayMerchantBalance2018 selectByExample(NpayMerchantBalance2018 balance);

	int insertSelective(NpayMerchantBalance2018 balance2018);

	int updateByPrimaryKey(NpayMerchantBalance2018 balance2018);
	
	int updateByPrimaryKeySelective(NpayMerchantBalance2018 balance2018);

	List<NpayMerchantBalance2018> selectByBalanceList(NpayMerchantBalance2018 balance);
	
	
	

}
