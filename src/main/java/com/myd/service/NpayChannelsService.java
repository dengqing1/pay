package com.myd.service;

import com.myd.entity.NpayChannels;

public interface NpayChannelsService {
	
	NpayChannels getChannels(String getway,String merabbr,String channelId);
	
	
	NpayChannels selectByPrimaryKey(String channelId);
	

}
