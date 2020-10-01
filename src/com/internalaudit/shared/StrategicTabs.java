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

	@Table(name="strategictabs")
	public class StrategicTabs   implements Serializable {

		@Id 
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="tabId")
		private int tabId;
		
		@Column(name ="strategicId")
		private int strategicId;
		
		@JoinColumn(name = "strategicTabId")
		private int strategicTabId;/// You will be fetching strategicDepartment , then strategicDepatment.getdepartment.getdeptName();  should give u finance etc
		
		public int getTabId() {
			return tabId;
		}

		public void setTabId(int tabId) {
			this.tabId = tabId;
		}

		public int getStrategicId() {
			return strategicId;
		}

		public void setStrategicId(int strategicId) {
			this.strategicId = strategicId;
		}

		public int getStrategicTabId() {
			return strategicTabId;
		}

		public void setStrategicTabId(int strategicTabId) {
			this.strategicTabId = strategicTabId;
		}

		@Transient
		private ArrayList<Integer> resourcesIds;

		public ArrayList<Integer> getResourcesIds() {
			return resourcesIds;
		}

		public void setResourcesIds(ArrayList<Integer> resourcesIds) {
			this.resourcesIds = resourcesIds;
		}

	}
