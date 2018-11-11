package com.internalaudit.client.view.ToDo;
import java.io.Serializable;
import java.util.Date;

import com.internalaudit.shared.Employee;
public class ToDoReceiverEntity implements Serializable {

	// field work data
	private String requestedItem;
	private Employee raisedBy;
	private String relatedJob;
	private String status;
	private String viewButton;
	private Date overDueDays;
	private int id;
	
	public ToDoReceiverEntity() {

	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Employee getRaisedBy() {
		return raisedBy;
	}


	public void setRaisedBy(Employee raisedBy) {
		this.raisedBy = raisedBy;
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


	

}