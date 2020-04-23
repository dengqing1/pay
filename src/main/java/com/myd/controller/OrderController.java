package com.myd.controller;




import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.myd.dto.StatusRequest;
import com.myd.dto.StatusResponse;
import com.myd.entity.NpayOrder;
import com.myd.service.StatusService;

@Controller
@RequestMapping("/order")
public class OrderController{

    private static Logger logger = Logger.getLogger(OrderController.class);
    
    @Autowired
    private StatusService statusService;
    
    StatusResponse statusResponse;
    
    
    
    
    @RequestMapping(value = "/status", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody()
    public String toStatus(StatusRequest statusRequest) {
    	//RequestStatus requestStatus = requestProperty(request, RequestStatus.class);
    	statusResponse = new StatusResponse();
    	statusResponse = bliudRequest(statusRequest);
    	if(!statusResponse.isCheckBool()) {
    		return  JSONObject.toJSONString(statusResponse);
    	}
	    logger.info(">>>>>>>>交易订单查询");
		    NpayOrder npayOrder = statusService.getOrderById(statusRequest.getMerOrderId(), statusRequest.getMerchantId());
		    if(npayOrder==null) {
		    	statusResponse.setCode("0002");
		    	statusResponse.setMsg("查无此交易");
		    }else {
			    statusResponse.setCode("0000");
			    statusResponse.setMsg("查询成功");
			    statusResponse.setMerchantId(npayOrder.getMerchantid());
			    statusResponse.setMerOrderId(npayOrder.getMerorderid());
			    statusResponse.setStatus(npayOrder.getStatus().toString());
			    statusResponse.setStatusDesc(npayOrder.getCstatus());
			    statusResponse.setTxnAmt(npayOrder.getTxnamt().toString());
		    }
	    String returnString = JSONObject.toJSONString(statusResponse);
	    logger.info(">>>>>>>>"+returnString);
        return returnString;
    }

    
    private StatusResponse bliudRequest(StatusRequest statusRequest) {
    	statusResponse.setCheckBool(false);
    	if(StringUtil.isEmpty(statusRequest.getSignMethod())) {
    		statusResponse.setCode("0002");
	    	statusResponse.setMsg("签名方法不能为空");
    	}else if(StringUtil.isEmpty(statusRequest.getSignature())) {
    		statusResponse.setCode("0002");
	    	statusResponse.setMsg("签名信息不能为空");
    	}else if(StringUtil.isEmpty(statusRequest.getMerchantId())) {
    		statusResponse.setCode("0002");
	    	statusResponse.setMsg("商户的唯一编号不能为空");
    	}else if(StringUtil.isEmpty(statusRequest.getMerOrderId())) {
    		statusResponse.setCode("0002");
	    	statusResponse.setMsg("商户的订单号不能为空");
    	}else {
    		statusResponse.setCheckBool(true);
    	}
    	return statusResponse;
    }
    
}
