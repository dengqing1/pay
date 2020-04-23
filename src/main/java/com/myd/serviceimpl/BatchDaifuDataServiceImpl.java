package com.myd.serviceimpl;

import static com.google.common.base.Charsets.UTF_8;

import java.util.SortedMap;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myd.entity.BizData;
import com.myd.entity.NpayOrder;
import com.myd.entity.NpayTf56Bank;
import com.myd.entity.ReqPars;
import com.myd.service.BatchDaifuDataService;
import com.myd.util.DateUtil;
import com.myd.util.PayUtil;
import com.myd.util.RSA;

@Service("batchDaifuDataService")
public class BatchDaifuDataServiceImpl  implements BatchDaifuDataService{

	@Override
	public String BatchDaifu(NpayOrder order, NpayTf56Bank oBank) {
		ReqPars reqPars = getDaifuPar(order,oBank);
		String param = null;
		try {
			 param = PayUtil.signature(PayUtil.objectToSortedMap(reqPars));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String objJson = PayUtil.sendPost("http://pay.tengjingshop.com/pay-api/issue", param);
		JSONObject jsonArray= JSONObject.parseObject(objJson);
		//ret_code 	String 	是 	业务返回码，详见状态码说明 	1002
		//ret_msg 	String 	是 	业务返回码描述 	没有可用的支付渠道
		//sign 	String 	是 	平台返回数据的签名 	~
		if( objJson != null && !"".equals(objJson) ){
			String code = jsonArray.getString("ret_code");
			if("0000".equals(code)){
				//返回成功
				return null ;
				
			}else{
			//返回失败，其他状态(实时的告诉商户失败的信息)
			String res = jsonArray.getString("ret_msg");
			return res ;
								
			}
			
			
		}
		
		
		return null;
	}
	
	/**
	 * 代付的信息
	 */
	public ReqPars getDaifuPar(NpayOrder order,NpayTf56Bank oBank){
		BizData bizdata = new BizData();
		String info = order.getCustomerinfo();
		JSONObject obj = JSONObject.parseObject(info);
		String name = obj.getString("customerNm").toString();
		String phoneNimber = obj.getString("phoneNo").toString();
		
		bizdata.setOut_order_no(order.getOrderid());
		bizdata.setAmount(order.getTxnamt());
		bizdata.setNotify_url("http://47.75.179.162:85/callback/dstbank/backend");
		bizdata.setSubject(order.getSubject());
		bizdata.setBody(order.getBody());
		bizdata.setPp_type(0);
		bizdata.setBank_code(oBank.getzBankId());//银行编号
		bizdata.setAcc_no(order.getAccno());//卡号
		bizdata.setAcc_name(name);//户名
		bizdata.setOpening_name(oBank.getBankName());//开户行
		bizdata.setMobile(phoneNimber);//预留手机号
		bizdata.setCurrency("CNY");
		bizdata.setPurpose("交易");

		SortedMap<String, Object> map = null ;
		try {
			  map = PayUtil.thirdobjectToSortedMap(bizdata);  //排序
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 String str = JSON.toJSONString(map);//转json
		 
		 ReqPars par = new ReqPars();//请求参数
		 par.setVersion("1.1");
		 par.setMerch_id("PAY10101014000002");//商户号
		 par.setBiz_code("P100000");//固定的值
		 par.setTimestamp(DateUtil.getNowTimeWithyyyyMMddHHmmss());
		 par.setBiz_data(str);
		 
		 SortedMap<String, Object> mapreq = null ;
			try {
				  mapreq = PayUtil.objectToSortedMap(par);  //排序
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 String strReq = PayUtil.thirdSignature(mapreq);//不要sign
		 System.out.println(strReq);
		 String sign = RSA.signBase64(strReq, RSA.toPrivateKey("MIICXgIBAAKBgQDpxnpX460qQV4RuFBNMSd8oQFWTqZA80fGTV1KJWKllZU1peDFcjPTaPMT2BAomMuDnLvck4QyPV3mVsQW27ok0Y7I2ex8uA1bfJBUas3sBNJrg3BQ5i529/FzyEZ98RumeBXxycErlVz4IHUlzZAEInS1obcvWDtGqMFJfQJm4wIDAQABAoGBALSPy5E01lwrzveKz+M/Uwts5DaAWuMRxN9ChAqv44iXh36/V2PJuIPSrOUn3hstIQvPtD5DZjjTs0IkxCIPpq4tcJrpWStOb45x6YQprQaRXrvlkhRp4DJ6NBUicsSyNo9YvRkN07oFYFdw4qDTdVMnlgvXyuKZd/T1ejMCojgZAkEA+ya7OJMooqWHvKL98DKRV6Ld6RYLH5YonvguHWldq1G+Z7TCjUPfKezL1P0uaoPPwjzT6dPz4ubGEL/AnHAOfwJBAO5J3nSGXw4aeEy0bTdKw3LNPb7GrDftjoI1dqaPeBgiZI9rQ9NG0OcMjv0VcyqD8f+BO18Yrowr54zHHUMK/Z0CQQD3ZU61SxiTJvWPlwsICffr2M45pXItmi/HcHeUl08izHIAHCotF3eEB/M9imynldIY5uxkgFnU4DipFQo5z5QnAkANEyiNpEHa+EDZlJzZh9Spm/FjYmtYtkQ3iM913DFuwZRa+jvCgAQ+aUX/RQoIryy8JE8prKUHM/GEm/hTEWtRAkEApfU0X1DkFPskYsiUTuEzVg3+oAIEP/hfw7v4rmPX/hX/QdO5DK6uNSlzUYjj0zfBrcn+6CcNLfcAkIGaJhu5AQ=="));//传入私钥生成sign信息
	
		 String b64BizData = Base64.encodeBase64String(str.getBytes(UTF_8));//进行base64转化
		 
		 par.setSign(sign);
		 par.setBiz_data(b64BizData);
		 return par;
	}
	
	

	

}
