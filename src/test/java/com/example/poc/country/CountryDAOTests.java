package com.example.poc.country;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.poc.bo.ContinentBO;
import com.example.poc.bo.CountryBO;
import com.example.poc.dao.ICountryDAO;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CountryDAOTests {
	

	  @Autowired
	  private ICountryDAO countryDAO;
	  
	  
	  @Test
	  public void should_find_all_countries() {
		ContinentBO europe = new ContinentBO(1,"EU","Europe");
		CountryBO france = new CountryBO(1, "France", "FR", "FRA", "French", europe);
		CountryBO spain = new CountryBO(2, "Spain", "ES", "SPA", "Spanish", europe);
		CountryBO italy = new CountryBO(3, "Italy", "IT", "ITA", "Italian", europe);
	
		countryDAO.save(france);
		countryDAO.save(spain);
		countryDAO.save(italy);

	    
	    List<CountryBO> countries = countryDAO.findAll();
	    assertEquals(countries.size(),3);
	  }


}
