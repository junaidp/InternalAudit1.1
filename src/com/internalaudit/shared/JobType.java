package com.internalaudit.shared;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity

@Table(name="jobtype")
public class JobType   implements Serializable {

	
	@Id 
	@Column(name="jobtypeId")
	private int jobTypeId;
	
	@Column(name="jobtypeName")
	private String jobTypeName;

	public int getJobTypeId() {
		return jobTypeId;
	}

	public void setJobTypeId(int jobTypeId) {
		this.jobTypeId = jobTypeId;
	}

	public String getJobTypeName() {
		return jobTypeName;
	}

	public void setJobTypeName(String jobTypeName) {
		this.jobTypeName = jobTypeName;
	}
}