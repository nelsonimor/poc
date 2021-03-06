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
import com.exemple.poc.client.dto.response.CityDto;
import com.exemple.poc.client.dto.response.PersonDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Person API for CRUD")
@RestController
public class PersonController {

	@Autowired
	private IPersonService personService;

	@ApiOperation("Retrieve persons")
	@RequestMapping(value = {"/view/persons"},method = {RequestMethod.GET})
	public List<PersonDto> findAllPersons() {
		return personService.findAllPersons();
	}

	@GetMapping({"/view/persons/{id}"})
	public PersonDto findPersonById(@PathVariable int id) {
		return personService.findPersonById(id);
	}

	@PostMapping({"/admin/persons"})
	public ResponseEntity<Void> addPerson(@RequestBody @Valid PersonDto person) throws Exception {
		PersonDto newPersonDTO = personService.addPerson(person);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(new Object[]{newPersonDTO.getId()})
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping({"/view/persons/{id}"})
	public void deletePersonById(@PathVariable int id) {
		personService.deletePerson(id);
	}

	@PutMapping({"/admin/persons/{id}"})
	public ResponseEntity<PersonBO> updatePerson(@RequestBody PersonBO person, @PathVariable int id) {
		/*PersonBO p = this.personDAO.findById(id);
		if (p == null) {
			logger.debug("PersonController.updatePerson() no user found");
			return ResponseEntity.notFound().build();
		} else {
			person.setId(id);
			person.setUpdateDate(new Timestamp(System.currentTimeMillis()));
			this.personDAO.save(person);
			return ResponseEntity.noContent().build();
		}*/
		return null;
	}
}
