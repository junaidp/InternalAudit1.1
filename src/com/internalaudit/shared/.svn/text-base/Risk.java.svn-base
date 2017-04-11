package com.internalaudit.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="risk")
public class Risk implements Serializable {

	private static final long serialVersionUID = 8886793627137760454L;
	
	@Id
	@Column(name="risk_id")
	private int riskId;
	
	@Column(name="description")
	private String description;
	
	@Column(name="existing_control")
	private String existingControl;
	
	@Column(name="audit_engage_id")
	private int auditEngageId; // this represents id of AuditEngagement table .
	
	@Column(name = "year")
	private int year;

	public int getRiskId() {
		return riskId;
	}

	public void setRiskId(int riskId) {
		this.riskId = riskId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExistingControl() {
		return existingControl;
	}

	public void setExistingControl(String existingControl) {
		this.existingControl = existingControl;
	}

	public int getAuditEngageId() {
		return auditEngageId;
	}

	public void setAuditEngageId(int auditEngageId) {
		this.auditEngageId = auditEngageId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
