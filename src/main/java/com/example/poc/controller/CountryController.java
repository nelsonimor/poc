package com.example.poc.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.poc.exception.AlreadyExistsException;
import com.example.poc.exception.NotFoundException;
import com.example.poc.service.ICountryService;
import com.exemple.poc.client.dto.response.ContinentDTO;
import com.exemple.poc.client.dto.response.CountryDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Country API for CRUD")
@RestController
public class CountryController {

	static Logger logger = LoggerFactory.getLogger(CountryController.class);

	@Autowired
	private ICountryService countryService;

	@ApiOperation("Retrieve all countries")
	@RequestMapping(value = {"/Countries"},method = {RequestMethod.GET})
	public List<CountryDTO> getAllCountries() {
		return countryService.findAllCountries();
	}

	@ApiOperation("Retrieve countries by name")
	@GetMapping({"/Countries/name/{name}"})
	public CountryDTO getCountryByName(@PathVariable String name) {
		return countryService.findByName(name);
	}
	
	@ApiOperation("Retrieve countries by continent")
	@GetMapping({"/Countries/continent/{continentName}"})
	public List<CountryDTO> getCountryByContinent(@PathVariable String continentName) {
		return countryService.findByContinent(continentName);
	}

	@ApiOperation("Add new country")
	@PostMapping({"/Countries"})
	public ResponseEntity<Void> addContinent(@RequestBody @Valid CountryDTO country) throws AlreadyExistsException,NotFoundException  {
		CountryDTO newCountryDTO = countryService.addCountry(country);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(new Object[]{newCountryDTO.getId()})
				.toUri();
		return ResponseEntity.created(location).build();
	}


}
