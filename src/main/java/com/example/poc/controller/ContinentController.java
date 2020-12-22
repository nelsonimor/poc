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
import com.exemple.poc.client.dto.response.ContinentDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Continent API for CRUD")
@RestController
public class ContinentController {

	static Logger logger = LoggerFactory.getLogger(ContinentController.class);

	@Autowired
	private IContinentService continentService;

	@ApiOperation("Retrieve all continents")
	@RequestMapping(value = {"/Continents"},method = {RequestMethod.GET})
	public List<ContinentDTO> getAllContinents() {
		return continentService.findAllContinents();
	}

	@ApiOperation("Retrieve continent by id")
	@GetMapping({"/Continents/id/{id}"})
	public ContinentDTO getContinentById(@PathVariable int id) throws NotFoundException {
		ContinentDTO result = continentService.findById(id);
		return result;
	}

	@ApiOperation("Retrieve continents by name")
	@GetMapping({"/Continents/name/{name}"})
	public ContinentDTO getContinentByName(@PathVariable String name) {
		return continentService.findByName(name);
	}
	
	@ApiOperation("Retrieve continents by code")
	@GetMapping({"/Continents/code/{code}"})
	public ContinentDTO getContinentByCode(@PathVariable String code) {
		return continentService.findByCode(code);
	}

	@ApiOperation("Retrieve continents by request")
	@RequestMapping("/Continents/request")
	public List<ContinentDTO> getContinentByRequest(ContinentRequest continentRequest) {
		return continentService.findByRequest(continentRequest);
	}

	@ApiOperation("Add new continent")
	@PostMapping({"/Continents"})
	public ResponseEntity<Void> addContinent(@RequestBody @Valid ContinentDTO continent) throws AlreadyExistsException  {
		ContinentDTO newContinentDTO = continentService.addContinent(continent);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(new Object[]{newContinentDTO.getId()})
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@ApiOperation("Delete continent")
	@DeleteMapping({"/Continents/{id}"})
	public void deleteContinentById(@PathVariable int id)  throws NotFoundException {
		continentService.deleteById(id);
	}

	@PutMapping({"/Continents/{id}"})
	public ResponseEntity<ContinentDTO> updateContinent(@RequestBody ContinentDTO continent, @PathVariable int id) throws NotFoundException {
		ContinentDTO dto = continentService.updateContinent(continent, id);
		return ResponseEntity.noContent().build();
	}




}
