package com.ats.exhibition.model.emptaskenq;

import java.util.List;

public class EmpTaskDatewiseBean {

	String date;
	
	List<GetEmpTask> empTask;

	public String getDate() {
		return date;
	}

	public List<GetEmpTask> getEmpTask() {
		return empTask;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setEmpTask(List<GetEmpTask> empTask) {
		this.empTask = empTask;
	}

	@Override
	public String toString() {
		return "EmpTaskDatewiseBean [date=" + date + ", empTask=" + empTask + "]";
	}
	
}
