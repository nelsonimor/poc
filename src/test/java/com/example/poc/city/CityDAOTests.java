package com.example.poc.city;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.example.poc.dao.ICityDAO;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CityDAOTests {
	
	  @Autowired
	  private ICityDAO cityDAO;
	  
	  @Test
	  public void findAllCities() {
		ContinentBO europe = new ContinentBO(1,"EU","Europe");
		CountryBO france = new CountryBO(1, "France", "FR", "FRA", "French", europe);
		
		CityBO dunkerque = new CityBO();
		dunkerque.setId(1);
		dunkerque.setName("Dunkerque");
		dunkerque.setCountry(france);
	
		CityBO lille = new CityBO();
		lille.setId(2);
		lille.setName("Lille");
		lille.setCountry(france);
		
		cityDAO.save(dunkerque);
		cityDAO.save(lille);

	    List<CityBO> cities = cityDAO.findAll();
	    assertEquals(cities.size(),2);
	  }
	  
	  @Test
	  public void findCityByName_NotNull() {
		ContinentBO europe = new ContinentBO(1,"EU","Europe");
		CountryBO france = new CountryBO(1, "France", "FR", "FRA", "French", europe);
		
		CityBO dunkerque = new CityBO();
		dunkerque.setId(1);
		dunkerque.setName("Dunkerque");
		dunkerque.setCountry(france);
	
		cityDAO.save(dunkerque);

	    CityBO city = cityDAO.findByNameAndCountry("Dunkerque", france);
	    assertNotNull(city);
	  }
	  
	  @Test
	  public void findCityByName_Null() {
		ContinentBO europe = new ContinentBO(1,"EU","Europe");
		CountryBO france = new CountryBO(1, "France", "FR", "FRA", "French", europe);
		
		CityBO dunkerque = new CityBO();
		dunkerque.setId(1);
		dunkerque.setName("Dunkerque");
		dunkerque.setCountry(france);
	
		cityDAO.save(dunkerque);

	    CityBO city = cityDAO.findByNameAndCountry("Lille", france);
	    assertEquals(city, null);
	  }


}
