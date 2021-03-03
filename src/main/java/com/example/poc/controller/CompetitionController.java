package com.example.poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.poc.bo.CompetitionBO;
import com.example.poc.service.ICompetitionService;

import io.swagger.annotations.Api;

@Api(description = "Controller API for CRUD")
@RestController
public class CompetitionController {

	@Autowired
	private ICompetitionService competitionService;

	@GetMapping({"/competitions"})
	public List<CompetitionBO> getAllCompetitions() {
		return competitionService.findAll();
	}	
	

	
	


}
