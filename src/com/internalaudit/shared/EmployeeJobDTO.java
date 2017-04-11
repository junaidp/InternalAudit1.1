package com.internalaudit.shared;

import java.io.Serializable;

public class EmployeeJobDTO implements Serializable {

	String jobName="";
	String satrtDate ="";
	String endDate ="";
	String employeeName;
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getSatrtDate() {
		return satrtDate;
	}
	public void setSatrtDate(String satrtDate) {
		this.satrtDate = satrtDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
}
