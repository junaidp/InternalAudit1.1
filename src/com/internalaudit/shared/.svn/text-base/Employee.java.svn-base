package com.internalaudit.shared;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

	@Entity

	@Table(name="employee")
	public class Employee   implements Serializable {

		private static final long serialVersionUID = 1L;

		@Id 
		@Column(name="employeeId")
		private int employeeId;
		
		@Column(name="employeeName")
		private String employeeName;
		
		@JoinColumn(name = "reportingTo", nullable = true)
		@ManyToOne(fetch = FetchType.EAGER)
		private Employee reportingTo;
		
		@Column(name="email")
		private String email;
		
		@Column(name="areaOfSpeciality") //
		private int areaOfSpeciality;
		
		@JoinColumn(name = "countryId")
		@ManyToOne(fetch = FetchType.LAZY)
		private Country countryId;
		
		@JoinColumn(name = "cityId", nullable = true)
		@ManyToOne(fetch = FetchType.EAGER)
		private City cityId;
		
		@JoinColumn(name = "departmentId", nullable = true)
		@ManyToOne(fetch = FetchType.EAGER)
		private Department departmentId;
		
		@JoinColumn(name = "userId", nullable = true)
		@ManyToOne(fetch = FetchType.EAGER)
		private User userId;
		
		@Column(name ="fromInternalAuditDept")
		private String fromInternalAuditDept;

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

		public Department getDepartmentId() {
			return departmentId;
		}

		public void setDepartmentId(Department departmentId) {
			this.departmentId = departmentId;
		}

		public User getUserId() {
			return userId;
		}

		public void setUserId(User userId) {
			this.userId = userId;
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
		
		
		

}
