package com.myd.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.myd.entity.NpayMerInfo;

public interface NpayMerInfoService {
	NpayMerInfo  getMerInfoById(String id);

	int insertSelective(NpayMerInfo npayMerInfo);

	List<NpayMerInfo> selectByPrimary(NpayMerInfo npayMerInfo);

	NpayMerInfo selectById(String merId);

	List<Map<String, Object>> selectByExampleMer(Map<String, Object> map, Integer page);

	List<Map<String, Object>> selectByExampleUser(Map<String, Object> map,Integer page);

	int updateByIsHide(NpayMerInfo npayMerInfo);

	List<Map<String, Object>> selectByExampleAccount(Map<String, Object> map,Integer page);

	void updateByExampleSelective(NpayMerInfo npayMerInfo);

	void export(HttpServletResponse response) throws Exception;

	void exportExcle(HttpServletResponse response) throws Exception;

	NpayMerInfo selectByEntity(NpayMerInfo npayMerInfo);
	
	List<Map<String, Object>> selectBy(); 
	
	List<Map<String, Object>> selectBydaifu();
	
	List<Map<String, Object>> selectBydaizhifu(); //待支付
	
	List<Map<String, Object>> selectBychujin(); //出金
	
	List<Map<String, Object>> selectByshijije(); //实际入金金额
	
	List<Map<String, Object>> selectBychujinze(); //出金总额

	List<Map<String, Object>> selectByrujinsum();

	List<Map<String, Object>> selectBychujinsum();
	
	
	

}
