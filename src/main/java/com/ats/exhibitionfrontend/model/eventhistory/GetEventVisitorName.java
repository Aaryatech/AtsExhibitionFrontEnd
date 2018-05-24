package com.ats.exhibitionfrontend.model.eventhistory;


public class GetEventVisitorName {
	
	
	private int visitorId;
	
	private String visitorName;
	
	private String visitorEmail;
	
	private String visitorMobile;
	
	private int likeCount;
	
	public int getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
	}

	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public String getVisitorEmail() {
		return visitorEmail;
	}

	public void setVisitorEmail(String visitorEmail) {
		this.visitorEmail = visitorEmail;
	}

	public String getVisitorMobile() {
		return visitorMobile;
	}

	public void setVisitorMobile(String visitorMobile) {
		this.visitorMobile = visitorMobile;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	@Override
	public String toString() {
		return "GetEventVisitorName [visitorId=" + visitorId + ", visitorName=" + visitorName + ", visitorEmail="
				+ visitorEmail + ", visitorMobile=" + visitorMobile + ", likeCount=" + likeCount + "]";
	}
	
}
