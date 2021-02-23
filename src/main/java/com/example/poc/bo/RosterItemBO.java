package com.example.poc.bo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

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

@Entity @Table(name = "t_roster_item")
public class RosterItemBO {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_roster_id",nullable = false)
	private RosterBO roster;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_person_id",nullable = false)
	private PersonBO person;
	
	private Timestamp startdate;
	
	private Timestamp enddate;
	
	@OneToMany(mappedBy="roster")
	private Set<RosterItemBO> rosterItems;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RosterBO getRoster() {
		return roster;
	}

	public void setRoster(RosterBO roster) {
		this.roster = roster;
	}

	public PersonBO getPerson() {
		return person;
	}

	public void setPerson(PersonBO person) {
		this.person = person;
	}

	public Timestamp getStartdate() {
		return startdate;
	}

	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}

	public Timestamp getEnddate() {
		return enddate;
	}

	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
	}

	public Set<RosterItemBO> getRosterItems() {
		return rosterItems;
	}

	public void setRosterItems(Set<RosterItemBO> rosterItems) {
		this.rosterItems = rosterItems;
	}
	

}
