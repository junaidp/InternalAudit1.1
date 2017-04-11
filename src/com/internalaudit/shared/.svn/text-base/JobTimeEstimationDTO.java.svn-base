package com.internalaudit.shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


public class JobTimeEstimationDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private JobTimeEstimation  jobTimeEstimation = new JobTimeEstimation();
	
	private ArrayList<ResourceUse> resourceUse = new ArrayList<ResourceUse>();
	
	public JobTimeEstimation getJobTimeEstimation() {
		return jobTimeEstimation;
	}
	public void setJobTimeEstimation(JobTimeEstimation jobTimeEstimation) {
		this.jobTimeEstimation = jobTimeEstimation;
	}
	public ArrayList<ResourceUse> getResourceUse() {
		return resourceUse;
	}
	public void setResourceUse(ArrayList<ResourceUse> resourceUse) {
		this.resourceUse = resourceUse;
	}
	
}
