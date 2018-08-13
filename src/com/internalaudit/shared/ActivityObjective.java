
package com.internalaudit.shared;

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
import javax.persistence.Transient;


@Entity

@Table(name="activityObjective")
public class ActivityObjective   implements Serializable {

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)	
	@Column(name="objectiveId")
	private int objectiveId;
	
	@Column(name ="objectiveName")
	private String objectiveName;
	
	@JoinColumn(name = "subProcessId", nullable = true)
	@ManyToOne(fetch = FetchType.LAZY)
	private SubProcess subProcessId;
	
	@Column(name ="checked")
	private boolean checked;
	
	@Column(name ="referenceNo")
	private String referenceNo;
	
	@Transient
	private int activityJobRelation;
	
	@Transient
	private int status;

	public int getObjectiveId() {
		return objectiveId;
	}

	public void setObjectiveId(int objectiveId) {
		this.objectiveId = objectiveId;
	}

	public String getObjectiveName() {
		return objectiveName;
	}

	public void setObjectiveName(String objectiveName) {
		this.objectiveName = objectiveName;
	}

	public SubProcess getSubProcessId() {
		return subProcessId;
	}

	public void setSubProcessId(SubProcess subProcessId) {
		this.subProcessId = subProcessId;
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public int getActivityJobRelation() {
		return activityJobRelation;
	}

	public void setActivityJobRelation(int activityJobRelation) {
		this.activityJobRelation = activityJobRelation;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
