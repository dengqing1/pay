package com.myd.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.myd.controller.OrderController;
import com.myd.dto.StatusResponse;
import com.myd.entity.NpayMerInfo;
import com.myd.entity.TestModel;
import com.myd.service.StatusService;
import com.myd.serviceimpl.TestCodeService;
import com.myd.serviceimpl.TestCodeServiceA;
import com.myd.serviceimpl.TestCodeServiceT;
import com.myd.util.LoginValidation;
import com.myd.util.MapFormat;
import com.myd.util.PayUtil;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


/**
 * Created by you on 2018/11/21.
 */
public class ApiInterceptor implements HandlerInterceptor {

	private static Logger logger = Logger.getLogger(ApiInterceptor.class);
    @Autowired
    private TestCodeService testCodeService;

    @Autowired
    private TestCodeServiceT testCodeServiceT;

    @Autowired
    private TestCodeServiceA testCodeServiceA;

    @Autowired
    private RedisTemplate redisTemplate;
    
    @Autowired
    private StatusService statusService;
    
    StatusResponse statusResponse;
    
    private boolean flag=true;

    /**
     *
     * 功能描述: 拦截url
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
    	if(!checkRequest(request)) {
    		String resultMsg =  JSONObject.toJSONString(statusResponse);	
    		request.setAttribute("resultMsg", resultMsg);
    		RequestDispatcher requestDispatcher =request.getRequestDispatcher("../index/index.do");
    		//response.sendRedirect("../index/index.do?resultMsg=" + resultMsg);
    		try {
    			requestDispatcher.forward(request,response);
			} catch (Exception e) {
				RequestDispatcher dispatcher =request.getRequestDispatcher("/index/index.do");
				dispatcher.forward(request,response);
			}
    		
    		return false;
    	}
    	return true;
    		
    		
    	
//        SortedMap<String, Object> parameters = new TreeMap<String, Object>();
//        LoginValidation loginValidation = new LoginValidation();
//
//        String code = request.getParameter("code");
//        String signMethod = request.getParameter("signMethod");
//        String signature = request.getParameter("signature");
//        String merchantId = request.getParameter("merchantId");
//        parameters.put("code",code);
//        parameters.put("signMethod",signMethod);
//        parameters.put("signature",signature);
//        parameters.put("merchantId",merchantId);
//        String newSignature = PayUtil.signature(parameters);
//
//        //获取用户的session信息
//       // Object name = request.getSession().getAttribute("name");
//        /*if (null != name) {
//            Object ValidationToken = loginValidation.validationRedisKey(name, request,redisTemplate);
//            //判断redis是否存在token
//            if (null != ValidationToken) {
//            } else {
//                //重定向到登录页面
//                response.sendRedirect(request.getContextPath() + "/fengmai/toLogin");
//                return false;
//            }
//        } else {
//            //重定向到登录页面
//            response.sendRedirect(request.getContextPath() + "/fengmai/toLogin");
//            return false;
//        }*/
//        if (!StringUtils.isEmpty(code)) {
//            //获取code的service
//            String serviceName = MapFormat.GetServiceNameByCode(code);
//            //判断商户下是否存在对应的code
//            boolean merchantIdFlag = MapFormat.ValidationMerchantId(merchantId, code);
//            //判断加密是否匹配
//            boolean md5Flag = MapFormat.ValidationSignature(code, merchantId, signature);
//            if (merchantIdFlag == true) {
//                if (md5Flag == true) {
//                    if ("TestCodeService".equals(serviceName)) {
//                        testCodeService.getData();
//                    }
//                }
//            }
//        }
//        return true;
    }
    
    public boolean checkRequest(HttpServletRequest request) throws IOException {
		statusResponse = new StatusResponse();
    	try {
			SortedMap<String, Object> sortedMap = PayUtil.mapToSortedMap(request);
			String merchantId =sortedMap.get("merchantId").toString();
			if(sortedMap.get("subject")!=null) {
				sortedMap.put("subject",new String(Base64.decodeBase64(sortedMap.get("subject").toString())));
			}
			if(sortedMap.get("body")!=null) {
				sortedMap.put("body",new String(Base64.decodeBase64(sortedMap.get("body").toString())));
			}
			
			logger.info(merchantId + ">>>>>>>>验签");
			NpayMerInfo merInfo = statusService.getMerInfoByMerId(merchantId);
			 
			if(merInfo == null || merInfo.getMerOpenStatus() != 1) {
				statusResponse.setCode("2000");
				statusResponse.setMsg("无效商户");
				return false;
			}
			
			String signature = PayUtil.signMethod(sortedMap,merInfo.getMerSecretKey());
			statusResponse.setSignature(signature);
			String signatureNew = sortedMap.get("signature").toString().replace(" ","+");
			logger.info("传来的签名-->"+sortedMap.get("signature")+">>>>>>>>>>替换的签名"+signatureNew);
			logger.info("自己生成的签名-->"+signature);
			
			
			if(signature.equals(signatureNew)) {
				logger.info("签名成功");           
				return true;
			}else {
				/*	//进行再次请求
				if(flag){
					logger.error(">>>>>>签名失败-----再次请求");
					flag=false;
					
					 // 所有请求第一个进入的方法
			        String reqURL = request.getRequestURL().toString();
//			        String ip = request.getRemoteHost();

			        InputStream is = request.getInputStream();
			        StringBuilder responseStrBuilder = new StringBuilder();
			        BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			        String inputStr;
			        while ((inputStr = streamReader.readLine()) != null) { responseStrBuilder.append(inputStr); }
			             System.out.println("请求参数: " + responseStrBuilder.toString ());
			        String parmeter = responseStrBuilder.toString();
			        
					
					PayUtil.sendPost(request.getRequestURL().toString(),PayUtil.getParam(sortedMap));
					return false;
				}else{*/
					flag=true;
					statusResponse.setCode("0002");
					statusResponse.setMsg("签名失败");
					return false;
			}
			
		} catch (Exception e) {
			logger.error(">>>>>>签名方法出错",e);
			statusResponse.setCode("0002");
			statusResponse.setMsg("签名错误");
			return false;
		}
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object
            o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse
            httpServletResponse, Object o, Exception e) throws Exception {

    }
    
    

}
