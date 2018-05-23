package com.ats.exhibitionfrontend.model.eventhistory;

import java.io.Serializable;
import java.util.Date;

public class GetEventHistory implements Serializable{

	private int eventId;
	
	private String eventName;
	
	private String eventLocation;
	
	private String eventFromDate;
	
	private String eventToDate;
	
	private String exhName;
	
	private int likeCount;

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

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	
	public String getExhName() {
		return exhName;
	}

	public void setExhName(String exhName) {
		this.exhName = exhName;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public String getEventFromDate() {
		return eventFromDate;
	}

	public void setEventFromDate(String eventFromDate) {
		this.eventFromDate = eventFromDate;
	}

	public String getEventToDate() {
		return eventToDate;
	}

	public void setEventToDate(String eventToDate) {
		this.eventToDate = eventToDate;
	}

	@Override
	public String toString() {
		return "GetEventHistory [eventId=" + eventId + ", eventName=" + eventName + ", eventLocation=" + eventLocation
				+ ", eventFromDate=" + eventFromDate + ", eventToDate=" + eventToDate + ", exhName=" + exhName
				+ ", likeCount=" + likeCount + "]";
	}
	
}
