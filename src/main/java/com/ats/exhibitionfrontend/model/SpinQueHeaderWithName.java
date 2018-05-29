package com.ats.exhibitionfrontend.model;

import java.util.Date;


public class SpinQueHeaderWithName {
	private int tQueId;
	private int empId;
	private String empName;

	private int exhId;

	private String exhName;

	private int visitorId;

	private String visitorName;

	private Date date;

	private String dateTime;

	private int isUsed;

	private String status;

	@Override
	public String toString() {
		return "SpinQueHeaderWithName [tQueId=" + tQueId + ", empId=" + empId + ", empName=" + empName + ", exhId="
				+ exhId + ", exhName=" + exhName + ", visitorId=" + visitorId + ", visitorName=" + visitorName
				+ ", date=" + date + ", dateTime=" + dateTime + ", isUsed=" + isUsed + ", status=" + status + "]";
	}

	public int gettQueId() {
		return tQueId;
	}

	public void settQueId(int tQueId) {
		this.tQueId = tQueId;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public int getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
