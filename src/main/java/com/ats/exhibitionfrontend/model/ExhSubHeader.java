package com.ats.exhibitionfrontend.model;


public class ExhSubHeader {

	
	private int subHeaderId;

	private int exhId;

	private String fromDate;

	private String toDate;

	private int paymentStatus;

	private float totolAmt;

	private float paidAmt;

	private float remAmt;

	private int isUsed;

	private int status;

	public int getSubHeaderId() {
		return subHeaderId;
	}

	public int getExhId() {
		return exhId;
	}

	public String getFromDate() {
		return fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public int getPaymentStatus() {
		return paymentStatus;
	}

	public float getTotolAmt() {
		return totolAmt;
	}

	public float getPaidAmt() {
		return paidAmt;
	}

	public float getRemAmt() {
		return remAmt;
	}

	public int getIsUsed() {
		return isUsed;
	}

	public int getStatus() {
		return status;
	}

	public void setSubHeaderId(int subHeaderId) {
		this.subHeaderId = subHeaderId;
	}

	public void setExhId(int exhId) {
		this.exhId = exhId;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public void setTotolAmt(float totolAmt) {
		this.totolAmt = totolAmt;
	}

	public void setPaidAmt(float paidAmt) {
		this.paidAmt = paidAmt;
	}

	public void setRemAmt(float remAmt) {
		this.remAmt = remAmt;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ExhSubHeader [subHeaderId=" + subHeaderId + ", exhId=" + exhId + ", fromDate=" + fromDate + ", toDate="
				+ toDate + ", paymentStatus=" + paymentStatus + ", totolAmt=" + totolAmt + ", paidAmt=" + paidAmt
				+ ", remAmt=" + remAmt + ", isUsed=" + isUsed + ", status=" + status + "]";
	}
	
	
	
}
