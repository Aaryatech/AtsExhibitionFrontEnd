package com.ats.exhibitionfrontend.model;

public class ExhEmpGraph {
	
	private int enqId;
	private int empId;
	private String empName;
	private int noOfEnq;
	private int completed;
	private int processing;
	private int pending;
	private int closed;

	public int getEnqId() {
		return enqId;
	}

	public void setEnqId(int enqId) {
		this.enqId = enqId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getNoOfEnq() {
		return noOfEnq;
	}

	public void setNoOfEnq(int noOfEnq) {
		this.noOfEnq = noOfEnq;
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public int getProcessing() {
		return processing;
	}

	public void setProcessing(int processing) {
		this.processing = processing;
	}

	public int getPending() {
		return pending;
	}

	public void setPending(int pending) {
		this.pending = pending;
	}

	public int getClosed() {
		return closed;
	}

	public void setClosed(int closed) {
		this.closed = closed;
	}

	@Override
	public String toString() {
		return "ExhEmpGraph [enqId=" + enqId + ", empId=" + empId + ", empName=" + empName + ", noOfEnq=" + noOfEnq
				+ ", completed=" + completed + ", processing=" + processing + ", pending=" + pending + ", closed="
				+ closed + "]";
	}


}
