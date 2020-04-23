package com.myd.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myd.config.DynamicDataSource;
import com.myd.dao.NpayMerInfoDao;
import com.myd.dao.NpayMerInfoMapper;
import com.myd.dao.NpayMerchantBalance2018Mapper;
import com.myd.dao.NpayOrderMapper;
import com.myd.entity.NpayMerInfo;
import com.myd.entity.NpayMerchantBalance2018;
import com.myd.entity.NpayOrder;
import com.myd.service.NpayMerInfoService;
import com.myd.service.StatusService;
@Service("statusService")
public class StatusServiceimpl implements StatusService {
	@Autowired
	private NpayOrderMapper npayOrderDao;
	
	@Autowired
	private NpayMerchantBalance2018Mapper balanceDao;
	
	@Autowired
	private NpayMerInfoMapper npayMerInfoDao;

	@Override
	public NpayOrder getOrderById(String merOrderId,String merchantId) {
		NpayOrder record = new NpayOrder();
		record.setMerorderid(merOrderId);
		record.setMerchantid(merchantId);
		record = npayOrderDao.selectByOrderId(record);
		return record;
	}
	
	@Override
	public NpayMerInfo getMerInfoByMerId(String merchantId) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayMerInfoDao.selectByPrimaryKey(merchantId);
	}
	
	@Override
	public NpayMerchantBalance2018 getBanlanceByMerId(String merId) {
		NpayMerchantBalance2018 balance =balanceDao.selectByPrimaryKey(merId);
		return balance;
	}
}
