package com.myd.dao;

import java.util.List;

import com.myd.entity.NpayDaifuRoutersTemp;

public interface NpayDaifuRoutersTempMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NpayDaifuRoutersTemp record);

    int insertSelective(NpayDaifuRoutersTemp record);

    NpayDaifuRoutersTemp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NpayDaifuRoutersTemp record);

    int updateByPrimaryKeyWithBLOBs(NpayDaifuRoutersTemp record);

    int updateByPrimaryKey(NpayDaifuRoutersTemp record);

	List<NpayDaifuRoutersTemp> selectByRoutersTemp(NpayDaifuRoutersTemp npayDaifuRoutersTemp);
}