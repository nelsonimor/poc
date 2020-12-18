package com.example.poc.service.impl;

import org.springframework.stereotype.Service;

import com.example.poc.service.IGeolocationService;

import compute.NominatimReverseGeocodingJAPI;
import model.Address;

@Service
public class GeolocationService implements IGeolocationService {

	@Override
	public Address geolocate(String cityName,String countryName) {
    	NominatimReverseGeocodingJAPI geocoding = new NominatimReverseGeocodingJAPI();
    	Address address = geocoding.getAdress(cityName,countryName);
		return address;
	}

}
