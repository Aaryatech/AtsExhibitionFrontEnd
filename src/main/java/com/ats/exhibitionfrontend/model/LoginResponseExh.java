package com.ats.exhibitionfrontend.model;
 

public class LoginResponseExh {
	
	private boolean error;
	private String msg;
	private Exhibitor exhibitor;
	
	private int isSubscribed;
	
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
	
	@Override
	public String toString() {
		return "LoginResponseExh [error=" + error + ", msg=" + msg + ", exhibitor=" + exhibitor + ", isSubscribed="
				+ isSubscribed + "]";
	}
	
	

}
