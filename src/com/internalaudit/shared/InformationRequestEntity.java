
package com.internalaudit.shared;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name="informationRequest")

public class InformationRequestEntity implements Serializable {

//	private static final long serialVersionUID = -7673298596496592711L;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="informationRequestId")
	private int informationRequestId;
	
	@Column(name="companyId")
	private int companyId;
	
	@Column(name="requestItem")
	private String requestItem;
	
	@JoinColumn(name = "contactResponsible")
	@ManyToOne(fetch = FetchType.LAZY)
	private Employee contactResponsible;
	

	
	@Column(name="contactEmail")
	private String contactEmail;

	@JoinColumn(name = "assignedFrom")
	@ManyToOne(fetch = FetchType.LAZY)
	private Employee assignedFrom;
	
	@JoinColumn(name = "job")
	@ManyToOne(fetch = FetchType.LAZY)
	private JobCreation job;
	
	@Column(name="dueDate")
	private Date dueDate;
	
	@Column(name="status")
	private int status;
	
	@Column(name="sendNotication")
	private Boolean sendNotication;
	
	@Column(name="sendReminder")
	private Boolean sendReminder;

	@Column(name="respond")
	private String respond;
	
	public int getInformationRequestId() {
		return informationRequestId;
	}

	public void setInformationRequestId(int informationRequestId) {
		this.informationRequestId = informationRequestId;
	}

	public String getRequestItem() {
		return requestItem;
	}

	public void setRequestItem(String requestItem) {
		this.requestItem = requestItem;
	}


	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date date) {
		this.dueDate = date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Boolean getSendNotication() {
		return sendNotication;
	}

	public void setSendNotication(Boolean sendNotication) {
		this.sendNotication = sendNotication;
	}

	public Boolean getSendReminder() {
		return sendReminder;
	}

	public void setSendReminder(Boolean sendReminder) {
		this.sendReminder = sendReminder;
	}

	public Employee getAssignedFrom() {
		return assignedFrom;
	}

	public void setAssignedFrom(Employee assignedFrom) {
		this.assignedFrom = assignedFrom;
	}

	public Employee getContactResponsible() {
		return contactResponsible;
	}

	public void setContactResponsible(Employee contactResponsible) {
		this.contactResponsible = contactResponsible;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public JobCreation getJob() {
		return job;
	}

	public void setJob(JobCreation job) {
		this.job = job;
	}

	public String getRespond() {
		return respond;
	}

	public void setRespond(String respond) {
		this.respond = respond;
	}
	
	
}
