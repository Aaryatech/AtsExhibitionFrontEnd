package com.ats.exhibitionfrontend.model;
 

public class VisitorByExhiId {
	 
	private int visitorExhibitorId; 
	private int visitorId; 
	private int eventId; 
	private int exhibitorTd; 
	private int likeStatus; 
	private String visitorName; 
	private String eventName; 
	private String exhName;
	public int getVisitorExhibitorId() {
		return visitorExhibitorId;
	}
	public void setVisitorExhibitorId(int visitorExhibitorId) {
		this.visitorExhibitorId = visitorExhibitorId;
	}
	public int getVisitorId() {
		return visitorId;
	}
	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public int getExhibitorTd() {
		return exhibitorTd;
	}
	public void setExhibitorTd(int exhibitorTd) {
		this.exhibitorTd = exhibitorTd;
	}
	public int getLikeStatus() {
		return likeStatus;
	}
	public void setLikeStatus(int likeStatus) {
		this.likeStatus = likeStatus;
	}
	public String getVisitorName() {
		return visitorName;
	}
	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getExhName() {
		return exhName;
	}
	public void setExhName(String exhName) {
		this.exhName = exhName;
	}
	@Override
	public String toString() {
		return "VisitorByExhiId [visitorExhibitorId=" + visitorExhibitorId + ", visitorId=" + visitorId + ", eventId="
				+ eventId + ", exhibitorTd=" + exhibitorTd + ", likeStatus=" + likeStatus + ", visitorName="
				+ visitorName + ", eventName=" + eventName + ", exhName=" + exhName + "]";
	}
	
	

}
