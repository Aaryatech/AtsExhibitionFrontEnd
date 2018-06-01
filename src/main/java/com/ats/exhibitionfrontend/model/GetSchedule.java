package com.ats.exhibitionfrontend.model;

import java.util.Date;
import java.util.List;

public class GetSchedule {

	
	private int scheduleId;
	
	private int eventId;
	
	private String eventName;

	private String date;
	
	private String dayName;
	
	private int isUsed;
	
	
	List<ScheduleDetail>  scheduleDetailList;
	

	public List<ScheduleDetail> getScheduleDetailList() {
		return scheduleDetailList;
	}

	public void setScheduleDetailList(List<ScheduleDetail> scheduleDetailList) {
		this.scheduleDetailList = scheduleDetailList;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public int getEventId() {
		return eventId;
	}

	public String getEventName() {
		return eventName;
	}
	

	public String getDayName() {
		return dayName;
	}

	public int getIsUsed() {
		return isUsed;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setDayName(String dayName) {
		this.dayName = dayName;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	
	@Override
	public String toString() {
		return "GetSchedule [scheduleId=" + scheduleId + ", eventId=" + eventId + ", eventName=" + eventName + ", date="
				+ date + ", dayName=" + dayName + ", isUsed=" + isUsed + "]";
	}
	
}
