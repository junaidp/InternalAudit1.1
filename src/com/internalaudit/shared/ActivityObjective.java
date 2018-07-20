
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
}
