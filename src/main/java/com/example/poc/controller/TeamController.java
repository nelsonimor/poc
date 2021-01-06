package com.example.poc.controller;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.poc.bo.PersonBO;
import com.example.poc.service.IPersonService;
import com.example.poc.service.ITeamService;
import com.exemple.poc.client.dto.response.CityDTO;
import com.exemple.poc.client.dto.response.PersonDTO;
import com.exemple.poc.client.dto.response.TeamDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Team API for CRUD")
@RestController
public class TeamController {

	@Autowired
	private ITeamService teamService;

	@ApiOperation("Retrieve teams")
	@RequestMapping(value = {"/Teams"},method = {RequestMethod.GET})
	public List<TeamDTO> findAllTeams() {
		return teamService.findAllTeams();
	}

	@PostMapping({"/Teams"})
	public ResponseEntity<Void> addTeam(@RequestBody @Valid TeamDTO team) throws Exception {
		TeamDTO newTeamDTO = teamService.addTeam(team);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(new Object[]{newTeamDTO.getId()})
				.toUri();
		return ResponseEntity.created(location).build();
	}


}