package com.ats.exhibitionfrontend.model;
 

public class FeedbackTxn {
	 
	private int fbTxId; 
	private int exhbId; 
	private int fbId; 
	private int rating; 
	private int queType; 
	private String options; 
	private String remark; 
	private int outOfRating; 
	private int userId; 
	private int isUsed; 
	private int eventId;
	public int getFbTxId() {
		return fbTxId;
	}
	public void setFbTxId(int fbTxId) {
		this.fbTxId = fbTxId;
	}
	public int getExhbId() {
		return exhbId;
	}
	public void setExhbId(int exhbId) {
		this.exhbId = exhbId;
	}
	public int getFbId() {
		return fbId;
	}
	public void setFbId(int fbId) {
		this.fbId = fbId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getQueType() {
		return queType;
	}
	public void setQueType(int queType) {
		this.queType = queType;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getOutOfRating() {
		return outOfRating;
	}
	public void setOutOfRating(int outOfRating) {
		this.outOfRating = outOfRating;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	@Override
	public String toString() {
		return "FeedbackTxn [fbTxId=" + fbTxId + ", exhbId=" + exhbId + ", fbId=" + fbId + ", rating=" + rating
				+ ", queType=" + queType + ", options=" + options + ", remark=" + remark + ", outOfRating="
				+ outOfRating + ", userId=" + userId + ", isUsed=" + isUsed + ", eventId=" + eventId + "]";
	}
	
	

}
