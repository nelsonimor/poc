package com.example.poc.country;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.poc.bo.ContinentBO;
import com.example.poc.bo.CountryBO;
import com.example.poc.dao.ICountryDAO;
import com.example.poc.service.ICountryService;
import com.example.poc.service.impl.CountryService;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CountryServiceTests {
	
	@Mock
    private ICountryDAO countryDAO;
	
	@InjectMocks
    private ICountryService countryService = new CountryService();
	
	private List<CountryBO> countryBOs = new ArrayList<CountryBO>();
	
	private ContinentBO europe = new ContinentBO(1,"EU","Europe");
	private CountryBO france = new CountryBO(1, "France", "FR", "FRA", "French", europe);

	
	@BeforeEach
    void setMockOutput() {
		countryBOs.add(france);
        Mockito.when(countryDAO.findAll()).thenReturn(countryBOs);
    }
	
	@DisplayName("Countries : findAll")
    @Test
    void testFindAll() {
        assertEquals(1, countryService.findAllCountries().size());
    }
	

}
