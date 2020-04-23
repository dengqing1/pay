package com.myd.entity;
/**
 * 短信发送成功之后返回的信息
 *@author xiaoqiang lu
 *
 *2019/01/21 17:02
 */
public class KJDuanXinReturn {
	
	private int	success  =1 ;	 	//M 	1：执行成功 0：执行失败, (注意此处非交易成功失败, 有可能向银行发请求在等待接受数据时候,发生网络故障,不能正常接受到返回数据,也为执行失败, 但交易有可能成功)
	private String	timestamp; 	 //	M 	时间戳
	private String 	signature; 	// 	M 	参见签名规则
	private String 	msg ;	 	//信息
	private String merOrderId ; // 订单号
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMerOrderId() {
		return merOrderId;
	}
	public void setMerOrderId(String merOrderId) {
		this.merOrderId = merOrderId;
	}
	@Override
	public String toString() {
		return "KJDuanXinReturn [success=" + success + ", " + (timestamp != null ? "timestamp=" + timestamp + ", " : "")
				+ (signature != null ? "signature=" + signature + ", " : "") + (msg != null ? "msg=" + msg + ", " : "")
				+ (merOrderId != null ? "merOrderId=" + merOrderId : "") + "]";
	}
	 
	
	

}
