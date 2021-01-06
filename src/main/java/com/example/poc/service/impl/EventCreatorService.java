package com.example.poc.service.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.poc.service.IEventCreatorService;
import com.exemple.poc.client.dto.response.EventDTO;

@Service
public class EventCreatorService implements IEventCreatorService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
    private MessageSource messageSource;
	
	@Value("${endpoint.events}")
	private String endPointEvent;

	
	public void createEvent(String code,Object[] messageParams,String nature) {
		String msg = messageSource.getMessage("msg."+code,messageParams, Locale.getDefault());
		EventDTO eventDTO = new EventDTO();
		eventDTO.setCode("EVT-"+code);
		eventDTO.setMessage(msg);
		eventDTO.setNature(nature);
		restTemplate.postForEntity(endPointEvent, eventDTO, EventDTO.class);
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

	@Override
	public void createEventSuccess(String code, Object[] messageParams) {
		createEvent(code,messageParams,"SUCCESS");
	}

	@Override
	public void createEventFailure(String code, Object[] messageParams) {
		createEvent(code,messageParams,"FAILURE");
	}
}
