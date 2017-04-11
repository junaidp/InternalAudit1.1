package com.internalaudit.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity

@Table(name = "audit_work" )
public class AuditWork implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7217627195055650063L;
	
	@Id
	@Column(name="audit_work_id")
	private int auditWorkId;
	
	@JoinColumn(name = "jobcreationid")
	@ManyToOne(fetch = FetchType.LAZY)
	private JobCreation jobCreationId;
	
	@Column(name="step_no")
	private String stepNo;
	
	@Column(name="description")
	private String description;

	@Column(name="responsible_control")
	private int  responsibleControl;
	
	@Column(name="status")
	private int status;
	
	@Column(name = "year")
	private int year;

	public int getAuditWorkId() {
		return auditWorkId;
	}

	public void setAuditWorkId(int auditWorkId) {
		this.auditWorkId = auditWorkId;
	}

	public String getStepNo() {
		return stepNo;
	}

	public void setStepNo(String stepNo) {
		this.stepNo = stepNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getResponsibleControl() {
		return responsibleControl;
	}

	public void setResponsibleControl(int responsibleControl) {
		this.responsibleControl = responsibleControl;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public JobCreation getJobCreationId() {
		return jobCreationId;
	}

	public void setJobCreationId(JobCreation jobCreationId) {
		this.jobCreationId = jobCreationId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	

	
	
}
