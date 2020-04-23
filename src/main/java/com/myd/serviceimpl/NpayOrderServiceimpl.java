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
import com.myd.dao.NpayOrderMapper;
import com.myd.dao.NpayTf56BankMapper;
import com.myd.entity.NpayOrder;
import com.myd.entity.NpayTf56Bank;
import com.myd.service.NpayOrderService;
import com.myd.util.CommonExcel;


@Service
public class NpayOrderServiceimpl implements NpayOrderService{
	@Autowired
	private NpayOrderMapper npayOrderDao ; 

	@Autowired
	private NpayTf56BankMapper npayTf56BankMapper;
	
	@Override
	public int addOrder(NpayOrder payOrder) {
		return npayOrderDao.insertSelective(payOrder);
	}
	@Override
	public int insert(NpayOrder payOrder) {
		return npayOrderDao.insert(payOrder);
	}

	@Override
	public List<NpayOrder> list(Map<String, Object> map) {
		return npayOrderDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return npayOrderDao.count(map);
	}

	@Override
	public NpayOrder getOrderByOurOrderId(String orderId) {
		// TODO Auto-generated method stub
		return npayOrderDao.getOrderByOurOrderId(orderId);
	}
	
	@Override
	public NpayOrder getMerorderid(String merorderid) {
		return npayOrderDao.getMerorderid(merorderid);
	}

	@Override
	public int updateOrder(NpayOrder npayOrder) {
		// TODO Auto-generated method stub
		return npayOrderDao.updateByPrimaryKeySelective(npayOrder);
	}
	

	@Override
	public List<NpayTf56Bank> selectByBank() {
		return npayTf56BankMapper.selectByBank(null);
	}
	
	@Override
	public NpayTf56Bank getBank(String bankName) {
		return npayTf56BankMapper.getBankByName(bankName);
	}

	
	
	
	@Override
	public void export(HttpServletResponse response,Map<String, Object> params) throws Exception {
		params.put("offset", 0);
		params.put("limit", 50000);
		String title = "交易查询";
	    String[] rowsName = new String[]{"订单日期","平台订单号","商户号","商户名称",
	    		"商户订单号","订单金额（元）","手续费（元）","产品类型","银行","状态","状态详情"};
	    List<Object[]>  dataList = new ArrayList<Object[]>();
	    List<NpayOrder> list = npayOrderDao.list(params);
	    for (int i = 0; i < list.size(); i++) {
	     	Object[] objects = new Object[rowsName.length];
	     	NpayOrder order = list.get(i);
	    	objects[0] = StringUtils.isBlank(order.getCreateTime()) ? "/" : order.getCreateTime();
	    	objects[1] = StringUtils.isBlank(order.getOrderid()) ? "/" : order.getOrderid();
	    	objects[2] = StringUtils.isBlank(order.getMerchantid()) ? "/" : order.getMerchantid();
	    	objects[3] = StringUtils.isBlank(order.getMsName()) ? "/" : order.getMsName();
	    	objects[4] = StringUtils.isBlank(order.getMerorderid()) ? "/" : order.getMerorderid();
	    	objects[5] = StringUtils.isBlank(order.getTxnAmts()) ? "/" : order.getTxnAmts();
	    	objects[6] = StringUtils.isBlank(order.getInFee()) ? "/" : order.getInFee();
	    	objects[7] = StringUtils.isBlank(order.getTypes()) ? "/" : order.getTypes();
	    	objects[8] = StringUtils.isBlank(order.getBankName()) ? "/" : order.getBankName();
	    	objects[9] = StringUtils.isBlank(order.getStatusStr()) ? "/" : order.getStatusStr();
	    	objects[10] = StringUtils.isBlank(order.getStatusdesc()) ? "/" : order.getStatusdesc();
	    	dataList.add(objects);
	    }
	    String fileName="交易查询-"+ new SimpleDateFormat("yyyy-MM-dd HH:mm").format((new Date()))+ ".xlsx";
  		CommonExcel ex = new CommonExcel(title, rowsName, dataList, response, fileName);
  		ex.downloadExcel();
	}

	@Override
	public void exportWithdraw(HttpServletResponse response, Map<String, Object> params) throws Exception {
		params.put("offset", 0);
		params.put("limit", 50000);
		String title = "账务查询";
	    String[] rowsName = new String[]{"时间","平台订单号","商户号","商户名称",
	    		"商户订单号","交易金额（元）","手续费额（元）","s_pan","姓名","状态","状态详情"};
	    List<Object[]>  dataList = new ArrayList<Object[]>();
	    List<NpayOrder> list = npayOrderDao.list(params);
	    for (int i = 0; i < list.size(); i++) {
	     	Object[] objects = new Object[rowsName.length];
	     	NpayOrder order = list.get(i);
	    	objects[0] = StringUtils.isBlank(order.getCreateTime()) ? "/" : order.getCreateTime();
	    	objects[1] = StringUtils.isBlank(order.getOrderid()) ? "/" : order.getOrderid();
	    	objects[2] = StringUtils.isBlank(order.getMerchantid()) ? "/" : order.getMerchantid();
	    	objects[3] = StringUtils.isBlank(order.getMsName()) ? "/" : order.getMsName();
	    	objects[4] = StringUtils.isBlank(order.getMerorderid()) ? "/" : order.getMerorderid();
	    	objects[5] = StringUtils.isBlank(order.getTxnAmts()) ? "/" : order.getTxnAmts();
	    	objects[6] = StringUtils.isBlank(order.getInFee()) ? "/" : order.getInFee();
	    	objects[7] = StringUtils.isBlank(order.getAccno()) ? "/" : order.getAccno();
	    	objects[8] = StringUtils.isBlank(order.getCustomerNm()) ? "/" : order.getCustomerNm();
	    	objects[9] = StringUtils.isBlank(order.getStatusStr()) ? "/" : order.getStatusStr();
	    	objects[10] = StringUtils.isBlank(order.getStatusdesc()) ? "/" : order.getStatusdesc();
	    	dataList.add(objects);
	    }
	    String fileName="账务查询-"+ new SimpleDateFormat("yyyy-MM-dd HH:mm").format((new Date()))+ ".xlsx";
  		CommonExcel ex = new CommonExcel(title, rowsName, dataList, response, fileName);
  		ex.downloadExcel();
	}

	@Override
	public NpayOrder getOrderByMerChantId(String merOrderId) {
		// TODO Auto-generated method stub
		return npayOrderDao.getOrderByMerChantId(merOrderId);
	}
	@Override
	public NpayOrder getOrderByMerChantIdandMerOId(String merId, String merOid) {
		// TODO Auto-generated method stub
		return npayOrderDao.getOrderByMerChantIdandMerOId(merId,merOid);
	}
	@Override
	public Integer selectSumTxnAmt(String channelMerAbbr, String merchantid) {
		// TODO Auto-generated method stub
		return npayOrderDao.selectSumTxnAmt(channelMerAbbr, merchantid);
	}
	@Override
	public NpayOrder selectByOrderId(NpayOrder record) {
//		订单查询
		
		return npayOrderDao.selectByOrderId(record);
	}

}
