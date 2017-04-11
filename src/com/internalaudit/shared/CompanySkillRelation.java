package com.internalaudit.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

	
	@Entity

	@Table(name="companyskillrelation")
	public class CompanySkillRelation   implements Serializable {

		private static final long serialVersionUID = 1L;

		@Id 
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="companySkillId")
		private int companySkillId;
		
		@Column(name="companyId")
		private int companyId;
		
		@JoinColumn(name = "skillId", nullable = true)
		@ManyToOne(fetch = FetchType.LAZY)
		private Skills skillId;
		
		@Column(name="year")
		private int year;
		
		@Column(name="availablehours")
		private int availableHours;

		public int getCompanySkillId() {
			return companySkillId;
		}

		public void setCompanySkillId(int companySkillId) {
			this.companySkillId = companySkillId;
		}

		public int getCompanyId() {
			return companyId;
		}

		public void setCompanyId(int companyId) {
			this.companyId = companyId;
		}

		public Skills getSkillId() {
			return skillId;
		}

		public void setSkillId(Skills skillId) {
			this.skillId = skillId;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public int getAvailableHours() {
			return availableHours;
		}

		public void setAvailableHours(int availableHours) {
			this.availableHours = availableHours;
		}

}
