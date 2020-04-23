package com.myd.dao;

import java.util.List;

import com.myd.entity.NpayMerchantBalance2018;

public interface NpayMerchantBalance2018Mapper {
    int deleteByPrimaryKey(String merchantid);

    int insert(NpayMerchantBalance2018 record);

    int insertSelective(NpayMerchantBalance2018 record);

    NpayMerchantBalance2018 selectByPrimaryKey(String merchantid);

    int updateByPrimaryKeySelective(NpayMerchantBalance2018 record);

    int updateByPrimaryKey(NpayMerchantBalance2018 record);

	void selectByContent(NpayMerchantBalance2018 npayMerchantBalance2018);

	NpayMerchantBalance2018 selectByExample(NpayMerchantBalance2018 balance);

	List<NpayMerchantBalance2018> selectByBalanceList(NpayMerchantBalance2018 balance);
}