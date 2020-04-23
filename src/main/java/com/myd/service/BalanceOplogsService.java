package com.myd.service;

import java.util.List;
import java.util.Map;

import com.myd.entity.NpayBalanceOplogs;

public interface BalanceOplogsService {
	
	List<NpayBalanceOplogs> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
}
