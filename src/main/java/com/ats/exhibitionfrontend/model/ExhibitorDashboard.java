package com.ats.exhibitionfrontend.model;

public class ExhibitorDashboard {
	
	private int totalPending;
	private int TotalProcessing;
	private int totalClosed;
	private int totalCompleted;
	public int getTotalPending() {
		return totalPending;
	}
	public void setTotalPending(int totalPending) {
		this.totalPending = totalPending;
	}
	public int getTotalProcessing() {
		return TotalProcessing;
	}
	public void setTotalProcessing(int totalProcessing) {
		TotalProcessing = totalProcessing;
	}
	public int getTotalClosed() {
		return totalClosed;
	}
	public void setTotalClosed(int totalClosed) {
		this.totalClosed = totalClosed;
	}
	public int getTotalCompleted() {
		return totalCompleted;
	}
	public void setTotalCompleted(int totalCompleted) {
		this.totalCompleted = totalCompleted;
	}
	@Override
	public String toString() {
		return "ExhibitorDashboard [totalPending=" + totalPending + ", TotalProcessing=" + TotalProcessing
				+ ", totalClosed=" + totalClosed + ", totalCompleted=" + totalCompleted + "]";
	}

	

}
