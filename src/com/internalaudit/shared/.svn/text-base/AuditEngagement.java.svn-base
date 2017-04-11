package com.internalaudit.shared;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.internalaudit.shared.JobCreation;

@Entity

@Table(name="audit_engagement")

public class AuditEngagement implements Serializable {

	private static final long serialVersionUID = 699598646114861759L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="auditEngId")
	private int auditEngId;// u need this ? yes
	
	@Column(name="jobstatus")
	private String jobStatus;
	
	@JoinColumn(name="jobcreationid")
	@OneToOne( fetch = FetchType.LAZY )
	private JobCreation jobCreation;
	
	@Column( name = "assignmentObj" )
	private String assignmentObj;
	
	@Column(name = "activityObj")
	private String activityObj;
	
	@Column(name = "processName")
	private String process;
	
	@Column(name = "auditNotification")
	private String auditNotification;
	
	@Column(name = "sendto")
	private String to;
	
	@Column(name = "cc")
	private String cc;
	
	@Column(name = "year")
	private int year;
	
	@Transient
	private int selectedId;

	public int getAuditEngId() {
		return auditEngId;
	}

	public void setAuditEngId(int auditEngId) {
		this.auditEngId = auditEngId;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public JobCreation getJobCreation() {
		return jobCreation;
	}

	public void setJobCreation(JobCreation jobCreation) {
		this.jobCreation = jobCreation;
	}

	public String getAssignmentObj() {
		return assignmentObj;
	}

	public void setAssignmentObj(String assignmentObj) {
		this.assignmentObj = assignmentObj;
	}

	public String getActivityObj() {
		return activityObj;
	}

	public void setActivityObj(String activityObj) {
		this.activityObj = activityObj;
	}

	public int getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(int selectedId) {
		this.selectedId = selectedId;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getAuditNotification() {
		return auditNotification;
	}

	public void setAuditNotification(String auditNotification) {
		this.auditNotification = auditNotification;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	

}
