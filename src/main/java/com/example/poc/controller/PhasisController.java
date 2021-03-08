package com.example.poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.poc.bo.RosterBO;
import com.example.poc.dao.IRosterDAO;
import com.example.poc.mapper.ObjectMapper;
import com.example.poc.service.IPhasisService;
import com.example.poc.service.IRosterService;
import com.exemple.poc.client.dto.response.PhasisDTO;
import com.exemple.poc.client.dto.response.RosterDto;
import com.exemple.poc.client.dto.response.RosterItemDto;

import io.swagger.annotations.Api;

@Api(description = "Phasis API for CRUD")
@RestController
public class PhasisController {

	@Autowired
	private IPhasisService phasisService;
	
	@GetMapping({"/view/phasis"})
	public List<PhasisDTO> findAllPhasis() {
		return phasisService.findAll();
	}	

	@GetMapping({"/view/phasis/{id}"})
	public PhasisDTO findPhasisById(@PathVariable int id) {
		return phasisService.findById(id);
	}	
	

	
	


}
