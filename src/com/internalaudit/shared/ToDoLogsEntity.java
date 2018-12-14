package com.internalaudit.shared;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name="todologs")

public class ToDoLogsEntity implements Serializable {

//	private static final long serialVersionUID = -7673298596496592711L;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="todologsId")
	private int toDoLogsId;
	
	
	@Column(name="description")
	private String description;
	
	@Column(name="respond")
	private String respond;
	
	@JoinColumn(name = "assignedTo")
	@ManyToOne(fetch = FetchType.LAZY)
	private Employee assignedTo;
	
	@JoinColumn(name = "assignedBy")
	@ManyToOne(fetch = FetchType.LAZY)
	private Employee assignedFrom;
	
	
	@Column(name="date")
	private Date Date;
	
	@Column(name="toDoId")
	private Integer toDoId;
	

	public int getToDoLogsId() {
		return toDoLogsId;
	}


	public void setToDoLogsId(int toDoLogsId) {
		this.toDoLogsId = toDoLogsId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Employee getAssignedTo() {
		return assignedTo;
	}


	public void setAssignedTo(Employee assignedTo) {
		this.assignedTo = assignedTo;
	}


	public Employee getAssignedFrom() {
		return assignedFrom;
	}


	public void setAssignedFrom(Employee assignedFrom) {
		this.assignedFrom = assignedFrom;
	}


	public Date getDate() {
		return Date;
	}


	public void setDate(Date date) {
		Date = date;
	}


	public String getRespond() {
		return respond;
	}


	public void setRespond(String respond) {
		this.respond = respond;
	}


	public Integer getToDoId() {
		return toDoId;
	}


	public void setToDoId(Integer toDoId) {
		this.toDoId = toDoId;
	}




}