package com.myd.manager.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myd.config.DynamicDataSource;
import com.myd.dao.NpayInRoutersHistoryMapper;
import com.myd.entity.NpayInRoutersHistory;
import com.myd.manager.service.NpayInRoutersHistoryService;

@Service
public class NpayInRoutersHistoryServiceImpl implements NpayInRoutersHistoryService {
	
	@Autowired
	private NpayInRoutersHistoryMapper npayInRoutersHistoryMapper;

	@Override
	public List<NpayInRoutersHistory> selectByExamplehistory(NpayInRoutersHistory npayInRoutersHistory) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayInRoutersHistoryMapper.selectByExamplehistory(npayInRoutersHistory);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayInRoutersHistoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(NpayInRoutersHistory npayInRoutersHistory) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayInRoutersHistoryMapper.insertSelective(npayInRoutersHistory);
	}


}
