package com.internalaudit.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity

	@Table(name="riskfactor")
	public class RiskFactor   implements Serializable {

		
		@Id 
		@Column(name="riskId")
		private int riskId;
		
		@Column(name="riskName")
		private String riskName;

		public int getRiskId() {
			return riskId;
		}

		public void setRiskId(int riskId) {
			this.riskId = riskId;
		}

		public String getRiskName() {
			return riskName;
		}

		public void setRiskName(String riskName) {
			this.riskName = riskName;
		}
		
		
}
