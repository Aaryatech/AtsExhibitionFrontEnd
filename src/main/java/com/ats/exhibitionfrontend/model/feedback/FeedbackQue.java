package com.ats.exhibitionfrontend.model.feedback;

public class FeedbackQue {
	
	private int fbId;

	private int exhbId;

	private String question;

	private String queDesc;
	
	private int queType;
	
	private String options;
	
	private int isActive;

	private int isUsed;
	
	private int outOfRating;
	

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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	public int getOutOfRating() {
		return outOfRating;
	}

	public void setOutOfRating(int outOfRating) {
		this.outOfRating = outOfRating;
	}

	@Override
	public String toString() {
		return "FeedbackQue [fbId=" + fbId + ", exhbId=" + exhbId + ", question=" + question + ", queDesc=" + queDesc
				+ ", queType=" + queType + ", options=" + options + ", isActive=" + isActive + ", isUsed=" + isUsed
				+ ", outOfRating=" + outOfRating + "]";
	}

}
