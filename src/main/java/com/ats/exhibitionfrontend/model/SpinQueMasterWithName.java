package com.ats.exhibitionfrontend.model;

public class SpinQueMasterWithName {

	private int queId;
	private int exhId;
	private String question;
	private String queDesc;
	private String date;
	private int isActive;
	private int isUsed;
	private String exhName;

	public int getQueId() {
		return queId;
	}

	public void setQueId(int queId) {
		this.queId = queId;
	}

	public int getExhId() {
		return exhId;
	}

	public void setExhId(int exhId) {
		this.exhId = exhId;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getExhName() {
		return exhName;
	}

	public void setExhName(String exhName) {
		this.exhName = exhName;
	}

	@Override
	public String toString() {
		return "SpinQueHeaderWithName [queId=" + queId + ", exhId=" + exhId + ", question=" + question + ", queDesc="
				+ queDesc + ", date=" + date + ", isActive=" + isActive + ", isUsed=" + isUsed + ", exhName=" + exhName
				+ "]";
	}

}
