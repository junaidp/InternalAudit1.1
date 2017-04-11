package com.internalaudit.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name="jobandareaofexpertise")

public class JobAndAreaOfExpertise implements Serializable {

//	private static final long serialVersionUID = -7673298596496592711L;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="jobandareaid")
	private int jobAndAreaId;
	
	@Column(name="ischecked")
	private int isChecked;
	
	@Column(name="jobid")
	private int jobId;
	
	@JoinColumn(name="departmentid") 
	@ManyToOne(fetch = FetchType.LAZY)
	private Department areaOfExpertiseId;

	public int getJobAndAreaId() {
		return jobAndAreaId;
	}

	public void setJobAndAreaId(int jobAndAreaId) {
		this.jobAndAreaId = jobAndAreaId;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public Department getAreaOfExpertise() {
		return areaOfExpertiseId;
	}

	public void setAreaOfExpertise(Department areaOfExpertise) {
		this.areaOfExpertiseId = areaOfExpertise;
	}

	public int getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(int isChecked) {
		this.isChecked = isChecked;
	}

//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
	
}
