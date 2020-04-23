package com.myd.manager.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.myd.config.DynamicDataSource;
import com.myd.dao.NpayInRoutersTempMapper;
import com.myd.entity.NpayInRoutersTemp;
import com.myd.manager.service.NpayInRoutersTempService;

@Service
public class NpayInRoutersTempServiceImpl implements NpayInRoutersTempService {
	
	@Autowired
	private NpayInRoutersTempMapper npayInRoutersTempMapper;

	@Override
	public List<Map<String, Object>> selectByRoutersTemp(NpayInRoutersTemp npayInRoutersTemp,Integer page) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		if(page==null){
			PageHelper.startPage(1, 10);
		}else{
			PageHelper.startPage(page, 10);
		}
		return npayInRoutersTempMapper.selectByRoutersTemp(npayInRoutersTemp);
	}

}
