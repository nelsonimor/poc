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
	
	private Integer totalShoot;
	private Integer totalShootAttempts;
	private Integer threePointShoot;
	private Integer threePointShootAttempts;
	private Integer freeThrow;
	private Integer freeThrowAttempts;
	private Integer rebound;
	private Integer reboundOffensive;
	private Integer reboundDefensive;
	private Integer assist;
	private Integer steal;
	private Integer turnover;
	private Integer block;
	private Integer ranking;
	private Integer foulsPersonal;
	private Integer foulsProvoked;
	private Integer minutes;
	private boolean starter;

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

	public Integer getTotalShoot() {
		return totalShoot;
	}

	public void setTotalShoot(Integer totalShoot) {
		this.totalShoot = totalShoot;
	}

	public Integer getTotalShootAttempts() {
		return totalShootAttempts;
	}

	public void setTotalShootAttempts(Integer totalShootAttempts) {
		this.totalShootAttempts = totalShootAttempts;
	}



	public Integer getFreeThrow() {
		return freeThrow;
	}

	public void setFreeThrow(Integer freeThrow) {
		this.freeThrow = freeThrow;
	}

	public Integer getFreeThrowAttempts() {
		return freeThrowAttempts;
	}

	public void setFreeThrowAttempts(Integer freeThrowAttempts) {
		this.freeThrowAttempts = freeThrowAttempts;
	}

	public Integer getRebound() {
		return rebound;
	}

	public void setRebound(Integer rebound) {
		this.rebound = rebound;
	}

	public Integer getReboundOffensive() {
		return reboundOffensive;
	}

	public void setReboundOffensive(Integer reboundOffensive) {
		this.reboundOffensive = reboundOffensive;
	}

	public Integer getReboundDefensive() {
		return reboundDefensive;
	}

	public void setReboundDefensive(Integer reboundDefensive) {
		this.reboundDefensive = reboundDefensive;
	}

	public Integer getAssist() {
		return assist;
	}

	public void setAssist(Integer assist) {
		this.assist = assist;
	}

	public Integer getSteal() {
		return steal;
	}

	public void setSteal(Integer steal) {
		this.steal = steal;
	}

	public Integer getTurnover() {
		return turnover;
	}

	public void setTurnover(Integer turnover) {
		this.turnover = turnover;
	}

	public Integer getBlock() {
		return block;
	}

	public void setBlock(Integer block) {
		this.block = block;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public Integer getFoulsPersonal() {
		return foulsPersonal;
	}

	public void setFoulsPersonal(Integer foulsPersonal) {
		this.foulsPersonal = foulsPersonal;
	}

	public Integer getFoulsProvoked() {
		return foulsProvoked;
	}

	public void setFoulsProvoked(Integer foulsProvoked) {
		this.foulsProvoked = foulsProvoked;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public boolean isStarter() {
		return starter;
	}

	public void setStarter(boolean starter) {
		this.starter = starter;
	}

	public Integer getThreePointShoot() {
		return threePointShoot;
	}

	public void setThreePointShoot(Integer threePointShoot) {
		this.threePointShoot = threePointShoot;
	}

	public Integer getThreePointShootAttempts() {
		return threePointShootAttempts;
	}

	public void setThreePointShootAttempts(Integer threePointShootAttempts) {
		this.threePointShootAttempts = threePointShootAttempts;
	}
	
	

}
