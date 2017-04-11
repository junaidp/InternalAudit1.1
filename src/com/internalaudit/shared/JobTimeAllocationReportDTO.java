package com.internalaudit.shared;

import java.io.Serializable;

public class JobTimeAllocationReportDTO implements Serializable {
	
	private String job;
	private String weeks;
	
	
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getWeeks() {
		return weeks;
	}
	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}
	
	
	

}
