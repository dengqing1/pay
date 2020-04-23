package com.myd.manager.service;

import java.util.List;
import java.util.Map;

import com.myd.entity.NpayChannels;
import com.myd.entity.NpayInRoutes;
import com.myd.entity.NpayMerFeeRates;
import com.myd.entity.NpayTf56Bank;

public interface NpayInRoutesService {

	List<NpayMerFeeRates> selectByRoutes(NpayInRoutes npayInRoutes,Integer page);

	void insertSelective(NpayInRoutes npayInRoutes);

	List<NpayTf56Bank> selectByBank(NpayTf56Bank npayTf56Bank);

	NpayInRoutes selectByPrimary(NpayInRoutes npayInRoutes);

	List<NpayChannels> selectNpayInRoutesById(String channelAbbr);


	void updateByPrimaryKeySelective(NpayInRoutes npayInRoutes);

	Map<String, Object> selectRoutesByRates(String merchantId,String channelAbbr, String channelMerId,String routesGateway,String routesCardType);

}
