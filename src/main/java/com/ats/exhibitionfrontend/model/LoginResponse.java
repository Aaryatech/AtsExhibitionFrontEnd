package com.ats.exhibitionfrontend.model;

public class LoginResponse {

	private boolean error;
	private String message;
	private Exhibitor exhibitor;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Exhibitor getExhibitor() {
		return exhibitor;
	}

	public void setExhibitor(Exhibitor exhibitor) {
		this.exhibitor = exhibitor;
	}

	@Override
	public String toString() {
		return "LoginResponse [error=" + error + ", message=" + message + ", exhibitor=" + exhibitor + "]";
	}

}
