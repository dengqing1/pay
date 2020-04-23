package com.myd.dao;

import org.springframework.stereotype.Repository;

import com.myd.entity.MerchantInformation;
import com.myd.entity.MerchantOrder;

import java.util.List;


@Repository
public interface MerchantMapper {

    List<MerchantInformation> getMerchantInformationData();

    boolean deleteMerchant(String merchantIds);

    MerchantInformation getUpdateMerchantEcho(String merchantId);

    List<MerchantInformation> getMerchantAccountData();

    List<MerchantOrder> getPayInformationData();

    boolean updateMerchantInformation(MerchantInformation model);

    List<MerchantInformation> getMerchantIntoSum();

    List<MerchantInformation> getMerchantPaidSum();

    //void addMerchantOpen(MerchantServiceOpen model);

    //void addChannelConfiguration(MerchantConfiguration model);

    void addMerchant(MerchantInformation model);
}
