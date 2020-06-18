package com.internalaudit.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity

	@Table(name="division")
	public class Division implements Serializable {

		private static final long serialVersionUID = 1L;

		@Id 
		@Column(name="divisionID")
		private int divisionID;
		
		@Column(name="divisionName")
		private String divisionName;

		public int getDivisionID() {
			return divisionID;
		}

		public void setDivisionID(int divisionID) {
			this.divisionID = divisionID;
		}

		public String getDivisionName() {
			return divisionName;
		}

		public void setDivisionName(String divisionName) {
			this.divisionName = divisionName;
		}
}
