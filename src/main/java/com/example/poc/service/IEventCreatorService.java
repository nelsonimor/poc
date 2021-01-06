package com.example.poc.service;

public interface IEventCreatorService {
	
	void createEventSuccess(String code,Object[] messageParams);
	
	void createEventFailure(String code,Object[] messageParams);

}
