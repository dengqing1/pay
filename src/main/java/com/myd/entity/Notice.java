package com.myd.entity;

import java.util.Date;

public class Notice {

	private Integer id;
	private String title;
	private String content;
	private String status;
	private String startTime;
	private String endTime;
	private Date CreatedAt;
	private Date UpdatedAt;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public Date getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}
	public Date getUpdatedAt() {
		return UpdatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		UpdatedAt = updatedAt;
	}
	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", content=" + content
				+ ", status=" + status + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", CreatedAt=" + CreatedAt
				+ ", UpdatedAt=" + UpdatedAt + "]";
	}
	
	
	
	
}
