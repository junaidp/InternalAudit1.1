
package com.internalaudit.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "assesmentgrid")
public class AssesmentGridDbEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "assesmentid")
	private int assesmentId;

	@Column(name = "assesmentname")
	private String assesmentName;

	@Column(name = "jobid")
	private int jobId;

	@Column(name = "completelysatisfied")
	private boolean completelySatisfied;

	@Column(name = "partiallysatisfied")
	private boolean partiallySatisfied;

	@Column(name = "unsatisfied")
	private boolean unSatisfied;

	public int getAssesmentId() {
		return assesmentId;
	}

	public void setAssesmentId(int assesmentId) {
		this.assesmentId = assesmentId;
	}

	public String getAssesmentName() {
		return assesmentName;
	}

	public void setAssesmentName(String assesmentName) {
		this.assesmentName = assesmentName;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public boolean isCompletelySatisfied() {
		return completelySatisfied;
	}

	public void setCompletelySatisfied(boolean completelySatisfied) {
		this.completelySatisfied = completelySatisfied;
	}

	public boolean isPartiallySatisfied() {
		return partiallySatisfied;
	}

	public void setPartiallySatisfied(boolean partiallySatisfied) {
		this.partiallySatisfied = partiallySatisfied;
	}

	public boolean isUnSatisfied() {
		return unSatisfied;
	}

	public void setUnSatisfied(boolean unSatisfied) {
		this.unSatisfied = unSatisfied;
	}

}
