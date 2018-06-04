package com.ats.exhibitionfrontend.model;
 

public class PostTrackDetail {
	 
	private int trackDetailId; 
	private int trackId; 
	private float locLat; 
	private float locLong; 
	private String address; 
	private int km; 
	private int isUsed;
	public int getTrackDetailId() {
		return trackDetailId;
	}
	public void setTrackDetailId(int trackDetailId) {
		this.trackDetailId = trackDetailId;
	}
	public int getTrackId() {
		return trackId;
	}
	public void setTrackId(int trackId) {
		this.trackId = trackId;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getKm() {
		return km;
	}
	public void setKm(int km) {
		this.km = km;
	}
	public int getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	@Override
	public String toString() {
		return "PostTrackDetail [trackDetailId=" + trackDetailId + ", trackId=" + trackId + ", locLat=" + locLat
				+ ", locLong=" + locLong + ", address=" + address + ", km=" + km + ", isUsed=" + isUsed + "]";
	}
	
	

}
