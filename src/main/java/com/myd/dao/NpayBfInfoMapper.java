package com.myd.dao;

import com.myd.entity.NpayBfInfo;

public interface NpayBfInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NpayBfInfo record);

    int insertSelective(NpayBfInfo record);

    NpayBfInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NpayBfInfo record);

    int updateByPrimaryKey(NpayBfInfo record);
    
    int updateByOrderId(NpayBfInfo info) ;

	NpayBfInfo getInfoByMerOid(String merOid);
}