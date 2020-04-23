package com.myd.dao;

import com.myd.entity.NpayRouters;

public interface NpayRoutersMapper {
    int deleteByPrimaryKey(Integer routerId);

    int insert(NpayRouters record);

    int insertSelective(NpayRouters record);

    NpayRouters selectByPrimaryKey(Integer routerId);

    int updateByPrimaryKeySelective(NpayRouters record);

    int updateByPrimaryKey(NpayRouters record);
}