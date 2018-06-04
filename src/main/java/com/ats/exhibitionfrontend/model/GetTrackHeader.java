package com.ats.exhibitionfrontend.model;
 
import java.util.List;
 
public class GetTrackHeader {
	 
	private int trackId; 
	private int exhiId; 
	private int empId; 
	private String empName; 
	private String date; 
	private float locLat; 
	private float locLong; 
	private float totalKm; 
	private int isUsed; 
	private int int1; 
	ErrorMessage errorMessage; 
	List<PostTrackDetail> postTrackDetailList;
	
	public int getTrackId() {
		return trackId;
	}
	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}
	public int getExhiId() {
		return exhiId;
	}
	public void setExhiId(int exhiId) {
		this.exhiId = exhiId;
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
	public float getLocLat() {
		return locLat;
	}
	public void setLocLat(float locLat) {
		this.locLat = locLat;
	}
	public float getLocLong() {
		return locLong;
	}
	public void setLocLong(float locLong) {
		this.locLong = locLong;
	}
	public float getTotalKm() {
		return totalKm;
	}
	public void setTotalKm(float totalKm) {
		this.totalKm = totalKm;
	}
	public int getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	public int getInt1() {
		return int1;
	}
	public void setInt1(int int1) {
		this.int1 = int1;
	}
	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}
	public List<PostTrackDetail> getPostTrackDetailList() {
		return postTrackDetailList;
	}
	public void setPostTrackDetailList(List<PostTrackDetail> postTrackDetailList) {
		this.postTrackDetailList = postTrackDetailList;
	}
	@Override
	public String toString() {
		return "GetTrackHeader [trackId=" + trackId + ", exhiId=" + exhiId + ", empId=" + empId + ", empName=" + empName
				+ ", date=" + date + ", locLat=" + locLat + ", locLong=" + locLong + ", totalKm=" + totalKm
				+ ", isUsed=" + isUsed + ", int1=" + int1 + ", errorMessage=" + errorMessage + ", postTrackDetailList="
				+ postTrackDetailList + "]";
	}
	
	

}
