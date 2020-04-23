package com.myd.entity;

import java.math.BigDecimal;

/**
 *
 * 功能描述: 订单查询
 *
 * @auther: loujiawei
 * @date: 2018/12/5 16:03
 */
public class MerchantOrder {

    //商户订单号
    private String merchant_order_id;
    //订单日期
    private String order_date;
    //商户号
    private String merchant_id;
    //商户名称
    private String merchant_name;
    //平台订单号
    private String platform_order_id;
    //订单金额
    private BigDecimal order_money;
    //手续费
    private BigDecimal poundage;
    //产品类型
    private String product_type;
    //银行
    private String bank_name;
    //通道简称
    private String channel_abbreviation;
    //通道名称
    private String channel_name;
    //订单状态
    private String order_status;
    //通道状态码
    private String channel_status_code;
    //状态详情
    private String status_details;

    public String getMerchant_order_id() {
        return merchant_order_id;
    }

    public void setMerchant_order_id(String merchant_order_id) {
        this.merchant_order_id = merchant_order_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getPlatform_order_id() {
        return platform_order_id;
    }

    public void setPlatform_order_id(String platform_order_id) {
        this.platform_order_id = platform_order_id;
    }

    public BigDecimal getOrder_money() {
        return order_money;
    }

    public void setOrder_money(BigDecimal order_money) {
        this.order_money = order_money;
    }

    public BigDecimal getPoundage() {
        return poundage;
    }

    public void setPoundage(BigDecimal poundage) {
        this.poundage = poundage;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getChannel_abbreviation() {
        return channel_abbreviation;
    }

    public void setChannel_abbreviation(String channel_abbreviation) {
        this.channel_abbreviation = channel_abbreviation;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getChannel_status_code() {
        return channel_status_code;
    }

    public void setChannel_status_code(String channel_status_code) {
        this.channel_status_code = channel_status_code;
    }

    public String getStatus_details() {
        return status_details;
    }

    public void setStatus_details(String status_details) {
        this.status_details = status_details;
    }
}
