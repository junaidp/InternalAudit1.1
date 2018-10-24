
package com.internalaudit.client.view.ToDo;
import java.io.Serializable;
public class ToDoRaiserEntity implements Serializable {

	// field work data
	private String requestedItem;
	private String raisedTo;
	private String relatedJob;
	private String status;
	private String overDueDays;
	private int id;

	public ToDoRaiserEntity() {

	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getRaisedTo() {
		return raisedTo;
	}


	public void setRaisedTo(String raisedTo) {
		this.raisedTo = raisedTo;
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