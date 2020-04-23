package com.myd.dao;

import com.myd.entity.NpayAinongRecon;

public interface NpayAinongReconMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NpayAinongRecon record);

    int insertSelective(NpayAinongRecon record);

    NpayAinongRecon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NpayAinongRecon record);

    int updateByPrimaryKey(NpayAinongRecon record);
}