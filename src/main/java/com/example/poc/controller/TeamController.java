package com.example.poc.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.poc.service.ITeamService;
import com.exemple.poc.client.dto.response.TeamDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Team API for CRUD")
@RestController
public class TeamController {

	@Autowired
	private ITeamService teamService;

	@ApiOperation("Retrieve teams")
	@RequestMapping(value = {"/view/teams"},method = {RequestMethod.GET})
	public List<TeamDto> findAllTeams() {
		return teamService.findAllTeams();
	}

	@PostMapping({"/admin/teams"})
	public ResponseEntity<Void> addTeam(@RequestBody @Valid TeamDto team) throws Exception {
		TeamDto newTeamDTO = teamService.addTeam(team);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(new Object[]{newTeamDTO.getId()})
				.toUri();
		return ResponseEntity.created(location).build();
	}


}
