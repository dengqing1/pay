package com.myd.util;

import com.alibaba.fastjson.JSON;
import com.myd.entity.BizData;
import com.myd.entity.ReqPars;


import org.apache.commons.codec.binary.Base64;


import static com.google.common.base.Charsets.UTF_8;

import java.util.SortedMap;

/**
 * 接三方支付的测试
 *@author xiaoqiang lu
 *
 *2018/12/27 17:10
 */
public class ThirdPayUtil {
	
	
	public static ReqPars  test(){
		BizData bizdata = new BizData();
		bizdata.setOut_order_no(DateUtil.getNowTimeStamp()+DateUtil.getNowTimeStamp());
		bizdata.setAmount(1000);
		bizdata.setFront_url("http://47.75.179.162:85/callback/dstbank/backend");
		bizdata.setNotify_url("http://47.75.179.162:85/callback/dstbank/backend");
		bizdata.setSubject("苹果X");
		bizdata.setBody("好看的商品");
		bizdata.setBank_code("CCB");
		bizdata.setTerminal_ip("127.0.0.1");
		
		
		SortedMap<String, Object> map = null ;
		try {
			  map = PayUtil.objectToSortedMap(bizdata);  //排序
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 String str = JSON.toJSONString(map);//转json
		 
		 ReqPars par = new ReqPars();//请求参数
		 par.setVersion("1.1");
		 par.setMerch_id("PAY10101014000002");//商户号
		 par.setBiz_code("P101172");
		 par.setTimestamp(DateUtil.getNowTimeStamp());
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
		 
		 SortedMap<String, Object> mapres = null ;
			try {
				  mapres = PayUtil.objectToSortedMap(par);  //排序
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
			
			
		String res = JSON.toJSONString(mapres);//最后转为Json字符串
		 
		 return par;
		 
		 
		 
		 
		 
		 
//		 
//		 String s =  Base64CodeUtil.Base64Encoding(str);//进行base64转化
//		 
//		 
//		 
//		 System.out.println(s+">>>>>>>>>>>>>>>>");
		
		
		
	}
	public static void main(String[] args) {
		test();
	}
	

}
