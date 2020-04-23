package com.myd.manager.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.myd.config.DynamicDataSource;
import com.myd.dao.NpayChannelsMapper;
import com.myd.entity.NpayChannels;
import com.myd.manager.service.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService {
	
	@Autowired
	private NpayChannelsMapper npayChannelsMapper;

	@Override
	public List<NpayChannels> selectByPrimary(NpayChannels npayChannels) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayChannelsMapper.selectByPrimary(npayChannels);
	}

	@Override
	public NpayChannels selectByPrimaryKey(String channelId) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayChannelsMapper.selectByPrimaryKey(channelId);
	}

	@Override
	public List<Map<String, Object>> selectByExamplechannel(Map<String, Object> map, Integer page) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		if(page==null){
			PageHelper.startPage(1, 10);
		}else{
			PageHelper.startPage(page, 10);
		}
		return npayChannelsMapper.selectByExamplechannel(map);
	}

	@Override
	public int updateByIsDelete(NpayChannels npayChannels) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayChannelsMapper.updateByIsDelete(npayChannels);
	}

	@Override
	public void insertSelective(NpayChannels channel) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		npayChannelsMapper.insert(channel);
	}

	@Override
	public void updateByPrimaryKeySelective(NpayChannels channel) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		npayChannelsMapper.updateByPrimaryKeySelective(channel);
	}

	@Override
	public List<NpayChannels> selectChannelAbbrById(String channelAbbr) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayChannelsMapper.selectChannelAbbrById(channelAbbr);
	}

	@Override
	public List<Map<String, Object>> selectByExampleReconciliation(Map<String, Object> map, Integer page) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		if(page==null){
			PageHelper.startPage(1, 10);
		}else{
			PageHelper.startPage(page, 10);
		}
		return npayChannelsMapper.selectByExampleReconciliation(map);
	}

	@Override
	public List<NpayChannels> selectByGateway(NpayChannels npayChannels) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayChannelsMapper.selectByGateway(npayChannels);
	}

}
