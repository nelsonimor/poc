package com.example.poc.service;

import model.Address;

public interface IGeolocationService {
	
	Address geolocate(String cityName,String countryName);

}
