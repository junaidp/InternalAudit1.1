package com.internalaudit.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


	@Entity

	@Table(name="rolls")
	public class Rolls   implements Serializable {

		@Id 
		@Column(name="rollId")
		private int rollId;
		
		@Column(name="rollName")
		private String rollName;

		public int getRollId() {
			return rollId;
		}

		public void setRollId(int rollId) {
			this.rollId = rollId;
		}

		public String getRollName() {
			return rollName;
		}

		public void setRollName(String rollName) {
			this.rollName = rollName;
		}
}
