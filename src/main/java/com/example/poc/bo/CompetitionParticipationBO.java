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

@Entity @Table(name = "t_competition_participation")
public class CompetitionParticipationBO {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_competition_organization_id",nullable = false)
	private CompetitionOrganizationBO competitionOrganization;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_roster_id",nullable = false)
	private RosterBO roster;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CompetitionOrganizationBO getCompetitionOrganization() {
		return competitionOrganization;
	}

	public void setCompetitionOrganization(CompetitionOrganizationBO competitionOrganization) {
		this.competitionOrganization = competitionOrganization;
	}

	public RosterBO getRoster() {
		return roster;
	}

	public void setRoster(RosterBO roster) {
		this.roster = roster;
	}

}
