package com.myd.manager.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.myd.config.DynamicDataSource;
import com.myd.dao.FreezeMapper;
import com.myd.entity.Freeze;
import com.myd.manager.service.FreezeService;

@Service
public class FreezeServiceImpl implements FreezeService {

	@Autowired
	private FreezeMapper freezeMapper;
	
	@Override
	public void insert(Freeze freeze) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		freezeMapper.insert(freeze);
	}

	@Override
	public List<Freeze> selectByFreeze(Freeze freeze,Integer page) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		if(page==null){
			PageHelper.startPage(1, 10);
		}else{
			PageHelper.startPage(page, 10);
		}
		return freezeMapper.selectByFreeze(freeze);
	}

}
