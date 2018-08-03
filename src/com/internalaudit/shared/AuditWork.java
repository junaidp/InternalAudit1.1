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
	
	@Column(name = "companyId")
	private int companyId;
	
	@JoinColumn(name = "initiatedBy")
	@ManyToOne(fetch = FetchType.LAZY)
	private Employee initiatedBy;
	
	@JoinColumn(name = "approvedBy")
	@ManyToOne(fetch = FetchType.LAZY)
	private Employee approvedBy;
	
	@Column(name = "feedback")
	private String feedback;
	
	/*
	@JoinColumn(name = "riskId")
	@ManyToOne(fetch = FetchType.EAGER)
	private Risk riskId;
	*/
	//2018 adding new fields

	@JoinColumn(name = "suggestedControlsId", nullable = true)
	@ManyToOne(fetch = FetchType.LAZY)
	private SuggestedControls suggestedControlsId;
	
	//end
	

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

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public Employee getInitiatedBy() {
		return initiatedBy;
	}

	public void setInitiatedBy(Employee initiatedBy) {
		this.initiatedBy = initiatedBy;
	}

	public Employee getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(Employee approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public int getResponsibleControl() {
		return responsibleControl;
	}

	public void setResponsibleControl(int responsibleControl) {
		this.responsibleControl = responsibleControl;
	}

	public SuggestedControls getSuggestedControlsId() {
		return suggestedControlsId;
	}

	public void setSuggestedControlsId(SuggestedControls suggestedControlsId) {
		this.suggestedControlsId = suggestedControlsId;
	}

	

	
}
