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
@Table(name="riskjobrelation")
public class RiskJobRelation   implements Serializable {

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="riskjobrelationId")
	private int riskjobrelationId;
	
	@JoinColumn(name = "jobCreation", nullable = true)
	@ManyToOne(fetch = FetchType.LAZY)
	private JobCreation jobCreationId;
	
	@JoinColumn(name = "riskObjective", nullable = true)
	@ManyToOne(fetch = FetchType.LAZY)
	private RiskObjective riskObjective;
	
	@Column(name="status")
	private int status;
	
	@Column(name="inherintRisk")
	private int inherintRisk;

	public int getRiskjobrelationId() {
		return riskjobrelationId;
	}

	public void setRiskjobrelationId(int riskjobrelationId) {
		this.riskjobrelationId = riskjobrelationId;
	}

	public JobCreation getJobCreationId() {
		return jobCreationId;
	}

	public void setJobCreationId(JobCreation jobCreationId) {
		this.jobCreationId = jobCreationId;
	}

	public RiskObjective getRiskObjective() {
		return riskObjective;
	}

	public void setRiskObjective(RiskObjective riskObjective) {
		this.riskObjective = riskObjective;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getInherintRisk() {
		return inherintRisk;
	}

	public void setInherintRisk(int inherintRisk) {
		this.inherintRisk = inherintRisk;
	}

	
	}
