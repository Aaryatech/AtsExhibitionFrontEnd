package com.ats.exhibitionfrontend.model;

public class MktMaterial {
	private int matId;
	private String matName;
	private String matLink;
	private int isUsed;
	public int getMatId() {
		return matId;
	}
	public void setMatId(int matId) {
		this.matId = matId;
	}
	public String getMatName() {
		return matName;
	}
	public void setMatName(String matName) {
		this.matName = matName;
	}
	public String getMatLink() {
		return matLink;
	}
	public void setMatLink(String matLink) {
		this.matLink = matLink;
	}
	public int getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	@Override
	public String toString() {
		return "MktMaterial [matId=" + matId + ", matName=" + matName + ", matLink=" + matLink + ", isUsed=" + isUsed
				+ "]";
	}

}
