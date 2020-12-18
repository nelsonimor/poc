package com.example.poc.continent;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.poc.bo.ContinentBO;
import com.example.poc.dao.IContinentDAO;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ContinentDAOTests {
	

	  @Autowired
	  private IContinentDAO continentDAO;
	  
	  
	  @Test
	  public void should_find_all_continents() {
	    ContinentBO europe = new ContinentBO(1,"EU","Europe");
	    ContinentBO asia = new ContinentBO(2,"AS","Asia");
	    ContinentBO africa = new ContinentBO(3,"AF","Africa");
	    
	    continentDAO.save(europe);
	    continentDAO.save(asia);
	    continentDAO.save(africa);
	    
	    List<ContinentBO> continents = continentDAO.findAll();

	    assertEquals(continents.size(),3);
	  }


}
