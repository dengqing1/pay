package com.myd.manager.service;

import java.util.List;
import java.util.Map;

import com.myd.entity.Gather;

public interface GatherService {

	void updateByExampleSelective(Gather gather);

	void insert(Gather gather);

	Gather selectById(String id);

	int count(Map<String, Object> params);

	List<Gather> list(Map<String, Object> params);

}
