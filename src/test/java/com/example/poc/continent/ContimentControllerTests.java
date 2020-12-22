package com.example.poc.continent;

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
import com.example.poc.dao.IContinentDAO;
import com.example.poc.dao.ICustomContinentDAO;
import com.example.poc.exception.TechnicalExceptionHandler;
import com.example.poc.exception.ValidationExceptionHandler;
import com.example.poc.service.IContinentService;
import com.exemple.poc.client.dto.response.ContinentDTO;



@RunWith(SpringRunner.class)
@WebMvcTest(value = ContinentController.class)
public class ContimentControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IContinentService continentService;
	
	@MockBean
	private TechnicalExceptionHandler technicalExceptionHandler;
	
	@MockBean
	private ValidationExceptionHandler validationExceptionHandler;

	@MockBean
	private IContinentDAO continentDAO;

	@MockBean
	private ICustomContinentDAO customContinentDAO;

	public int id = 1;
	public String name = "Asia";
	public String code = "AS";

	public final ContinentDTO mockContinent = new ContinentDTO(id,code,name);


	@Test
	public void testFindAllContinents() throws Exception {
		List<ContinentDTO> mockContinents = new ArrayList<ContinentDTO>();
		mockContinents.add(mockContinent);
		Mockito.when(continentService.findAllContinents()).thenReturn(mockContinents);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Continents").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[{\"id\":1,\"code\":\""+code+"\",\"name\":\""+name+"\"}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testFindContinentById() throws Exception {
		Mockito.when(continentService.findById(id)).thenReturn(mockContinent);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Continents/id/"+id).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{\"id\":"+id+",\"code\":\""+code+"\",\"name\":\""+name+"\"}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testFindContinentByName() throws Exception {
		Mockito.when(continentService.findByName(name)).thenReturn(mockContinent);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Continents/name/"+name).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{\"id\":1,\"code\":\""+code+"\",\"name\":\""+name+"\"}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testFindContinentByCode() throws Exception {
		Mockito.when(continentService.findByCode(code)).thenReturn(mockContinent);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Continents/code/"+code).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{\"id\":1,\"code\":\""+code+"\",\"name\":\""+name+"\"}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testFindContinentByRequest() throws Exception {
		List<ContinentDTO> mockContinents = new ArrayList<ContinentDTO>();
		mockContinents.add(mockContinent);
		Mockito.when(continentService.findByRequest(Mockito.any())).thenReturn(mockContinents);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Continents/request?name="+name+"&code="+code+"&id="+id+"").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[{\"id\":1,\"code\":\""+code+"\",\"name\":\""+name+"\"}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testAddContinent() throws Exception {
		ContinentDTO continentDTOOutput = new ContinentDTO();
		continentDTOOutput.setId(2);
		continentDTOOutput.setName("Africa");
		continentDTOOutput.setCode("AF");


		Mockito.when(continentService.addContinent(Mockito.any())).thenReturn(continentDTOOutput);
		RequestBuilder request = MockMvcRequestBuilders
				.post("/Continents")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"code\":\"AF\",\"name\":\"Africa\"}")
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
	}



}
