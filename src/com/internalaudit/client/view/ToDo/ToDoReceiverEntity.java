package com.internalaudit.client.view.ToDo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.internalaudit.shared.ToDoLogsEntity;

public class ToDoReceiverEntity implements Serializable {

	// field work data
	private String taskName;
	private String taskDescription;
	private String raisedBy;
	private String raisedTo;
	private int raisedById;
	private int relatedJobId;
	private String relatedJob;
	private String status;
	private String viewButton;
	private Date overDueDays;
	private int id;
	private int raisedToId;
	private boolean read;
	private ArrayList<ToDoLogsEntity> todoLogList;

	public ToDoReceiverEntity() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String requestedItem) {
		this.taskDescription = taskDescription;
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

	public String getRaisedBy() {
		return raisedBy;
	}

	public void setRaisedBy(String raisedBy) {
		this.raisedBy = raisedBy;
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

	public int getRaisedToId() {
		return raisedToId;
	}

	public void setRaisedToId(int raisedToId) {
		this.raisedToId = raisedToId;
	}

	public String getRaisedTo() {
		return raisedTo;
	}

	public void setRaisedTo(String raisedTo) {
		this.raisedTo = raisedTo;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public ArrayList<ToDoLogsEntity> getTodoLogList() {
		return todoLogList;
	}

	public void setTodoLogList(ArrayList<ToDoLogsEntity> todoLogList) {
		this.todoLogList = todoLogList;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

}