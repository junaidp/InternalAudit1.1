package com.internalaudit.shared;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

	@Entity

	@Table(name="strategicAudit")
	public class StrategicAudit   implements Serializable {

		private static final long serialVersionUID = 1L;

		@Id 
		@Column(name="auditId")
		private int auditId;
		
		@Column(name="strategicObjective")
		private String strategicObjective;
		
		@JoinColumn(name = "objectiveOwner", nullable = true)
		@ManyToOne(fetch = FetchType.LAZY)
		private Employee objectiveOwner;
		
		@JoinColumn(name = "relevantDepartment", nullable = true)
		@ManyToOne(fetch = FetchType.LAZY)
		private Department relevantDepartment;
		
		@Column(name="acheivementDate")
		private Date acheivementDate;
		
		@Column(name="rating")
		private String rating;
		
		@JoinColumn(name = "riskFactor", nullable = true)
		@ManyToOne(fetch = FetchType.LAZY)
		private RiskFactor riskFactor;
		
		@Column(name="auditableUnit")
		private String auditableUnit;
		
		@Column(name="phase")
		private String phase;
		
		@Column(name="status")
		private String status;
		
		@Column(name="comments")
		private String comments;
		
		@Column(name="date")
		private Date date;
		
		@JoinColumn(name = "initiatedBy", nullable = true)
		@ManyToOne(fetch = FetchType.LAZY)
		private Employee initiatedBy;
		
		@JoinColumn(name = "assignedTo", nullable = true)
		@ManyToOne(fetch = FetchType.LAZY)
		private Employee assignedTo;
		
		@JoinColumn(name = "approvedBy", nullable = true)
		@ManyToOne(fetch = FetchType.LAZY)
		private Employee approvedBy;
		
		@Transient
		private String nextPhase;

		

		public String getStrategicObjective() {
			return strategicObjective;
		}

		public void setStrategicObjective(String strategicObjective) {
			this.strategicObjective = strategicObjective;
		}

		public Employee getObjectiveOwner() {
			return objectiveOwner;
		}

		public void setObjectiveOwner(Employee objectiveOwner) {
			this.objectiveOwner = objectiveOwner;
		}

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

		public String getPhase() {
			return phase;
		}

		public void setPhase(String phase) {
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

		public String getNextPhase() {
			return nextPhase;
		}

		public void setNextPhase(String nextPhase) {
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

		public int getAuditId() {
			return auditId;
		}

		public void setAuditId(int auditId) {
			this.auditId = auditId;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

	
}
