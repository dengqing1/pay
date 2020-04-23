package com.myd.serviceimpl;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myd.entity.BizData;
import com.myd.entity.KLTDaifuContent;
import com.myd.entity.KLTDaifuReturn;
import com.myd.entity.KLTDuanXin;
import com.myd.entity.KLTGatewayContent;
import com.myd.entity.KLTGatewayReturn;
import com.myd.entity.KLTKJConfirmPay;
import com.myd.entity.KLTKJConfirmReturn;
import com.myd.entity.KLTKJDuanXinReturn;
import com.myd.entity.KLTKJHead;
import com.myd.entity.NpayOrder;
import com.myd.entity.NpayTf56Bank;
import com.myd.entity.OrdersBank;
import com.myd.entity.OrdersDaifu;
import com.myd.entity.ReqPars;
import com.myd.service.ThirdPayService;
import com.myd.util.DateUtil;
import com.myd.util.HttpUtils;
import com.myd.util.KLTUtil;
import com.myd.util.PayUtil;
import com.myd.util.RSA;

import static com.google.common.base.Charsets.UTF_8;

@Service("thirdPayService")
public class ThirdPayServiceimpl implements ThirdPayService{
	/**
	 * 三方支付
	 */
	@Override
	public String thirdPay(String gateway,Object obj,NpayOrder order,NpayTf56Bank nBank) {
		// TODO Auto-generated method stub
		if("bank".equals(gateway)){
			OrdersBank obank = (OrdersBank)obj ;
			//网银支付
			String param = getKltGatewayParam(order);

			//向指定的第三方接口发数据
			//String res = PayUtil.sendPost("http://47.99.165.66/pay/kltGateway",param);
			String res = HttpUtils.sendHttpPostRequest("http://47.99.165.66:8080/zhuanfa/gatewayPay",param,false);
			System.out.println("网关支付的结果"+res);
			KLTGatewayReturn gateReturn = KLTUtil.stringToEntity(res, KLTGatewayReturn.class);
			System.out.println("对应的实体类："+gateReturn);
			if(gateReturn.getPayResult()  == 1 || gateReturn.getPayResult() == 0){
				return null;
			}else {
				
				return gateReturn.getResponseMsg();
			}
	
		}else if("daifu".equals(gateway)){
			OrdersDaifu odifu = (OrdersDaifu)obj ;
			//代付
			String param = getDaifuParam(order,nBank.getBankName());
			//String res = PayUtil.sendPost("http://47.99.165.66/pay/kltdfzhuanfacs", param);
			String res = HttpUtils.sendHttpPostRequest("http://47.99.165.66:8080/zhuanfa/daifu",param,false);
			System.out.println("代付返回的结果："+res);
			KLTDaifuReturn payReturn  = KLTUtil.stringToEntity(res, KLTDaifuReturn.class);
			System.out.println("代付返回的结果映射到实体类："+payReturn);
			if("SUCCESS".equals(payReturn.getOrderState())){
				return null;
			}else{
				
				return payReturn.getResponseMsg();
			}
		
		}
		return null ;
		
	}
	
