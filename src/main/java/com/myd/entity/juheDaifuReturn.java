package com.myd.entity;



/**
 * 代付 响应   回调
 * @author admin
 *
 */


public class juheDaifuReturn {

	
	private String merId ;//	商户号
	private String batchNo	;//平台批次号
	private String outBatchNo;//	商户批次号
	private String totalNum	;//批量付款总笔数
	private String totalAmount;//	付款总金额
	private String status	;//提交状态
	private String nonceStr	;//随机字符串
	private String sign	;//签名
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getOutBatchNo() {
		return outBatchNo;
	}
	public void setOutBatchNo(String outBatchNo) {
		this.outBatchNo = outBatchNo;
	}
	public String getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
	@Override
	public String toString() {
		return "juheDaifuReturn [merId=" + merId + ", batchNo=" + batchNo + ", outBatchNo=" + outBatchNo + ", totalNum="
				+ totalNum + ", totalAmount=" + totalAmount + ", status=" + status + ", nonceStr=" + nonceStr
				+ ", sign=" + sign + "]";
	}
	
	
}
