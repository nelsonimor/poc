package com.example.poc.service;

import java.util.List;

import com.example.poc.exception.AlreadyExistsException;
import com.example.poc.exception.NotFoundException;
import com.exemple.poc.client.dto.response.CityDTO;
import com.exemple.poc.client.dto.response.TeamDTO;

public interface ITeamService {
	
	List<TeamDTO> findAllTeams();
	
	TeamDTO addTeam(TeamDTO team) throws AlreadyExistsException,NotFoundException;


}
