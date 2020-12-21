package com.example.poc.geolocation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.poc.service.IGeolocationService;
import com.example.poc.service.impl.GeolocationService;

import model.Address;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class GeolocationServiceTests {
	
	@InjectMocks
    private IGeolocationService geolocationService = new GeolocationService();

	@BeforeEach
    void setMockOutput() {

    }
	
	@DisplayName("GeolocationService : geolocate")
    @Test
    void testGeolocate() {
		Address address = geolocationService.geolocate("Dunkerque", "France");
        assertEquals("Nord",address.getCounty());
    }
	
	/*@DisplayName("GeolocationService : geolocate accent")
    @Test
    void testGeolocateAccent() {
		Address address = geolocationService.geolocate("Marseille", "France");
        assertEquals("Bouches-du-Rhône",address.getCounty());
    }*/
	

}
