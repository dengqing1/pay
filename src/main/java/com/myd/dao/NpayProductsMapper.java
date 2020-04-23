package com.myd.dao;

import com.myd.entity.NpayProducts;

public interface NpayProductsMapper {
    int deleteByPrimaryKey(String productId);

    int insert(NpayProducts record);

    int insertSelective(NpayProducts record);

    NpayProducts selectByPrimaryKey(String productId);

    int updateByPrimaryKeySelective(NpayProducts record);

    int updateByPrimaryKey(NpayProducts record);
}