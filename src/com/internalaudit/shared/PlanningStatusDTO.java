package com.internalaudit.shared;

import java.io.Serializable;
import java.util.Date;

public class PlanningStatusDTO implements Serializable{
	
	private String planningName;
	private Date date;
	private String status;
	private int id;
	
	public PlanningStatusDTO(){
		
	}

	public String getPlanningName() {
		return planningName;
	}

	public void setPlanningName(String planningName) {
		this.planningName = planningName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

}
