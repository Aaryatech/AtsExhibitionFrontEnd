package com.ats.exhibitionfrontend.model;

import java.util.List;

public class VisitorExcelResponse {
	
	List<Visitor> addedVis;
	
	List<Visitor> dupMobNoVis;

	public List<Visitor> getAddedVis() {
		return addedVis;
	}

	public List<Visitor> getDupMobNoVis() {
		return dupMobNoVis;
	}

	public void setAddedVis(List<Visitor> addedVis) {
		this.addedVis = addedVis;
	}

	public void setDupMobNoVis(List<Visitor> dupMobNoVis) {
		this.dupMobNoVis = dupMobNoVis;
	}

	@Override
	public String toString() {
		return "VisitorExcelResponse [addedVis=" + addedVis + ", dupMobNoVis=" + dupMobNoVis + "]";
	}

}
