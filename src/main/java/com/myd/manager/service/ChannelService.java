package com.myd.manager.service;

import java.util.List;
import java.util.Map;

import com.myd.entity.NpayChannels;

public interface ChannelService {

	List<NpayChannels> selectByPrimary(NpayChannels npayChannels);

	NpayChannels selectByPrimaryKey(String channelId);

	List<Map<String, Object>> selectByExamplechannel(Map<String, Object> map,
			Integer page);

	int updateByIsDelete(NpayChannels npayChannels);

	void insertSelective(NpayChannels channel);

	void updateByPrimaryKeySelective(NpayChannels channel);

	List<NpayChannels> selectChannelAbbrById(String channelAbbr);

	List<Map<String, Object>> selectByExampleReconciliation(Map<String, Object> map, Integer page);


	List<NpayChannels> selectByGateway(NpayChannels npayChannels);

}
