package com.myd.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myd.dao.NpayChannelsMapper;
import com.myd.entity.NpayChannels;
import com.myd.service.NpayChannelsService;

@Service("npayChannelsService")
public class NpayChannelsServiceimpl implements NpayChannelsService{
	@Autowired
	private  NpayChannelsMapper npayChannelsDao ;

	@Override
	public NpayChannels getChannels(String getway,String merabbr,String channelId) {
		// TODO Auto-generated method stub
		return npayChannelsDao.getChannelsBygetwayandmerabbr(getway, merabbr, channelId);
	}

	@Override
	public NpayChannels selectByPrimaryKey(String channelId) {
		// TODO Auto-generated method stub
		return npayChannelsDao.selectByPrimaryKey(channelId);
	}

}
