
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

@Table(name="todo")

public class ToDo implements Serializable {

//	private static final long serialVersionUID = -7673298596496592711L;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="toDoId")
	private int toDoId;
	
	 @Column(name="companyId")
	private int companyId;
	
	@Column(name="description")
	private String description;
	
	@JoinColumn(name = "assignedTo")
	@ManyToOne(fetch = FetchType.LAZY)
	private Employee assignedTo;
	
	@JoinColumn(name = "assignedFrom")
	@ManyToOne(fetch = FetchType.LAZY)
	private Employee assignedFrom;
	
	@JoinColumn(name = "job")
	@ManyToOne(fetch = FetchType.LAZY)
	private JobCreation job;
	
	@Column(name="dueDate")
	private Date dueDate;

	@Column(name="respond")
	private String respond;
	
	public int getToDoId() {
		return toDoId;
	}

	public void setToDoId(int toDoId) {
		this.toDoId = toDoId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
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

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getRespond() {
		return respond;
	}

	public void setRespond(String respond) {
		this.respond = respond;
	}

	public JobCreation getJob() {
		return job;
	}

	public void setJob(JobCreation job) {
		this.job = job;
	}
	
	
}
