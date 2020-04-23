package com.myd.dao;

import com.myd.entity.NpayAinongBank;

public interface NpayAinongBankMapper {
    int deleteByPrimaryKey(String bankid);

    int insert(NpayAinongBank record);

    int insertSelective(NpayAinongBank record);

    NpayAinongBank selectByPrimaryKey(String bankid);

    int updateByPrimaryKeySelective(NpayAinongBank record);

    int updateByPrimaryKey(NpayAinongBank record);
}