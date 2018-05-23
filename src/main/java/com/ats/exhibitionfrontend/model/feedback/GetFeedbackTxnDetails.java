package com.ats.exhibitionfrontend.model.feedback;


public class GetFeedbackTxnDetails {

	private int visitorId;

	private int fbId;

	private int exhbId;

	private String question;

	private String queDesc;
	
	private int fbTxId;
	
	private int eventId;
	
	private int rating;
	
	private int remark;
	
	private String visitorName;
	
	private String visitorMobile;


	public int getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
	}

	public int getFbId() {
		return fbId;
	}

	public void setFbId(int fbId) {
		this.fbId = fbId;
	}

	public int getExhbId() {
		return exhbId;
	}

	public void setExhbId(int exhbId) {
		this.exhbId = exhbId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQueDesc() {
		return queDesc;
	}

	public void setQueDesc(String queDesc) {
		this.queDesc = queDesc;
	}

	public int getFbTxId() {
		return fbTxId;
	}

	public void setFbTxId(int fbTxId) {
		this.fbTxId = fbTxId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getRemark() {
		return remark;
	}

	public void setRemark(int remark) {
		this.remark = remark;
	}

	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	
	
	public String getVisitorMobile() {
		return visitorMobile;
	}

	public void setVisitorMobile(String visitorMobile) {
		this.visitorMobile = visitorMobile;
	}

	@Override
	public String toString() {
		return "GetFeedbackTxnDetails [visitorId=" + visitorId + ", fbId=" + fbId + ", exhbId=" + exhbId + ", question="
				+ question + ", queDesc=" + queDesc + ", fbTxId=" + fbTxId + ", eventId=" + eventId + ", rating="
				+ rating + ", remark=" + remark + ", visitorName=" + visitorName + ", visitorMobile=" + visitorMobile+ "]";
	}
	
}
