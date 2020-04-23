package com.myd.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myd.dao.NpayTf56BankMapper;
import com.myd.entity.NpayTf56Bank;
import com.myd.service.NpayTf56BankService;

@Service("npayTf56BankServiceImpl")
public class NpayTf56BankServiceImpl implements NpayTf56BankService {
	@Autowired
	private NpayTf56BankMapper npayTf56BankDao;

	@Override
	public NpayTf56Bank getBankByBankId(String bankId) {
		// TODO Auto-generated method stub
		return npayTf56BankDao.getBankById(bankId);
	}

	
	
}
