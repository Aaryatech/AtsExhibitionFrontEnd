package com.ats.exhibitionfrontend.model;

public class ExhEventSubscription {
	
	private int exhEsubId;
	
	private int orgId;
	
	private int eventId;
	
	private int exhId;
	
	private float subscriptionAmt;
	
	private float paidAmt;
	
	private float remAmt;
	
	private String stallNo;
	
	private int isApproved;
	
	private int isUsed;

	public int getExhEsubId() {
		return exhEsubId;
	}

	public int getOrgId() {
		return orgId;
	}

	public int getEventId() {
		return eventId;
	}

	public int getExhId() {
		return exhId;
	}

	public float getSubscriptionAmt() {
		return subscriptionAmt;
	}

	public float getPaidAmt() {
		return paidAmt;
	}

	public float getRemAmt() {
		return remAmt;
	}

	public String getStallNo() {
		return stallNo;
	}

	public int getIsApproved() {
		return isApproved;
	}

	public int getIsUsed() {
		return isUsed;
	}

	public void setExhEsubId(int exhEsubId) {
		this.exhEsubId = exhEsubId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public void setExhId(int exhId) {
		this.exhId = exhId;
	}

	public void setSubscriptionAmt(float subscriptionAmt) {
		this.subscriptionAmt = subscriptionAmt;
	}

	public void setPaidAmt(float paidAmt) {
		this.paidAmt = paidAmt;
	}

	public void setRemAmt(float remAmt) {
		this.remAmt = remAmt;
	}

	public void setStallNo(String stallNo) {
		this.stallNo = stallNo;
	}

	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	@Override
	public String toString() {
		return "ExhEventSubscription [exhEsubId=" + exhEsubId + ", orgId=" + orgId + ", eventId=" + eventId + ", exhId="
				+ exhId + ", subscriptionAmt=" + subscriptionAmt + ", paidAmt=" + paidAmt + ", remAmt=" + remAmt
				+ ", stallNo=" + stallNo + ", isApproved=" + isApproved + ", isUsed=" + isUsed + "]";
	}
	
	
	
	
}
