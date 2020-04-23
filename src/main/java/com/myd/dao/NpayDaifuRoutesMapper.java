package com.myd.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.myd.entity.NpayChannels;
import com.myd.entity.NpayDaifuRoutes;
import com.myd.entity.NpayInRoutes;

public interface NpayDaifuRoutesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NpayDaifuRoutes record);

    int insertSelective(NpayDaifuRoutes record);

    NpayDaifuRoutes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NpayDaifuRoutes record);

    int updateByPrimaryKeyWithBLOBs(NpayDaifuRoutes record);

    int updateByPrimaryKey(NpayDaifuRoutes record);

	List<NpayDaifuRoutes> selectByRoutes(NpayDaifuRoutes npayDaifuRoutes);

	List<NpayDaifuRoutes> selectByChartId(@Param(value="chartId") String chartId,@Param(value="money")Integer money);


	NpayDaifuRoutes selectByPrimary(NpayDaifuRoutes npayDaifuRoutes);

	List<NpayChannels> selectNpayDaifuRoutesById(String channelAbbr);

	Map<String, Object> selectDaifuRoutesByRates(NpayDaifuRoutes daifuRoutes);

}