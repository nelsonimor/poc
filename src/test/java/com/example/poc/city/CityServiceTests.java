package com.example.poc.city;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.poc.bo.CityBO;
import com.example.poc.bo.ContinentBO;
import com.example.poc.bo.CountryBO;
import com.example.poc.dao.ICityDAO;
import com.example.poc.dao.ICountryDAO;
import com.example.poc.dao.ICustomContinentDAO;
import com.example.poc.service.ICityService;
import com.example.poc.service.IEventCreatorService;
import com.example.poc.service.IGeolocationService;
import com.example.poc.service.impl.CityService;
import com.exemple.poc.client.dto.response.CityDto;

import model.Address;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CityServiceTests {
	
	@Mock
    private ICityDAO cityDAO;
	
	@Mock
    private ICountryDAO countryDAO;
	
	@Mock
    private IGeolocationService geolocationService;
	
	@Mock
    private ICustomContinentDAO customContinentDAO;
	
	@Mock
	private IEventCreatorService eventCreatorService;
	
	@InjectMocks
    private ICityService cityService = new CityService();
	
	private List<CityBO> cityBOs = new ArrayList<CityBO>();
	

	@BeforeEach
    void setMockOutput() {
		
		CountryBO france = new CountryBO(1, "France", "FR", "FRA","French", new ContinentBO(1, "EU", "Europe"));
		Optional<CountryBO> optFrance = Optional.of((CountryBO) france);
		
		
		CityBO dk = new CityBO();
		dk.setId(1);
		dk.setName("Dunkerque");
		dk.setCountry(france);
		dk.setLongitude(2d);
		dk.setLatitude(2d);

		cityBOs.add(dk);
        Mockito.when(countryDAO.findByName("France")).thenReturn(optFrance);
        Mockito.when(cityDAO.findAll()).thenReturn(cityBOs);
        Mockito.when(cityDAO.save(Mockito.any())).thenReturn(dk);

    }
	
	@DisplayName("City : findAll")
    @Test
    void testFindAll() {
        assertEquals(1, cityService.findAllCities().size());
    }
	

	@DisplayName("City : add city without geolocation")
    @Test
    void testAddCityWithoutGeolocation() {
		CityDto cityDTO = new CityDto();
		cityDTO.setCountryName("France");
		cityDTO.setName("Dunkerque");
		cityDTO.setLongitude(1d);
		cityDTO.setLatitude(1d);
		
		CityDto dto = cityService.addCity(cityDTO);
		
		Assertions.assertNotNull(dto);
    }
	
	@DisplayName("City : add city with geolocation")
    @Test
    void testAddCityWithGeolocation() {
		CityDto cityDTO = new CityDto();
		cityDTO.setCountryName("France");
		cityDTO.setName("Dunkerque");

		CityDto dto = cityService.addCity(cityDTO);

		Assertions.assertEquals(dto.getLatitude(),2);
    }
	


}
