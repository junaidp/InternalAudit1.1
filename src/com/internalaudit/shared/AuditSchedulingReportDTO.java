package com.internalaudit.shared;

import java.io.Serializable;

public class AuditSchedulingReportDTO implements Serializable {
	
	private String job;
	private String resources;
	private String domain;
	private String division;
	private String department;
	
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	private String riskAssesment;
	private String timeAllocated;
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getResources() {
		return resources;
	}
	public void setResources(String resources) {
		this.resources = resources;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getRiskAssesment() {
		return riskAssesment;
	}
	public void setRiskAssesment(String riskAssesment) {
		this.riskAssesment = riskAssesment;
	}
	public String getTimeAllocated() {
		return timeAllocated;
	}
	public void setTimeAllocated(String timeAllocated) {
		this.timeAllocated = timeAllocated;
	}
	
	
	

}
