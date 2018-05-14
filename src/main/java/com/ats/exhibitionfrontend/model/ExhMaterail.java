package com.ats.exhibitionfrontend.model;

public class ExhMaterail {
	private int trId;
	private int exhId;
	private String matName;
	private String matLink;
	private int isUsed;
	public int getTrId() {
		return trId;
	}
	public void setTrId(int trId) {
		this.trId = trId;
	}
	public int getExhId() {
		return exhId;
	}
	public void setExhId(int exhId) {
		this.exhId = exhId;
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
		return "ExhMaterail [trId=" + trId + ", exhId=" + exhId + ", matName=" + matName + ", matLink=" + matLink
				+ ", isUsed=" + isUsed + "]";
	}


}
