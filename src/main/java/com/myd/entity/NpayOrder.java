package com.myd.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class NpayOrder extends NpayOrderKey {
    private Integer oid;//id自增

    private String orderid;//订单id自己生成的
	
    private String merchantid;//商户id

    private String merorderid;//订单id商户传来

    private Integer txnamt;//交易金额单位（分）

    private String fronturl;//商户前台通知地址

    private String backurl;//商户后台通知地址

    private String subject;//商品标题base64编码

    private String body;//商品描述64编码

    private String merresv1;

    private String userid;//商户用户id传来的

    private String signature;//签名

    private String signmethod;//签名方法

    private String txntype;//交易类型

    private String txnsubtype;//交易子类型

    private String biztype;//产品类型

    private String version;//版本号

    private Date createAt;//订单创建时间

    private String accesstype;//接入类型

    private String accessmode;//接入方式

    private String currency;//交易币种

    private String paytype;//支付方式

    private Date updateAt;//更新时间

    private String txntime;//订单发送时间（商户传来的）

    private Integer status;//订单状态

    private String gateway;//网关（传来）

    private String customerip;//终端用户ip(传来)

    private String bankid;//银行编号

    private Byte dctype;//借贷类型(传来)

    private Integer notifytimes;

    private String statusdesc;//订单状态描述

    private Byte refundtimes;//

    private String refundorderid;//退款订单号

    private String inFeeAmount;//下家费率

    private String inFee;//下家手续费金额   费用(CNY)

    private String inFeeType;//下家费率类型，percent, fix

    private String outFeeType;//上家费率类型，percent, fix

    private String outFee;//上家手续费金额

    private String outFeeAmount;//上家费率

    private String channelMerAbbr;

    private String channelId;

    private String channelMerId;

    private String accno;//账号(卡号传来)

    private String customerinfo;//商户信息(名字跟手机号的json格式字符串)

    private String ppflag;//对公对私标志

    private Boolean notifysuccess;

    private Integer checkStatus;//对账状态

    private String cstatus;

    private Date lastUpdate;//最后更新时间

    private Byte t1;
    
    
    private Integer count; //订单数量
    
    private String msName; //商户简称
    
    private String txnAmts;//数额(CNY)

    private String bankName;//银行名称
    
    private String customerNm;//姓名
    
    private String createAtTimeBegin;
    
    private String createAtTimeEnd;
    
    
    private String cvv2;//信用卡安全码

    private String payeridno;//身份证信息

    private String acctvaliddate;//信用卡有效日期
    
    
	public String getStatusStr() {
		String str = "其他";
		switch (this.status) {
		case 1000:
			str="初始状态";
			break;
		case 1001:
			str="交易成功";
			break;
		case 1002:
			str="交易失败";
			break;
		case 1111:
			str="进行中";
			break;
		default:
			break;
		}
		
		return str;
	}

	public String getCreateAtTimeBegin() {
		return createAtTimeBegin;
	}

	public void setCreateAtTimeBegin(String createAtTimeBegin) {
		this.createAtTimeBegin = createAtTimeBegin;
	}

	public String getCreateAtTimeEnd() {
		return createAtTimeEnd;
	}

	public void setCreateAtTimeEnd(String createAtTimeEnd) {
		this.createAtTimeEnd = createAtTimeEnd;
	}

	public String getCustomerNm() {
		return customerNm;
	}

	public void setCustomerNm(String customerNm) {
		if(StringUtils.isNotBlank(customerNm)){
			this.customerNm = JSONObject.parseObject(customerNm).getString("customerNm");
		}else{
			this.customerNm = customerNm;
		}
	}

	public String getBankName() {
		if(bankName == null){
			return "其他";
		}
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

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

	/**
	 * 订单类型
	 */
	public String getTypes(){
    	String types = "";
    	if(this.gateway == null){
    		this.gateway ="";
    	}
    	switch (this.gateway) {
		case "wxpay":
			types="微信支付";
			break;
		case "alipay":
			types="支付宝支付";
			break;
		case "qqpay":
			types="QQ支付";
			break;
		case "jdpay":
			types="京东支付";
			break;
		case "bank":
			types="网银";
			break;
		case "daifu":
			types="代付";
			break;
		default:
			break;
		}
    	return types;
    }
    
    
    
    
    public String getMsName() {
		return msName;
	}

	public void setMsName(String msName) {
		this.msName = msName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid == null ? null : merchantid.trim();
    }

    public String getMerorderid() {
        return merorderid;
    }

    public void setMerorderid(String merorderid) {
        this.merorderid = merorderid == null ? null : merorderid.trim();
    }

    public Integer getTxnamt() {
        return txnamt;
    }

    public void setTxnamt(Integer txnamt) {
        this.txnamt = txnamt;
    }

    public String getFronturl() {
        return fronturl;
    }

    public void setFronturl(String fronturl) {
        this.fronturl = fronturl == null ? null : fronturl.trim();
    }

    public String getBackurl() {
        return backurl;
    }

    public void setBackurl(String backurl) {
        this.backurl = backurl == null ? null : backurl.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    public String getMerresv1() {
        return merresv1;
    }

    public void setMerresv1(String merresv1) {
        this.merresv1 = merresv1 == null ? null : merresv1.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public String getSignmethod() {
        return signmethod;
    }

    public void setSignmethod(String signmethod) {
        this.signmethod = signmethod == null ? null : signmethod.trim();
    }

    public String getTxntype() {
        return txntype;
    }

    public void setTxntype(String txntype) {
        this.txntype = txntype == null ? null : txntype.trim();
    }

    public String getTxnsubtype() {
        return txnsubtype;
    }

    public void setTxnsubtype(String txnsubtype) {
        this.txnsubtype = txnsubtype == null ? null : txnsubtype.trim();
    }

    public String getBiztype() {
        return biztype;
    }

    public void setBiztype(String biztype) {
        this.biztype = biztype == null ? null : biztype.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getAccesstype() {
        return accesstype;
    }

    public void setAccesstype(String accesstype) {
        this.accesstype = accesstype == null ? null : accesstype.trim();
    }

    public String getAccessmode() {
        return accessmode;
    }

    public void setAccessmode(String accessmode) {
        this.accessmode = accessmode == null ? null : accessmode.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype == null ? null : paytype.trim();
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getTxntime() {
        return txntime;
    }

    public void setTxntime(String txntime) {
        this.txntime = txntime == null ? null : txntime.trim();
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

    public String getCustomerip() {
        return customerip;
    }

    public void setCustomerip(String customerip) {
        this.customerip = customerip == null ? null : customerip.trim();
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid == null ? null : bankid.trim();
    }

    public Byte getDctype() {
        return dctype;
    }

    public void setDctype(Byte dctype) {
        this.dctype = dctype;
    }

    public Integer getNotifytimes() {
        return notifytimes;
    }

    public void setNotifytimes(Integer notifytimes) {
        this.notifytimes = notifytimes;
    }

    public String getStatusdesc() {
        return statusdesc;
    }

    public void setStatusdesc(String statusdesc) {
        this.statusdesc = statusdesc == null ? null : statusdesc.trim();
    }

    public Byte getRefundtimes() {
        return refundtimes;
    }

    public void setRefundtimes(Byte refundtimes) {
        this.refundtimes = refundtimes;
    }

    public String getRefundorderid() {
        return refundorderid;
    }

    public void setRefundorderid(String refundorderid) {
        this.refundorderid = refundorderid == null ? null : refundorderid.trim();
    }

    public String getInFeeAmount() {
        return inFeeAmount;
    }

    public void setInFeeAmount(String inFeeAmount) {
        this.inFeeAmount = inFeeAmount == null ? null : inFeeAmount.trim();
    }

    public String getInFee() {
        return inFee;
    }

    public void setInFee(String inFee) {
        this.inFee = inFee == null ? null : inFee.trim();
    }

    public String getInFeeType() {
        return inFeeType;
    }

    public void setInFeeType(String inFeeType) {
        this.inFeeType = inFeeType == null ? null : inFeeType.trim();
    }

    public String getOutFeeType() {
        return outFeeType;
    }

    public void setOutFeeType(String outFeeType) {
        this.outFeeType = outFeeType == null ? null : outFeeType.trim();
    }

    public String getOutFee() {
        return outFee;
    }

    public void setOutFee(String outFee) {
        this.outFee = outFee == null ? null : outFee.trim();
    }

    public String getOutFeeAmount() {
        return outFeeAmount;
    }

    public void setOutFeeAmount(String outFeeAmount) {
        this.outFeeAmount = outFeeAmount == null ? null : outFeeAmount.trim();
    }

    public String getChannelMerAbbr() {
        return channelMerAbbr;
    }

    public void setChannelMerAbbr(String channelMerAbbr) {
        this.channelMerAbbr = channelMerAbbr == null ? null : channelMerAbbr.trim();
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public String getChannelMerId() {
        return channelMerId;
    }

    public void setChannelMerId(String channelMerId) {
        this.channelMerId = channelMerId == null ? null : channelMerId.trim();
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno == null ? null : accno.trim();
    }

    public String getCustomerinfo() {
        return customerinfo;
    }

    public void setCustomerinfo(String customerinfo) {
        this.customerinfo = customerinfo == null ? null : customerinfo.trim();
    }

    public String getPpflag() {
        return ppflag;
    }

    public void setPpflag(String ppflag) {
        this.ppflag = ppflag == null ? null : ppflag.trim();
    }

    public Boolean getNotifysuccess() {
        return notifysuccess;
    }

    public void setNotifysuccess(Boolean notifysuccess) {
        this.notifysuccess = notifysuccess;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCstatus() {
        return cstatus;
    }

    public void setCstatus(String cstatus) {
        this.cstatus = cstatus == null ? null : cstatus.trim();
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Byte getT1() {
        return t1;
    }

    public void setT1(Byte t1) {
        this.t1 = t1;
    }

    
    
	public String getCvv2() {
		return cvv2;
	}

	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}

	
	
	
	

	public String getPayeridno() {
		return payeridno;
	}

	public void setPayeridno(String payeridno) {
		this.payeridno = payeridno;
	}

	public String getAcctvaliddate() {
		return acctvaliddate;
	}

	public void setAcctvaliddate(String acctvaliddate) {
		this.acctvaliddate = acctvaliddate;
	}

	@Override
	public String toString() {
		return "NpayOrder [" + (oid != null ? "oid=" + oid + ", " : "")
				+ (orderid != null ? "orderid=" + orderid + ", " : "")
				+ (merchantid != null ? "merchantid=" + merchantid + ", " : "")
				+ (merorderid != null ? "merorderid=" + merorderid + ", " : "")
				+ (txnamt != null ? "txnamt=" + txnamt + ", " : "")
				+ (fronturl != null ? "fronturl=" + fronturl + ", " : "")
				+ (backurl != null ? "backurl=" + backurl + ", " : "")
				+ (subject != null ? "subject=" + subject + ", " : "") + (body != null ? "body=" + body + ", " : "")
				+ (merresv1 != null ? "merresv1=" + merresv1 + ", " : "")
				+ (userid != null ? "userid=" + userid + ", " : "")
				+ (signature != null ? "signature=" + signature + ", " : "")
				+ (signmethod != null ? "signmethod=" + signmethod + ", " : "")
				+ (txntype != null ? "txntype=" + txntype + ", " : "")
				+ (txnsubtype != null ? "txnsubtype=" + txnsubtype + ", " : "")
				+ (biztype != null ? "biztype=" + biztype + ", " : "")
				+ (version != null ? "version=" + version + ", " : "")
				+ (createAt != null ? "createAt=" + createAt + ", " : "")
				+ (accesstype != null ? "accesstype=" + accesstype + ", " : "")
				+ (accessmode != null ? "accessmode=" + accessmode + ", " : "")
				+ (currency != null ? "currency=" + currency + ", " : "")
				+ (paytype != null ? "paytype=" + paytype + ", " : "")
				+ (updateAt != null ? "updateAt=" + updateAt + ", " : "")
				+ (txntime != null ? "txntime=" + txntime + ", " : "")
				+ (status != null ? "status=" + status + ", " : "")
				+ (gateway != null ? "gateway=" + gateway + ", " : "")
				+ (customerip != null ? "customerip=" + customerip + ", " : "")
				+ (bankid != null ? "bankid=" + bankid + ", " : "") + (dctype != null ? "dctype=" + dctype + ", " : "")
				+ (notifytimes != null ? "notifytimes=" + notifytimes + ", " : "")
				+ (statusdesc != null ? "statusdesc=" + statusdesc + ", " : "")
				+ (refundtimes != null ? "refundtimes=" + refundtimes + ", " : "")
				+ (refundorderid != null ? "refundorderid=" + refundorderid + ", " : "")
				+ (inFeeAmount != null ? "inFeeAmount=" + inFeeAmount + ", " : "")
				+ (inFee != null ? "inFee=" + inFee + ", " : "")
				+ (inFeeType != null ? "inFeeType=" + inFeeType + ", " : "")
				+ (outFeeType != null ? "outFeeType=" + outFeeType + ", " : "")
				+ (outFee != null ? "outFee=" + outFee + ", " : "")
				+ (outFeeAmount != null ? "outFeeAmount=" + outFeeAmount + ", " : "")
				+ (channelMerAbbr != null ? "channelMerAbbr=" + channelMerAbbr + ", " : "")
				+ (channelId != null ? "channelId=" + channelId + ", " : "")
				+ (channelMerId != null ? "channelMerId=" + channelMerId + ", " : "")
				+ (accno != null ? "accno=" + accno + ", " : "")
				+ (customerinfo != null ? "customerinfo=" + customerinfo + ", " : "")
				+ (ppflag != null ? "ppflag=" + ppflag + ", " : "")
				+ (notifysuccess != null ? "notifysuccess=" + notifysuccess + ", " : "")
				+ (checkStatus != null ? "checkStatus=" + checkStatus + ", " : "")
				+ (cstatus != null ? "cstatus=" + cstatus + ", " : "")
				+ (lastUpdate != null ? "lastUpdate=" + lastUpdate + ", " : "") + (t1 != null ? "t1=" + t1 + ", " : "")
				+ (count != null ? "count=" + count + ", " : "") + (msName != null ? "msName=" + msName + ", " : "")
				+ (txnAmts != null ? "txnAmts=" + txnAmts + ", " : "")
				+ (bankName != null ? "bankName=" + bankName + ", " : "")
				+ (customerNm != null ? "customerNm=" + customerNm + ", " : "")
				+ (createAtTimeBegin != null ? "createAtTimeBegin=" + createAtTimeBegin + ", " : "")
				+ (createAtTimeEnd != null ? "createAtTimeEnd=" + createAtTimeEnd + ", " : "")
				+ (cvv2 != null ? "cvv2=" + cvv2 + ", " : "")
				+ (payeridno != null ? "payeridno=" + payeridno + ", " : "")
				+ (acctvaliddate != null ? "acctvaliddate=" + acctvaliddate : "") + "]";
	}

	
    
    
    
    
}