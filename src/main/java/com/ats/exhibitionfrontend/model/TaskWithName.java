package com.ats.exhibitionfrontend.model;

public class TaskWithName {

	private int taskId;
	private int exhId;
	private String exhName;
	private int empId;
	private String empName;
	private String date;
	private String taskDesc;
	private String remark;
	private String status;
	private int isUsed;
	private String taskCompletedDate;
	
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTaskDesc() {
		return taskDesc;
	}
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	
	
	public String getTaskCompletedDate() {
		return taskCompletedDate;
	}
	public void setTaskCompletedDate(String taskCompletedDate) {
		this.taskCompletedDate = taskCompletedDate;
	}
	@Override
	public String toString() {
		return "TaskWithName [taskId=" + taskId + ", exhId=" + exhId + ", exhName=" + exhName + ", empId=" + empId
				+ ", empName=" + empName + ", date=" + date + ", taskDesc=" + taskDesc + ", remark=" + remark
				+ ", status=" + status + ", isUsed=" + isUsed + ", taskCompletedDate=" + taskCompletedDate + "]";
	}
	
}
