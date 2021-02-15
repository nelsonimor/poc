package com.example.poc.bo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity @Table(name = "t_competition")
public class CompetitionBO {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Length(min = 2,max = 60)
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_continent_id",nullable = true)
	private ContinentBO continent;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_country_id",nullable = true)
	private CountryBO country;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ContinentBO getContinent() {
		return continent;
	}

	public void setContinent(ContinentBO continent) {
		this.continent = continent;
	}

	public CountryBO getCountry() {
		return country;
	}

	public void setCountry(CountryBO country) {
		this.country = country;
	}
	
	

}
