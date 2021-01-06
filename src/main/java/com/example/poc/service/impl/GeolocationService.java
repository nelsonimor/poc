package com.example.poc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.poc.service.IEventCreatorService;
import com.example.poc.service.IGeolocationService;
import com.example.poc.util.ActionCode;

import compute.NominatimReverseGeocodingJAPI;
import model.Address;

@Service
public class GeolocationService implements IGeolocationService {
	
	@Autowired
	private IEventCreatorService eventCreatorService;

	@Override
	public Address geolocate(String cityName,String countryName) {
    	NominatimReverseGeocodingJAPI geocoding = new NominatimReverseGeocodingJAPI();
    	Address address = geocoding.getAdress(cityName,countryName);
		eventCreatorService.createEventSuccess(ActionCode.GEOLOC_SUCCESS,new Object[] {cityName,countryName,address.getLat(),address.getLon()});
		return address;
	}

}
