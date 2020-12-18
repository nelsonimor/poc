package com.example.poc.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
import com.exemple.poc.client.dto.response.CityDTO;
import com.exemple.poc.client.dto.response.PersonDTO;

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
	public List<PersonDTO> findAllPersons() {
		List<PersonBO> personBOs = personDAO.findAll();
		List<PersonDTO> personDTOs = new ArrayList<PersonDTO>();
		personBOs.stream().forEach((c) -> personDTOs.add(ObjectMapper.toPersonDTO(c)));
		return personDTOs;
	}

	@Override
	public PersonDTO findPersonById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonDTO addPerson(PersonDTO person) throws AlreadyExistsException, NotFoundException {
		
		CountryBO nationality1 = countryDAO.findByName(person.getNationality1());
		if(nationality1==null) {
			throw new NotFoundException("Main nationality with name "+person.getNationality1()+" does not exist");
		}
		
		CountryBO nationality2 = null;
		if(person.getNationality2()!=null) {
			nationality2 = countryDAO.findByName(person.getNationality2());
			if(nationality2==null) {
				throw new NotFoundException("Second nationality with name "+person.getNationality2()+" does not exist");
			}
		}
		
		CountryBO countryBO = countryDAO.findByName(person.getBirthCountryPlace());
		if(countryBO==null) {
			throw new NotFoundException("Birth country with name "+person.getBirthCountryPlace()+" does not exist");
		}
		
		CityBO birthplace = cityDAO.findByNameAndCountry(person.getBirthCityPlace(), countryBO);
		if(birthplace==null) {
			throw new NotFoundException("Birth city with name "+person.getBirthCityPlace()+" and country "+person.getBirthCountryPlace()+" do not exist");
		}
		
		PersonBO personBO = personDAO.findByLastnameAndFirstnameAndBirthdate(person.getLastname(), person.getFirstname(), new Timestamp(person.getBirthDate().getTime()));
		if(personBO!=null) {
			throw new AlreadyExistsException("Person with name ["+person.getLastname()+" "+person.getFirstname()+"] and birthdate "+person.getBirthDate()+" already exists");
		}
		
		PersonBO personBOAdded = (PersonBO)this.personDAO.save(ObjectMapper.toPersonBO(person,nationality1,nationality2,birthplace));
		PersonDTO personDTO = ObjectMapper.toPersonDTO(personBOAdded);
		return personDTO;
	}

	@Override
	public PersonDTO updatePerson(PersonDTO person) throws AlreadyExistsException, NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonDTO deletePerson(int id) throws AlreadyExistsException, NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
