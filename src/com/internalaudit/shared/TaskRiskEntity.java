package com.internalaudit.shared;

import java.io.Serializable;

public class TaskRiskEntity implements Serializable {

	// field work data
	private String task;
	private String job;
	private String raisedBy;
	private String overDueDays;
	private String status;
	private String resolution;
	private int id;

	public TaskRiskEntity() {

	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getRaisedBy() {
		return raisedBy;
	}

	public void setRaisedBy(String raisedBy) {
		this.raisedBy = raisedBy;
	}

	public String getOverDueDays() {
		return overDueDays;
	}

	public void setOverDueDays(String overDueDays) {
		this.overDueDays = overDueDays;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



}