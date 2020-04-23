package com.myd.entity;

import java.math.BigDecimal;

public class DailyOrderCheck {
    private String orderid;//npay订单号

    private Byte t1;

    private Integer status;//1001：成功，0：待支付， 其他：失败

    private String gateway;//支付方式, wxpay,alipay,qqpay,jdpay,bank,daifu

    private Integer txnamt;//订单金额，单位：分

    private Integer amt;//订单金额，单位：分

    private BigDecimal bb;

    private BigDecimal aa;

    private BigDecimal ff;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Byte getT1() {
        return t1;
    }

    public void setT1(Byte t1) {
        this.t1 = t1;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway == null ? null : gateway.trim();
    }

    public Integer getTxnamt() {
        return txnamt;
    }

    public void setTxnamt(Integer txnamt) {
        this.txnamt = txnamt;
    }

    public Integer getAmt() {
        return amt;
    }

    public void setAmt(Integer amt) {
        this.amt = amt;
    }

    public BigDecimal getBb() {
        return bb;
    }

    public void setBb(BigDecimal bb) {
        this.bb = bb;
    }

    public BigDecimal getAa() {
        return aa;
    }

    public void setAa(BigDecimal aa) {
        this.aa = aa;
    }

    public BigDecimal getFf() {
        return ff;
    }

    public void setFf(BigDecimal ff) {
        this.ff = ff;
    }
}