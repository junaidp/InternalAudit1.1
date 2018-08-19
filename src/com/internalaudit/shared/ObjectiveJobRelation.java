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

@Table(name="objectivejobrelation")
public class ObjectiveJobRelation   implements Serializable {

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="activityjobid")
	private int activityJobId;
	

	@JoinColumn(name = "jobId", nullable = true)
	@ManyToOne(fetch = FetchType.LAZY)
	private JobCreation jobCreationId;
	

	@JoinColumn(name = "objectiveId", nullable = true)
	@ManyToOne(fetch = FetchType.LAZY)
	private ActivityObjective objectiveId;
	
	@Column(name="status")
	private int status;
	
	@Column(name="date")
	private Date date;
	

	public int getActivityJobId() {
		return activityJobId;
	}


	public void setActivityJobId(int activityJobId) {
		this.activityJobId = activityJobId;
	}


	public JobCreation getJobCreationId() {
		return jobCreationId;
	}


	public void setJobCreationId(JobCreation jobCreationId) {
		this.jobCreationId = jobCreationId;
	}


	public ActivityObjective getObjectiveId() {
		return objectiveId;
	}


	public void setObjectiveId(ActivityObjective objectiveId) {
		this.objectiveId = objectiveId;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	

	}
