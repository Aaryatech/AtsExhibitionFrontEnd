package com.ats.exhibitionfrontend.model;

import java.util.Date;

public class EnquiryDetail {

	private int enqDetailId;
	private int enqId;
	private int empId;
	private Date date;

	private String venue;
	private String approximateTime;
	private String remark;

	private int isUsed;

	public int getEnqDetailId() {
		return enqDetailId;
	}

	public void setEnqDetailId(int enqDetailId) {
		this.enqDetailId = enqDetailId;
	}

	public int getEnqId() {
		return enqId;
	}

	public void setEnqId(int enqId) {
		this.enqId = enqId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
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

	public int getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	@Override
	public String toString() {
		return "EnquiryDetail [enqDetailId=" + enqDetailId + ", enqId=" + enqId + ", empId=" + empId + ", date=" + date
				+ ", venue=" + venue + ", approximateTime=" + approximateTime + ", remark=" + remark + ", isUsed="
				+ isUsed + "]";
	}
}
