package com.internalaudit.shared;
import java.io.Serializable;
import java.util.Date;

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
	
	@Column(name="referenceNo")
	private String referenceNo;
	
	//auditEngId, jobcreationid, jobstatus, assignmentObj, activityObj, processName, auditNotification, sendto, cc, year, companyId, status, initiatedBy, approvedBy, referenceNo, from, subject
	
	@Column(name="fromPerson")
	private String from;
	
	@Column(name="subject")
	private String subject;
	
//	@Column(name="date")
//	private Date dateTime;
	
	@Column(name = "year")
	private int year;
	
	@Column(name = "companyId")
	private int companyId;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "notificationSentDate")
	private Date notificationSentDate;
	
	@JoinColumn(name = "initiatedBy")
	@ManyToOne(fetch = FetchType.LAZY)
	private Employee initiatedBy;
	
	@JoinColumn(name = "approvedBy")
	@ManyToOne(fetch = FetchType.LAZY)
	private Employee approvedBy;
	
	@Transient
	private int selectedId;
	
	@Transient 
	private Strategic strategic;
	
	@Transient 
	private EngagementDTO engagementDTO;

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

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public Strategic getStrategic() {
		return strategic;
	}

	public void setStrategic(Strategic strategic) {
		this.strategic = strategic;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public EngagementDTO getEngagementDTO() {
		return engagementDTO;
	}

	public void setEngagementDTO(EngagementDTO engagementDTO) {
		this.engagementDTO = engagementDTO;
	}

	public Date getNotificationSentDate() {
		return notificationSentDate;
	}

	public void setNotificationSentDate(Date notificationSentDate) {
		this.notificationSentDate = notificationSentDate;
	}

//	public DateTime getDateTime() {
//		return dateTime;
//	}
//
//	public void setDateTime(DateTime dateTime) {
//		this.dateTime = dateTime;
//	}


}
