package com.myd.dao;

import java.util.List;
import java.util.Map;

import com.myd.entity.NpayInRoutersTemp;

public interface NpayInRoutersTempMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NpayInRoutersTemp record);

    int insertSelective(NpayInRoutersTemp record);

    NpayInRoutersTemp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NpayInRoutersTemp record);

    int updateByPrimaryKeyWithBLOBs(NpayInRoutersTemp record);

    int updateByPrimaryKey(NpayInRoutersTemp record);

	List<Map<String, Object>> selectByRoutersTemp(NpayInRoutersTemp npayInRoutersTemp);
}