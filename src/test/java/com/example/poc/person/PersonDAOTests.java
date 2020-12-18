package com.example.poc.person;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.poc.bo.CityBO;
import com.example.poc.bo.ContinentBO;
import com.example.poc.bo.CountryBO;
import com.example.poc.bo.PersonBO;
import com.example.poc.dao.IPersonDAO;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonDAOTests {
	
	  @Autowired
	  private IPersonDAO personDAO;
	  
	  private final String lastname = "Jordan";
	  private final String firstname = "Mickael";
	  private Timestamp birthdate = new Timestamp(System.currentTimeMillis());
	  
	  @Test
	  public void findAllPersons() {
		ContinentBO europe = new ContinentBO(1,"EU","Europe");
		CountryBO france = new CountryBO(1, "France", "FR", "FRA", "French", europe);
		
		CityBO lille = new CityBO();
		lille.setId(1);
		lille.setName("Lille");
		lille.setCountry(france);
		
		PersonBO person = new PersonBO();
		person.setId(1);
		person.setLastname(lastname);
		person.setFirstname(firstname);
		person.setBirthdate(birthdate);
		person.setBirthplace(lille);
		person.setNationality1(france);

		personDAO.save(person);

	    List<PersonBO> persons = personDAO.findAll();
	    assertEquals(persons.size(),1);
	  }
	  
	  @Test
	  public void findPersonByNamesAndBirthDate() {
			ContinentBO europe = new ContinentBO(1,"EU","Europe");
			CountryBO france = new CountryBO(1, "France", "FR", "FRA", "French", europe);
			
			CityBO lille = new CityBO();
			lille.setId(1);
			lille.setName("Lille");
			lille.setCountry(france);
			
			PersonBO person = new PersonBO();
			person.setId(1);
			person.setLastname(lastname);
			person.setFirstname(firstname);
			person.setBirthdate(birthdate);
			person.setBirthplace(lille);
			person.setNationality1(france);

			personDAO.save(person);

		    PersonBO p = personDAO.findByLastnameAndFirstnameAndBirthdate(lastname, firstname, birthdate);
		    assertNotNull(p);
	  }
	  



}
