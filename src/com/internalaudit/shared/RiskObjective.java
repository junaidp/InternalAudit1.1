
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

@Table(name="riskObjective")
public class RiskObjective   implements Serializable {

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)	
	@Column(name="riskId")
	private int riskId;
	
	@Column(name ="riskName")
	private String riskname;
	
	@JoinColumn(name = "objectiveId", nullable = true)
	@ManyToOne(fetch = FetchType.LAZY)
	private ActivityObjective objectiveId;

	public int getRiskId() {
		return riskId;
	}

	public void setRiskId(int riskId) {
		this.riskId = riskId;
	}

	public String getRiskname() {
		return riskname;
	}

	public void setRiskname(String riskname) {
		this.riskname = riskname;
	}

	public ActivityObjective getObjectiveId() {
		return objectiveId;
	}

	public void setObjectiveId(ActivityObjective objectiveId) {
		this.objectiveId = objectiveId;
	}

}