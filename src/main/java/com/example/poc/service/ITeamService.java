package com.example.poc.service;

import java.util.List;

import com.example.poc.exception.AlreadyExistsException;
import com.example.poc.exception.NotFoundException;
import com.exemple.poc.client.dto.response.CityDto;
import com.exemple.poc.client.dto.response.TeamDto;

public interface ITeamService {
	
	List<TeamDto> findAllTeams();
	
	TeamDto addTeam(TeamDto team) throws AlreadyExistsException,NotFoundException;


}
