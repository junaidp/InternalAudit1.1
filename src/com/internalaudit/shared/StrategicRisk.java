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

	@Table(name="strategicRisk")
	public class StrategicRisk   implements Serializable {

		private static final long serialVersionUID = 1L;

		@Id 
		@Column(name="id")
		private int id;
		
		@Column(name="rating")
		private String rating;
		
		@Column(name="comments")
		private String comments;
		
		@JoinColumn(name = "strategicId", nullable = true)
		@ManyToOne(fetch = FetchType.EAGER)
		private Strategic strategicId;
		
		@JoinColumn(name = "riskFactorId", nullable = true)
		@ManyToOne(fetch = FetchType.EAGER)
		private RiskFactor riskFactorId;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public Strategic getStrategicId() {
			return strategicId;
		}

		public void setStrategicId(Strategic strategicId) {
			this.strategicId = strategicId;
		}

		public RiskFactor getRiskFactorId() {
			return riskFactorId;
		}

		public void setRiskFactorId(RiskFactor riskFactorId) {
			this.riskFactorId = riskFactorId;
		}

		public String getRating() {
			return rating;
		}

		public void setRating(String rating) {
			this.rating = rating;
		}

		public String getComments() {
			return comments;
		}

		public void setComments(String comments) {
			this.comments = comments;
		}
		
		
}
