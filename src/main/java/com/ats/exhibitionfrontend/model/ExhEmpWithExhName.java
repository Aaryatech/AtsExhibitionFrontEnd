package com.ats.exhibitionfrontend.model;

public class ExhEmpWithExhName {

	private int empId;
	private int exhId;
	private String exhName;
	private String empName;
	private String empDesignation;
	private String empMobile;
	private String empEmail;
	private String empPhoto;
	private String empEducation;
	private int isUsed;
	private String password;
	private String token;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getExhId() {
		return exhId;
	}

	public void setExhId(int exhId) {
		this.exhId = exhId;
	}

	public String getExhName() {
		return exhName;
	}

	public void setExhName(String exhName) {
		this.exhName = exhName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpDesignation() {
		return empDesignation;
	}

	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}

	public String getEmpMobile() {
		return empMobile;
	}

	public void setEmpMobile(String empMobile) {
		this.empMobile = empMobile;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpPhoto() {
		return empPhoto;
	}

	public void setEmpPhoto(String empPhoto) {
		this.empPhoto = empPhoto;
	}

	public String getEmpEducation() {
		return empEducation;
	}

	public void setEmpEducation(String empEducation) {
		this.empEducation = empEducation;
	}

	public int getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "ExhEmpWithExhName [empId=" + empId + ", exhId=" + exhId + ", exhName=" + exhName + ", empName="
				+ empName + ", empDesignation=" + empDesignation + ", empMobile=" + empMobile + ", empEmail=" + empEmail
				+ ", empPhoto=" + empPhoto + ", empEducation=" + empEducation + ", isUsed=" + isUsed + ", password="
				+ password + ", token=" + token + "]";
	}

}
