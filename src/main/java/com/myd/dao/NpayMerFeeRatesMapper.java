package com.myd.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.myd.entity.NpayMerFeeRates;

public interface NpayMerFeeRatesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NpayMerFeeRates record);

    int insertSelective(NpayMerFeeRates record);

    NpayMerFeeRates selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NpayMerFeeRates record);
    
//    @Update("update npay_mer_fee_rates set channelAbbr = #{channelAbbr},channelMerId = #{channelMerId} where mer_id = #{merId} and channelAbbr = #{channelAbbr} and channelMerId = #{channelMerId}")
    int updateByPrimaryKey(NpayMerFeeRates record);
    
//    NpayMerFeeRates selectByIdandGetway(@Param(value="chanId")String chanId,@Param(value="getway")String getway,@Param(value="cardType")String cardType);
    NpayMerFeeRates selectByIdandGetway(Map<String,Object> map);
    
}