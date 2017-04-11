package com.internalaudit.shared;

import java.io.Serializable;
import java.util.ArrayList;

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

@Table(name = "audit_step" )
public class AuditStep implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7217627195055650063L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="audit_step_id")
	private int auditStepId;
	
	@Column(name="population")
	private String population;
	
	@Column(name="sample_selected")
	private String sampleSelected;

	@Column(name="selection_basis")
	private String selectionBasis;
	
	@Column(name="proceduce_performance")
	private String proceducePerformance;
	
	@Column(name="conclusion")
	private String conclusion;

	@Column(name="job_id")
	private int jobId; //job creation id
	
	@JoinColumn(name = "auditWork")
	@ManyToOne(fetch = FetchType.LAZY)
	private AuditWork auditWork;
	
	@Transient
	private ArrayList<Exceptions> exceptions = new ArrayList<Exceptions>();
	
	@Column(name = "year")
	private int year;
	
	@Column(name="status")
	private int status;
	
	public int getAuditStepId() {
		return auditStepId;
	}

	public void setAuditStepId(int exceptionId) {
		this.auditStepId = exceptionId;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public String getSampleSelected() {
		return sampleSelected;
	}

	public void setSampleSelected(String sampleSelected) {
		this.sampleSelected = sampleSelected;
	}

	public String getSelectionBasis() {
		return selectionBasis;
	}

	public void setSelectionBasis(String selectionBasis) {
		this.selectionBasis = selectionBasis;
	}

	public String getProceducePerformance() {
		return proceducePerformance;
	}

	public void setProceducePerformance(String proceducePerformance) {
		this.proceducePerformance = proceducePerformance;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public AuditWork getAuditWork() {
		return auditWork;
	}

	public void setAuditWork(AuditWork auditWork) {
		this.auditWork = auditWork;
	}

	public ArrayList<Exceptions> getExceptions() {
		return exceptions;
	}

	public void setExceptions(ArrayList<Exceptions> exceptions) {
		this.exceptions = exceptions;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	


}
