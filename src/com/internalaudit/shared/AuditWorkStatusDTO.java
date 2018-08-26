package com.internalaudit.shared;

import java.io.Serializable;

public class AuditWorkStatusDTO implements Serializable {

	private int jobId;
	private String jobName;
	private int underReview;
	private int open;
	private int completed;

	public AuditWorkStatusDTO() {

	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getUnderReview() {
		return underReview;
	}

	public void setUnderReview(int underReview) {
		this.underReview = underReview;
	}

	public int getOpen() {
		return open;
	}

	public void setOpen(int open) {
		this.open = open;
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

}
