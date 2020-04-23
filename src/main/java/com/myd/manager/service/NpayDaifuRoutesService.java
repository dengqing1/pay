package com.myd.manager.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.myd.entity.NpayChannels;
import com.myd.entity.NpayDaifuRoutes;
import com.myd.entity.NpayTf56Bank;

public interface NpayDaifuRoutesService {

	List<NpayDaifuRoutes> selectByRoutes(NpayDaifuRoutes npayDaifuRoutes,Integer page);

	void insertSelective(NpayDaifuRoutes npayDaifuRoutes);

	
	List<NpayDaifuRoutes> selectByChartId(@Param(value="chartId") String chartId,@Param(value="money")Integer money);

	NpayDaifuRoutes selectByPrimary(NpayDaifuRoutes npayDaifuRoutes);

	List<NpayTf56Bank> selectByBank(NpayTf56Bank npayTf56Bank);


	List<NpayChannels> selectNpayDaifuRoutesById(String channelAbbr);

	Map<String, Object> selectDaifuRoutesByRates(NpayDaifuRoutes daifuRoutes);

	void updateByPrimaryKeySelective(NpayDaifuRoutes npayDaifuRoutes);
	
}
