package com.myd.dao;

import java.util.List;
import java.util.Map;

import com.myd.entity.BatchDaifu;
import com.myd.entity.Gather;

public interface BatchDaifuMapper {
	
	List<BatchDaifu> list(Map<String, Object> map);
	
	BatchDaifu get(BatchDaifu batchDaifu);

	int count(Map<String, Object> map);

	int insert(BatchDaifu batchDaifu);

	List<Map<String, Object>> selectByDaifu(Gather gather);

	int updateByExampleSelective(BatchDaifu batchNewDaifu);
}
