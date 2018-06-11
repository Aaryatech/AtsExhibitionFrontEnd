package com.ats.exhibition.model.emptaskenq;

import java.util.List;

public class EmpEnqDatewiseBean {

	String date;
	
	List<GetEmpEnquiry> empEnq;
	
	
	public String getDate() {
		return date;
	}

	public List<GetEmpEnquiry> getEmpEnq() {
		return empEnq;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setEmpEnq(List<GetEmpEnquiry> empEnq) {
		this.empEnq = empEnq;
	}


	@Override
	public String toString() {
		return "EmpEnqDatewiseBean [date=" + date + ", empEnq=" + empEnq + "]";
	}

	
}
