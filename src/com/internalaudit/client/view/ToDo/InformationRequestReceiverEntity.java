package com.internalaudit.client.view.ToDo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.internalaudit.shared.InformationRequestLogEntity;

public class InformationRequestReceiverEntity implements Serializable {

	// field work data
	private String requestedItem;
	private String raisedBy;
	private String relatedJob;
	private String status;
	private String viewButton;
	private Date overDueDays;
	private int id;
	private int raiseById;
	private int raisedToId;
	private String contactEmail;
	private Boolean sendNotification;
	private Boolean sendReminder;
	private int sstatus;
	private int relatedJobId;
	private ArrayList<InformationRequestLogEntity> informationRequestLogList;

	public InformationRequestReceiverEntity() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRaisedBy() {
		return raisedBy;
	}

	public void setRaisedBy(String raisedTo) {
		this.raisedBy = raisedTo;
	}

	public String getRequestedItem() {
		return requestedItem;
	}

	public void setRequestedItem(String requestedItem) {
		this.requestedItem = requestedItem;
	}

	public String getRelatedJob() {
		return relatedJob;
	}

	public void setRelatedJob(String relatedJob) {
		this.relatedJob = relatedJob;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getViewButton() {
		return viewButton;
	}

	public void setViewButton(String viewButton) {
		this.viewButton = viewButton;
	}

	public int getRaiseById() {
		return raiseById;
	}

	public void setRaiseById(int raiseById) {
		this.raiseById = raiseById;
	}

	public int getRaisedToId() {
		return raisedToId;
	}

	public void setRaisedToId(int raisedToId) {
		this.raisedToId = raisedToId;
	}

	public Boolean getSendNotification() {
		return sendNotification;
	}

	public void setSendNotification(Boolean sendNotification) {
		this.sendNotification = sendNotification;
	}

	public Boolean getSendReminder() {
		return sendReminder;
	}

	public void setSendReminder(Boolean sendReminder) {
		this.sendReminder = sendReminder;
	}

	public int getSstatus() {
		return sstatus;
	}

	public void setSstatus(int sstatus) {
		this.sstatus = sstatus;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public void setOverDueDays(Date overDueDays) {
		this.overDueDays = overDueDays;
	}

	public Date getOverDueDays() {
		return overDueDays;
	}

	public int getRelatedJobId() {
		return relatedJobId;
	}

	public void setRelatedJobId(int relatedJobId) {
		this.relatedJobId = relatedJobId;
	}

	public ArrayList<InformationRequestLogEntity> getInformationRequestLogList() {
		return informationRequestLogList;
	}

	public void setInformationRequestLogList(ArrayList<InformationRequestLogEntity> informationRequestLogList) {
		this.informationRequestLogList = informationRequestLogList;
	}

}