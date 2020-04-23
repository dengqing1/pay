package com.myd.dao;

import com.myd.entity.NpayMerInfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface NpayMerInfoDao {
	NpayMerInfo  getMerInfoById(String id);

	int insertSelective(NpayMerInfo npayMerInfo);

	List<NpayMerInfo> selectByPrimary(NpayMerInfo npayMerInfo);

	NpayMerInfo selectById(String merId);

	List<Map<String, Object>> selectByExampleMer(Map<String, Object> map);

	List<Map<String, Object>> selectByExampleUser(Map<String, Object> map);

	int updateByIsHide(NpayMerInfo npayMerInfo);

	List<Map<String, Object>> selectByExampleAccount(Map<String, Object> map); //当日入金

	void updateByExampleSelective(NpayMerInfo npayMerInfo);

	List<NpayMerInfo> selectByService(NpayMerInfo npayMerInfo);

	NpayMerInfo selectByEntity(NpayMerInfo npayMerInfo);
	
	List<Map<String, Object>> selectBy(); //当日入金
	
	List<Map<String, Object>> selectBydaifu(); //当日入金

	List<Map<String, Object>> selectBydaizhifu();

	List<Map<String, Object>> selectBychujin();

	List<Map<String, Object>> selectByshijije();

	List<Map<String, Object>> selectBychujinze();

	List<Map<String, Object>> selectByrujinsum();

	List<Map<String, Object>> selectBychujinsum();
	
}