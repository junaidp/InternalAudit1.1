package com.internalaudit.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="skills")
public class Skills implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@Column(name="skillId")
	private int skillId;
	
	@Column(name="skillName")
	private String skillName;
	
	@Column(name="availablehours")
	private int availableHours;


	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public int getAvailableHours() {
		return availableHours;
	}

	public void setAvailableHours(int availableHours) {
		this.availableHours = availableHours;
	}
}
