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
import com.example.poc.service.IEventCreatorService;
import com.exemple.poc.client.dto.response.ContinentDto;
import com.exemple.poc.client.dto.response.CountryDto;



@RunWith(SpringRunner.class)
@WebMvcTest(value = CountryController.class)
public class CountryControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ICountryService countryService;
	
	@MockBean
	private IEventCreatorService eventCreatorService;

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
		CountryDto mockCountry= new CountryDto();
		mockCountry.setId(id);
		mockCountry.setCodeiso2(codeIso2);
		mockCountry.setCodeiso3(codeIso3);
		mockCountry.setContinentName(continentName);
		mockCountry.setNationality(nationality);
		mockCountry.setName(name);
		
		List<CountryDto> mockCountries = new ArrayList<CountryDto>();
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
		CountryDto mockCountry= new CountryDto();
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
	
	
	@Test
	public void testAddContinentSuccess() throws Exception {
		CountryDto countryDTO = new CountryDto();
		countryDTO.setId(1);
		countryDTO.setNationality("French");
		countryDTO.setName("France");
		countryDTO.setCodeiso2("FR");
		countryDTO.setCodeiso3("FRA");
		countryDTO.setContinentName("europe");

		Mockito.when(countryService.addCountry(Mockito.any())).thenReturn(countryDTO);
		RequestBuilder request = MockMvcRequestBuilders
				.post("/Countries")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"id\":\"1\","
						+ "\"codeiso2\":\"FR\","
						+ "\"codeiso3\":\"FRA\","
						+ "\"nationality\":\"French\","
						+ "\"continentName\":\"Europe\","
						+ "\"name\":\"France\"}")
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
	}
	
	@Test
	public void testAddContinentFailed() throws Exception {
		CountryDto countryDTO = new CountryDto();
		countryDTO.setId(1);
		countryDTO.setNationality("French");
		countryDTO.setName("France (");
		countryDTO.setCodeiso2("FR");
		countryDTO.setCodeiso3("FRA");
		countryDTO.setContinentName("europe");

		Mockito.when(countryService.addCountry(Mockito.any())).thenReturn(countryDTO);
		RequestBuilder request = MockMvcRequestBuilders
				.post("/Countries")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"id\":\"1\","
						+ "\"codeiso2\":\"FR\","
						+ "\"codeiso3\":\"FRA\","
						+ "\"nationality\":\"French\","
						+ "\"continentName\":\"Europe\","
						+ "\"name\":\"France (\"}")
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
	}
	
	@Test
	public void testAddContinentSuccessWithSpace() throws Exception {
		CountryDto countryDTO = new CountryDto();
		countryDTO.setId(1);
		countryDTO.setNationality("American");
		countryDTO.setName("United States of America");
		countryDTO.setCodeiso2("US");
		countryDTO.setCodeiso3("USA");
		countryDTO.setContinentName("America");

		Mockito.when(countryService.addCountry(Mockito.any())).thenReturn(countryDTO);
		RequestBuilder request = MockMvcRequestBuilders
				.post("/Countries")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"id\":\"1\","
						+ "\"codeiso2\":\"US\","
						+ "\"codeiso3\":\"USA\","
						+ "\"nationality\":\"American\","
						+ "\"continentName\":\"America\","
						+ "\"name\":\"United States of America\"}")
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
	}
	
	@Test
	public void testAddContinentSuccessSpecialChar1() throws Exception {
		CountryDto countryDTO = createDummyCountryDto("Curaçao");
		Mockito.when(countryService.addCountry(Mockito.any())).thenReturn(countryDTO);
		RequestBuilder request = MockMvcRequestBuilders
				.post("/Countries")
				.accept(MediaType.APPLICATION_JSON)
				.content(createDummyCountryResponse("Curaçao"))
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
	}
	
	@Test
	public void testAddContinentSuccessSpecialChar2() throws Exception {
		CountryDto countryDTO = createDummyCountryDto("Guinea-Bissau");

		Mockito.when(countryService.addCountry(Mockito.any())).thenReturn(countryDTO);
		RequestBuilder request = MockMvcRequestBuilders
				.post("/Countries")
				.accept(MediaType.APPLICATION_JSON)
				.content(createDummyCountryResponse("Guinea-Bissau"))
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
	}
	
	@Test
	public void testAddContinentSuccessSpecialChar3() throws Exception {
		CountryDto countryDTO = createDummyCountryDto("Réunion");

		Mockito.when(countryService.addCountry(Mockito.any())).thenReturn(countryDTO);
		RequestBuilder request = MockMvcRequestBuilders
				.post("/Countries")
				.accept(MediaType.APPLICATION_JSON)
				.content(createDummyCountryResponse("Réunion"))
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
	}
	
	@Test
	public void testAddContinentSuccessSpecialChar4() throws Exception {
		CountryDto countryDTO = createDummyCountryDto("Svalbard & Jan Mayen Islands");

		Mockito.when(countryService.addCountry(Mockito.any())).thenReturn(countryDTO);
		RequestBuilder request = MockMvcRequestBuilders
				.post("/Countries")
				.accept(MediaType.APPLICATION_JSON)
				.content(createDummyCountryResponse("Svalbard & Jan Mayen Islands"))
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
	}
	
	private CountryDto createDummyCountryDto(String name) {
		CountryDto countryDTO = new CountryDto();
		countryDTO.setId(1);
		countryDTO.setName(name);
		countryDTO.setNationality("dummy");
		countryDTO.setCodeiso2("DM");
		countryDTO.setCodeiso3("DMY");
		countryDTO.setContinentName("America");
		return countryDTO;
	}
	
	private String createDummyCountryResponse(String name) {
		return "{\"id\":\"1\","
				+ "\"codeiso2\":\"DM\","
				+ "\"codeiso3\":\"DMY\","
				+ "\"nationality\":\"dummy\","
				+ "\"continentName\":\"America\","
				+ "\"name\":\""+name+"\"}";
	}
	
	

}
