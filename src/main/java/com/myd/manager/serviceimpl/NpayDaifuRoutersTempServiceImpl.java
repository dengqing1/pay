package com.myd.manager.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.myd.config.DynamicDataSource;
import com.myd.dao.NpayDaifuRoutersTempMapper;
import com.myd.entity.NpayDaifuRoutersTemp;
import com.myd.manager.service.NpayDaifuRoutersTempService;

@Service
public class NpayDaifuRoutersTempServiceImpl implements NpayDaifuRoutersTempService{
	
	@Autowired
	private NpayDaifuRoutersTempMapper npayDaifuRoutersTempMapper;

	@Override
	public List<NpayDaifuRoutersTemp> selectByRoutersTemp(NpayDaifuRoutersTemp npayDaifuRoutersTemp,Integer page) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		if(page==null){
			PageHelper.startPage(1, 10);
		}else{
			PageHelper.startPage(page, 10);
		}
		return npayDaifuRoutersTempMapper.selectByRoutersTemp(npayDaifuRoutersTemp);
	}

}
