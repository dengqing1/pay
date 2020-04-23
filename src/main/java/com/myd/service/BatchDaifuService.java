package com.myd.service;

import java.util.List;
import java.util.Map;

import com.myd.entity.BatchDaifu;
import com.myd.entity.Gather;


public interface BatchDaifuService {

	List<BatchDaifu> list(Map<String, Object> map);
	
	BatchDaifu get(BatchDaifu batchDaifu);
	
	int count(Map<String, Object> map);

	List<Map<String, Object>> selectByDaifu(Gather gather, Integer page);
	
	void insert(BatchDaifu batchDaifu);

	int updateByExampleSelective(BatchDaifu batchNewDaifu);
	
}
