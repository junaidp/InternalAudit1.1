
package com.internalaudit.client.DashboardNew;

import java.io.Serializable;
import java.util.Date;

public class OutstandingCoaching implements Serializable {

	// field work data
	private String coachingNote;
	private String raisedTo;
	private String raisedBy;
	private Date overDueDays;
	private int id;

	public OutstandingCoaching() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCoachingNote() {
		return coachingNote;
	}

	public void setCoachingNote(String coachingNote) {
		this.coachingNote = coachingNote;
	}

	public String getRaisedTo() {
		return raisedTo;
	}

	public void setRaisedTo(String raisedTo) {
		this.raisedTo = raisedTo;
	}

	public String getRaisedBy() {
		return raisedBy;
	}

	public void setRaisedBy(String raisedBy) {
		this.raisedBy = raisedBy;
	}

	public Date getOverDueDays() {
		return overDueDays;
	}

	public void setOverDueDays(Date overDueDays) {
		this.overDueDays = overDueDays;
	}

}