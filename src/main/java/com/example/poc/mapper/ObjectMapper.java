package com.example.poc.mapper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

import com.example.poc.bo.CityBO;
import com.example.poc.bo.ContinentBO;
import com.example.poc.bo.CountryBO;
import com.example.poc.bo.PersonBO;
import com.example.poc.bo.RosterBO;
import com.example.poc.bo.RosterItemBO;
import com.example.poc.bo.TeamBO;
import com.exemple.poc.client.dto.response.CityDTO;
import com.exemple.poc.client.dto.response.ContinentDTO;
import com.exemple.poc.client.dto.response.CountryDTO;
import com.exemple.poc.client.dto.response.PersonDTO;
import com.exemple.poc.client.dto.response.RosterDto;
import com.exemple.poc.client.dto.response.RosterItemDto;
import com.exemple.poc.client.dto.response.TeamDTO;

import model.Address;


public class ObjectMapper {

	public static ContinentDTO toContinentDto(ContinentBO continentBO) {
		if(continentBO==null)return null;
		ContinentDTO continentDTO = new ContinentDTO();
		continentDTO.setId(continentBO.getId());
		continentDTO.setName(continentBO.getName());
		continentDTO.setCode(continentBO.getCode());
		return continentDTO;
	}

	public static ContinentBO toContinentBo(ContinentDTO continentDTO) {
		if(continentDTO==null)return null;
		ContinentBO continentBO = new ContinentBO();
		continentBO.setId(continentDTO.getId());
		continentBO.setName(continentDTO.getName());
		continentBO.setCode(continentDTO.getCode());
		return continentBO;
	}

	public static CountryDTO toCountryDto(CountryBO countryBO) {
		if(countryBO==null)return null;
		CountryDTO countryDTO = new CountryDTO();
		countryDTO.setId(countryBO.getId());
		countryDTO.setName(countryBO.getName());
		countryDTO.setCodeiso2(countryBO.getCodeiso2());
		countryDTO.setCodeiso3(countryBO.getCodeiso3());
		countryDTO.setContinentName(countryBO.getContinent().getName());
		countryDTO.setNationality(countryBO.getNationality());
		return countryDTO;
	}

	public static CountryBO toCountryBO(CountryDTO countryDTO, ContinentBO continentBO) {
		if(countryDTO==null)return null;
		CountryBO countryBO = new CountryBO();
		countryBO.setId(countryDTO.getId());
		countryBO.setName(countryDTO.getName());
		countryBO.setCodeiso2(countryDTO.getCodeiso2());
		countryBO.setCodeiso3(countryDTO.getCodeiso3());
		countryBO.setContinent(continentBO);
		countryBO.setNationality(countryDTO.getNationality());
		return countryBO;
	}

	public static CityDTO toCityDto(CityBO cityBO) {
		if(cityBO==null)return null;
		CityDTO cityDTO = new CityDTO();
		cityDTO.setId(cityBO.getId());
		cityDTO.setName(cityBO.getName());
		cityDTO.setCounty(cityBO.getCounty());
		cityDTO.setCountycode(cityBO.getCountycode());
		cityDTO.setState(cityBO.getState());
		cityDTO.setCountryName(cityBO.getCountry().getName());
		cityDTO.setLatitude(cityBO.getLatitude());
		cityDTO.setLongitude(cityBO.getLongitude());
		return cityDTO;
	}

	public static CityBO toCityBO(CityDTO cityDTO, CountryBO countryBO) {
		if(cityDTO==null)return null;
		CityBO cityBO = new CityBO();
		cityBO.setId(cityDTO.getId());
		cityBO.setName(cityDTO.getName());
		cityBO.setCounty(cityDTO.getCounty());
		cityBO.setCountycode(cityDTO.getCountycode());
		cityBO.setState(cityDTO.getState());
		cityBO.setCountry(countryBO);
		cityBO.setLongitude(cityDTO.getLongitude());
		cityBO.setLatitude(cityDTO.getLatitude());
		cityBO.setZip(cityDTO.getZip());
		return cityBO;
	}

