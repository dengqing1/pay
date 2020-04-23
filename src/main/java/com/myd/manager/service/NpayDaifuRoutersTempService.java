package com.myd.manager.service;

import java.util.List;

import com.myd.entity.NpayDaifuRoutersTemp;

public interface NpayDaifuRoutersTempService {

	List<NpayDaifuRoutersTemp> selectByRoutersTemp(NpayDaifuRoutersTemp npayDaifuRoutersTemp,Integer page);

}
