package com.myd.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.myd.entity.NpayChannels;
import com.myd.entity.NpayOrder;
import com.myd.entity.NpayOrderKey;

public interface NpayOrderMapper {
    int deleteByPrimaryKey(NpayOrderKey key);

    int insert(NpayOrder record);

    int insertSelective(NpayOrder record);

    NpayOrder selectByPrimaryKey(NpayOrderKey key);
    
    NpayOrder selectByOrderId(NpayOrder record);

    int updateByPrimaryKeySelective(NpayOrder record);

    int updateByPrimaryKey(NpayOrder record);
    
	List<Map<String, Object>> selectByExampleOrder(Map<String, Object> map);
	
	NpayOrder getOrderByOurOrderId(String orderId);

	List<NpayOrder> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	NpayOrder getMerorderid(String merorderid);

	NpayOrder getOrderByMerChantId(String merOrderId);

	List<Map<String, Object>> selectByPrimary(NpayChannels npayChannels);

	NpayOrder getOrderByMerChantIdandMerOId(@Param(value="merId") String merId, @Param(value="merOid") String merOid);

	Integer selectSumTxnAmt(@Param(value="channelMerAbbr")String channelMerAbbr, @Param(value="merchantid")String merchantid);
}