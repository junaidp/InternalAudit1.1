package com.internalaudit.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class EngagementDTO implements Serializable{
	
	private ArrayList<ActivityObjective> activityObjectiveList = new ArrayList<ActivityObjective>();
	private ArrayList<RiskObjective> riskObjectiveList = new ArrayList<RiskObjective>();
	private ArrayList<SuggestedControls> suggestedControlsList = new ArrayList<SuggestedControls>();
	private ArrayList<AuditProgramme> auditProgrammeList = new ArrayList<AuditProgramme>();
	
	
	
	
	public ArrayList<ActivityObjective> getActivityObjectiveList() {
		return activityObjectiveList;
	}
	public void setActivityObjectiveList(ArrayList<ActivityObjective> activityObjectiveList) {
		this.activityObjectiveList = activityObjectiveList;
	}
	public ArrayList<RiskObjective> getRiskObjectiveList() {
		return riskObjectiveList;
	}
	public void setRiskObjectiveList(ArrayList<RiskObjective> riskObjectiveList) {
		this.riskObjectiveList = riskObjectiveList;
	}
	public ArrayList<SuggestedControls> getSuggestedControlsList() {
		return suggestedControlsList;
	}
	public void setSuggestedControlsList(ArrayList<SuggestedControls> suggestedControlsList) {
		this.suggestedControlsList = suggestedControlsList;
	}
	public ArrayList<AuditProgramme> getAuditProgrammeList() {
		return auditProgrammeList;
	}
	public void setAuditProgrammeList(ArrayList<AuditProgramme> auditProgrammeList) {
		this.auditProgrammeList = auditProgrammeList;
	}

}
