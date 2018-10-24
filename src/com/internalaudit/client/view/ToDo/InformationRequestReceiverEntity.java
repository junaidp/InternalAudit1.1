package com.internalaudit.client.view.ToDo;
import java.io.Serializable;
public class InformationRequestReceiverEntity implements Serializable {

	// field work data
	private String requestedItem;
	private String raisedBy;
	private String relatedJob;
	private String status;
	private String overDueDays;
	private int id;

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



	public String getOverDueDays() {
		return overDueDays;
	}


	public void setOverDueDays(String overDueDays) {
		this.overDueDays = overDueDays;
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



	

}