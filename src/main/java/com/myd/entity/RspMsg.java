package com.myd.entity;

public class RspMsg {
	
	private int success = 0;//失败
	private String code = "1002";//失败
	private String timestamp ;
	private String msg;
	private String signature ;
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	@Override
	public String toString() {
		return "RspMsg [success=" + success + ", code=" + code + ", timestamp=" + timestamp + ", msg=" + msg
				+ ", signature=" + signature + "]";
	}
	
	
	
	

}
