package com.ats.exhibitionfrontend.model;

public class GenerateOtp {
	private int otp;
	private int exhId;

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public int getExhId() {
		return exhId;
	}

	public void setExhId(int exhId) {
		this.exhId = exhId;
	}

	@Override
	public String toString() {
		return "GenerateOtp [otp=" + otp + ", exhId=" + exhId + "]";
	}

}
