package com.myd.manager.serviceimpl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.myd.config.DynamicDataSource;
import com.myd.dao.NpayInRoutesMapper;
import com.myd.dao.NpayTf56BankMapper;
import com.myd.entity.NpayChannels;
import com.myd.entity.NpayInRoutes;
import com.myd.entity.NpayMerFeeRates;
import com.myd.entity.NpayTf56Bank;
import com.myd.manager.service.NpayInRoutesService;

@Service
public class NpayInRoutesServiceImpl implements NpayInRoutesService {
	
	@Autowired
	private NpayInRoutesMapper npayInRoutesMapper;
	
	@Autowired
	private NpayTf56BankMapper npayTf56BankMapper;

	@Override
	public List<NpayMerFeeRates> selectByRoutes(NpayInRoutes npayInRoutes,Integer page) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		if(page==null){
			PageHelper.startPage(1, 10);
		}else{
			PageHelper.startPage(page, 10);
		}
		return npayInRoutesMapper.selectByRoutes(npayInRoutes);
	}

	@Override
	public void insertSelective(NpayInRoutes npayInRoutes) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		npayInRoutesMapper.insert(npayInRoutes);
		
	}

	@Override
	public List<NpayTf56Bank> selectByBank(NpayTf56Bank npayTf56Bank) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayTf56BankMapper.selectByBank(npayTf56Bank);
	}

	@Override
	public NpayInRoutes selectByPrimary(NpayInRoutes npayInRoutes) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayInRoutesMapper.selectByPrimary(npayInRoutes);
		
	}

	@Override
	public List<NpayChannels> selectNpayInRoutesById(String channelAbbr) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayInRoutesMapper.selectNpayInRoutesById(channelAbbr);
	}


	@Override
	public void updateByPrimaryKeySelective(NpayInRoutes npayInRoutes) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		npayInRoutesMapper.updateByPrimaryKey(npayInRoutes);
	}

	@Override
	public Map<String, Object> selectRoutesByRates(String merchantId,String channelAbbr, String channelMerId,String routesGateway,String routesCardType) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayInRoutesMapper.selectRoutesByRates(merchantId,channelAbbr,channelMerId,routesGateway,routesCardType);
	}


}
