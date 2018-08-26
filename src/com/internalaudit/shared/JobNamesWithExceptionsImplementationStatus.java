package com.internalaudit.shared;

import java.io.Serializable;

public class JobNamesWithExceptionsImplementationStatus implements Serializable {

	private int jobId;
	private String jobName;
	private int notImplemented;
	private int implemented;

	public JobNamesWithExceptionsImplementationStatus() {

	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getNotImplemented() {
		return notImplemented;
	}

	public void setNotImplemented(int notImplemented) {
		this.notImplemented = notImplemented;
	}

	public int getImplemented() {
		return implemented;
	}

	public void setImplemented(int implemented) {
		this.implemented = implemented;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

}
