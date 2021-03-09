package com.example.poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.poc.service.IPhasisOrganizationService;
import com.example.poc.service.IPhasisParticipationService;
import com.example.poc.service.IPhasisService;
import com.exemple.poc.client.dto.response.PhasisDTO;
import com.exemple.poc.client.dto.response.PhasisOrganizationDto;
import com.exemple.poc.client.dto.response.PhasisParticipationDto;

import io.swagger.annotations.Api;

@Api(description = "Phasis API for CRUD")
@RestController
public class PhasisController {

	@Autowired
	private IPhasisService phasisService;
	
	@Autowired
	private IPhasisOrganizationService phasisOrganizationService;
	
	@Autowired
	private IPhasisParticipationService phasisParticipationService;
	
	@GetMapping({"/view/phasis"})
	public List<PhasisDTO> findAllPhasis() {
		return phasisService.findAll();
	}	

	@GetMapping({"/view/phasis/{id}"})
	public PhasisDTO findPhasisById(@PathVariable int id) {
		return phasisService.findById(id);
	}
	
	@GetMapping({"/view/phasis/organizations/"})
	public List<PhasisOrganizationDto> findAllPhasisOrganizations() {
		return phasisOrganizationService.findAll();
	}
	
	@GetMapping({"/view/phasis/organizations/{id}"})
	public PhasisOrganizationDto findAllPhasisOrganizationById(@PathVariable int id) {
		return phasisOrganizationService.findById(id);
	}
	
	@GetMapping({"/view/phasis/organizations/participations"})
	public List<PhasisParticipationDto> findAllPhasisParticipations() {
		return phasisParticipationService.findAll();
	}
	
	@GetMapping({"/view/phasis/organizations/participations/{id}"})
	public PhasisParticipationDto findAllPhasisParticipationById(@PathVariable int id) {
		return phasisParticipationService.findById(id);
	}
	

	
	


}
