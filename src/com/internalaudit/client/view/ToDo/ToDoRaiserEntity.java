
package com.internalaudit.client.view.ToDo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.internalaudit.shared.ToDoLogsEntity;

public class ToDoRaiserEntity implements Serializable {

	// field work data
	private String taskName;
	private String taskDescription;
	private String viewButton;
	private String reply;
	private String raisedTo;
	private String relatedJob;
	private String status;
	private Date overDueDays;
	private int id;
	private int raisedToId;
	private int raisedById;
	private String raisedBy;
	private int relatedJobId;
	private ArrayList<ToDoLogsEntity> todoLogList;
	private boolean readBySender;
	private boolean readByReceiver;

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

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
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

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public Date getOverDueDays() {
		return overDueDays;
	}

	public void setOverDueDays(Date overDueDays) {
		this.overDueDays = overDueDays;
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

	public String getViewButton() {
		return viewButton;
	}

	public void setViewButton(String viewButton) {
		this.viewButton = viewButton;
	}

	public ArrayList<ToDoLogsEntity> getTodoLogList() {
		return todoLogList;
	}

	public void setTodoLogList(ArrayList<ToDoLogsEntity> todoLogList) {
		this.todoLogList = todoLogList;
	}

	public String getRaisedBy() {
		return raisedBy;
	}

	public void setRaisedBy(String raisedBy) {
		this.raisedBy = raisedBy;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public boolean isReadBySender() {
		return readBySender;
	}

	public void setReadBySender(boolean readBySender) {
		this.readBySender = readBySender;
	}

	public boolean isReadByReceiver() {
		return readByReceiver;
	}

	public void setReadByReceiver(boolean readByReceiver) {
		this.readByReceiver = readByReceiver;
	}
}