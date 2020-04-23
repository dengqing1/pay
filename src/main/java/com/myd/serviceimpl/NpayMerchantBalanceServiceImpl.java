package com.myd.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myd.dao.NpayMerchantBalance2018Mapper;
import com.myd.entity.NpayMerchantBalance2018;
import com.myd.service.NpayMerchantBalanceService;
@Service("npayMerchantBalanceService")
public class NpayMerchantBalanceServiceImpl implements NpayMerchantBalanceService{
	@Autowired
	private NpayMerchantBalance2018Mapper npayMerchantBalanceDao ;

	@Override
	public NpayMerchantBalance2018 getBanlaceById(String merId) {
		// TODO Auto-generated method stub
		return npayMerchantBalanceDao.selectByPrimaryKey(merId);
	}

}
