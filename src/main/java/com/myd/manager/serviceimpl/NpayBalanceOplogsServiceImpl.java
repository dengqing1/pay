package com.myd.manager.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.myd.config.DynamicDataSource;
import com.myd.dao.NpayBalanceOplogsMapper;
import com.myd.entity.NpayBalanceOplogs;
import com.myd.manager.service.NpayBalanceOplogsService;

@Service
public class NpayBalanceOplogsServiceImpl implements NpayBalanceOplogsService {
	
	@Autowired
	private NpayBalanceOplogsMapper npayBalanceOplogsMapper;

	@Override
	public List<NpayBalanceOplogs> selectByExampleoplogs(NpayBalanceOplogs npayBalanceOplogs) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayBalanceOplogsMapper.selectByExampleoplogs(npayBalanceOplogs);
	}

	@Override
	public void insertSelective(NpayBalanceOplogs npayBalanceOplogs) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		npayBalanceOplogsMapper.insertSelective(npayBalanceOplogs);
	}

	@Override
	public List<Map<String, Object>> findByAbnormal(Map<String, Object> map,Integer page) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		if(page==null){
			PageHelper.startPage(1, 10);
		}else{
			PageHelper.startPage(page, 10);
		}
		return npayBalanceOplogsMapper.findByAbnormal(map);
	}

}
