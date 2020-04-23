package com.myd.dto;



import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.myd.util.DateUtil;

/**
 * Created by you on 2018/11/21.
 */
public class BodyResponse implements Serializable {

    
	/**
	 * 
	 */
	private static final long serialVersionUID = -3985045821873379637L;
	
	private String success;//执行结果
	
	private String code;//执行代码
	
	private String timestamp = DateUtil.getNowTimeStamp();//时间戳
	
	private String msg;//错误消息
	
	private boolean checkBool;
	
	

	public boolean isCheckBool() {
		return checkBool;
	}

	public void setCheckBool(boolean checkBool) {
		this.checkBool = checkBool;
	}
	
	
    public String getSuccess() {
		return success;
	}




	public void setSuccess(String success) {
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






	public String getMsg() {
		return msg;
	}




	public void setMsg(String msg) {
		this.msg = msg;
	}




	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
	
	
	
	
}
