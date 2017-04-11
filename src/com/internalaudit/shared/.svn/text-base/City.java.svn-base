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

@Table(name="city")
public class City   implements Serializable {

	@Id 
	@Column(name="cityId")
	private int cityId;
	
	@Column(name ="city")
	private String city;
	
	@JoinColumn(name = "countryId", nullable = true)
	@ManyToOne(fetch = FetchType.LAZY)
	private Country countryId;
	
	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public Country getCountryId() {
		return countryId;
	}

	public void setCountryId(Country country) {
		countryId = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
