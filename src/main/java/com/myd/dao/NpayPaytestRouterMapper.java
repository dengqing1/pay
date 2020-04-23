package com.myd.dao;

import com.myd.entity.NpayPaytestRouter;

public interface NpayPaytestRouterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NpayPaytestRouter record);

    int insertSelective(NpayPaytestRouter record);

    NpayPaytestRouter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NpayPaytestRouter record);

    int updateByPrimaryKey(NpayPaytestRouter record);
}