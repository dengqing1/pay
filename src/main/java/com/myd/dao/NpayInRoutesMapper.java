package com.myd.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.myd.entity.NpayChannels;
import com.myd.entity.NpayInRoutes;
import com.myd.entity.NpayMerFeeRates;

public interface NpayInRoutesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NpayInRoutes record);

    int insertSelective(NpayInRoutes record);

    NpayInRoutes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NpayInRoutes record);

    int updateByPrimaryKeyWithBLOBs(NpayInRoutes record);

    int updateByPrimaryKey(NpayInRoutes record);
    
    List<NpayInRoutes> selectByChartId(@Param(value="chartId") String chartId,@Param(value="money")Integer money);

	List<NpayMerFeeRates> selectByRoutes(NpayInRoutes npayInRoutes);

	NpayInRoutes selectByPrimary(NpayInRoutes npayInRoutes);

	List<NpayChannels> selectNpayInRoutesById(String channelAbbr);


	Map<String, Object> selectRoutesByRates(@Param("merchantId") String merchantId,@Param("channelAbbr") String channelAbbr, 
			@Param("channelMerId")String channelMerId, @Param("routesGateway")String routesGateway,@Param("routesCardType") String routesCardType);
    
}