package com.ats.exhibitionfrontend.model;

import java.util.Date;

public class EnquiryHeaderWithName {
	
	private int enqId;
	private int visitorId;
	private String visitorName;

	private int exhId;
	private String exhName;
	private int eventId;
	private String eventName;
	private int empId;
	private String empName;
	private String date;
	private String nextMeetDate;
	private String approximateTime;
	private String remark;
	private int status;
	private int isUsed;
	private String visitorMobile;

	public String getVisitorMobile() {
		return visitorMobile;
	}

	public void setVisitorMobile(String visitorMobile) {
		this.visitorMobile = visitorMobile;
	}

	public int getEnqId() {
		return enqId;
	}

	public void setEnqId(int enqId) {
		this.enqId = enqId;
	}

	public int getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
	}

	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public int getExhId() {
		return exhId;
	}

	public void setExhId(int exhId) {
		this.exhId = exhId;
	}

	public String getExhName() {
		return exhName;
	}

	public void setExhName(String exhName) {
		this.exhName = exhName;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNextMeetDate() {
		return nextMeetDate;
	}

	public void setNextMeetDate(String nextMeetDate) {
		this.nextMeetDate = nextMeetDate;
	}

	public String getApproximateTime() {
		return approximateTime;
	}

	public void setApproximateTime(String approximateTime) {
		this.approximateTime = approximateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	
	private long noOfEnqDays;

	public long getNoOfEnqDays() {
		return noOfEnqDays;
	}

	public void setNoOfEnqDays(long noOfEnqDays) {
		this.noOfEnqDays = noOfEnqDays;
	}

	@Override
	public String toString() {
		return "EnquiryHeaderWithName [enqId=" + enqId + ", visitorId=" + visitorId + ", visitorName=" + visitorName
				+ ", exhId=" + exhId + ", exhName=" + exhName + ", eventId=" + eventId + ", eventName=" + eventName
				+ ", empId=" + empId + ", empName=" + empName + ", date=" + date + ", nextMeetDate=" + nextMeetDate
				+ ", approximateTime=" + approximateTime + ", remark=" + remark + ", status=" + status + ", isUsed="
				+ isUsed + ", visitorMobile=" + visitorMobile + ", noOfEnqDays=" + noOfEnqDays + "]";
	}
	
	

}
