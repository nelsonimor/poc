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

@Entity @Table(name = "t_country")
public class CountryBO extends CoreBO {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Length(min = 3,max = 60)
	@Column(unique = true)
	private String name;

	@Length(min = 2,max = 2)
	@Column(unique = true)
	private String codeiso2;
	
	@Length(min = 3,max = 3)
	@Column(unique = true)
	private String codeiso3;
	
	@Length(min = 0,max = 60)
	private String nationality;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_continent_id",nullable = false)
	private ContinentBO continent;
	
	public CountryBO(int id, String name, String codeiso2,String codeiso3, String nationality,ContinentBO continent) {
		this.id = id;
		this.name = name;
		this.codeiso2 = codeiso2;
		this.codeiso3 = codeiso3;
		this.nationality = nationality;
		this.continent = continent;
	}


	public CountryBO() {
		
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



	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public ContinentBO getContinent() {
		return continent;
	}

	public void setContinent(ContinentBO continent) {
		this.continent = continent;
	}

	public String getCodeiso2() {
		return codeiso2;
	}

	public void setCodeiso2(String codeiso2) {
		this.codeiso2 = codeiso2;
	}

	public String getCodeiso3() {
		return codeiso3;
	}

	public void setCodeiso3(String codeiso3) {
		this.codeiso3 = codeiso3;
	}




}
