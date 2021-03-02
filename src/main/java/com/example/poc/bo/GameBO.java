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

@Entity @Table(name = "t_game")
public class GameBO {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_local_roster_id",nullable = false)
	private RosterBO localRoster;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_visitor_roster_id",nullable = false)
	private RosterBO visitorRoster;
	
	private Integer localscore;
	
	private Integer visitscore;
	
	private Timestamp gamedate;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_phasis_organization_id",nullable = false)
	private PhasisOrganizationBO phasisOrganization;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_arena_id",nullable = false)
	private GameBO game;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RosterBO getLocalRoster() {
		return localRoster;
	}

	public void setLocalRoster(RosterBO localRoster) {
		this.localRoster = localRoster;
	}

	public RosterBO getVisitorRoster() {
		return visitorRoster;
	}

	public void setVisitorRoster(RosterBO visitorRoster) {
		this.visitorRoster = visitorRoster;
	}



	public Timestamp getGamedate() {
		return gamedate;
	}

	public void setGamedate(Timestamp gamedate) {
		this.gamedate = gamedate;
	}

	public Integer getLocalscore() {
		return localscore;
	}

	public void setLocalscore(Integer localscore) {
		this.localscore = localscore;
	}

	public Integer getVisitscore() {
		return visitscore;
	}

	public void setVisitscore(Integer visitscore) {
		this.visitscore = visitscore;
	}

	public PhasisOrganizationBO getPhasisOrganization() {
		return phasisOrganization;
	}

	public void setPhasisOrganization(PhasisOrganizationBO phasisOrganization) {
		this.phasisOrganization = phasisOrganization;
	}

	public GameBO getGame() {
		return game;
	}

	public void setGame(GameBO game) {
		this.game = game;
	}



}
