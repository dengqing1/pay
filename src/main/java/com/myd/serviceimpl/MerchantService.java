package com.myd.serviceimpl;

import com.myd.dao.MerchantMapper;
import com.myd.entity.MerchantInformation;
import com.myd.entity.MerchantOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;

    public List<MerchantInformation> getMerchantInformationData() {

        return merchantMapper.getMerchantInformationData();
    }

    public boolean deleteMerchant(String merchantIds) {

        boolean flag = true;
        String[] ids = merchantIds.split(",");
        for (int i = 0; i < ids.length; i++) {
            flag = merchantMapper.deleteMerchant(ids[i]);
        }
        return flag;
    }

    public MerchantInformation getUpdateMerchantEcho(String merchantId) {
        return merchantMapper.getUpdateMerchantEcho(merchantId);

    }


    public List<MerchantInformation> getMerchantAccountData() {
        return merchantMapper.getMerchantAccountData();
    }

    public List<MerchantOrder> getPayInformationData() {

        return merchantMapper.getPayInformationData();

    }


    public boolean updateMerchantInformation(MerchantInformation model) {
        return merchantMapper.updateMerchantInformation(model);
    }

    public List<MerchantInformation> getMerchantIntoSum() {
        return merchantMapper.getMerchantIntoSum();

    }

    public List<MerchantInformation> getMerchantPaidSum() {
        return merchantMapper.getMerchantPaidSum();
    }

//    public void addMerchantOpen(MerchantServiceOpen model) {
//        merchantMapper.addMerchantOpen(model);
//    }
//
//    public void addChannelConfiguration(MerchantConfiguration model) {
//        merchantMapper.addChannelConfiguration(model);
//
//    }

    public void addMerchant(MerchantInformation model) {
        merchantMapper.addMerchant(model);
    }
}
