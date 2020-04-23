package com.myd.manager.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myd.dao.StatementMapper;
import com.myd.entity.Statement;
import com.myd.manager.service.StatementService;

@Service
public class StatementServiceImpl implements StatementService {

	@Autowired
	public StatementMapper statementMapper;
	
	@Override
	public int insert(Statement record) {
		// TODO Auto-generated method stub
		return statementMapper.insert(record);
	}

	@Override
	public List<Statement> selectByList(String str) {
		// TODO Auto-generated method stub
		return statementMapper.selectByList(str);
	}

	@Override
	public List<Map<String,Object>> selectOrder(String str) {
		// TODO Auto-generated method stub
		return statementMapper.selectOrder(str);
	}

	@Override
	public List<Statement> selectCheckDate(String str) {
		// TODO Auto-generated method stub
		return statementMapper.selectCheckDate(str);
	}

	@Override
	public List<Statement> selectOneMerchant(String str) {
		// TODO Auto-generated method stub
		return statementMapper.selectOneMerchant(str);
	}



}
