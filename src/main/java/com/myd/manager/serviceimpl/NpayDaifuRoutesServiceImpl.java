package com.myd.manager.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.myd.config.DynamicDataSource;
import com.myd.dao.NpayDaifuRoutesMapper;
import com.myd.dao.NpayTf56BankMapper;
import com.myd.entity.NpayChannels;
import com.myd.entity.NpayDaifuRoutes;
import com.myd.entity.NpayTf56Bank;
import com.myd.manager.service.NpayDaifuRoutesService;
@Service
public class NpayDaifuRoutesServiceImpl implements NpayDaifuRoutesService {
	
	@Autowired
	private NpayDaifuRoutesMapper npayDaifuRoutesMapper;
	
	@Autowired
	private NpayTf56BankMapper npayTf56BankMapper;

	@Override
	public List<NpayDaifuRoutes> selectByRoutes(NpayDaifuRoutes npayDaifuRoutes, Integer page) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		if(page==null){
			PageHelper.startPage(1, 10);
		}else{
			PageHelper.startPage(page, 10);
		}
		return npayDaifuRoutesMapper.selectByRoutes(npayDaifuRoutes);
	}

	@Override
	public void insertSelective(NpayDaifuRoutes npayDaifuRoutes) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		npayDaifuRoutesMapper.insert(npayDaifuRoutes);
		
	}

	@Override
	public List<NpayDaifuRoutes> selectByChartId(String chartId, Integer money) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		// TODO Auto-generated method stub
		return npayDaifuRoutesMapper.selectByChartId(chartId, money);
	}

	@Override
	public NpayDaifuRoutes selectByPrimary(NpayDaifuRoutes npayDaifuRoutes) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayDaifuRoutesMapper.selectByPrimary(npayDaifuRoutes);
	}

	@Override
	public List<NpayTf56Bank> selectByBank(NpayTf56Bank npayTf56Bank) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayTf56BankMapper.selectByBank(npayTf56Bank);
	}

	@Override
	public List<NpayChannels> selectNpayDaifuRoutesById(String channelAbbr) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayDaifuRoutesMapper.selectNpayDaifuRoutesById(channelAbbr);
	}

	@Override
	public Map<String, Object> selectDaifuRoutesByRates(NpayDaifuRoutes daifuRoutes) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayDaifuRoutesMapper.selectDaifuRoutesByRates(daifuRoutes);
	}

	@Override
	public void updateByPrimaryKeySelective(NpayDaifuRoutes npayDaifuRoutes) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		npayDaifuRoutesMapper.updateByPrimaryKey(npayDaifuRoutes);
	}


}
