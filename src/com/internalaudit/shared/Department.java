package com.internalaudit.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity

	@Table(name="department")
	public class Department   implements Serializable {

		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name="departmentId")
		private int departmentId;
		
		@Column(name="departmentName")
		private String departmentName;
		
		@Column(name="divisionID")
		private int divisionID;

		public int getDivisionID() {
			return divisionID;
		}

		public void setDivisionID(int divisionID) {
			this.divisionID = divisionID;
		}

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

}
