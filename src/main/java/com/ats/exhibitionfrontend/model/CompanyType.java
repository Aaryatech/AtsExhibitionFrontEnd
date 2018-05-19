package com.ats.exhibitionfrontend.model;


public class CompanyType {


	private int companyTypeId;

	private String companyTypeName;

	private String companyTypeDesc;

	private int isUsed;


	public int getCompanyTypeId() {
		return companyTypeId;
	}

	public void setCompanyTypeId(int companyTypeId) {
		this.companyTypeId = companyTypeId;
	}

	public String getCompanyTypeName() {
		return companyTypeName;
	}

	public void setCompanyTypeName(String companyTypeName) {
		this.companyTypeName = companyTypeName;
	}

	public String getCompanyTypeDesc() {
		return companyTypeDesc;
	}

	public void setCompanyTypeDesc(String companyTypeDesc) {
		this.companyTypeDesc = companyTypeDesc;
	}

	public int getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	@Override
	public String toString() {
		return "CompanyType [companyTypeId=" + companyTypeId + ", companyTypeName=" + companyTypeName
				+ ", companyTypeDesc=" + companyTypeDesc + ", isUsed=" + isUsed + "]";
	}

}
