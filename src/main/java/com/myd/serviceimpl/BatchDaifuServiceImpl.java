package com.myd.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.myd.config.DynamicDataSource;
import com.myd.dao.BatchDaifuMapper;
import com.myd.entity.BatchDaifu;
import com.myd.entity.Gather;
import com.myd.service.BatchDaifuService;

@Service
public class BatchDaifuServiceImpl implements BatchDaifuService {

	@Autowired
	private BatchDaifuMapper batchDaifuDao;
	
	@Override
	public List<BatchDaifu> list(Map<String, Object> map) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_B);
		List<BatchDaifu> list = batchDaifuDao.list(map);
		return list;
	}

	@Override
	public int count(Map<String, Object> map) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_B);
		int count = batchDaifuDao.count(map);
		return count;
	}

	@Override
	public void insert(BatchDaifu batchDaifu) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_B);
		batchDaifuDao.insert(batchDaifu);
	}

	@Override
	public List<Map<String, Object>> selectByDaifu(Gather gather,Integer page) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		if(page==null){
			PageHelper.startPage(1, 10);
		}else{
			PageHelper.startPage(page, 10);
		}
		List<Map<String, Object>> list = batchDaifuDao.selectByDaifu(gather);
		return list;
	}

	@Override
	public BatchDaifu get(BatchDaifu batchDaifu) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_B);
		BatchDaifu data =  batchDaifuDao.get(batchDaifu);
		return data;
	}

	@Override
	public int updateByExampleSelective(BatchDaifu batchNewDaifu) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_B);
		int bat = batchDaifuDao.updateByExampleSelective(batchNewDaifu);
		return bat;
	}

	
	
}
