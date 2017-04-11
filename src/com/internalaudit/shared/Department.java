package com.internalaudit.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity

	@Table(name="department")
	public class Department   implements Serializable {

		private static final long serialVersionUID = 1L;

		@Id 
		@Column(name="departmentId")
		private int departmentId;
		
		@Column(name="departmentName")
		private String departmentName;
		
//		@Column(name="availableHours")
//		private String availableHours;

		public int getDepartmentId() {
			return departmentId;
		}

		public void setDepartmentId(int departmentId) {
			this.departmentId = departmentId;
		}

		public String getDepartmentName() {
			return departmentName;
		}

		public void setDepartmentName(String departmentName) {
			this.departmentName = departmentName;
		}

//		public String getAvailableHours() {
//			return availableHours;
//		}
//
//		public void setAvailableHours(String availableHours) {
//			this.availableHours = availableHours;
//		}

}
