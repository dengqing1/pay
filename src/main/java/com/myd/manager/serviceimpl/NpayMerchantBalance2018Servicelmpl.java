package com.myd.manager.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myd.config.DynamicDataSource;
import com.myd.dao.NpayMerchantBalance2018Mapper;
import com.myd.entity.NpayMerchantBalance2018;
import com.myd.manager.service.NpayMerchantBalance2018Service;

@Service
public class NpayMerchantBalance2018Servicelmpl implements NpayMerchantBalance2018Service {
	
	@Autowired
	private NpayMerchantBalance2018Mapper npayMerchantBalance2018Mapper;

	@Override
	public NpayMerchantBalance2018 selectByPrimaryKey(String merchantid) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayMerchantBalance2018Mapper.selectByPrimaryKey(merchantid);
	}

	@Override
	public NpayMerchantBalance2018 selectByExample(NpayMerchantBalance2018 balance) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayMerchantBalance2018Mapper.selectByExample(balance);
	}

	@Override
	public int insertSelective(NpayMerchantBalance2018 balance2018) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayMerchantBalance2018Mapper.insertSelective(balance2018);
	}

	@Override
	public int updateByPrimaryKey(NpayMerchantBalance2018 balance2018) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayMerchantBalance2018Mapper.updateByPrimaryKey(balance2018);
	}

	@Override
	public int updateByPrimaryKeySelective(NpayMerchantBalance2018 balance2018) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayMerchantBalance2018Mapper.updateByPrimaryKeySelective(balance2018);
	}

	@Override
	public List<NpayMerchantBalance2018> selectByBalanceList(NpayMerchantBalance2018 balance) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayMerchantBalance2018Mapper.selectByBalanceList(balance);
	}

}
