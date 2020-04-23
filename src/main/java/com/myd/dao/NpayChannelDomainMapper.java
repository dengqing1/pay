package com.myd.dao;

import com.myd.entity.NpayChannelDomain;

public interface NpayChannelDomainMapper {
    int insert(NpayChannelDomain record);

    int insertSelective(NpayChannelDomain record);
}