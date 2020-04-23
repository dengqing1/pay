package com.myd.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myd.config.DynamicDataSource;
import com.myd.dao.NpayMerFeeRatesMapper;
import com.myd.entity.NpayMerFeeRates;
import com.myd.service.NpayMerFeeRatesService;
@Service("npayMerFeeRatesService")
public class NpayMerFeeRatesServiceimpl implements NpayMerFeeRatesService {
	@Autowired
	private NpayMerFeeRatesMapper npayMerFeeRatesDao ;

	@Override
	public NpayMerFeeRates getNpayMerFeeRates(Map<String,Object> map) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		// TODO Auto-generated method stub
		return npayMerFeeRatesDao.selectByIdandGetway(map);
	}

	@Override
	public void insertSelective(NpayMerFeeRates npayMerFeeRates) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		npayMerFeeRatesDao.insert(npayMerFeeRates);
	}

	@Override
	public void updateByPrimaryKeySelective(NpayMerFeeRates npayMerFeeRates) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		npayMerFeeRatesDao.updateByPrimaryKey(npayMerFeeRates);
	}

}
