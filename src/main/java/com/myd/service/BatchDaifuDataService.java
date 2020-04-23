package com.myd.service;

import com.myd.entity.NpayOrder;
import com.myd.entity.NpayTf56Bank;

public interface BatchDaifuDataService {
	String BatchDaifu(NpayOrder order,NpayTf56Bank oBank);
	

}
