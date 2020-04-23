package com.myd.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NpayBalanceOplogs {
    private Integer id;

    private String merId;

    private String channelMerId;

    private String txnamt;

    private Date createAt;

    private String orderid;

    private Integer inFee;

    private Integer outFee;

    private Date updateAt;

    private Integer settleAmount;

    private String comment;

    private Integer opType;//类型
    
    
    private String txnAmts;//金额(元)
    
    
    public String getTxnAmts() {
		return txnAmts;
	}

	public void setTxnAmts(String txnAmts) {
		this.txnAmts = txnAmts;
	}

	public String getCreateTime() {
		if(createAt != null){
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createAt);
		}
		return "";
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId == null ? null : merId.trim();
    }

    public String getChannelMerId() {
        return channelMerId;
    }

    public void setChannelMerId(String channelMerId) {
        this.channelMerId = channelMerId == null ? null : channelMerId.trim();
    }

    public String getTxnamt() {
        return txnamt;
    }

    public void setTxnamt(String txnamt) {
        this.txnamt = txnamt;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Integer getInFee() {
        return inFee;
    }

    public void setInFee(Integer inFee) {
        this.inFee = inFee;
    }

    public Integer getOutFee() {
        return outFee;
    }

    public void setOutFee(Integer outFee) {
        this.outFee = outFee;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Integer getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(Integer settleAmount) {
        this.settleAmount = settleAmount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }
}