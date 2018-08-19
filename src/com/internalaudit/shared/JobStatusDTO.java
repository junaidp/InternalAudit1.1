package com.internalaudit.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class JobStatusDTO implements Serializable{
	private ArrayList<FieldWorkStatusDTO> listFieldWorkStatus = new ArrayList<FieldWorkStatusDTO>();
	private ArrayList<PlanningStatusDTO> listPlanningStatus = new ArrayList<PlanningStatusDTO>();
	private String reportingStatus;
	
	public JobStatusDTO(){
		
	}
	
	public ArrayList<FieldWorkStatusDTO> getListFieldWorkStatus() {
		return listFieldWorkStatus;
	}

	public void setListFieldWorkStatus(ArrayList<FieldWorkStatusDTO> listAuditSteps) {
		this.listFieldWorkStatus = listAuditSteps;
	}

	public ArrayList<PlanningStatusDTO> getListPlanningStatus() {
		return listPlanningStatus;
	}

	public void setListPlanningStatus(ArrayList<PlanningStatusDTO> listPlanningStatus) {
		this.listPlanningStatus = listPlanningStatus;
	}

	public String getReportingStatus() {
		return reportingStatus;
	}

	public void setReportingStatus(String reportingStatus) {
		this.reportingStatus = reportingStatus;
	}

	

	
}
