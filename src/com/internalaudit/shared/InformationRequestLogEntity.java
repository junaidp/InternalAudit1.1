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

@Table(name="informationrequestlogs")

public class InformationRequestLogEntity implements Serializable {

//	private static final long serialVersionUID = -7673298596496592711L;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="informationrequestlogsId")
	private int informationrequestlogsId;
	
	
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
	
	@Column(name="informationRequestId")
	private int informationRequestId;

	
	@Column(name="date")
	private Date Date;

	public int getInformationrequestlogsId() {
		return informationrequestlogsId;
	}

	public void setInformationrequestlogsId(int informationrequestlogsId) {
		this.informationrequestlogsId = informationrequestlogsId;
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

	public int getInformationRequestId() {
		return informationRequestId;
	}

	public void setInformationRequestId(int informationRequestId) {
		this.informationRequestId = informationRequestId;
	}

	
}