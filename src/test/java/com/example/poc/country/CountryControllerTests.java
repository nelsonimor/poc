package com.example.poc.country;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.poc.controller.ContinentController;
import com.example.poc.controller.CountryController;
import com.example.poc.dao.IContinentDAO;
import com.example.poc.dao.ICountryDAO;
import com.example.poc.dao.ICustomContinentDAO;
import com.example.poc.service.IContinentService;
import com.example.poc.service.ICountryService;
import com.exemple.poc.client.dto.response.ContinentDTO;
import com.exemple.poc.client.dto.response.CountryDTO;



@RunWith(SpringRunner.class)
@WebMvcTest(value = CountryController.class)
public class CountryControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ICountryService countryService;

	@MockBean
	private ICountryDAO countryDAO;
	
	private final int id = 1;
	private final String name = "France";
	private final String codeIso2 = "FR";
	private final String codeIso3 = "FRA";
	private final String continentName = "Europe";
	private final String nationality = "French";

	@Test
	public void testFindAllCountries() throws Exception {

		CountryDTO mockCountry= new CountryDTO();
		mockCountry.setId(id);
		mockCountry.setCodeiso2(codeIso2);
		mockCountry.setCodeiso3(codeIso3);
		mockCountry.setContinentName(continentName);
		mockCountry.setNationality(nationality);
		mockCountry.setName(name);
		
		List<CountryDTO> mockCountries = new ArrayList<CountryDTO>();
		mockCountries.add(mockCountry);
		Mockito.when(countryService.findAllCountries()).thenReturn(mockCountries);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Countries").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[{\"id\":1,"
				+ "\"name\":\""+name+"\","
				+ "\"nationality\":\""+nationality+"\","
				+ "\"continentName\":\""+continentName+"\","
				+ "\"codeiso3\":\""+codeIso3+"\","
				+ "\"codeiso2\":\""+codeIso2+"\"}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}


	@Test
	public void testFindCountriesByName() throws Exception {
		
		CountryDTO mockCountry= new CountryDTO();
		mockCountry.setId(id);
		mockCountry.setCodeiso2(codeIso2);
		mockCountry.setCodeiso3(codeIso3);
		mockCountry.setContinentName(continentName);
		mockCountry.setNationality(nationality);
		mockCountry.setName(name);

		Mockito.when(countryService.findByName(name)).thenReturn(mockCountry);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Countries/name/"+name).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{\"id\":1,"
				+ "\"name\":\""+name+"\","
				+ "\"nationality\":\""+nationality+"\","
				+ "\"continentName\":\""+continentName+"\","
				+ "\"codeiso3\":\""+codeIso3+"\","
				+ "\"codeiso2\":\""+codeIso2+"\"}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

}
