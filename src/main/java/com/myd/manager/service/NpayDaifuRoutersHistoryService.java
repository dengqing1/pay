package com.myd.manager.service;

import java.util.List;

import com.myd.entity.NpayDaifuRoutersHistory;

public interface NpayDaifuRoutersHistoryService {

	List<NpayDaifuRoutersHistory> selectByExamplehistory(NpayDaifuRoutersHistory npayDaifuRoutersHistory);

	int deleteByPrimaryKey(Integer id);

	int insertSelective(NpayDaifuRoutersHistory npayDaifuRoutersHistory);

}
