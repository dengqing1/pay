package com.myd.controller;




import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.myd.dto.BalanceRequest;
import com.myd.dto.BalanceResponse;
import com.myd.dto.StatusRequest;
import com.myd.dto.StatusResponse;
import com.myd.entity.NpayMerchantBalance2018;
import com.myd.entity.NpayOrder;
import com.myd.service.StatusService;

@Controller
public class BalanceController{

    private static Logger logger = Logger.getLogger(BalanceController.class);
    
    @Autowired
    private StatusService statusService;
    
    BalanceResponse balanceResponse;
    
    
    
    
    @RequestMapping(value = "/balance", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody()
    public String toStatus(BalanceRequest balanceRequest) {
    	//RequestStatus requestStatus = requestProperty(request, RequestStatus.class);
    	balanceResponse = new BalanceResponse();
    	balanceResponse = bliudRequest(balanceRequest);
    	if(!balanceResponse.isCheckBool()) {
    		return  JSONObject.toJSONString(balanceResponse);
    	}
	    logger.info(">>>>>>>>商户余额查询");
	    NpayMerchantBalance2018 balance = statusService.getBanlanceByMerId(balanceRequest.getMerchantId());
		    if(balance==null) {
		    	balanceResponse.setCode("0002");
		    	balanceResponse.setMsg("该商户暂无资料");
		    }else {
		    	balanceResponse.setCode("0000");
		    	balanceResponse.setMsg("查询成功");
		    	balanceResponse.setBalance(balance.getBalance().toString());
		    	balanceResponse.setMerchantId(balance.getMerchantid());
		    }
	    String returnString = JSONObject.toJSONString(balanceResponse);
	    logger.info(">>>>>>>>"+returnString);
        return returnString;
    }

    
    private BalanceResponse bliudRequest(BalanceRequest balanceRequest) {
    	balanceResponse.setCheckBool(false);
    	if(StringUtil.isEmpty(balanceRequest.getSignMethod())) {
    		balanceResponse.setCode("0002");
    		balanceResponse.setMsg("签名方法不能为空");
    	}else if(StringUtil.isEmpty(balanceRequest.getSignature())) {
    		balanceResponse.setCode("0002");
    		balanceResponse.setMsg("签名信息不能为空");
    	}else if(StringUtil.isEmpty(balanceRequest.getMerchantId())) {
    		balanceResponse.setCode("0002");
    		balanceResponse.setMsg("商户的唯一编号不能为空");
    	}else {
    		balanceResponse.setCheckBool(true);
    	}
    	return balanceResponse;
    }
    
}
