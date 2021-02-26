package com.example.poc.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity @Table(name = "t_person")
public class PersonBO extends CoreBO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Length(min = 3,max = 50)
	private String lastname;

	@Length(min = 3,max = 50)
	private String firstname;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_nationality1_id",nullable = false)
	private CountryBO nationality1;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_nationality2_id",nullable = true)
	private CountryBO nationality2;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_city_id",nullable = false)
	private CityBO birthplace;
	
	private Timestamp birthdate;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
	private List<RosterItemBO> rosterItems= new ArrayList<RosterItemBO>();

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public CountryBO getNationality1() {
		return nationality1;
	}

	public void setNationality1(CountryBO nationality1) {
		this.nationality1 = nationality1;
	}

	public CountryBO getNationality2() {
		return nationality2;
	}

	public void setNationality2(CountryBO nationality2) {
		this.nationality2 = nationality2;
	}

	public CityBO getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(CityBO birthplace) {
		this.birthplace = birthplace;
	}

	public Timestamp getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Timestamp birthdate) {
		this.birthdate = birthdate;
	}

	public List<RosterItemBO> getRosterItems() {
		return rosterItems;
	}

	public void setRosterItems(List<RosterItemBO> rosterItems) {
		this.rosterItems = rosterItems;
	}





}
