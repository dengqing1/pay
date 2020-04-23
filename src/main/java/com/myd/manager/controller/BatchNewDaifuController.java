package com.myd.manager.controller;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.myd.config.PayConfig;
import com.myd.controller.JuhePayController;
import com.myd.controller.JuhePayReturnController;
import com.myd.entity.BatchDaifu;
import com.myd.entity.Gather;
import com.myd.entity.MerchantAsynchronousResult;
import com.myd.entity.NpayChannels;
import com.myd.entity.NpayMerFeeRates;
import com.myd.entity.NpayMerInfo;
import com.myd.entity.NpayMerchantBalance2018;
import com.myd.entity.NpayOrder;
import com.myd.entity.OrdersDaifu;
import com.myd.entity.juheDaifuReturn;
import com.myd.manager.service.BatchNewDaifuService;
import com.myd.manager.service.GatherService;
import com.myd.manager.service.NpayMerchantBalance2018Service;
import com.myd.service.BatchDaifuService;
import com.myd.service.NewOrderSettleService;
import com.myd.service.NpayChannelsService;
import com.myd.service.NpayMerInfoService;
import com.myd.service.NpayMerchantBalanceService;
import com.myd.service.NpayOrderService;
import com.myd.service.PayService;
import com.myd.service.StatusService;
import com.myd.util.DateUtil;
import com.myd.util.Msg;
import com.myd.util.OfTime;
import com.myd.util.PayUtil;
import com.myd.util_wx.ZjxPayUtil;

@Controller
@RequestMapping("/daifu")
public class BatchNewDaifuController {

	private static Logger logger = Logger.getLogger(BatchNewDaifuController.class);
	@Autowired
	private BatchDaifuService batchDaifuService;
	
	@Autowired
	private GatherService gatherService;
	
	@Autowired
	private PayService payService;
	@Autowired
	private NpayMerchantBalanceService npayMerchantBalanceService;
	
	
	@Autowired
	private NpayMerInfoService npayMerInfoService;
	@Autowired
	private NpayOrderService npayOrderService ;

	@Autowired
	private NewOrderSettleService newOrderSettleService;
	
