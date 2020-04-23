package com.myd.controller;




import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.myd.dto.StatusResponse;


@Controller
@RequestMapping("/index")
public class IndexController{

    private static Logger logger = Logger.getLogger(IndexController.class);
    

    
    StatusResponse statusResponse;
    
    
    
    
    @RequestMapping(value = "/index", produces = "application/json;charset=utf-8")
    @ResponseBody()
    public String toStatus(HttpServletRequest request) {
	    String returnString = request.getAttribute("resultMsg").toString();
	    logger.info(">>>>>>>>"+returnString);
        return returnString;
    }

    
}
