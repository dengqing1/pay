package com.myd.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.myd.entity.NpayChannels;

public interface NpayChannelsMapper {
    int deleteByPrimaryKey(String channelId);

    

    int updateByPrimaryKey(NpayChannels record);



	List<NpayChannels> selectByPrimary(NpayChannels npayChannels);



	NpayChannels selectByPrimaryKey(String channelId);



	List<Map<String, Object>> selectByExamplechannel(Map<String, Object> map);



	int updateByIsDelete(NpayChannels npayChannels);



	void insertSelective(NpayChannels channel);



	void updateByPrimaryKeySelective(NpayChannels channel);
	
	NpayChannels getChannelsBygetwayandmerabbr(@Param(value="getway")String getway,@Param(value="merabbr")String nerabbr,@Param(value="channelId")String channelId);


	List<NpayChannels> selectChannelAbbrById(String channelAbbr);

	List<Map<String, Object>> selectByExampleReconciliation(Map<String, Object> map);


	void insert(NpayChannels channel);



	List<NpayChannels> selectByGateway(NpayChannels npayChannels);
	
}