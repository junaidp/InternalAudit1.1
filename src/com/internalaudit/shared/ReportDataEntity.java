
package com.internalaudit.shared;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "reportdata")
public class ReportDataEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "reportdataid")
	private int reportDataId;

	@Column(name = "jobid")
	private int jobId;

	@Column(name = "userid")
	private int userId;

	@Column(name = "executivesummary")
	private String executiveSummary;

	@Column(name = "auditpurpose")
	private String auditPurpose;

	@Column(name = "annexure")
	private String annexure;

	@Column(name = "date")
	private Date date;

	@Column(name = "operationalEffectiveness")
	private String operationalEffectiveness;

	public int getReportDataId() {
		return reportDataId;
	}

	public void setReportDataId(int reportDataId) {
		this.reportDataId = reportDataId;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getExecutiveSummary() {
		return executiveSummary;
	}

	public void setExecutiveSummary(String executiveSummary) {
		this.executiveSummary = executiveSummary;
	}

	public String getAuditPurpose() {
		return auditPurpose;
	}

	public void setAuditPurpose(String auditPurpose) {
		this.auditPurpose = auditPurpose;
	}

	public String getAnnexure() {
		return annexure;
	}

	public void setAnnexure(String annexure) {
		this.annexure = annexure;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getOperationalEffectiveness() {
		return operationalEffectiveness;
	}

	public void setOperationalEffectiveness(String operationalEffectiveness) {
		this.operationalEffectiveness = operationalEffectiveness;
	}

}
