package com.myd.dao;

import java.util.List;

import com.myd.entity.NpayTf56Bank;

public interface NpayTf56BankMapper {
    int deleteByPrimaryKey(String zBankId);

    int insert(NpayTf56Bank record);

    int insertSelective(NpayTf56Bank record);

    NpayTf56Bank selectByPrimaryKey(String zBankId);

    int updateByPrimaryKeySelective(NpayTf56Bank record);

    int updateByPrimaryKey(NpayTf56Bank record);

	List<NpayTf56Bank> selectByBank(NpayTf56Bank npayTf56Bank);
	
	NpayTf56Bank getBankById(String bankId);
	
	NpayTf56Bank getBankByName(String bankName);
}