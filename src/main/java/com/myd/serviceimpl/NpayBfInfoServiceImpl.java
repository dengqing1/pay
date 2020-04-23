package com.myd.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myd.dao.NpayBfInfoMapper;
import com.myd.entity.NpayBfInfo;
import com.myd.service.NpayBfInfoService;
@Service("npayBfInfoService")
public class NpayBfInfoServiceImpl implements NpayBfInfoService{
	@Autowired
	private NpayBfInfoMapper npayBfInfoDao;

	@Override
	public void addNpayBfInfo(NpayBfInfo npaybfInfo) {
		// TODO Auto-generated method stub
		npayBfInfoDao.insertSelective(npaybfInfo);
	}
	
	
	public void updateBfinfo(NpayBfInfo npaybfInfo){
		npayBfInfoDao.updateByOrderId(npaybfInfo);
	}


	@Override
	public NpayBfInfo getInfoByMerOid(String merOid) {
		// TODO Auto-generated method stub
		return npayBfInfoDao.getInfoByMerOid(merOid);
	}

}
