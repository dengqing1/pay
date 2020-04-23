package com.myd.service;

import org.apache.ibatis.annotations.Param;

import com.myd.entity.NpayDaifuRoutes;

public interface NpayDaifuRoutesService {

	
	NpayDaifuRoutes selectByChartId(@Param(value="chartId") String chartId,@Param(value="money")Integer money);
}
