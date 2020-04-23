package com.myd.service;

import java.util.Map;

import com.myd.entity.NpayDaifuRoutes;
import com.myd.entity.NpayMerFeeRates;

public interface NpayMerFeeRatesService {
	
	NpayMerFeeRates getNpayMerFeeRates(Map<String,Object> map);

	void insertSelective(NpayMerFeeRates npayMerFeeRates);

	void updateByPrimaryKeySelective(NpayMerFeeRates npayMerFeeRates);


}
