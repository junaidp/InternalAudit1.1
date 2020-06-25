package com.internalaudit.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class EngagementDTO implements Serializable{
	
	private ArrayList<ActivityObjective> activityObjectiveList = new ArrayList<ActivityObjective>();
	private ArrayList<RiskObjective> riskObjectiveList = new ArrayList<RiskObjective>();
	private ArrayList<SuggestedControls> suggestedControlsList = new ArrayList<SuggestedControls>();
	private ArrayList<AuditProgramme> auditProgrammeList = new ArrayList<AuditProgramme>();
	private ArrayList<ActivityObjective> selectedActivityObjectives = new  ArrayList<ActivityObjective>();
	private ArrayList<RiskObjective> selectedObjectiveRisks = new ArrayList<RiskObjective>();
	private ArrayList<SuggestedControls> selectedControls = new ArrayList<SuggestedControls>();
	
	
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
	public ArrayList<ActivityObjective> getSelectedActivityObjectives() {
		return selectedActivityObjectives;
	}
	public void setSelectedActivityObjectives(ArrayList<ActivityObjective> selectedActivityObjectives) {
		this.selectedActivityObjectives = selectedActivityObjectives;
	}
	public ArrayList<RiskObjective> getSelectedObjectiveRisks() {
		return selectedObjectiveRisks;
	}
	public void setSelectedObjectiveRisks(ArrayList<RiskObjective> selectedObjectiveRisks) {
		this.selectedObjectiveRisks = selectedObjectiveRisks;
	}
	public ArrayList<SuggestedControls> getSelectedControls() {
		return selectedControls;
	}
	public void setSelectedControls(ArrayList<SuggestedControls> selectedControls) {
		this.selectedControls = selectedControls;
	}
	
	
}
