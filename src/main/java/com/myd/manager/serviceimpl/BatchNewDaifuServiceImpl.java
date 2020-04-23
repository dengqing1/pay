package com.myd.manager.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.myd.dao.BatchDaifuMapper;
import com.myd.entity.BatchDaifu;
import com.myd.manager.service.BatchNewDaifuService;

public class BatchNewDaifuServiceImpl implements BatchNewDaifuService {

	@Autowired
	private BatchDaifuMapper batchDaifuMapper;

	@Override
	public List<BatchDaifu> selectByDaifu(BatchDaifu batchNewDaifu) {
		/*DataSourceContextHolder.setDBType("dataUser");
		DataSourceContextHolder.clearDBType();
		return batchDaifuMapper.selectByDaifu(batchNewDaifu);*/
		return null;
	}
}
