package com.myd.entity;

public class BatchDaifuData {
	
	private String mchtOrderNo;		//	M	商户订单号，批量代付中不能重复
	private Long amt ;//10	//M	金额，正整数，单位为分。例如，票款为1280元，则表示为“128000”
	private String bankCardId ;//银行卡号
	private String falge	;	//用途
	private String cumstomName ;//持卡人姓名
	private String bankName ;//开户行名字
	private String msg ;//信息
	public String getMchtOrderNo() {
		return mchtOrderNo;
	}
	public void setMchtOrderNo(String mchtOrderNo) {
		this.mchtOrderNo = mchtOrderNo;
	}
	public Long getAmt() {
		return amt;
	}
	public void setAmt(Long amt) {
		this.amt = amt;
	}
	public String getBankCardId() {
		return bankCardId;
	}
	public void setBankCardId(String bankCardId) {
		this.bankCardId = bankCardId;
	}
	public String getFalge() {
		return falge;
	}
	public void setFalge(String falge) {
		this.falge = falge;
	}
	public String getCumstomName() {
		return cumstomName;
	}
	public void setCumstomName(String cumstomName) {
		this.cumstomName = cumstomName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "BatchDaifuData [" + (mchtOrderNo != null ? "mchtOrderNo=" + mchtOrderNo + ", " : "")
				+ (amt != null ? "amt=" + amt + ", " : "")
				+ (bankCardId != null ? "bankCardId=" + bankCardId + ", " : "")
				+ (falge != null ? "falge=" + falge + ", " : "")
				+ (cumstomName != null ? "cumstomName=" + cumstomName + ", " : "")
				+ (bankName != null ? "bankName=" + bankName + ", " : "") + (msg != null ? "msg=" + msg : "") + "]";
	}

	
	

}