	/**
	 * 网银支付的信息
	 */
	public ReqPars getPayPar(OrdersBank obank,NpayOrder order,NpayTf56Bank oBank){
		BizData bizdata = new BizData();
		bizdata.setOut_order_no(order.getOrderid());//订单编号(自己生成的订单编号)
		bizdata.setAmount(order.getTxnamt());//金额
		bizdata.setFront_url("http://47.75.179.162:85/callback/dstbank/backend");//回调前台地址（我们自己的）
		bizdata.setNotify_url("http://47.75.179.162:85/callback/dstbank/backend");//回调后台地址（我们自己的）
		bizdata.setSubject(order.getSubject());
		bizdata.setBody(order.getBody());
		bizdata.setBank_code(oBank.getzBankId());//银行（只有网银支付的时候）//待查询
		bizdata.setTerminal_ip("127.0.0.1");//ip可以不传
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
		 par.setBiz_code("P100784");//网银支付固定值
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
	
	
	/**
	 * 代付的信息
	 */
	public ReqPars getDaifuPar(OrdersDaifu odaifu,NpayOrder order,NpayTf56Bank oBank){
	
		BizData bizdata = new BizData();
		bizdata.setOut_order_no(order.getOrderid());
		bizdata.setAmount(order.getTxnamt());
		bizdata.setNotify_url("http://47.75.179.162:85/callback/dstbank/backend");
		bizdata.setSubject(order.getSubject());
		bizdata.setBody(order.getBody());
		bizdata.setPp_type(0);
		bizdata.setBank_code(oBank.getzBankId());//银行编号
		bizdata.setAcc_no(odaifu.getAccNo());//卡号
		bizdata.setAcc_name(odaifu.getCustomerNm());//户名
		bizdata.setOpening_name(odaifu.getIssInsName());//开户行
		bizdata.setMobile(odaifu.getPhoneNo());//预留手机号
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

	
	/**
	 * 快捷支付短信
	 */
	@Override
	public String KLTKJPay(NpayOrder order) {
		
		Map<Object,Object> map = new HashMap<>();
		KLTDuanXin duanxin = new KLTDuanXin();
		KLTKJHead kjhead = new KLTKJHead();
		String info = order.getCustomerinfo();
		JSONObject obj = JSON.parseObject(info);
		String phone = obj.getString("phoneNo");
		String name = obj.getString("customerNm");

		duanxin.setPayerName(name);//姓名
		duanxin.setPayerTelephone(phone);//电话
		duanxin.setPayerAcctNo(order.getAccno());//卡号
		duanxin.setOrderAmount((long)order.getTxnamt());//金额
		duanxin.setPayerIdNo(order.getPayeridno());//省份证号
		duanxin.setCvv2(order.getCvv2());//安全码
		duanxin.setAcctValidDate(order.getAcctvaliddate());//有效日期
		duanxin.setOrderNo(order.getOrderid());//订单号
		
		kjhead.setMerchantId("903110153110001");//我们的商户号(固定值)
		String sign = KLTUtil.getSign(duanxin,kjhead,"742fa3ffd050fb441763bf8fb6c0594f");
		
		kjhead.setSign(sign);
		map.put("content", duanxin);
		map.put("head", kjhead);
		String parm = JSON.toJSON(map).toString();
		System.out.println(parm);
		System.out.println(sign);
		//String res = PayUtil.sendPost("http://47.99.165.66/pay/kltkjmessagezhuanfacs",parm);
		String res = HttpUtils.sendHttpPostRequest("http://47.99.165.66:8080/zhuanfa/kjDuanxin",parm,false);
		return res;
	}

	/**
	 * 快捷支付想开联通发确认支付信息
	 */
	@Override
	public String kjConfirm(NpayOrder order, String smsCode,String reqId) {
		KLTKJConfirmPay pay = new KLTKJConfirmPay();
//		private String originalRequestId;		//32	M	原请求流水号
		pay.setOriginalRequestId(reqId);
//		private String smsCode;		//10	M	短信验证码
		pay.setSmsCode(smsCode);
//		private String acctValidDate;		//4	O	信用卡有效期，信用卡必填，格式：yyMM
		pay.setAcctValidDate(order.getAcctvaliddate());
//		private String orderNo;		//50	M	原发短信商户订单号 
		pay.setOrderNo(order.getOrderid());
//		private Long orderAmount;		//10	M	商户订单金额,整型数字，金额与币种有关则单位是分，即10元提交时金额应为1000
		pay.setOrderAmount((long)order.getTxnamt());
//		private Integer orderCurrency;		//15	M	币种类型：156代表人民币、840代表美元、344代表港币目前只支持人民币
		pay.setOrderCurrency(156);
//		private String orderDatetime;		//30	M	商户下单时间,日期格式：yyyyMMddHHmmss，例如：20121116020101
		pay.setOrderDatetime(DateUtil.getNowTimeWithyyyyMMddHHmmss());
//		private Integer orderExpireDatetime;			//M	订单过期时间 单位分钟 整数
		pay.setOrderExpireDatetime(9999);
//		private String pickupUrl;		//256	O	客户的取货地址	需http://格式的完整路径，不能加?id=123这类自定义参数
//		private String cvv2;		//3	O	信用卡安全码，信用卡必填
		pay.setCvv2(order.getCvv2());
//		private String receiveUrl;		//256	M	交易结果后台通知地址	需http://格式的完整路径，不能加?id=123这类自定义参数
		pay.setReceiveUrl("http://47.99.165.66:8080/zhuanfa/payReturn");
		
//		private String productName;		//60	M	商品名称,英文或中文字符串，请勿首尾有空格字符
		pay.setProductName("名称");
//		private Integer productNum;		//10	O	商品数量
//		private String productId;		//50	O	商品代码
//		private String productDesc;		//200	O	商品描述
//		private Long productPrice;		//10	O	商品价格
		
//		private String ext1;			//40 O	拓展字段1
//		private String ext2;			//40 O	拓展字段2
//		private String split;			//2 C	订单分账标识，0-非分账订单 1-分账订单 默认为0
		
		Map<Object,Object> map = new HashMap<>();
		KLTKJHead head = new KLTKJHead();
		head.setMerchantId("903110153110001");//咱们的商户号(唯一的)
		String sign = KLTUtil.getSign(pay, head, "742fa3ffd050fb441763bf8fb6c0594f");
		head.setSign(sign);
		map.put("content", pay);
		map.put("head", head);
		String parm = JSON.toJSON(map).toString();
		
		//String res = PayUtil.sendPost("http://47.99.165.66/pay/kltkuaijiezhuanfacs",parm);
		String res = HttpUtils.sendHttpPostRequest("http://47.99.165.66:8080/zhuanfa/kjConfirm",parm,false);
		return res;
		
	}
	
	
	
	
	/**
	 * 得到网关支付的参数
	 */
	public String getKltGatewayParam(NpayOrder order){
		JSONObject obj = JSONObject.parseObject(order.getCustomerinfo());
		String name = obj.getString("customerNm");
		String phone = obj.getString("phoneNo");
		
		KLTGatewayContent cont = new KLTGatewayContent();
//		private String pickupUrl;		//256	M	支付成功后跳转的地址，建议填需http://格式的完整路径
		cont.setPickupUrl("http://47.75.179.162:85/klt/KltKuaijieCallBack/backend");
//		private String receiveUrl;		//256	M	交易结果后台通知地址，必填需http://格式的完整路径
		cont.setReceiveUrl("http://47.99.165.66:8080/zhuanfa/payReturn");
//		private String payerName;		//30	O	付款人姓名
		cont.setPayerName(name);
//		private String payerEmail;		//50	O	付款人邮件联系方式
		
//		private String payerTelPhone;		//16	O	付款人电话联系方式
		cont.setPayerTelPhone(phone);
//		private String payerAcctNo;		//128	O	微信公众号支付	微信小程序支付	微信APP支付	openid填入此字段
		
//		private String orderNo;	//64	M	商户订单号，只允许使用字母、数字、- 、_,并以字母或数字开头；每商户提交的订单号，必须在当天的该商户所有交易中唯一
		cont.setOrderNo(order.getOrderid());
//		private Long orderAmount;		//10	M	金额，金额与币种有关	如果是人民币，则单位是分，即10元提交时金额应为1000	如果是美元，单位是美分，即10美元提交时金额为1000
		cont.setOrderAmount((long)order.getTxnamt());
//		private Integer orderCurrency=156;		//3	M	币种类型：156代表人民币、840代表美元、344代表港币	目前只支持人民币
		cont.setOrderCurrency(156);
//		private String orderDateTime;		//14	M	商户订单时间，日期格式：yyyyMMDDhhmmss，例如：20121116020101
		cont.setOrderDateTime(DateUtil.getNowTimeWithyyyyMMddHHmmss());
//		private Integer orderExpireDatetime;  //14	O	订单过期时间，单位为分钟。最大值为9999分钟。	如填写则以商户上送时间为准，如不填写或填0或填非法值，则服务端默认该订单9999分钟后过期。
		cont.setOrderExpireDatetime(9999);
//		private String productName;		//60	M	商品名称，英文或中文字符串，请勿首尾有空格字符
		cont.setProductName("名称");
//		private Long productPrice;		//20	O	商品价格，英文或中文字符串
	
//		private Integer productNum;		//8	O	商品数量，英文或中文字符串
		
//		private String productId;		//20	O	商品代码，字母、数字或- 、_ 的组合；用于使用产品数据中心的产品数据，或用于市场活动的优惠
		
//		private String productDesc;		//400	O	商品描述，英文或中文字符串
		
//		private String ext1;		//40	O	扩展字段1
		
//		private String ext2;		//40	O	扩展字段2
		
//		private String termId;			//O	终端类型，app支付
		
//		private Integer payType;		//2	M	用户在支付时可以使用的支付方式，固定选择值：	1-个人网银支付	4-企业网银支付	20-微信扫码支付(c扫B)	36-微信公众号支付	60-微信小程序支付	43-微信app支付47-银联二维码	31-银联在线支付
		cont.setPayType(1);
		
//		private String  issuerId;//8	C	机构代码，payType=1或者4时必传，（测试环境：00000000）需要直连支付时填写。	参考附录支付机构代码
		cont.setIssuerId("00000000");
//		private String split; //2	C	订单分账标识，0-非分账订单 1-分账订单 默认为0
		
		
		Map<Object,Object> map = new HashMap<>();
		KLTKJHead head = new KLTKJHead();
		head.setMerchantId("903110153110001");//咱们的商户号(唯一的)
		String sign = KLTUtil.getSign(cont, head, "742fa3ffd050fb441763bf8fb6c0594f");
		head.setSign(sign);
		map.put("content",cont);
		map.put("head", head);
		String parm = JSON.toJSON(map).toString();
		return parm;
		
		
		
	}
	
	
	
	
	/**
	 * 代付的参数
	 */
	public String getDaifuParam(NpayOrder order,String bankName){
		JSONObject obj = JSONObject.parseObject(order.getCustomerinfo());
		String name = obj.getString("customerNm");
		String phone = obj.getString("phoneNo");
		KLTDaifuContent cont = new KLTDaifuContent();
//		private String mchtOrderNo;//		50	M	商户订单号，单笔实时代付中不能重复
		cont.setMchtOrderNo(order.getOrderid());
//		private String orderDateTime;//		14	M	商户订单时间，yyyyMMddHHmmss
		cont.setOrderDateTime(DateUtil.getNowTimeWithyyyyMMddHHmmss());
//		private String accountNo;//		30	M	收款方账号
		cont.setAccountNo(order.getAccno());
//		private String accountName;//		200	M	收款方姓名
		cont.setAccountName(name);
//		private String accountType;//		15	M	收款方账户类型，固定值，可取值：	1代表个人账户	2代表企业账户
		cont.setAccountType("1");
//		private String bankNo;//		30	M	收款方开户行行号（电子联行号）对公需要校验，对私填写000000000000（12个0）
		cont.setBankNo("000000000000");
//		private String bankName;//		200	M	收款方开户行名称（企业账户精确到支行，比如中国工商银行上海市浦东大道支行）个人账户只写银行名
		cont.setBankName(bankName);
//		private Long amt;//		10	M	金额，正整数，单位为分。例如，票款为1280元，则表示为“128000”
		cont.setAmt((long)order.getTxnamt());
//		private String purpose;//		50	M	用途
		cont.setPurpose("用途");
//		private String remark;//		200	O	备注
//		private String notifyUrl;//		200	M	用于接收开联通的交易结果通知
		cont.setNotifyUrl("http://47.99.165.66:8080/zhuanfa/daifuReturn");
		Map<Object,Object> map = new HashMap<>();
		KLTKJHead head = new KLTKJHead();
		head.setMerchantId("903110153110001");//咱们的商户号(唯一的)
		String sign = KLTUtil.getSign(cont, head, "742fa3ffd050fb441763bf8fb6c0594f");
		head.setSign(sign);
		map.put("content",cont);
		map.put("head", head);
		String parm = JSON.toJSON(map).toString();
		return parm;
	}

	
	

}
