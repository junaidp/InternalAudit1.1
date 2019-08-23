
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

@Table(name="riskObjective")
public class RiskObjective   implements Serializable {

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)	
	@Column(name="riskId")
	private int riskId;
	
	@Column(name ="riskName")
	private String riskname;
	
	@JoinColumn(name = "objectiveId", nullable = true)
	@ManyToOne(fetch = FetchType.EAGER)
	private ActivityObjective objectiveId;
	
	@Column(name ="checked")
	private boolean checked;
	
	@Column(name ="riskReferenceNo")
	private String riskReferenceNo;

	@Column(name ="riskRating")
	private int riskRating;
	
	@Transient
	private int status;
	
	@Transient
	private int riskJobRelation;


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

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getRiskReferenceNo() {
		return riskReferenceNo;
	}

	public void setRiskReferenceNo(String riskReferenceNo) {
		this.riskReferenceNo = riskReferenceNo;
	}

	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRiskJobRelation() {
		return riskJobRelation;
	}

	public void setRiskJobRelation(int riskJobRelation) {
		this.riskJobRelation = riskJobRelation;
	}

	public int getRiskRating() {
		return riskRating;
	}

	public void setRiskRating(int riskRating) {
		this.riskRating = riskRating;
	}

}