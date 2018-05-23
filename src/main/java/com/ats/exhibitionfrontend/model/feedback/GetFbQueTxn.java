package com.ats.exhibitionfrontend.model.feedback;



public class GetFbQueTxn {
	
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

	private String question;


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


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "GetFbQueTxn [fbTxId=" + fbTxId + ", exhbId=" + exhbId + ", fbId=" + fbId + ", rating=" + rating
				+ ", queType=" + queType + ", options=" + options + ", remark=" + remark + ", outOfRating="
				+ outOfRating + ", userId=" + userId + ", isUsed=" + isUsed + ", question=" + question + "]";
	}

}
