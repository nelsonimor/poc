package com.example.poc.city;

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

import com.example.poc.controller.CityController;
import com.example.poc.controller.ContinentController;
import com.example.poc.dao.ICityDAO;
import com.example.poc.dao.IContinentDAO;
import com.example.poc.dao.ICustomContinentDAO;
import com.example.poc.exception.TechnicalExceptionHandler;
import com.example.poc.exception.ValidationExceptionHandler;
import com.example.poc.service.ICityService;
import com.example.poc.service.IContinentService;
import com.example.poc.service.IEventCreatorService;
import com.exemple.poc.client.dto.response.CityDTO;
import com.exemple.poc.client.dto.response.ContinentDTO;



@RunWith(SpringRunner.class)
@WebMvcTest(value = CityController.class)
public class CityControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ICityService cityService;
	
	@MockBean
	private IEventCreatorService eventCreatorService;
	
	String cityName = "Dunkerque";
	String countryName = "France";

	@Test
	public void testFindAllCities() throws Exception {

		CityDTO cityDTO = new CityDTO();
		cityDTO.setId(1);
		cityDTO.setName(cityName);
		cityDTO.setCountryName(countryName);
		
		List<CityDTO> mockCities = new ArrayList<CityDTO>();
		mockCities.add(cityDTO);
		Mockito.when(cityService.findAllCities()).thenReturn(mockCities);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Cities").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[{\"id\":1,\"name\":\""+cityName+"\",\"countryName\":\""+countryName+"\"}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}


	@Test
	public void testAddCity() throws Exception {
		CityDTO cityDTO = new CityDTO();
		cityDTO.setId(1);
		cityDTO.setName(cityName);
		cityDTO.setCountryName(countryName);


		Mockito.when(cityService.addCity(Mockito.any())).thenReturn(cityDTO);
		RequestBuilder request = MockMvcRequestBuilders
				.post("/Cities")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"id\":\"1\",\"name\":\"Dunkerque\",\"countryName\":\"France\"}")
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
	}
	
	@Test
	public void testAddCityValidationFailedTooSmall() throws Exception {
		CityDTO cityDTO = new CityDTO();
		cityDTO.setId(1);
		cityDTO.setName("A");
		cityDTO.setCountryName(countryName);


		Mockito.when(cityService.addCity(Mockito.any())).thenReturn(cityDTO);
		RequestBuilder request = MockMvcRequestBuilders
				.post("/Cities")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"id\":\"1\",\"name\":\"A\",\"countryName\":\"France\"}")
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
	}
	
	@Test
	public void testAddCityValidationFailedWrongPattern() throws Exception {
		CityDTO cityDTO = new CityDTO();
		cityDTO.setId(1);
		cityDTO.setName("<%UNDEFINED%>");
		cityDTO.setCountryName(countryName);


		Mockito.when(cityService.addCity(Mockito.any())).thenReturn(cityDTO);
		RequestBuilder request = MockMvcRequestBuilders
				.post("/Cities")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"id\":\"1\",\"name\":\"<%UNDEFINED%>\",\"countryName\":\"France\"}")
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
	}



}
