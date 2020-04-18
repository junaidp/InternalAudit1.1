package com.internalaudit.shared;

import java.io.Serializable;
import java.util.Date;

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

@Table(name = "recommendation")
public class Recommendation implements Serializable {
	// recommendationId, jobId, employeeId, recommendation,

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "recommendationId")
	private int recommendationId;

	@JoinColumn(name = "jobId")
	@ManyToOne(fetch = FetchType.EAGER)
	private JobCreation jobCreationId;

	@Column(name = "recommendation")
	private String recommendation;

	@Column(name = "dueDate")
	private Date dueDate;

	public JobCreation getJobCreationId() {
		return jobCreationId;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dateBox) {
		this.dueDate = dateBox;
	}

	public void setJobCreationId(JobCreation jobCreationId) {
		this.jobCreationId = jobCreationId;
	}
}
