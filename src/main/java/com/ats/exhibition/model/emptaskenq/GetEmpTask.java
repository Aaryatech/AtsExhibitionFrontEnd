package com.ats.exhibition.model.emptaskenq;

public class GetEmpTask  {
	private int  taskId;
	
	private String date;
	
	private String empName;
	
	private int status;
	
	private int empId;
	
	private int tempStatus;
	
	private String taskDesc;

	public int getTaskId() {
		return taskId;
	}
	

	public String getEmpName() {
		return empName;
	}

	public int getStatus() {
		return status;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public String getDate() {
		return date;
	}


	public int getEmpId() {
		return empId;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public void setEmpId(int empId) {
		this.empId = empId;
	}


	public String getTaskDesc() {
		return taskDesc;
	}


	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}


	public int getTempStatus() {
		return tempStatus;
	}


	public void setTempStatus(int tempStatus) {
		this.tempStatus = tempStatus;
	}


	@Override
	public String toString() {
		return "GetEmpTask [taskId=" + taskId + ", date=" + date + ", empName=" + empName + ", status=" + status
				+ ", empId=" + empId + ", tempStatus=" + tempStatus + ", taskDesc=" + taskDesc + "]";
	}


	

}
