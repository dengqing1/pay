package com.myd.manager.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myd.config.DynamicDataSource;
import com.myd.dao.GatherMapper;
import com.myd.entity.Gather;
import com.myd.entity.NpayOrder;
import com.myd.manager.service.GatherService;

@Service
public class GatherServiceImpl implements GatherService {
	
	@Autowired
	private GatherMapper gatherMapper;

	@Override
	public void updateByExampleSelective(Gather gather) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		gatherMapper.updateByExampleSelective(gather);
	}

	@Override
	public void insert(Gather gather) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		gatherMapper.insert(gather);
	}
	

	@Override
	public Gather selectById(String id) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return gatherMapper.selectById(id);
	}

	@Override
	public List<Gather> list(Map<String, Object> map) {
		return gatherMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return gatherMapper.count(map);
	}

}
