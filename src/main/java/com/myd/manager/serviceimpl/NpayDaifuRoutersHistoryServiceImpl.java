package com.myd.manager.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myd.config.DynamicDataSource;
import com.myd.dao.NpayDaifuRoutersHistoryMapper;
import com.myd.entity.NpayDaifuRoutersHistory;
import com.myd.manager.service.NpayDaifuRoutersHistoryService;

@Service
public class NpayDaifuRoutersHistoryServiceImpl implements NpayDaifuRoutersHistoryService {
	
	@Autowired
	private NpayDaifuRoutersHistoryMapper npayDaifuRoutersHistoryMapper;

	@Override
	public List<NpayDaifuRoutersHistory> selectByExamplehistory(NpayDaifuRoutersHistory npayDaifuRoutersHistory) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayDaifuRoutersHistoryMapper.selectByExamplehistory(npayDaifuRoutersHistory);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayDaifuRoutersHistoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(NpayDaifuRoutersHistory npayDaifuRoutersHistory) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayDaifuRoutersHistoryMapper.insertSelective(npayDaifuRoutersHistory);
	}

}