	public static CityBO toCityBO(Address address,String cityName,CountryBO countryBO) {
		if(address==null)return null;
		CityBO cityBO = new CityBO();
		cityBO.setName(cityName);
		cityBO.setCountry(countryBO);
		cityBO.setCounty(address.getCounty());
		cityBO.setLatitude(address.getLat());
		cityBO.setLongitude(address.getLon());
		cityBO.setState(address.getState());
		cityBO.setZip(address.getPostcode());
		return cityBO;
	}
	
	public static PersonDTO toPersonDTO(PersonBO personBO) {
		PersonDTO personDto = new PersonDTO();
		personDto.setFirstname(personBO.getFirstname());
		personDto.setLastname(personBO.getLastname());
		personDto.setBirthCityPlace(personBO.getBirthplace().getName());
		personDto.setBirthCountryPlace(personBO.getBirthplace().getCountry().getName());
		personDto.setId(personBO.getId());
		personDto.setNationality1(personBO.getNationality1().getName());
		if(personBO.getNationality2()!=null) {
			personDto.setNationality2(personBO.getNationality2().getName());
		}
		return personDto;
	}

	public static PersonBO toPersonBO(PersonDTO person,CountryBO nationality1,Optional<CountryBO> nationality2,CityBO birthplace) {
		PersonBO personBO = new PersonBO();
		personBO.setId(person.getId());
		personBO.setFirstname(person.getFirstname());
		personBO.setLastname(person.getLastname());
		personBO.setBirthdate(new Timestamp(person.getBirthDate().getTime()));
		personBO.setNationality1(nationality1);
		if(nationality2.isPresent()) {
			personBO.setNationality2(nationality2.get());
		}
		personBO.setBirthplace(birthplace);
		return personBO;
	}

	public static TeamDTO toTeamDto(TeamBO t) {
		TeamDTO teamDto = new TeamDTO();
		teamDto.setId(t.getId());
		teamDto.setName(t.getName());
		if(t.getCity1()!=null)teamDto.setCityName1(t.getCity1().getName());
		if(t.getCity2()!=null)teamDto.setCityName2(t.getCity2().getName());
		if(t.getCity3()!=null)teamDto.setCityName3(t.getCity3().getName());
		if(t.getCountry()!=null)teamDto.setCountryName(t.getCountry().getName());
		teamDto.setType(t.getType());
		return teamDto;
	}

	public static TeamBO toTeamBO(TeamDTO teamDto, Optional<CityBO> optCity1, Optional<CityBO> optCity2, Optional<CityBO> optCity3, Optional<CountryBO> optCountry) {
		TeamBO teamBO = new TeamBO();
		teamBO.setName(teamDto.getName());
		teamBO.setType(teamDto.getType());
		if(optCity1.isPresent())teamBO.setCity1(optCity1.get());
		if(optCity2.isPresent())teamBO.setCity2(optCity2.get());
		if(optCity3.isPresent())teamBO.setCity3(optCity3.get());
		if(optCountry.isPresent())teamBO.setCountry(optCountry.get());
		return teamBO;
	}
	
	public static RosterDto toRosterDto(RosterBO rosterBo) {
		RosterDto rosterDto = new RosterDto();
		rosterDto.setTeamDto(toTeamDto(rosterBo.getTeam()));
		rosterDto.setStartdate(new Date(rosterBo.getStartdate().getTime()));
		rosterDto.setEnddate(new Date(rosterBo.getEnddate().getTime()));
		for (Iterator iterator = rosterBo.getRosterItems().iterator(); iterator.hasNext();) {
			RosterItemBO item = (RosterItemBO) iterator.next();
			rosterDto.addItem(toRosterItemDto(item));
		}
		return rosterDto;
	}
	
	public static RosterItemDto toRosterItemDto(RosterItemBO rosterItemBo) {
		RosterItemDto rosterItemDto = new RosterItemDto();
		rosterItemDto.setPersonDto(toPersonDTO(rosterItemBo.getPerson()));
		if(rosterItemBo.getStartdate()!=null)rosterItemDto.setStartdate(new Date(rosterItemBo.getStartdate().getTime()));
		if(rosterItemBo.getEnddate()!=null)rosterItemDto.setEnddate(new Date(rosterItemBo.getEnddate().getTime()));
		return rosterItemDto;
	}


}
