package com.internalaudit.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
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

	@Table(name="strategic")
	public class Strategic   implements Serializable {

		private static final long serialVersionUID = 1L;

		@Id 
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="id")
		private int id;
		
		
		@Column(name="strategicObjective")
		private String strategicObjective;
		
//		@JoinColumn(name = "objectiveOwner", nullable = true)
//		@ManyToOne(fetch = FetchType.LAZY)
//		private Employee objectiveOwner;
		
		@JoinColumn(name = "relevantDepartment", nullable = true)
		@ManyToOne(fetch = FetchType.LAZY)
		private Department relevantDepartment;
		
		@Column(name="acheivementDate")
		private Date acheivementDate;
		
		@Column(name="rating")
		private String rating;
		
		@Column(name="userDefinedRating")
		private String userDefinedRating;
		
		@JoinColumn(name = "process")
		@ManyToOne(fetch = FetchType.LAZY)
		private Process process;
		
		@JoinColumn(name = "subProcess")
		@ManyToOne(fetch = FetchType.LAZY)
		private SubProcess subProcess;
		
		@JoinColumn(name = "jobType")
		@ManyToOne(fetch = FetchType.LAZY)
		private JobType jobType;
		
		@Column(name="audit")
		private boolean audit;
		
		@JoinColumn(name = "riskFactor", nullable = true)
		@ManyToOne(fetch = FetchType.LAZY)
		private RiskFactor riskFactor;
		
		@Column(name="auditableUnit")
		private String auditableUnit;
		
		@Column(name="phase")
		private int phase;
		
		@Column(name="status")
		private String status;
		
		@Column(name="comments")
		private String comments;
		
		@Column(name="date")
		private Date date;
		
		@Column(name="tab")
		private int tab;
		
		@JoinColumn(name = "initiatedBy", nullable = true)
		@ManyToOne(fetch = FetchType.LAZY)
		private Employee initiatedBy;
		
		@JoinColumn(name = "assignedTo", nullable = true)
		@ManyToOne(fetch = FetchType.LAZY /*, cascade = {CascadeType.ALL}*/)
		private Employee assignedTo;
		
		@JoinColumn(name = "approvedBy", nullable = true)
		@ManyToOne(fetch = FetchType.LAZY)
		private Employee approvedBy;
		
		@Column(name="approvedByAuditHead")
		private boolean approvedByAuditHead;
		
		public boolean isApprovedByAuditHead() {
			return approvedByAuditHead;
		}

		public void setApprovedByAuditHead(boolean approvedByAuditHead) {
			this.approvedByAuditHead = approvedByAuditHead;
		}

		@Transient
		private int nextPhase;
		
		@Transient
		private int loggedInUser;
		
		@Transient
		private String divisionName;
		
		@Transient String jobName;
		
		@Transient
		private String estimatedWeeks;
		
		@Transient
		private ArrayList<String> employees;
		
		public String getJobName() {
			return jobName;
		}

		public void setJobName(String jobName) {
			this.jobName = jobName;
		}

		public String getJobStatus() {
			return jobStatus;
		}

		public void setJobStatus(String jobStatus) {
			this.jobStatus = jobStatus;
		}

		@Transient 
		private String jobStatus;
		
		@Column(name = "year")
		private int year;
		
		@Column(name = "companyId")
		private int companyId;
		
		@Transient
		ArrayList<StrategicDepartments> strategicDepartments;

		public int getLoggedInUser() {
			return loggedInUser;
		}

		public void setLoggedInUser(int loggedInUser) {
			this.loggedInUser = loggedInUser;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getStrategicObjective() {
			return strategicObjective;
		}

		public void setStrategicObjective(String strategicObjective) {
			this.strategicObjective = strategicObjective;
		}

//		public Employee getObjectiveOwner() {
//			return objectiveOwner;
//		}
//
//		public void setObjectiveOwner(Employee objectiveOwner) {
//			this.objectiveOwner = objectiveOwner;
//		}

		public Department getRelevantDepartment() {
			return relevantDepartment;
		}

		public void setRelevantDepartment(Department relevantDepartment) {
			this.relevantDepartment = relevantDepartment;
		}

		public Date getAcheivementDate() {
			return acheivementDate;
		}

		public void setAcheivementDate(Date acheivementDate) {
			this.acheivementDate = acheivementDate;
		}

		public String getRating() {
			return rating;
		}

		public void setRating(String rating) {
			this.rating = rating;
		}

		public RiskFactor getRiskFactor() {
			return riskFactor;
		}

		public void setRiskFactor(RiskFactor riskFactor) {
			this.riskFactor = riskFactor;
		}

		public String getAuditableUnit() {
			return auditableUnit;
		}

		public void setAuditableUnit(String auditableUnit) {
			this.auditableUnit = auditableUnit;
		}

		public int getPhase() {
			return phase;
		}

		public void setPhase(int phase) {
			this.phase = phase;
		}

		public Employee getInitiatedBy() {
			return initiatedBy;
		}

		public void setInitiatedBy(Employee initiatedBy) {
			this.initiatedBy = initiatedBy;
		}

		public Employee getAssignedTo() {
			return assignedTo;
		}

		public void setAssignedTo(Employee assignedTo) {
			this.assignedTo = assignedTo;
		}

		public String getComments() {
			return comments;
		}

		public void setComments(String comments) {
			this.comments = comments;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public int getNextPhase() {
			return nextPhase;
		}

		public void setNextPhase(int nextPhase) {
			this.nextPhase = nextPhase;
		}

		public Employee getApprovedBy() {
			return approvedBy;
		}

		public void setApprovedBy(Employee approvedBy) {
			this.approvedBy = approvedBy;
		}
//
//		public String getDelete() {
//			return delete;
//		}
//
//		public void setDelete(String delete) {
//			this.delete = delete;
//		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		
		public boolean isAudit() {
			return audit;
		}

		public void setAudit(boolean audit) {
			this.audit = audit;
		}

		public int getTab() {
			return tab;
		}

		public void setTab(int tab) {
			this.tab = tab;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public ArrayList<StrategicDepartments> getStrategicDepartments() {
			return strategicDepartments;
		}

		public void setStrategicDepartments(
				ArrayList<StrategicDepartments> strategicDepartments) {
			this.strategicDepartments = strategicDepartments;
		}

		public String getDomain()
		{
			switch (tab)
			{
				case 0: return "Strategic";
				case 1: return "Operations";
				case 2: return "Reporting";
				case 3: return "Compliance";
				default: return "unknown";
			
			}
		}

		public String getDivisionName() {
			return divisionName;
		}

		public void setDivisionName(String divisionName) {
			this.divisionName = divisionName;
		}

		public int getCompanyId() {
			return companyId;
		}

		public void setCompanyId(int companyId) {
			this.companyId = companyId;
		}

		public String getEstimatedWeeks() {
			return estimatedWeeks;
		}

		public void setEstimatedWeeks(String estimatedWeeks) {
			this.estimatedWeeks = estimatedWeeks;
		}

		public ArrayList<String> getEmployees() {
			return employees;
		}

		public void setEmployees(ArrayList<String> employees) {
			this.employees = employees;
		}

		public String getUserDefinedRating() {
			return userDefinedRating;
		}

		public void setUserDefinedRating(String userDefinedRating) {
			this.userDefinedRating = userDefinedRating;
		}

		public void setProcess(Process process) {
			this.process = process;
		}

		public void setSubProcess(SubProcess subProcess) {
			this.subProcess = subProcess;
		}

		public void setJobType(JobType jobType) {
			this.jobType = jobType;
		}

		public Process getProcess() {
			return process;
		}

		public SubProcess getSubProcess() {
			return subProcess;
		}

		public JobType getJobType() {
			return jobType;
		}
	
	
}
