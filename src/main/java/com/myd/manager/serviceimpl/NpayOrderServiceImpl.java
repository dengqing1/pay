package com.myd.manager.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.myd.config.DynamicDataSource;
import com.myd.dao.NpayOrderMapper;
import com.myd.dao.NpayTf56BankMapper;
import com.myd.entity.NpayChannels;
import com.myd.entity.NpayTf56Bank;
import com.myd.manager.service.NpayOrderService;

@Service
public class NpayOrderServiceImpl implements NpayOrderService {
	
	@Autowired
	private NpayOrderMapper npayOrderMapper;
	
	@Autowired
	private NpayTf56BankMapper npayTf56BankMapper;

	@Override
	public List<Map<String, Object>> selectByExampleOrder(Map<String, Object> map, Integer page) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		if(page==null){
			PageHelper.startPage(1, 10);
		}else{
			PageHelper.startPage(page, 10);
		}
		return npayOrderMapper.selectByExampleOrder(map);
	}

	@Override
	public List<NpayTf56Bank> selectByBank(NpayTf56Bank npayTf56Bank) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayTf56BankMapper.selectByBank(npayTf56Bank);
	}

	@Override
	public List<Map<String, Object>> selectByPrimary(NpayChannels npayChannels) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayOrderMapper.selectByPrimary(npayChannels);
	}

}
