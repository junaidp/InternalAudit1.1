package com.internalaudit.client.DashboardNew;

import java.io.Serializable;

public class Issues implements Serializable {

	// field work data
	private String issueTitle;
	private String managementResponce;
	private String status;
	private int id;

	public Issues() {

	}

	public String getIssueTitle() {
		return issueTitle;
	}

	public void setIssueTitle(String issueTitle) {
		this.issueTitle = issueTitle;
	}

	public String getManagementResponce() {
		return managementResponce;
	}

	public void setManagementResponce(String managementResponce) {
		this.managementResponce = managementResponce;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}