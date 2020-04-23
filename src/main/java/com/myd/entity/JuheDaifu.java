package com.myd.entity;
/**
 * 聚合代付参数
 * @author admin
 *
 */
public class JuheDaifu {

	private String merId;//	商户号
	private String outBatchNo;//	批次号
	private String totalNum	;//批量付款总笔数
	private String totalAmount;//	付款总金额
	private String detailData;//	付款详细数据   json格式
	private String nonceStr	;//随机字符串
	private String sign	;//签名
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
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
	public String getDetailData() {
		return detailData;
	}
	public void setDetailData(String detailData) {
		this.detailData = detailData;
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
		return "JuheDaifu [merId=" + merId + ", outBatchNo=" + outBatchNo + ", totalNum=" + totalNum + ", totalAmount="
				+ totalAmount + ", detailData=" + detailData + ", nonceStr=" + nonceStr + ", sign=" + sign + "]";
	}
	
	
	
	
	
}
