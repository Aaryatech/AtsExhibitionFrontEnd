package com.ats.exhibitionfrontend.model;
 

public class Visitor {
	 
	private int visitorId; 
	private int eventId; 
	private int orgId; 
	private String visitorName; 
	private String visitorEmail; 
	private String visitorMobile; 
	private String visitorRepresent; 
	private int isActive; 
	private int isUsed; 
	private int locationId; 
	private int companyTypeId; 
	private String token;
	public int getVisitorId() {
		return visitorId;
	}
	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
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
	public String getVisitorRepresent() {
		return visitorRepresent;
	}
	public void setVisitorRepresent(String visitorRepresent) {
		this.visitorRepresent = visitorRepresent;
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
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getCompanyTypeId() {
		return companyTypeId;
	}
	public void setCompanyTypeId(int companyTypeId) {
		this.companyTypeId = companyTypeId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "Visitor [visitorId=" + visitorId + ", eventId=" + eventId + ", orgId=" + orgId + ", visitorName="
				+ visitorName + ", visitorEmail=" + visitorEmail + ", visitorMobile=" + visitorMobile
				+ ", visitorRepresent=" + visitorRepresent + ", isActive=" + isActive + ", isUsed=" + isUsed
				+ ", locationId=" + locationId + ", companyTypeId=" + companyTypeId + ", token=" + token + "]";
	}
	
	
	

}
