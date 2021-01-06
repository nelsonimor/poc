package com.example.poc.util;

public final class ActionCode {
	
	//continent
	public static final String CONTINENT_ADD_FAILED_ALREADY_EXIST = "CONTINENT-001";
	public static final String CONTINENT_ADD_SUCCESS = "CONTINENT-002";
	
	//country
	public static final String COUNTRY_ADD_FAILED_ALREADY_EXIST = "COUNTRY-001";
	public static final String COUNTRY_ADD_FAILED_UNKNOWN_CONTINENT = "COUNTRY-002";
	public static final String COUNTRY_ADD_SUCCESS = "COUNTRY-003";
	
	//city
	public static final String CITY_ADD_FAILED_UNKNOWN_COUNTRY = "CITY-001";
	public static final String CITY_ADD_FAILED_ALREADY_EXIST= "CITY-002";
	public static final String CITY_ADD_SUCCESS= "CITY-003";
	
	//geoloc
	public static final String GEOLOC_SUCCESS = "GEOLOC-001";
	
	//team
	public static final String TEAM_ADD_FAILED_ALREADY_EXIST = "TEAM-001";
	public static final String TEAM_ADD_FAILED_COUNTRY_OF_CITY1_UNKOWN = "TEAM-002";
	public static final String TEAM_ADD_FAILED_COUNTRY_OF_CITY2_UNKOWN = "TEAM-003";
	public static final String TEAM_ADD_FAILED_COUNTRY_OF_CITY3_UNKOWN = "TEAM-004";
	public static final String TEAM_ADD_FAILED_CITY1_UNKOWN = "TEAM-005";
	public static final String TEAM_ADD_FAILED_CITY2_UNKOWN = "TEAM-006";
	public static final String TEAM_ADD_FAILED_CITY3_UNKOWN = "TEAM-007";
	public static final String TEAM_ADD_FAILED_COUNTRY_UNKOWN = "TEAM-008";
	public static final String TEAM_ADD_SUCCESS= "TEAM-009";
}
