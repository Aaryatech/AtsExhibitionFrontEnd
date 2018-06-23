package com.ats.exhibitionfrontend.model;
 

public class LoginResponseExh {
	
	private boolean error;
	private String msg;
	private Exhibitor exhibitor;
	
	private int isSubscribed;
	
	private int noOfEmpCanAdd;
	
	ExhSubHeader exhSubHeader;
	
	
	
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Exhibitor getExhibitor() {
		return exhibitor;
	}
	public void setExhibitor(Exhibitor exhibitor) {
		this.exhibitor = exhibitor;
	}
	
	public int getIsSubscribed() {
		return isSubscribed;
	}
	public void setIsSubscribed(int isSubscribed) {
		this.isSubscribed = isSubscribed;
	}
	public int getNoOfEmpCanAdd() {
		return noOfEmpCanAdd;
	}
	public void setNoOfEmpCanAdd(int noOfEmpCanAdd) {
		this.noOfEmpCanAdd = noOfEmpCanAdd;
	}
	public ExhSubHeader getExhSubHeader() {
		return exhSubHeader;
	}
	public void setExhSubHeader(ExhSubHeader exhSubHeader) {
		this.exhSubHeader = exhSubHeader;
	}
	
	@Override
	public String toString() {
		return "LoginResponseExh [error=" + error + ", msg=" + msg + ", exhibitor=" + exhibitor + ", isSubscribed="
				+ isSubscribed + ", noOfEmpCanAdd=" + noOfEmpCanAdd + ", exhSubHeader=" + exhSubHeader + "]";
	}
	
	
	
}
