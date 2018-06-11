package com.ats.exhibition.model.emptaskenq;


public class GetEmpEnquiry  {
	
	
	private int enqId;
	
	private int empId;
	
	private String visitorName;
	
	private String visitorEmail;
	
	private String visitorMobile;
	
	private String eventName;
	
	private String empName;
	
	private String approximateTime;
	
	private int status;
	
	private String nextMeetDate;
	
	private int tempStatus;

	public int getEnqId() {
		return enqId;
	}

	public String getVisitorName() {
		return visitorName;
	}

	public String getVisitorEmail() {
		return visitorEmail;
	}

	public String getVisitorMobile() {
		return visitorMobile;
	}

	public String getEventName() {
		return eventName;
	}

	public String getEmpName() {
		return empName;
	}

	public String getApproximateTime() {
		return approximateTime;
	}

	public int getStatus() {
		return status;
	}

	public void setEnqId(int enqId) {
		this.enqId = enqId;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public void setVisitorEmail(String visitorEmail) {
		this.visitorEmail = visitorEmail;
	}

	public void setVisitorMobile(String visitorMobile) {
		this.visitorMobile = visitorMobile;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public void setApproximateTime(String approximateTime) {
		this.approximateTime = approximateTime;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getNextMeetDate() {
		return nextMeetDate;
	}

	public void setNextMeetDate(String nextMeetDate) {
		this.nextMeetDate = nextMeetDate;
	}

	public int getTempStatus() {
		return tempStatus;
	}

	public void setTempStatus(int tempStatus) {
		this.tempStatus = tempStatus;
	}

	@Override
	public String toString() {
		return "GetEmpEnquiry [enqId=" + enqId + ", empId=" + empId + ", visitorName=" + visitorName + ", visitorEmail="
				+ visitorEmail + ", visitorMobile=" + visitorMobile + ", eventName=" + eventName + ", empName="
				+ empName + ", approximateTime=" + approximateTime + ", status=" + status + ", nextMeetDate="
				+ nextMeetDate + ", tempStatus=" + tempStatus + "]";
	}

	
}
