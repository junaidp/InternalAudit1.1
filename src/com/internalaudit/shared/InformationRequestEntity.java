
package com.internalaudit.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity

@Table(name = "informationrequest")

public class InformationRequestEntity implements Serializable {

	// private static final long serialVersionUID = -7673298596496592711L;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "informationRequestId")
	private int informationRequestId;

	@Column(name = "companyId")
	private int companyId;

	@Column(name = "requestItem")
	private String requestItem;

	@JoinColumn(name = "contactResponsible")
	@ManyToOne(fetch = FetchType.EAGER)
	private Employee contactResponsible;

	@Column(name = "contactEmail")
	private String contactEmail;

	@JoinColumn(name = "assignedFrom")
	@ManyToOne(fetch = FetchType.EAGER)
	private Employee assignedFrom;

	@JoinColumn(name = "job")
	@ManyToOne(fetch = FetchType.EAGER)
	private JobCreation job;

	@Column(name = "dueDate")
	private Date dueDate;

	@Column(name = "status")
	private int status;

	@Column(name = "sendNotication")
	private Boolean sendNotication;

	@Column(name = "messageread")
	private Boolean read;

	@Column(name = "sendReminder")
	private Boolean sendReminder;

	@Column(name = "respond")
	private String respond;

	@Transient
	private ArrayList<InformationRequestLogEntity> informationRequestLogList;

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

	public Boolean getRead() {
		return read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

	public ArrayList<InformationRequestLogEntity> getInformationRequestLogList() {
		return informationRequestLogList;
	}

	public void setInformationRequestLogList(ArrayList<InformationRequestLogEntity> informationRequestLogList) {
		this.informationRequestLogList = informationRequestLogList;
	}

}
