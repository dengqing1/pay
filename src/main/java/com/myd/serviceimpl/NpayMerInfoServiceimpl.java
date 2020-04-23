package com.myd.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.myd.config.DynamicDataSource;
import com.myd.dao.NpayMerInfoDao;
import com.myd.dao.NpayMerInfoMapper;
import com.myd.entity.NpayMerInfo;
import com.myd.entity.NpayOrder;
import com.myd.service.NpayMerInfoService;
import com.myd.util.CommonExcel;
@Service("npayMerInfoService")
public class NpayMerInfoServiceimpl implements NpayMerInfoService {
	@Autowired
	private NpayMerInfoDao npayMerInfoDao;
	
	@Autowired
	private NpayMerInfoMapper npayMerInfoMapper;

	@Override
	public NpayMerInfo getMerInfoById(String id) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		// TODO Auto-generated method stub
		return npayMerInfoDao.getMerInfoById(id);
	}

	@Override
	public int insertSelective(NpayMerInfo npayMerInfo) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		//npayMerInfo.setMerShortName("321");
		/*npayMerInfo.setMerName("321");
		npayMerInfo.setMerRegNo("91110105786191530s");
		npayMerInfo.setOpenProductIds("1");
		npayMerInfo.setOpenGateways("1");
		npayMerInfo.setMerInfoCreateTime(12334);
		npayMerInfo.setMerInfoUpdateTime(34565);
		npayMerInfo.setMerCheckStatus(1);
		npayMerInfo.setMerInfoCheckTime(1345435);
		npayMerInfo.setMerOpenStatus(1);
		npayMerInfo.setMerSecretKey("43521356465732432434544");
		npayMerInfo.setMerIpWhitelist("21321432");
		npayMerInfo.setMerNeedCheckIpWhitelist(324324);
		npayMerInfo.setMerOpenTime(23534534);
		npayMerInfo.setMerRegAddr("123");
		npayMerInfo.setMerTaxNo("123");
		npayMerInfo.setMerOrganizationCertificate("123");
		npayMerInfo.setMerRegCapital("123");
		npayMerInfo.setMerKind("国营");
		npayMerInfo.setMerRiskLevel(1);
		npayMerInfo.setMerLicenseValidDate("2047.03.13");
		npayMerInfo.setMerBusinessType("0001");
		npayMerInfo.setMerProvinceId(0);
		npayMerInfo.setMerCityId(0);
		npayMerInfo.setMerOpenMailSentTime(12324325);
		npayMerInfo.setMerAddress("123");
		npayMerInfo.setMerLegalPerson("123");
		npayMerInfo.setMerLegalIdType("01");
		npayMerInfo.setMerLegalIdNumber("123");
		npayMerInfo.setMerLegalIdValidDate("123");
		npayMerInfo.setDaifuChannelId("100");
		npayMerInfo.setDaifuChannelMerAbbr("0");
		npayMerInfo.setDaifuChannelMerId("0");
		npayMerInfo.setDaifuChannelChannelId("0");
		npayMerInfo.setDaifuChannelT1(0);
		npayMerInfo.setLimitInMoneyDaily(321);
		npayMerInfo.setLimitInMoneySingle(123);
		npayMerInfo.setLimitOutMoneySingle(234);
		npayMerInfo.setLimitOutMoneyCardDaily(345);
		npayMerInfo.setT1(1);
		npayMerInfo.setKftMerId("1");
		npayMerInfo.setKftSceMerIds("2");
		npayMerInfo.setIsDelete(0);*/
		return npayMerInfoDao.insertSelective(npayMerInfo);
	}

	@Override
	public List<NpayMerInfo> selectByPrimary(NpayMerInfo npayMerInfo) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayMerInfoDao.selectByPrimary(npayMerInfo);
	}

	@Override
	public NpayMerInfo selectById(String merId) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayMerInfoDao.selectById(merId);
	}

	@Override
	public List<Map<String, Object>> selectByExampleMer(Map<String, Object> map,Integer page) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		if(page==null){
			PageHelper.startPage(1, 10);
		}else{
			PageHelper.startPage(page, 10);
		}
		return npayMerInfoDao.selectByExampleMer(map);
	}

	@Override
	public List<Map<String, Object>> selectByExampleUser(Map<String, Object> map, Integer page) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		if(page==null){
			PageHelper.startPage(1, 10);
		}else{
			PageHelper.startPage(page, 10);
		}
		return npayMerInfoDao.selectByExampleUser(map);
	}

	@Override
	public int updateByIsHide(NpayMerInfo npayMerInfo) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayMerInfoDao.updateByIsHide(npayMerInfo);
	}

	@Override
	public List<Map<String, Object>> selectByExampleAccount(Map<String, Object> map, Integer page) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		if(page==null){
			PageHelper.startPage(1, 10);
		}else{
			PageHelper.startPage(page, 10);
		}
		return npayMerInfoDao.selectByExampleAccount(map);
	}

	@Override
	public void updateByExampleSelective(NpayMerInfo npayMerInfo) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		/*npayMerInfo.setMerName("内部专用测试账号1");
		npayMerInfo.setMerRegNo("91110105786191530r");
		npayMerInfo.setOpenGateways("bank,daifu");
		npayMerInfo.setMerInfoCreateTime(1537267028);
		npayMerInfo.setMerInfoUpdateTime(1537267028);
		npayMerInfo.setMerCheckStatus(1);
		npayMerInfo.setMerInfoCheckTime(1537267028);
		npayMerInfo.setMerRegAddr("123");
		npayMerInfo.setMerTaxNo("123");
		npayMerInfo.setMerOrganizationCertificate("123");
		npayMerInfo.setMerRegCapital("123");
		npayMerInfo.setMerKind("国营");
		npayMerInfo.setMerRiskLevel(1);
		npayMerInfo.setMerLicenseValidDate("2047.03.13");
		npayMerInfo.setMerBusinessType("0001");
		npayMerInfo.setMerProvinceId(0);
		npayMerInfo.setMerCityId(0);
		npayMerInfo.setMerAddress("123");
		npayMerInfo.setMerLegalPerson("123");
		npayMerInfo.setMerLegalIdType("01");
		npayMerInfo.setMerLegalIdNumber("123");
		npayMerInfo.setMerLegalIdValidDate("123");
		npayMerInfo.setOpenProductIds("网银");
		npayMerInfo.setMerIpWhitelist("113.105.88.378");
		npayMerInfo.setMerNeedCheckIpWhitelist(0);
		npayMerInfo.setMerOpenTime(1537267028);
		npayMerInfo.setMerOpenMailSentTime(1537267028);
		npayMerInfo.setDaifuChannelId("100");
		npayMerInfo.setDaifuChannelMerAbbr("0");
		npayMerInfo.setDaifuChannelMerId("0");
		npayMerInfo.setDaifuChannelChannelId("0");
		npayMerInfo.setDaifuChannelT1(0);
		npayMerInfo.setIsDelete(0);*/
		npayMerInfoDao.updateByExampleSelective(npayMerInfo);
	}

	@Override
	public void export(HttpServletResponse response) throws Exception {
		String title = "商户列表";
		String[] rowsName = new String[]{"商户号","商户名称","产品","信息更新时间","风险等级","信息审核状态","审核时间","开通状态","开通时间"};
		List<Object[]> dataList = new ArrayList<Object[]>();
		NpayMerInfo npayMerInfo = new NpayMerInfo();
		List<NpayMerInfo> list = npayMerInfoDao.selectByPrimary(npayMerInfo);
		for (int i = 0; i < list.size(); i++) {
	     	Object[] objects = new Object[rowsName.length];
	     	NpayMerInfo merInfo = list.get(i);
	     	objects[0] = StringUtils.isBlank(merInfo.getMerId()) ? "/" : merInfo.getMerId();
	     	objects[1] = StringUtils.isBlank(merInfo.getMerShortName()) ? "/" : merInfo.getMerShortName();
	     	objects[2] = StringUtils.isBlank(merInfo.getOpenProductIds()) ? "/" : merInfo.getOpenProductIds();
	     	objects[3] = StringUtils.isBlank(String.valueOf(merInfo.getMerInfoUpdateTime()) ) ? "/" : String.valueOf(merInfo.getMerInfoUpdateTime());
	     	objects[4] = StringUtils.isBlank(String.valueOf(merInfo.getMerRiskLevel()) ) ? "/" : String.valueOf(merInfo.getMerRiskLevel());
	     	objects[5] = StringUtils.isBlank(String.valueOf(merInfo.getMerCheckStatus()) ) ? "/" : String.valueOf(merInfo.getMerCheckStatus());
	     	objects[6] = StringUtils.isBlank(String.valueOf(merInfo.getMerInfoCheckTime()) ) ? "/" : String.valueOf(merInfo.getMerInfoCheckTime());
	     	objects[7] = StringUtils.isBlank(String.valueOf(merInfo.getMerOpenStatus()) ) ? "/" : String.valueOf(merInfo.getMerOpenStatus());
	     	objects[8] = StringUtils.isBlank(String.valueOf(merInfo.getMerOpenTime()) ) ? "/" : String.valueOf(merInfo.getMerOpenTime());
	    	dataList.add(objects);
	    }
		String fileName="商户报表-"+ new SimpleDateFormat("yyyy-MM-dd HH:mm").format((new Date()))+ ".xlsx";
		CommonExcel ex = new CommonExcel(title, rowsName, dataList, response, fileName);
	  	ex.downloadExcel();
	}

	@Override
	public void exportExcle(HttpServletResponse response) throws Exception {
		String title = "商户开通服务列表";
		String[] rowsName = new String[]{"商户号","商户名称","代付-渠道商户名缩写","审核状态","审核时间","开通状态","开通时间"};
		List<Object[]> dataList = new ArrayList<Object[]>();
		NpayMerInfo npayMerInfo = new NpayMerInfo();
		List<NpayMerInfo> list = npayMerInfoDao.selectByService (npayMerInfo);
		for (int i = 0; i < list.size(); i++) {
	     	Object[] objects = new Object[rowsName.length];
	     	NpayMerInfo merInfo = list.get(i);
	     	objects[0] = StringUtils.isBlank(merInfo.getMerId()) ? "/" : merInfo.getMerId();
	     	objects[1] = StringUtils.isBlank(merInfo.getMerShortName()) ? "/" : merInfo.getMerShortName();
	     	objects[2] = StringUtils.isBlank(merInfo.getOpenProductIds()) ? "/" : merInfo.getOpenProductIds();
	     	objects[3] = StringUtils.isBlank(String.valueOf(merInfo.getMerInfoUpdateTime()) ) ? "/" : String.valueOf(merInfo.getMerInfoUpdateTime());
	     	objects[4] = StringUtils.isBlank(String.valueOf(merInfo.getMerInfoCheckTime()) ) ? "/" : String.valueOf(merInfo.getMerInfoCheckTime());
	     	objects[5] = StringUtils.isBlank(String.valueOf(merInfo.getMerCheckStatus()) ) ? "/" : String.valueOf(merInfo.getMerCheckStatus());
	     	objects[6] = StringUtils.isBlank(String.valueOf(merInfo.getMerOpenTime()) ) ? "/" : String.valueOf(merInfo.getMerOpenTime());
	    	dataList.add(objects);
	    }
		String fileName="商户开通服务报表-"+ new SimpleDateFormat("yyyy-MM-dd HH:mm").format((new Date()))+ ".xlsx";
		CommonExcel ex = new CommonExcel(title, rowsName, dataList, response, fileName);
	  	ex.downloadExcel();
		
	}

	@Override
	public NpayMerInfo selectByEntity(NpayMerInfo npayMerInfo) {
		return npayMerInfoDao.selectByEntity(npayMerInfo);
	}

	@Override
	public List<Map<String, Object>> selectBy() {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayMerInfoDao.selectBy();
	}

	@Override
	public List<Map<String, Object>> selectBydaifu() {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayMerInfoDao.selectBydaifu();
	}

	@Override
	public List<Map<String, Object>> selectBydaizhifu() {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayMerInfoDao.selectBydaizhifu();
	}

	@Override
	public List<Map<String, Object>> selectBychujin() {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayMerInfoDao.selectBychujin();
	}

	@Override
	public List<Map<String, Object>> selectByshijije() {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayMerInfoDao.selectByshijije();
	}

	@Override
	public List<Map<String, Object>> selectBychujinze() {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayMerInfoDao.selectBychujinze();
	}

	@Override
	public List<Map<String, Object>> selectByrujinsum() {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayMerInfoDao.selectByrujinsum();
	}

	@Override
	public List<Map<String, Object>> selectBychujinsum() {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		return npayMerInfoDao.selectBychujinsum();
	}

}
