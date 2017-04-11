package com.internalaudit.shared;

import java.io.Serializable;
import java.util.ArrayList;

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

	@Table(name="strategicdepartments")
	public class StrategicDepartments   implements Serializable {

		@Id 
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="strategicDepartmentId")
		private int strategicDepartmentId;
		
		@Column(name ="strategic")
		private int strategic;
		
		@JoinColumn(name = "department")
		@ManyToOne(fetch = FetchType.LAZY)
		private Department department;/// You will be fetching strategicDepartment , then strategicDepatment.getdepartment.getdeptName();  should give u finance etc
		
		@Transient
		private ArrayList<Integer> resourcesIds;

		public ArrayList<Integer> getResourcesIds() {
			return resourcesIds;
		}

		public void setResourcesIds(ArrayList<Integer> resourcesIds) {
			this.resourcesIds = resourcesIds;
		}

		public int getStrategicDepartmentId() {
			return strategicDepartmentId;
		}

		public void setStrategicDepartmentId(int strategicDepartmentId) {
			this.strategicDepartmentId = strategicDepartmentId;
		}

		
		public Department getDepartment() {
			return department;
		}

		public void setDepartment(Department department) {
			this.department = department;
		}

		public int getStrategic() {
			return strategic;
		}

		public void setStrategic(int strategic) {
			this.strategic = strategic;
		}

}
