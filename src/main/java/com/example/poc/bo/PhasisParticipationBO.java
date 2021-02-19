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

@Entity @Table(name = "t_phasis_participation")
public class PhasisParticipationBO {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_phasis_organization_id",nullable = false)
	private PhasisOrganizationBO phasisOrganization;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_roster_id",nullable = false)
	private RosterBO roster;

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

	public PhasisOrganizationBO getPhasisOrganization() {
		return phasisOrganization;
	}

	public void setPhasisOrganization(PhasisOrganizationBO phasisOrganization) {
		this.phasisOrganization = phasisOrganization;
	}

}
