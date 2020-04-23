package com.myd.dao;

import java.util.List;
import java.util.Map;

import com.myd.entity.Gather;

public interface GatherMapper {

	void updateByExampleSelective(Gather gather);

	void insert(Gather gather);

	Gather selectById(String id);

	List<Gather> list(Map<String, Object> map);

	int count(Map<String, Object> map);

}
