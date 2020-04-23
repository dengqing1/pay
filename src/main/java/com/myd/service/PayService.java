package com.myd.service;

import com.myd.entity.NpayOrder;
import com.myd.entity.OrdersBank;
import com.myd.entity.OrdersDaifu;

public interface PayService {
	int payConfirmBank(OrdersBank orderInfo);
	int payConfirmDaifu(OrdersDaifu orderInfoDaifu);
	
	int addOrder(NpayOrder order,Object obj);
	

}
