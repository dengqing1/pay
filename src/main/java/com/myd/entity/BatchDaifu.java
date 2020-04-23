package com.myd.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.myd.util.Base64CodeUtil;

public class BatchDaifu {

	private Integer id; 
	private Integer status; 
	private String fileName; 
	private String createTime;
	
	private Long userId;
	private String merchId;
	private String extra;
	private String desc;
	private Long updateTimeDate;
	private Long createTimeDate;
	private String verifiedBy;
	
	
	
	public Long getUpdateTimeDate() {
		return updateTimeDate;
	}
	public Long getCreateTimeDate() {
		return createTimeDate;
	}
	public void setUpdateTimeDate(Long updateTimeDate) {
		this.updateTimeDate = updateTimeDate;
	}
	public void setCreateTimeDate(Long createTimeDate) {
		this.createTimeDate = createTimeDate;
	}
	public String getVerifiedBy() {
		return verifiedBy;
	}
	public void setVerifiedBy(String verifiedBy) {
		this.verifiedBy = verifiedBy;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getMerchId() {
		return merchId;
	}
	public void setMerchId(String merchId) {
		this.merchId = merchId;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getFileNames() {
		if (StringUtils.isNotBlank(fileName)) {
			return Base64CodeUtil.Base64Decoding(fileName);
		}
		return fileName;
	}
	public String getFileName() {
		return fileName;
	}
	
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	

	
	public String getCreateTimes() {
		if (createTime != null) {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.parseLong(createTime)*1000));
		}
		return createTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	} 
	
	
	
}