    @Autowired
    private StatusService statusService;
	@Autowired
	private NpayMerchantBalance2018Service    npayMerchantBalance2018Service;
	
	
	/**
	 * 批量代付列表
	 * @return
	 */
	@RequestMapping(value = "/daifulist", method = {RequestMethod.GET,RequestMethod.POST})
	public String daifulist(HttpServletRequest request,Integer page){
		if(page==null){
			page = 1;
		}
		Gather gather = new Gather();
		gather.setGatherMerchantId(request.getParameter("gatherMerchantId"));
		gather.setGatherMerOrderId(request.getParameter("gatherMerOrderId"));
		gather.setGatherState(request.getParameter("gatherState"));
		
		List<Map<String,Object>> list = batchDaifuService.selectByDaifu(gather,page);
		request.setAttribute("list",list);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		request.setAttribute("pageInfo", pageInfo);
		
		
		request.setAttribute("gatherMerchantId", gather.getGatherMerchantId());
		request.setAttribute("gatherMerOrderId", gather.getGatherMerOrderId());
		request.setAttribute("gatherState", gather.getGatherState());
		
		
		return "batch_daifu_list";
		
	}
	
	
	/**
	 * 审核通过
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updateShtg", method = RequestMethod.POST)
	@ResponseBody
	public Msg updateShtg(String id) throws Exception{

		Gather ga = gatherService.selectById(id);
		
		
		
		//通过 ，立即提现	
		OrdersDaifu daifu = new OrdersDaifu();	
//		签名方法 	signMethod 	M 	取值：MD5
		
//		签名信息 	signature 	M 	签名信息，详见签名机制
//		消息版本号 	version 	M 	取值：1.0.0
		daifu.setVersion("1.0.0");
//		交易类型 	txnType 	M 	取值：12
		daifu.setTxnType("1");
//		交易子类型 	txnSubType 	M 	取值：01
		daifu.setTxnSubType("0");
//		产品类型 	bizType 	M 	取值：000401
		daifu.setBizType("000401");
//		接入类型 	accessType 	M 	取值：0
		daifu.setAccessType("0");
//		接入方式 	accessMode 	M 	取值：01
		daifu.setAccessMode("0");
//		商户号 	merchantId 	M 	由支付平台分配给商户的唯一编号
		daifu.setMerchantId(ga.getGatherMerchantId());
//		商户订单号 	merOrderId 	M 	商户订单号
//		daifu.setMerOrderId(DateUtil.getOrderId(new Date()));
		daifu.setMerOrderId(ga.getGatherMerOrderId());
//		账号 	accNo 	M 	银行卡卡号
		daifu.setAccNo(ga.getGatherAccno());
//		身份信息 	customerNm 	M 	开户账号名
		daifu.setCustomerNm(ga.getGatherCustomerName());
//		手机号 	phoneNo 	M 	银行开户预留手机号
		daifu.setPhoneNo(ga.getGatherPhoneNo());
//		对公对私标志 	ppFlag 	M 	取值：
		daifu.setPpFlag("01");
//		00：对公
//		01：对私
//		开户行名 	issInsName 	M 	如果ppFlag为00对公的时候必填
		daifu.setIssInsName("银行");
//		订单发送时间 	txnTime 	M 	YYYYMMDDHHMMSS举例：20171128090356
		daifu.setTxnTime(DateUtil.getNowTimeWithyyyyMMddHHmmss());
//		交易金额 	txnAmt 	M 	单位为分
		daifu.setTxnAmt(String.valueOf(ga.getGatherTxnAmt()));
//		交易币种 	currency 	M 	三位 ISO 货币代码，目前仅支持人民币 CNY
		daifu.setCurrency("CNY");
//		后台通知地址 	backUrl 	M 	商户端接收交易异步通知的地址，在银行返回最终交易结果后支付平台通过该地址进行异步通知
		daifu.setBackUrl("http://47.75.179.162:8080/fengmai/mechrtklt/callback");
		
//		支付方式 	payType 	M 	取值：0401
		daifu.setPayType("0401");
//		银行编号 	bankId 	M 	8位银行编码，详见数据字典
		daifu.setBankId(ga.getGatherBankId());
//		网关 	gateway 	M 	daifu
		daifu.setGateway("daifu");
//		商品标题 	subject 	M 	上送报文时需BASE64编码，参与签名计算时不需要编码
		daifu.setSubject("biaoti");
//		商品描述 	body 	M 	上送报文时需BASE64编码，参与签名计算时不需要编码
		daifu.setBody("miaoshu");
//		用途 	purpose 	O 	
		daifu.setPurpose("交易");
		
		NpayMerInfo merInfo = statusService.getMerInfoByMerId(ga.getGatherMerchantId());
		
		
		 SortedMap<String, Object> parameters = new TreeMap<String, Object>();
		 parameters = PayUtil.objectToSortedMap(daifu);
		 String temp = PayUtil.signMethod(parameters,merInfo.getMerSecretKey());//得到方法签名
		 parameters.put("subject",  new String(Base64.encodeBase64(daifu.getSubject().getBytes("UTF-8")), "UTF-8")); //商品标题
		 parameters.put("body", new String(Base64.encodeBase64(daifu.getBody().getBytes("UTF-8")), "UTF-8"));//商品描述
		 String signnature = PayUtil.signature(parameters);//得到排好序的字符串
		 String param =signnature+"&signMethod=MD5"+"&signature="+temp;
		 
		 logger.info("审核请求参数>>>>>"+param);
		String str = PayUtil.sendPost(PayConfig.DAIFUPAYURL,param);
		JSONObject o = JSON.parseObject(str);
		if(str != null && str != ""){ 
		
			if(!"1".equals(o.getString("success"))){
				return Msg.error(o.getString("msg"));
			}
		}else{
			return Msg.error(o.getString("msg"));
		}
		
		logger.info("审核请求>>>>>"+str);
		
//		代付通过后再添加订单
		Gather gather = new Gather();
		gather.setGatherId(Integer.parseInt(id));
		gather.setGatherState("2");
		gather.setGatherUpdateTime(OfTime.getLongTime());//审核时间
		gatherService.updateByExampleSelective(gather);
		logger.info("审核成功，添加订单>>>>>"+str);
		
		return Msg.success();
		
	}
	
	
	
	

	
	/**
	 * 审核不通过
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updateShbtg", method = RequestMethod.POST)
	@ResponseBody
	public Msg updateShbtg(String id){
		Gather gather = new Gather();
		gather.setGatherId(Integer.parseInt(id));
		gather.setGatherState("3");
		gather.setGatherUpdateTime(OfTime.getLongTime());//审核时间
		gatherService.updateByExampleSelective(gather);
		
	/*	
		Gather ga = gatherService.selectById(id);
		String merchantId = ga.getGatherMerchantId();
		String txnAmt =ga.getGatherTxnAmt() ;
		
//		审核不通过     返回原来的金额
//		查询原来的金额
		 NpayMerchantBalance2018 balance2018 = npayMerchantBalance2018Service.selectByPrimaryKey(merchantId);
		
//		 txnAmt    txnmon
//		  余额    +  本次交易金额    
		 String yue = new BigDecimal(balance2018.getBalance()).add(new BigDecimal(txnAmt)).stripTrailingZeros().toPlainString();

//		  在途余额    +  本次交易金额  
		 String ztyue = new BigDecimal(balance2018.getBalanceAvailable()).subtract(new BigDecimal(txnAmt)).stripTrailingZeros().toPlainString();
		 
		 
		 
			NpayMerchantBalance2018   merchant2018=new NpayMerchantBalance2018();
			merchant2018.setMerchantid(merchantId);
			merchant2018.setBalance(Long.parseLong(yue));
			merchant2018.setBalanceAvailable(Long.parseLong(ztyue));
			
//			修改账户余额
			npayMerchantBalance2018Service.updateByPrimaryKeySelective(merchant2018);
		
		
	*/	
		
		return Msg.success();
	}
	
	
	
	
	
	
}
