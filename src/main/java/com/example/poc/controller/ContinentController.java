package com.example.poc.controller;

import java.net.URI;
import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.example.poc.client.dto.request.ContinentRequest;
import com.example.poc.exception.AlreadyExistsException;
import com.example.poc.exception.NotFoundException;
import com.example.poc.service.IContinentService;
import com.exemple.poc.client.dto.response.ContinentDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Continent API for CRUD")
@RestController
public class ContinentController {

	static Logger logger = LoggerFactory.getLogger(ContinentController.class);

	@Autowired
	private IContinentService continentService;

	@ApiOperation("Retrieve all continents")
	@RequestMapping(value = {"/continents"},method = {RequestMethod.GET})
	public List<ContinentDto> getAllContinents() {
		return continentService.findAllContinents();
	}

	@ApiOperation("Retrieve continent by id")
	@GetMapping({"/view/continents/id/{id}"})
	public ContinentDto getContinentById(@PathVariable int id) throws NotFoundException {
		ContinentDto result = continentService.findById(id);
		return result;
	}

	@ApiOperation("Retrieve continents by name")
	@GetMapping({"/view/continents/name/{name}"})
	public ContinentDto getContinentByName(@PathVariable String name) {
		return continentService.findByName(name);
	}
	
	@ApiOperation("Retrieve continents by code")
	@GetMapping({"/view/continents/code/{code}"})
	public ContinentDto getContinentByCode(@PathVariable String code) {
		return continentService.findByCode(code);
	}

	@ApiOperation("Retrieve continents by request")
	@RequestMapping("/view/continents/request")
	public List<ContinentDto> getContinentByRequest(ContinentRequest continentRequest) {
		return continentService.findByRequest(continentRequest);
	}

	@ApiOperation("Add new continent")
	@PostMapping({"/admin/continents"})
	public ResponseEntity<Void> addContinent(@RequestBody @Valid ContinentDto continent) throws AlreadyExistsException  {
		ContinentDto newContinentDTO = continentService.addContinent(continent);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(new Object[]{newContinentDTO.getId()})
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@ApiOperation("Delete continent")
	@DeleteMapping({"/admin/continents/{id}"})
	public void deleteContinentById(@PathVariable int id)  throws NotFoundException {
		continentService.deleteById(id);
	}

	@PutMapping({"/admin/continents/{id}"})
	public ResponseEntity<ContinentDto> updateContinent(@RequestBody ContinentDto continent, @PathVariable int id) throws NotFoundException {
		ContinentDto dto = continentService.updateContinent(continent, id);
		return ResponseEntity.noContent().build();
	}




}
