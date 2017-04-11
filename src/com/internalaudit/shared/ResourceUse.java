package com.internalaudit.shared;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name="resourceUse")

public class ResourceUse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@Column(name="resourceUseId")
	private int resourceUseId;
	

	@Column(name="noOfResources")
	private int noOfResources;
	
	
	@JoinColumn(name = "skillId", nullable = true)
	@ManyToOne(fetch = FetchType.LAZY)  // in db  it will be INT
	private Skills skillId;
	
	
//	@JoinColumn(name = "jobTimeEstimation", nullable = true)
//	@Column(name="jobId")
//	private String jobId;
	@Column(name="jobId")
	private int jobId;  // in db it will be INT, I think its ok if we just make it INT , dont need to make it much heavy .
	
	@Column(name = "year")
	private int year;
	
	@Column(name = "companyId")
	private int companyId;
	
	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	// lets run 
	public int getResourceUseId() {
		return resourceUseId;
	}


	public void setResourceUseId(int resourceUseId) {
		this.resourceUseId = resourceUseId;
	}


	


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public int getNoOfResources() {
		return noOfResources;
	}


	public void setNoOfResources(int noOfResources) {
		this.noOfResources = noOfResources;
	}


	public Skills getSkill() {
		return skillId;
	}


	public void setSkillId(Skills skillId) {
		this.skillId = skillId;
	}


	public int getJobId() {
		return jobId;
	}


	public void setJobId(int jobId) {
		this.jobId = jobId;
	}


	public int getCompanyId() {
		return companyId;
	}


	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}


}
