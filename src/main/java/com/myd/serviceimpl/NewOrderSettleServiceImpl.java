package com.myd.serviceimpl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myd.dao.NewOrderSettleDao;
import com.myd.service.NewOrderSettleService;
@Service("newOrderSettleService")
public class NewOrderSettleServiceImpl implements NewOrderSettleService{
	@Autowired
	private NewOrderSettleDao newOrderSettleDao ;

	@Override
	public HashMap<Object,Object> callProcedure(String sql) {
		return newOrderSettleDao.callProcedure(sql);
	}

}
