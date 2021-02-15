package com.example.poc.bo;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity @Table(name = "t_roster")
public class RosterBO {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_team_id",nullable = false)
	private TeamBO team;
	
	private Timestamp startdate;
	
	private Timestamp enddate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TeamBO getTeam() {
		return team;
	}

	public void setTeam(TeamBO team) {
		this.team = team;
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
	
	
	

}
