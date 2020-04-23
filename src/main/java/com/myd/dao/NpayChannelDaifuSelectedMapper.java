package com.myd.dao;

import com.myd.entity.NpayChannelDaifuSelected;

public interface NpayChannelDaifuSelectedMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NpayChannelDaifuSelected record);

    int insertSelective(NpayChannelDaifuSelected record);

    NpayChannelDaifuSelected selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NpayChannelDaifuSelected record);

    int updateByPrimaryKey(NpayChannelDaifuSelected record);
}