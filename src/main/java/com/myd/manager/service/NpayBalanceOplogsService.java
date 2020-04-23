package com.myd.manager.service;

import java.util.List;
import java.util.Map;

import com.myd.entity.NpayBalanceOplogs;

public interface NpayBalanceOplogsService {

	List<NpayBalanceOplogs> selectByExampleoplogs(NpayBalanceOplogs npayBalanceOplogs);

	void insertSelective(NpayBalanceOplogs npayBalanceOplogs);

	List<Map<String, Object>> findByAbnormal(Map<String, Object> map,Integer page);

}
