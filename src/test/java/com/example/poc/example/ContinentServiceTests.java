package com.example.poc.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.poc.service.IContinentService;

@SpringBootTest
public class ContinentServiceTests {
	
	@Autowired
    IContinentService continentService;

	
	@DisplayName("Test Spring @Autowired Integration")
	@Test
    public void testGet()
    {
		assertEquals("Hello JUnit 5", continentService.get());
    }
	
	

}
