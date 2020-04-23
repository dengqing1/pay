package com.myd.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myd.entity.*;
import com.myd.serviceimpl.MerchantService;
import com.myd.serviceimpl.TestService;
import com.myd.util.LoginValidation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述: 商户信息查询
 *
 * @auther: loujiawei
 * @date: 2018/11/30 18:35
 */
@Controller
public class MerchantController {

    private static Logger logger = Logger.getLogger(MerchantController.class);

    @Autowired
    private MerchantService merchantService;


    /**
     * 功能描述: 进入商户列表页面
     *
     * @auther: loujiawei
     * @date: 2018/12/4 13:37
     */
/*    @RequestMapping(value = "/toMerchantInformation", method = RequestMethod.GET)
    public String toMerchant() {

        return "merchant";
    }*/

    /**
     * 功能描述: 商户信息页面 获取商户信息数据
     *
     * @auther: loujiawei
     * @date: 2018/12/4 11:46
     */
  /* @RequestMapping(value = "/getMerchant", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getMerchantInformation(@RequestParam(defaultValue="1",required=true,value="pageNo") Integer pageNo) {
        Map<String, Object> map = new HashMap<>();
        Integer pageSize=1;//每页显示记录数
        //分页查询
        PageHelper.startPage(pageNo, pageSize);
        List<MerchantInformation> model = merchantService.getMerchantInformationData();
        PageInfo<MerchantInformation> pageInfo=new PageInfo<>(model);
        map.put("model",model);
        map.put("pageInfo",pageInfo);
        return map;
    }*/
    @RequestMapping(value = "/getMerchant", method = RequestMethod.GET)
    public String getMerchantInformation(ModelMap map, @RequestParam(defaultValue = "1", required = true, value = "pageNo") Integer pageNo) {
        Integer pageSize = 2;//每页显示记录数
        //分页查询
        PageHelper.startPage(pageNo, pageSize);
        List<MerchantInformation> model = merchantService.getMerchantInformationData();
        PageInfo<MerchantInformation> pageInfo = new PageInfo<>(model);
        map.addAttribute(pageInfo);
        return "merchant";
    }

    /**
     * 功能描述: 删除商户信息（修改状态0为不展示）
     *
     * @auther: loujiawei
     * @date: 2018/12/4 14:54
     */
    @RequestMapping(value = "/delMerchant", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteMerchant(String merchantIds) {
        boolean flag = merchantService.deleteMerchant(merchantIds);
        return flag;
    }

    /**
     * 功能描述: 回显修改商户的信息
     *
     * @auther: loujiawei
     * @date: 2018/12/5 10:20
     */
    @RequestMapping(value = "/getUpdateMerchantEcho", method = RequestMethod.GET)
    @ResponseBody
    public MerchantInformation getUpdateMerchantEcho(String merchantId) {

        MerchantInformation model = merchantService.getUpdateMerchantEcho(merchantId);

        return model;
    }

    /**
     * 功能描述: 修改商户信息
     *
     * @auther: loujiawei
     * @date: 2018/12/5 15:23
     */
    @RequestMapping(value = "/updateMerchantInformation", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateMerchantInformation(MerchantInformation model) {

        boolean flag = merchantService.updateMerchantInformation(model);

        return flag;
    }

    /**
     * 功能描述: 进入商户账户列表页面
     *
     * @auther: loujiawei
     * @date: 2018/12/4 13:37
     */
    @RequestMapping(value = "/toMerchantAccount", method = RequestMethod.GET)
    public String toMerchantAccount() {

        return "merchantAccount";
    }

    /**
     * 功能描述: 商户账户信息
     *
     * @auther: loujiawei
     * @date: 2018/12/5 10:27
     */
    @RequestMapping(value = "/getMerchantAccount", method = RequestMethod.GET)
    @ResponseBody
    public List<MerchantInformation> getMerchantAccount() {

        List<MerchantInformation> model = merchantService.getMerchantAccountData();

        return model;
    }

    /**
     * 功能描述: 进入支付信息页面
     *
     * @auther: loujiawei
     * @date: 2018/12/5 11:31
     */
    @RequestMapping(value = "/toPay", method = RequestMethod.GET)
    public String toPay() {

        return "pay";
    }

    /**
     * 功能描述: 获取支付信息
     *
     * @auther: loujiawei
     * @date: 2018/12/5 11:32
     */
    @RequestMapping(value = "/getPayInformation", method = RequestMethod.GET)
    @ResponseBody
    public List<MerchantOrder> getPayInformation() {

        List<MerchantOrder> model = merchantService.getPayInformationData();

        return model;
    }

    /**
     * 功能描述: 入金，代付汇总展示
     *
     * @auther: loujiawei
     * @date: 2018/12/6 13:16
     */
    @RequestMapping(value = "/getMerchantSum", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<MerchantInformation>> getMerchantSum() {
        Map<String, List<MerchantInformation>> map = new HashMap<>();
        //入金汇总
        List<MerchantInformation> intoSum = merchantService.getMerchantIntoSum();
        //代付汇总
        List<MerchantInformation> paidSum = merchantService.getMerchantPaidSum();
        map.put("intoSum", intoSum);
        map.put("paidSum", paidSum);
        return map;
    }

    /**
     * 功能描述: 进入商户录入页面
     *
     * @auther: loujiawei
     * @date: 2018/12/7 10:01
     */
    @RequestMapping(value = "/toAddMerchant", method = RequestMethod.GET)
    public String addMerchant() {

        return "addMerchant";
    }

    /**
     * 功能描述: 进入商户开通页面
     *
     * @auther: loujiawei
     * @date: 2018/12/7 10:01
     */
    @RequestMapping(value = "/toMerchantOpen", method = RequestMethod.GET)
    public String toMerchantOpen() {

        return "merchantOpen";
    }

    /**
     * 功能描述: 进入通道配置页面
     *
     * @auther: loujiawei
     * @date: 2018/12/7 10:01
     */
    @RequestMapping(value = "/toChannelConfiguration", method = RequestMethod.GET)
    public String toChannelConfiguration() {

        return "channelConfiguration";
    }

    /**
     * 功能描述: 商户录入
     *
     * @auther: loujiawei
     * @date: 2018/12/7 11:40
     */
    @RequestMapping(value = "/addMerchant", method = RequestMethod.POST)
    public void addMerchant(MerchantInformation model) {

        //  merchantService.addMerchant(model);
    }

    /**
     * 功能描述: 执行商户开通
     *
     * @auther: loujiawei
     * @date: 2018/12/7 11:40
     */
//    @RequestMapping(value = "/addMerchantOpen", method = RequestMethod.POST)
//    public void addMerchantOpen(MerchantServiceOpen model) {
//
//        // merchantService.addMerchantOpen(model);
//    }

    /**
     * 功能描述: 执行通道配置
     *
     * @auther: loujiawei
     * @date: 2018/12/7 11:40
     */
//    @RequestMapping(value = "/addChannelConfiguration", method = RequestMethod.POST)
//    public void addChannelConfiguration(MerchantConfiguration model) {
//        System.out.println(model);
//        // merchantService.addChannelConfiguration(model);
//
//    }
}
