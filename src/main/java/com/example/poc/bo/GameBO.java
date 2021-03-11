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
	
	private Integer localQt1score;
	private Integer localQt2score;
	private Integer localQt3score;
	private Integer localQt4score;
	private Integer localOt1score;
	private Integer localOt2score;
	private Integer localOt3score;
	private Integer localOt4score;
	private Integer localOt5score;
	
	private Integer visitscore;
	
	private Integer visitQt1score;
	private Integer visitQt2score;
	private Integer visitQt3score;
	private Integer visitQt4score;
	private Integer visitOt1score;
	private Integer visitOt2score;
	private Integer visitOt3score;
	private Integer visitOt4score;
	private Integer visitOt5score;
	
	private Timestamp gamedate;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_phasis_organization_id",nullable = false)
	private PhasisOrganizationBO phasisOrganization;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_arena_id",nullable = false)
	private ArenaBO arena;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "game")
	private List<BoxlineBO> boxLines = new ArrayList<BoxlineBO>();
	


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

	public ArenaBO getArena() {
		return arena;
	}

	public void setArena(ArenaBO arena) {
		this.arena = arena;
	}

	public List<BoxlineBO> getBoxLines() {
		return boxLines;
	}

	public void setBoxLines(List<BoxlineBO> boxLines) {
		this.boxLines = boxLines;
	}

	public Integer getLocalQt1score() {
		return localQt1score;
	}

	public void setLocalQt1score(Integer localQt1score) {
		this.localQt1score = localQt1score;
	}

	public Integer getLocalQt2score() {
		return localQt2score;
	}

	public void setLocalQt2score(Integer localQt2score) {
		this.localQt2score = localQt2score;
	}

	public Integer getLocalQt3score() {
		return localQt3score;
	}

	public void setLocalQt3score(Integer localQt3score) {
		this.localQt3score = localQt3score;
	}

	public Integer getLocalQt4score() {
		return localQt4score;
	}

	public void setLocalQt4score(Integer localQt4score) {
		this.localQt4score = localQt4score;
	}

	public Integer getLocalOt1score() {
		return localOt1score;
	}

	public void setLocalOt1score(Integer localOt1score) {
		this.localOt1score = localOt1score;
	}

	public Integer getLocalOt2score() {
		return localOt2score;
	}

	public void setLocalOt2score(Integer localOt2score) {
		this.localOt2score = localOt2score;
	}

	public Integer getLocalOt3score() {
		return localOt3score;
	}

	public void setLocalOt3score(Integer localOt3score) {
		this.localOt3score = localOt3score;
	}

	public Integer getLocalOt4score() {
		return localOt4score;
	}

	public void setLocalOt4score(Integer localOt4score) {
		this.localOt4score = localOt4score;
	}

	public Integer getLocalOt5score() {
		return localOt5score;
	}

	public void setLocalOt5score(Integer localOt5score) {
		this.localOt5score = localOt5score;
	}

	public Integer getVisitQt1score() {
		return visitQt1score;
	}

	public void setVisitQt1score(Integer visitQt1score) {
		this.visitQt1score = visitQt1score;
	}

	public Integer getVisitQt2score() {
		return visitQt2score;
	}

	public void setVisitQt2score(Integer visitQt2score) {
		this.visitQt2score = visitQt2score;
	}

	public Integer getVisitQt3score() {
		return visitQt3score;
	}

	public void setVisitQt3score(Integer visitQt3score) {
		this.visitQt3score = visitQt3score;
	}

	public Integer getVisitQt4score() {
		return visitQt4score;
	}

	public void setVisitQt4score(Integer visitQt4score) {
		this.visitQt4score = visitQt4score;
	}

	public Integer getVisitOt1score() {
		return visitOt1score;
	}

	public void setVisitOt1score(Integer visitOt1score) {
		this.visitOt1score = visitOt1score;
	}

	public Integer getVisitOt2score() {
		return visitOt2score;
	}

	public void setVisitOt2score(Integer visitOt2score) {
		this.visitOt2score = visitOt2score;
	}

	public Integer getVisitOt3score() {
		return visitOt3score;
	}

	public void setVisitOt3score(Integer visitOt3score) {
		this.visitOt3score = visitOt3score;
	}

	public Integer getVisitOt4score() {
		return visitOt4score;
	}

	public void setVisitOt4score(Integer visitOt4score) {
		this.visitOt4score = visitOt4score;
	}

	public Integer getVisitOt5score() {
		return visitOt5score;
	}

	public void setVisitOt5score(Integer visitOt5score) {
		this.visitOt5score = visitOt5score;
	}





}
