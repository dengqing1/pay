package com.myd.dao;

import org.apache.ibatis.annotations.Select;

import com.myd.entity.NpayMerInfo;

public interface NpayMerInfoMapper {
    int deleteByPrimaryKey(String merId);

    int insert(NpayMerInfo record);

    int insertSelective(NpayMerInfo record);
    
    @Select("select mer_secret_key as merSecretKey,mer_open_status as merOpenStatus from npay_mer_info where mer_id = #{merId}")
    NpayMerInfo selectByPrimaryKey(String merId);

    int updateByPrimaryKeySelective(NpayMerInfo record);

    int updateByPrimaryKey(NpayMerInfo record);
}