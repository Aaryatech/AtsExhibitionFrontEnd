package com.ats.exhibitionfrontend.model;
 
import java.util.List;
 
  
public class EnquiryHeader {
	
	 
	private int enqId; 
	private int visitorId; 
	private int exhId; 
	private int eventId; 
	private int empId; 
	private String date; 
	private String nextMeetDate; 
	private String approximate_time; 
	private String remark; 
	private int status; 
	private int isUsed; 
	List<EnquiryDetail> enquiryDetailList;
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
	public int getExhId() {
		return exhId;
	}
	public void setExhId(int exhId) {
		this.exhId = exhId;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
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
	public String getApproximate_time() {
		return approximate_time;
	}
	public void setApproximate_time(String approximate_time) {
		this.approximate_time = approximate_time;
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
	public List<EnquiryDetail> getEnquiryDetailList() {
		return enquiryDetailList;
	}
	public void setEnquiryDetailList(List<EnquiryDetail> enquiryDetailList) {
		this.enquiryDetailList = enquiryDetailList;
	}
	@Override
	public String toString() {
		return "EnquiryHeader [enqId=" + enqId + ", visitorId=" + visitorId + ", exhId=" + exhId + ", eventId="
				+ eventId + ", empId=" + empId + ", date=" + date + ", nextMeetDate=" + nextMeetDate
				+ ", approximate_time=" + approximate_time + ", remark=" + remark + ", status=" + status + ", isUsed="
				+ isUsed + ", enquiryDetailList=" + enquiryDetailList + "]";
	}
	
	

}
