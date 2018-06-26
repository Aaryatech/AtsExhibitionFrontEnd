package com.ats.exhibitionfrontend.model.eventhistory;

public class ProductLikeByEvent {
	
	private int visitorProductId;
	
	private int visitorId;
	private int likeStatus;
	private String visitorName;
	private String visitorEmail;
	private String visitorMobile;
	private String locationName;
	
	public int getVisitorProductId() {
		return visitorProductId;
	}
	public int getVisitorId() {
		return visitorId;
	}
	public int getLikeStatus() {
		return likeStatus;
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
	public String getLocationName() {
		return locationName;
	}
	public void setVisitorProductId(int visitorProductId) {
		this.visitorProductId = visitorProductId;
	}
	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
	}
	public void setLikeStatus(int likeStatus) {
		this.likeStatus = likeStatus;
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
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	@Override
	public String toString() {
		return "ProductLikeByEvent [visitorProductId=" + visitorProductId + ", visitorId=" + visitorId + ", likeStatus="
				+ likeStatus + ", visitorName=" + visitorName + ", visitorEmail=" + visitorEmail + ", visitorMobile="
				+ visitorMobile + ", locationName=" + locationName + "]";
	}
	
}
