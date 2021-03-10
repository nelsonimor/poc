package com.example.poc.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.poc.bo.CityBO;
import com.example.poc.bo.CountryBO;
import com.example.poc.bo.PersonBO;
import com.example.poc.dao.ICityDAO;
import com.example.poc.dao.ICountryDAO;
import com.example.poc.dao.IPersonDAO;
import com.example.poc.exception.AlreadyExistsException;
import com.example.poc.exception.NotFoundException;
import com.example.poc.mapper.ObjectMapper;
import com.example.poc.service.IPersonService;
import com.exemple.poc.client.dto.response.CityDto;
import com.exemple.poc.client.dto.response.PersonDto;

import model.Address;

@Service
public class PersonService implements IPersonService {
	
	@Autowired
	private IPersonDAO personDAO;
	
	@Autowired
	private ICountryDAO countryDAO;
	
	@Autowired
	private ICityDAO cityDAO;

	@Override
	public List<PersonDto> findAllPersons() {
		List<PersonBO> personBOs = personDAO.findAll();
		List<PersonDto> personDTOs = new ArrayList<PersonDto>();
		personBOs.stream().forEach((c) -> personDTOs.add(ObjectMapper.toPersonDTO(c)));
		return personDTOs;
	}

	@Override
	public PersonDto findPersonById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonDto addPerson(PersonDto person) throws AlreadyExistsException, NotFoundException {
		
		Optional<CountryBO> nationality1 = countryDAO.findByName(person.getNationality1());
		if(!nationality1.isPresent()) {
			throw new NotFoundException("Main nationality with name "+person.getNationality1()+" does not exist");
		}
		
		Optional<CountryBO> nationality2 = Optional.empty();
		if(person.getNationality2()!=null) {
			nationality2 = countryDAO.findByName(person.getNationality2());
			if(!nationality2.isPresent()) {
				throw new NotFoundException("Second nationality with name "+person.getNationality2()+" does not exist");
			}
		}
		
		Optional<CountryBO> countryBO = countryDAO.findByName(person.getBirthCountryPlace());
		if(!countryBO.isPresent()) {
			throw new NotFoundException("Birth country with name "+person.getBirthCountryPlace()+" does not exist");
		}
		
		Optional<CityBO> birthplace = cityDAO.findByNameAndCountry(person.getBirthCityPlace(), countryBO.get());
		if(!birthplace.isPresent()) {
			throw new NotFoundException("Birth city with name "+person.getBirthCityPlace()+" and country "+person.getBirthCountryPlace()+" do not exist");
		}
		
		Optional<PersonBO> personBO = personDAO.findByLastnameAndFirstnameAndBirthdate(person.getLastname(), person.getFirstname(), new Timestamp(person.getBirthDate().getTime()));
		if(personBO.isPresent()) {
			throw new AlreadyExistsException("Person with name ["+person.getLastname()+" "+person.getFirstname()+"] and birthdate "+person.getBirthDate()+" already exists");
		}
		
		PersonBO personBOAdded = (PersonBO)this.personDAO.save(ObjectMapper.toPersonBO(person,nationality1.get(),nationality2,birthplace.get()));
		PersonDto personDTO = ObjectMapper.toPersonDTO(personBOAdded);
		return personDTO;
	}

	@Override
	public PersonDto updatePerson(PersonDto person) throws AlreadyExistsException, NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonDto deletePerson(int id) throws AlreadyExistsException, NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
