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

@Entity @Table(name = "t_boxline")
public class BoxlineBO {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_game_id",nullable = false)
	private GameBO game;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_person_id",nullable = false)
	private PersonBO person;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_roster_id",nullable = false)
	private RosterBO roster;
	
	private Integer points;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GameBO getGame() {
		return game;
	}

	public void setGame(GameBO game) {
		this.game = game;
	}

	public PersonBO getPerson() {
		return person;
	}

	public void setPerson(PersonBO person) {
		this.person = person;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public RosterBO getRoster() {
		return roster;
	}

	public void setRoster(RosterBO roster) {
		this.roster = roster;
	}
	
	

}
