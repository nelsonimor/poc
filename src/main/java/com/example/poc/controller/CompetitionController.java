package com.example.poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.poc.service.ICompetitionOrganizationService;
import com.example.poc.service.ICompetitionService;
import com.exemple.poc.client.dto.response.CompetitionDto;
import com.exemple.poc.client.dto.response.CompetitionOrganizationDto;

import io.swagger.annotations.Api;

@Api(description = "Controller API for CRUD")
@RestController
public class CompetitionController {

	@Autowired
	private ICompetitionService competitionService;
	
	@Autowired
	private ICompetitionOrganizationService competitionOrganizationService;


	@GetMapping({"/competitions"})
	public List<CompetitionDto> getAllCompetitions() {
		return competitionService.findAll();
	}	
	
	@GetMapping({"/competitions/organization/{competitionId}"})
	public List<CompetitionOrganizationDto> getAllCompetitions(@PathVariable int competitionId) {
		return competitionOrganizationService.findByCompetition(competitionId);
	}	
	

	
	


}
