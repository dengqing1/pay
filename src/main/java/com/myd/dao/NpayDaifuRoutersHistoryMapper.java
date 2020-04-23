package com.myd.dao;

import java.util.List;

import com.myd.entity.NpayDaifuRoutersHistory;

public interface NpayDaifuRoutersHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NpayDaifuRoutersHistory record);

    int insertSelective(NpayDaifuRoutersHistory record);

    NpayDaifuRoutersHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NpayDaifuRoutersHistory record);

    int updateByPrimaryKeyWithBLOBs(NpayDaifuRoutersHistory record);

    int updateByPrimaryKey(NpayDaifuRoutersHistory record);

	List<NpayDaifuRoutersHistory> selectByExamplehistory(NpayDaifuRoutersHistory npayDaifuRoutersHistory);
}