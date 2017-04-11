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

@Table(name="job_employee_relation")
public class JobEmployeeRelation   implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@Column(name="id")
	private int id;
	
	//join this with employee table
	@JoinColumn(name = "employeeid") 
	@ManyToOne(fetch = FetchType.LAZY)  
	private Employee employeeId;
	
	@JoinColumn(name = "jobCreationId")
	@ManyToOne(fetch = FetchType.LAZY) 
	private JobCreation jobCreationId;

	public JobCreation getJobCreationId() {
		return jobCreationId;
	}

	public void setJobCreationId(JobCreation jobCreationId) {
		this.jobCreationId = jobCreationId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employeeId;
	}

	public void setEmployee(Employee employeeId) {
		this.employeeId = employeeId;
	}

	
}