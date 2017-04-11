package com.internalaudit.shared;

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


@Entity

@Table(name="jobTimeEstimation")
public class JobTimeEstimation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="jobTimeEstimationId")
	private int jobTimeEstimationId;
	
	@Column(name="jobid")
	private int jobId;
	
	@Column(name="scopeOfWork")
	private String scopeOfWork;
	
	@Column(name="estimatedWeeks")
	private int estimatedWeeks;  
	
	@Column(name="fieldWorkManHours")
	private int fieldWorkManHours;
	
	@Column(name="managementHours")
	private int managementHours;
	
	@Column(name="totalWorkingManHours")
	private int totalWorkingManHours;
	
	@Column(name="placeOfWork")
	private String placeOfWork;
	
	@Column(name="travelDays")
	private int travelDays;
	
	@Column(name="hoursInclTravel")
	private int hoursInclTravel;

	@Column(name = "year")
	private int year;
	
//	public Map<String, Integer> resourcenNum = new HashMap<String, Integer>();  
	
	
	public String getScopeOfWork() {
		return scopeOfWork;
	}


	public void setScopeOfWork(String scopeOfWork) {
		this.scopeOfWork = scopeOfWork;
	}


	public int getEstimatedWeeks() {
		return estimatedWeeks;
	}


	public void setEstimatedWeeks(int estimatedWeeks) {
		this.estimatedWeeks = estimatedWeeks;
	}


	public int getFieldWorkManHours() {
		return fieldWorkManHours;
	}


	public void setFieldWorkManHours(int fieldWorkManHours) {
		this.fieldWorkManHours = fieldWorkManHours;
	}


	public int getManagementHours() {
		return managementHours;
	}


	public void setManagementHours(int managementHours) {
		this.managementHours = managementHours;
	}


	public int getTotalWorkingManHours() {
		return totalWorkingManHours;
	}


	public void setTotalWorkingManHours(int totalWorkingManHours) {
		this.totalWorkingManHours = totalWorkingManHours;
	}


	public String getPlaceOfWork() {
		return placeOfWork;
	}


	public void setPlaceOfWork(String placeOfWork) {
		this.placeOfWork = placeOfWork;
	}


	public int getTravelDays() {
		return travelDays;
	}


	public void setTravelDays(int travelDays) {
		this.travelDays = travelDays;
	}


	public int getHoursInclTravel() {
		return hoursInclTravel;
	}


	public void setHoursInclTravel(int hoursInclTravel) {
		this.hoursInclTravel = hoursInclTravel;
	}


//	public Map<String, Integer> getResourcenNum() {
//		return resourcenNum;
//	}
//
//
//	public void setResourcenNum(Map<String, Integer> resourcenNum) {
//		this.resourcenNum = resourcenNum;
//	}


	public int getJobTimeEstimationId() {
		return jobTimeEstimationId;
	}


	public void setJobTimeEstimationId(int jobTimeEstimationId) {
		this.jobTimeEstimationId = jobTimeEstimationId;
	}


	public int getJobId() {
		return jobId;
	}


	public void setJobId(int jobId) {
		this.jobId = jobId;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}
	
}
