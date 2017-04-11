package com.internalaudit.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class DashBoardDTO implements Serializable{
	
	private ArrayList<Strategic> strategics = new ArrayList<Strategic>();
//	private ArrayList<Risk> risks = new ArrayList<Risk>();
//	private ArrayList<AuditWork> auditWorks = new ArrayList<AuditWork>();
//	private ArrayList<AuditStep> auditSteps = new ArrayList<AuditStep>();

	private String objectiveName ;
	private String initiatedBy ;
	private String assignedTo ;
//	private String approvedBy ;
	private String phase ;
	private String status ;
//	private String date ;
	
	
	public ArrayList<Strategic> getStrategics() {
		return strategics;
	}

	public void setStrategics(ArrayList<Strategic> strategics) {
		this.strategics = strategics;
	}

	public String getObjectiveName() {
		return objectiveName;
	}

	public void setObjectiveName(String objectiveName) {
		this.objectiveName = objectiveName;
	}

	public String getInitiatedBy() {
		return initiatedBy;
	}

	public void setInitiatedBy(String initiatedBy) {
		this.initiatedBy = initiatedBy;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
