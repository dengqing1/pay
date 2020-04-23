package com.myd.dao;

import com.myd.entity.NpayChannelDaifuChangeHistory;

public interface NpayChannelDaifuChangeHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NpayChannelDaifuChangeHistory record);

    int insertSelective(NpayChannelDaifuChangeHistory record);

    NpayChannelDaifuChangeHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NpayChannelDaifuChangeHistory record);

    int updateByPrimaryKey(NpayChannelDaifuChangeHistory record);
}