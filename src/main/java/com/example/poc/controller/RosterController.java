package com.example.poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.poc.bo.RosterBO;
import com.example.poc.dao.IRosterDAO;
import com.example.poc.mapper.ObjectMapper;
import com.example.poc.service.IRosterService;
import com.exemple.poc.client.dto.response.RosterDto;
import com.exemple.poc.client.dto.response.RosterItemDto;

import io.swagger.annotations.Api;

@Api(description = "Person API for CRUD")
@RestController
public class RosterController {

	@Autowired
	private IRosterService rosterService;

	@GetMapping({"/view/rosters/{id}"})
	public RosterDto findRosterById(@PathVariable int id) {
		return rosterService.findById(id);
	}	
	
	@GetMapping({"/view/rosters/team/{teamId}"})
	public List<RosterDto> findRosterByTeamId(@PathVariable int teamId) {
		return rosterService.findByTeamId(teamId);
	}
	
	@GetMapping({"/view/career/person/{playerId}"})
	public List<RosterItemDto> findCareer(@PathVariable int playerId) {
		return rosterService.findByPlayerId(playerId);
	}
	
	


}
