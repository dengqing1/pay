package com.myd.entity;

public class Pay10088Result {

	private String memberid;//	商户编号
	private String orderid;//	订单号
	private String amount;//	订单金额   元
	private String transaction_id;//	交易流水号
	private String datetime;//	交易时间
	private String returncode;//	交易状态
	private String attach	;//扩展返回
	
	private String sign	;//签名

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getReturncode() {
		return returncode;
	}

	public void setReturncode(String returncode) {
		this.returncode = returncode;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Override
	public String toString() {
		return "Pay10088Result [memberid=" + memberid + ", orderid=" + orderid + ", amount=" + amount
				+ ", transaction_id=" + transaction_id + ", datetime=" + datetime + ", returncode=" + returncode
				+ ", attach=" + attach + ", sign=" + sign + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
