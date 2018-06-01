package com.ats.exhibitionfrontend.model;

public class ScheduleDetail {

	
    private int scheduleDetailId;
	
	private int scheduleId;
	
	private String topic;
	
	private String fromTime;
	
	private String toTime;
	
	private String venue;
	
	private String speaker;
	
	private String remark;
	
	private int seatsAvailable;

	private int isUsed;
	
	
	public int getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	public int getScheduleDetailId() {
		return scheduleDetailId;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public String getTopic() {
		return topic;
	}

	public String getFromTime() {
		return fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public String getVenue() {
		return venue;
	}

	public String getSpeaker() {
		return speaker;
	}

	public String getRemark() {
		return remark;
	}

	public int getSeatsAvailable() {
		return seatsAvailable;
	}

	public void setScheduleDetailId(int scheduleDetailId) {
		this.scheduleDetailId = scheduleDetailId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	@Override
	public String toString() {
		return "ScheduleDetail [scheduleDetailId=" + scheduleDetailId + ", scheduleId=" + scheduleId + ", topic="
				+ topic + ", fromTime=" + fromTime + ", toTime=" + toTime + ", venue=" + venue + ", speaker=" + speaker
				+ ", remark=" + remark + ", seatsAvailable=" + seatsAvailable + "]";
	}
	
}
