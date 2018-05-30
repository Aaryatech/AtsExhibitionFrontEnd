package com.ats.exhibitionfrontend.model;

import java.util.Date;

public class SpinQueDetailWithQue {

	private int tQueDetailId;
	private int tQueId;

	private int queId;

	private String question;
	private Date date;
	private String actualAns;
	private int isUsed;
	private String status;

	public int gettQueDetailId() {
		return tQueDetailId;
	}

	public void settQueDetailId(int tQueDetailId) {
		this.tQueDetailId = tQueDetailId;
	}

	public int gettQueId() {
		return tQueId;
	}

	public void settQueId(int tQueId) {
		this.tQueId = tQueId;
	}

	public int getQueId() {
		return queId;
	}

	public void setQueId(int queId) {
		this.queId = queId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getActualAns() {
		return actualAns;
	}

	public void setActualAns(String actualAns) {
		this.actualAns = actualAns;
	}

	public int getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SpinQueDetailWithQue [tQueDetailId=" + tQueDetailId + ", tQueId=" + tQueId + ", queId=" + queId
				+ ", question=" + question + ", date=" + date + ", actualAns=" + actualAns + ", isUsed=" + isUsed
				+ ", status=" + status + "]";
	}

}
