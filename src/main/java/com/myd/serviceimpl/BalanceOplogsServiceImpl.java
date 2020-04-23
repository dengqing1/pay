package com.myd.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myd.dao.BalanceOplogsMapper;
import com.myd.dao.NpayOrderMapper;
import com.myd.entity.NpayBalanceOplogs;
import com.myd.entity.NpayOrder;
import com.myd.service.BalanceOplogsService;


@Service
public class BalanceOplogsServiceImpl implements BalanceOplogsService{
	@Autowired
	private BalanceOplogsMapper balanceOplogsDao ; 


	@Override
	public List<NpayBalanceOplogs> list(Map<String, Object> map) {
		return balanceOplogsDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return balanceOplogsDao.count(map);
	}



}
