package com.internalaudit.client.DashboardNew;

import java.io.Serializable;

public class InformationRequest implements Serializable {

	// field work data
	private String informationReport;
	private String raisedTo;
	private String raisedBy;
	private String overDueDays;
	private int id;

	public InformationRequest() {

	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getInformationReport() {
		return informationReport;
	}


	public void setInformationReport(String informationReport) {
		this.informationReport = informationReport;
	}


	public String getRaisedTo() {
		return raisedTo;
	}


	public void setRaisedTo(String raisedTo) {
		this.raisedTo = raisedTo;
	}


	public String getRaisedBy() {
		return raisedBy;
	}


	public void setRaisedBy(String raisedBy) {
		this.raisedBy = raisedBy;
	}


	public String getOverDueDays() {
		return overDueDays;
	}


	public void setOverDueDays(String overDueDays) {
		this.overDueDays = overDueDays;
	}


	

}