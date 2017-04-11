package com.internalaudit.shared;

import java.io.Serializable;

public class ExceptionsReportDTO implements Serializable {
	
	private String exceptionName;
	private String jobName;
	private String exceptionStatus;
	private String auditee;
	
	public String getExceptionName() {
		return exceptionName;
	}
	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getExceptionStatus() {
		return exceptionStatus;
	}
	public void setExceptionStatus(String exceptionStatus) {
		this.exceptionStatus = exceptionStatus;
	}
	public String getAuditee() {
		return auditee;
	}
	public void setAuditee(String auditee) {
		this.auditee = auditee;
	}
	

		}
