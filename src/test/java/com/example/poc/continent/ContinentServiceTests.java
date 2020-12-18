package com.example.poc.continent;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

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

import com.example.poc.bo.ContinentBO;
import com.example.poc.client.dto.request.ContinentRequest;
import com.example.poc.dao.IContinentDAO;
import com.example.poc.dao.ICustomContinentDAO;
import com.example.poc.exception.AlreadyExistsException;
import com.example.poc.exception.NotFoundException;
import com.example.poc.service.IContinentService;
import com.example.poc.service.impl.ContinentService;
import com.exemple.poc.client.dto.response.ContinentDTO;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ContinentServiceTests {
	
	@Mock
    private IContinentDAO continentDAO;
	
	@Mock
    private ICustomContinentDAO customContinentDAO;
	
	@InjectMocks
    private IContinentService continentService = new ContinentService();
	
	private List<ContinentBO> continentBOs = new ArrayList<ContinentBO>();
	
	private ContinentBO europe = new ContinentBO(1, "EU", "Europe");
	
	private ContinentRequest continentRequest = new ContinentRequest(1, "Europe", "EU");
	
	@BeforeEach
    void setMockOutput() {
		continentBOs.add(europe);
        Mockito.when(continentDAO.findAll()).thenReturn(continentBOs);
        Mockito.when(continentDAO.findById(1)).thenReturn(europe);
        Mockito.when(continentDAO.findById(2)).thenReturn(null);
        Mockito.when(continentDAO.findByName("Europe")).thenReturn(continentBOs);
        Mockito.when(continentDAO.findByCode("EU")).thenReturn(continentBOs);
        Mockito.when(continentDAO.findByNameAndCode("Africa", "AF")).thenReturn(continentBOs);
        Mockito.when(continentDAO.findByNameAndCode("Europe", "EU")).thenReturn(null);
        Mockito.when(continentDAO.save(Mockito.any())).thenReturn(europe);
        Mockito.when(customContinentDAO.findContinents(continentRequest)).thenReturn(continentBOs);
    }
	
	@DisplayName("Continent : findAll")
    @Test
    void testFindAll() {
        assertEquals(1, continentService.findAllContinents().size());
    }
	
	@DisplayName("Continent : findById")
    @Test
    void testFindById() {
        assertEquals(europe.getId(), continentService.findById(1).getId());
    }
	
	@DisplayName("Continent : findById not found")
    @Test
    void testFindByIdNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> {
        	continentService.findById(2);
          });
    }
	
	
	
	@DisplayName("Continent : findByName")
    @Test
    void testFindByName() {
        assertEquals(1, continentService.findByName("Europe").size());
    }
	
	@DisplayName("Continent : findByName")
    @Test
    void testFindByCode() {
        assertEquals(1, continentService.findByCode("EU").size());
    }
	
	@DisplayName("Continent : findByRequest")
    @Test
    void testFindByRequest() {
        assertEquals(1, continentService.findByRequest(continentRequest).size());
    }
	
	@DisplayName("Continent : add continent alreadyExists")
    @Test
    void testAddContinent() {
		ContinentDTO dto = continentService.addContinent(new ContinentDTO(1, "Europe", "EU"));
		Assertions.assertNotNull(dto);
    }
	
	@DisplayName("Continent : add continent alreadyExists")
    @Test
    void testAddContinentAlreadyExists() {
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
        	continentService.addContinent(new ContinentDTO(1, "AF", "Africa"));
          });
    }

}
