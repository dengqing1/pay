package com.myd.dao;

import com.myd.entity.NpayReconOrder;

public interface NpayReconOrderMapper {
    int deleteByPrimaryKey(String merorderid);

    int insert(NpayReconOrder record);

    int insertSelective(NpayReconOrder record);

    NpayReconOrder selectByPrimaryKey(String merorderid);

    int updateByPrimaryKeySelective(NpayReconOrder record);

    int updateByPrimaryKey(NpayReconOrder record);
}