package com.internalaudit.client.view.ToDo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.internalaudit.shared.InformationRequestEntity;
import com.internalaudit.shared.InformationRequestLogEntity;
public class InformationRequestRaiseEntity implements Serializable {

	// field work data
	private String requestedItem;
	private String raisedTo;
	private String relatedJob;
	private String status;
	private Date overDueDays;
	private int id;
	private String viewButton;
	private String Reply;
	private int raisedToId;
	private int raisedById;
	private int relatedJobId;
	private String contactEmail;
	private Boolean sendNotification;
	private Boolean sendReminder;
	private int sstatus;
	private  ArrayList<InformationRequestLogEntity>   informationRequestLogList;
	public InformationRequestRaiseEntity() {

	}




	public String getRaisedTo() {
		return raisedTo;
	}


	public void setRaisedTo(String raisedTo) {
		this.raisedTo = raisedTo;
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


	public Date getOverDueDays() {
		return overDueDays;
	}


	public void setOverDueDays(Date overDueDays) {
		this.overDueDays = overDueDays;
	}


	public String getReply() {
		return Reply;
	}


	public void setReply(String reply) {
		Reply = reply;
	}


	public int getRaisedToId() {
		return raisedToId;
	}


	public void setRaisedToId(int raisedToId) {
		this.raisedToId = raisedToId;
	}


	public int getRaisedById() {
		return raisedById;
	}


	public void setRaisedById(int raisedById) {
		this.raisedById = raisedById;
	}


	public int getRelatedJobId() {
		return relatedJobId;
	}


	public void setRelatedJobId(int relatedJobId) {
		this.relatedJobId = relatedJobId;
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getContactEmail() {
		return contactEmail;
	}




	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
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




	public ArrayList<InformationRequestLogEntity> getInformationRequestLogList() {
		return informationRequestLogList;
	}




	public void setInformationRequestLogList(ArrayList<InformationRequestLogEntity> informationRequestLogList) {
		this.informationRequestLogList = informationRequestLogList;
	}



	

}