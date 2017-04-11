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

@Table(name="job_skill_relation")
public class JobSkillRelation   implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@Column(name="jobskillrelation")
	private int id;
	
	//join this with employee table
	@JoinColumn(name = "softskillid") 
	@ManyToOne(fetch = FetchType.LAZY)  
	private Softskills softskills;
	
	@Column(name = "jobId")
	private int jobId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public Softskills getSoftskills() {
		return softskills;
	}

	public void setSoftskills(Softskills softskills) {
		this.softskills = softskills;
	}
	
}