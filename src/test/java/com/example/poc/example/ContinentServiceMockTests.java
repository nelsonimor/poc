package com.example.poc.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.poc.dao.ICustomContinentDAO;
import com.example.poc.service.IContinentService;
import com.example.poc.service.impl.ContinentService;

@SpringBootTest
public class ContinentServiceMockTests {
	
	@Mock
    private ICustomContinentDAO continentDAO;
	
	@InjectMocks
    private IContinentService continentService = new ContinentService();
	
	@BeforeEach
    void setMockOutput() {
        Mockito.when(continentDAO.get()).thenReturn("Hello Mockito From Repository");
    }
	
	@DisplayName("Test Mock helloService + helloRepository")
    @Test
    void testGet() {
        assertEquals("Hello Mockito From Repository", continentService.get());
    }
	
	

}
