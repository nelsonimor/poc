package com.example.poc.bo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity @Table(name = "t_team")
public class TeamBO extends CoreBO {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Length(min = 3,max = 80)
	@Column(unique = true)
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_team_city1_id",nullable = true)
	private CityBO city1;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_team_city2_id",nullable = true)
	private CityBO city2;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_team_city3_id",nullable = true)
	private CityBO city3;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_team_country_id",nullable = true)
	private CountryBO country;
	
	@Length(min = 3,max = 80)
	private String type;
	
	
	public TeamBO() {
		
	}
	
	public int getId() {
		return this.id;
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

	public CityBO getCity1() {
		return city1;
	}

	public void setCity1(CityBO city1) {
		this.city1 = city1;
	}

	public CityBO getCity2() {
		return city2;
	}

	public void setCity2(CityBO city2) {
		this.city2 = city2;
	}

	public CityBO getCity3() {
		return city3;
	}

	public void setCity3(CityBO city3) {
		this.city3 = city3;
	}

	public CountryBO getCountry() {
		return country;
	}

	public void setCountry(CountryBO country) {
		this.country = country;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}




}
