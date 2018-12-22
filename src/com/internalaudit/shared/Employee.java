package com.internalaudit.shared;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Transient;

	@Entity

	@Table(name="employee")
	public class Employee   implements Serializable {

		private static final long serialVersionUID = 1L;

		@Id 
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="employeeId")
		private int employeeId;
		
		@Column(name="employeeName")
		private String employeeName;
		
		@JoinColumn(name = "reportingTo", nullable = true)
		@ManyToOne(fetch = FetchType.LAZY)
		private Employee reportingTo;
		
		@Column(name="email")
		private String email;
		
		
		@JoinColumn(name = "countryId")
		@ManyToOne(fetch = FetchType.LAZY)
		private Country countryId;
		
		@JoinColumn(name = "cityId", nullable = true)
		@ManyToOne(fetch = FetchType.LAZY)
		private City cityId;
		
		
		@JoinColumn(name = "skillId", nullable = true)
		@ManyToOne(fetch = FetchType.LAZY)
		private Skills skillId;
		
		@Column(name ="companyId")
		private int companyId;
		
		@Column(name="rollId")
		private int rollId;
		
		@Column(name ="fromInternalAuditDept")
		private String fromInternalAuditDept;
		
		@Column(name="auditHead")
		private boolean auditHead;
		
		@Column(name = "dateOfJoining")
		private Date dateOfJoining;
		
		@Column(name = "designation")
		private String designation;
		
		@Column(name = "password")
		private String password;

		
		
		@Transient
		private ArrayList<ToDo> todos;
		
		@Transient
		private ArrayList<ToDo> userRaisedToDos;
		
		@Transient
		private ArrayList<InformationRequestEntity> userRaisedInformationRequests;
		
		@Transient
		private ArrayList<InformationRequestEntity> informationRequests ;
		
		@Transient
		private int TotalNumberOfHoursAvailable;

		public boolean isAuditHead() {
			return auditHead;
		}

		public void setAuditHead(boolean auditHead) {
			this.auditHead = auditHead;
		}

		public int getEmployeeId() {
			return employeeId;
		}

		public void setEmployeeId(int employeeId) {
			this.employeeId = employeeId;
		}

		public String getEmployeeName() {
			return employeeName;
		}

		public void setEmployeeName(String employeeName) {
			this.employeeName = employeeName;
		}

	
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Country getCountryId() {
			return countryId;
		}

		public void setCountryId(Country countryId) {
			this.countryId = countryId;
		}

		public City getCityId() {
			return cityId;
		}

		public void setCityId(City cityId) {
			this.cityId = cityId;
		}

		public Employee getReportingTo() {
			return reportingTo;
		}

		public void setReportingTo(Employee reportingTo) {
			this.reportingTo = reportingTo;
		}

		public String getFromInternalAuditDept() {
			return fromInternalAuditDept;
		}

		public void setFromInternalAuditDept(String fromInternalAuditDept) {
			this.fromInternalAuditDept = fromInternalAuditDept;
		}

		public void setTotalNumberOfHoursAvailable(int numberOfHours) {
			TotalNumberOfHoursAvailable = numberOfHours;
		}

		public int getTotalNumberOfHoursAvailable() {
			return TotalNumberOfHoursAvailable;
		}

		public Skills getSkillId() {
			return skillId;
		}

		public void setSkillId(Skills skillId) {
			this.skillId = skillId;
		}

		public Date getDateOfJoining() {
			return dateOfJoining;
		}

		public void setDateOfJoining(Date dateOfJoining) {
			this.dateOfJoining = dateOfJoining;
		}

		public String getDesignation() {
			return designation;
		}

		public void setDesignation(String designation) {
			this.designation = designation;
		}

		public int getCompanyId() {
			return companyId;
		}

		public void setCompanyId(int companyId) {
			this.companyId = companyId;
		}

		public int getRollId() {
			return rollId;
		}

		public void setRollId(int rollId) {
			this.rollId = rollId;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public ArrayList<ToDo> getTodos() {
			return todos;
		}

		public void setTodos(ArrayList<ToDo> todos) {
			this.todos = todos;
		}

		public ArrayList<ToDo> getUserRaisedToDos() {
			return userRaisedToDos;
		}

		public void setUserRaisedToDos(ArrayList<ToDo> userRaisedToDos) {
			this.userRaisedToDos = userRaisedToDos;
		}

		public ArrayList<InformationRequestEntity> getUserRaisedInformationRequests() {
			return userRaisedInformationRequests;
		}

		public void setUserRaisedInformationRequests(ArrayList<InformationRequestEntity> userRaisedInformationRequests) {
			this.userRaisedInformationRequests = userRaisedInformationRequests;
		}

		public ArrayList<InformationRequestEntity> getInformationRequests() {
			return informationRequests;
		}

		public void setInformationRequests(ArrayList<InformationRequestEntity> informationRequests) {
			this.informationRequests = informationRequests;
		}
}
