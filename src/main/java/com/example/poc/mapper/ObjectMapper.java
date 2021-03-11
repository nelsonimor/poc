package com.example.poc.mapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.example.poc.bo.BoxlineBO;
import com.example.poc.bo.CityBO;
import com.example.poc.bo.CompetitionBO;
import com.example.poc.bo.CompetitionOrganizationBO;
import com.example.poc.bo.CompetitionParticipationBO;
import com.example.poc.bo.ContinentBO;
import com.example.poc.bo.CountryBO;
import com.example.poc.bo.GameBO;
import com.example.poc.bo.PersonBO;
import com.example.poc.bo.PhasisBO;
import com.example.poc.bo.PhasisOrganizationBO;
import com.example.poc.bo.PhasisParticipationBO;
import com.example.poc.bo.RosterBO;
import com.example.poc.bo.RosterItemBO;
import com.example.poc.bo.TeamBO;
import com.exemple.poc.client.dto.response.TeamListDto;
import com.exemple.poc.client.dto.response.BoxlineDto;
import com.exemple.poc.client.dto.response.CityDto;
import com.exemple.poc.client.dto.response.CompetitionDto;
import com.exemple.poc.client.dto.response.CompetitionOrganizationDto;
import com.exemple.poc.client.dto.response.CompetitionParticipationDto;
import com.exemple.poc.client.dto.response.ContinentDto;
import com.exemple.poc.client.dto.response.CountryDto;
import com.exemple.poc.client.dto.response.GameDto;
import com.exemple.poc.client.dto.response.PersonDto;
import com.exemple.poc.client.dto.response.PhasisDto;
import com.exemple.poc.client.dto.response.PhasisOrganizationDto;
import com.exemple.poc.client.dto.response.PhasisParticipationDto;
import com.exemple.poc.client.dto.response.RosterDto;
import com.exemple.poc.client.dto.response.RosterItemDto;
import com.exemple.poc.client.dto.response.TeamDto;

import model.Address;


public class ObjectMapper {

	public static ContinentDto toContinentDto(ContinentBO continentBO) {
		if(continentBO==null)return null;
		ContinentDto continentDTO = new ContinentDto();
		continentDTO.setId(continentBO.getId());
		continentDTO.setName(continentBO.getName());
		continentDTO.setCode(continentBO.getCode());
		return continentDTO;
	}

	public static ContinentBO toContinentBo(ContinentDto continentDTO) {
		if(continentDTO==null)return null;
		ContinentBO continentBO = new ContinentBO();
		continentBO.setId(continentDTO.getId());
		continentBO.setName(continentDTO.getName());
		continentBO.setCode(continentDTO.getCode());
		return continentBO;
	}

	public static CountryDto toCountryDto(CountryBO countryBO) {
		if(countryBO==null)return null;
		CountryDto countryDTO = new CountryDto();
		countryDTO.setId(countryBO.getId());
		countryDTO.setName(countryBO.getName());
		countryDTO.setCodeiso2(countryBO.getCodeiso2());
		countryDTO.setCodeiso3(countryBO.getCodeiso3());
		countryDTO.setContinentName(countryBO.getContinent().getName());
		countryDTO.setNationality(countryBO.getNationality());
		return countryDTO;
	}

	public static CountryBO toCountryBO(CountryDto countryDTO, ContinentBO continentBO) {
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

	public static CityDto toCityDto(CityBO cityBO) {
		if(cityBO==null)return null;
		CityDto cityDTO = new CityDto();
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

	public static CityBO toCityBO(CityDto cityDTO, CountryBO countryBO) {
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
	
	public static PersonDto toPersonDTO(PersonBO personBO) {
		PersonDto personDto = new PersonDto();
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

	public static PersonBO toPersonBO(PersonDto person,CountryBO nationality1,Optional<CountryBO> nationality2,CityBO birthplace) {
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

	public static TeamDto toTeamDto(TeamBO t) {
		TeamDto teamDto = new TeamDto();
		teamDto.setId(t.getId());
		teamDto.setName(t.getName());
		if(t.getCity1()!=null) {
			teamDto.setCityName1(t.getCity1().getName());
			teamDto.setCountryOfCity1(t.getCity1().getCountry().getName());
		}
		
		if(t.getCity2()!=null) {
			teamDto.setCityName2(t.getCity2().getName());
			teamDto.setCountryOfCity2(t.getCity2().getCountry().getName());
		}
		
		if(t.getCity3()!=null) {
			teamDto.setCityName3(t.getCity3().getName());
			teamDto.setCountryOfCity3(t.getCity3().getCountry().getName());
		}
		
		if(t.getCountry()!=null)teamDto.setCountryName(t.getCountry().getName());
		teamDto.setType(t.getType());
		return teamDto;
	}

	public static TeamBO toTeamBO(TeamDto teamDto, Optional<CityBO> optCity1, Optional<CityBO> optCity2, Optional<CityBO> optCity3, Optional<CountryBO> optCountry) {
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
		rosterItemDto.setRosterId(rosterItemBo.getRoster().getId());
		rosterItemDto.setTeamName(rosterItemBo.getRoster().getTeam().getName());
		rosterItemDto.setSeasonStartdate(rosterItemBo.getRoster().getStartdate());
		rosterItemDto.setSeasonEnddate(rosterItemBo.getRoster().getEnddate());
		return rosterItemDto;
	}

	public static CompetitionDto toCompetitionDto(CompetitionBO c) {
		CompetitionDto competitionDto = new CompetitionDto();
		competitionDto.setId(c.getId());
		competitionDto.setName(c.getName());
		competitionDto.setCountry(c.getCountry()!=null?c.getCountry().getName():null);
		competitionDto.setContient(c.getContinent()!=null?c.getContinent().getName():null);
		return competitionDto;
	}

	public static CompetitionOrganizationDto toCompetitionOrganizationDto(CompetitionOrganizationBO c) {
		CompetitionOrganizationDto competitionOrganizationDto = new CompetitionOrganizationDto();
		competitionOrganizationDto.setId(c.getId());
		competitionOrganizationDto.setCompetitionName(c.getCompetition().getName());
		competitionOrganizationDto.setStartdate(c.getStartdate());
		competitionOrganizationDto.setEnddate(c.getEnddate());
		return competitionOrganizationDto;
	}

	public static CompetitionParticipationDto toCompetitionParticipationDto(CompetitionParticipationBO c) {
		CompetitionParticipationDto competitionParticipationDto = new CompetitionParticipationDto();
		competitionParticipationDto.setId(c.getId());
		competitionParticipationDto.setCompetitionId(c.getCompetitionOrganization().getCompetition().getId());
		competitionParticipationDto.setCompetitionName(c.getCompetitionOrganization().getCompetition().getName());
		competitionParticipationDto.setTeamName(c.getRoster().getTeam().getName());
		competitionParticipationDto.setRosterId(c.getRoster().getId());
		competitionParticipationDto.setStartdate(c.getCompetitionOrganization().getStartdate());
		competitionParticipationDto.setEnddate(c.getCompetitionOrganization().getEnddate());
		competitionParticipationDto.setCompetitionOrganizationId(c.getCompetitionOrganization().getId());
		return competitionParticipationDto;
	}

	public static PhasisDto toPhasisDto(PhasisBO phasisBo) {
		PhasisDto phasisDto = new PhasisDto();
		phasisDto.setId(phasisBo.getId());
		phasisDto.setName(phasisBo.getName());
		phasisDto.setCompetitionId(phasisBo.getCompetition().getId());
		phasisDto.setCompetitionName(phasisBo.getCompetition().getName());
		return phasisDto;
	}

	public static PhasisOrganizationDto toPhasisOrganizationDto(PhasisOrganizationBO phasisOrganizationBo) {
		PhasisOrganizationDto phasisOrganizationDto = new PhasisOrganizationDto();
		phasisOrganizationDto.setId(phasisOrganizationBo.getId());
		phasisOrganizationDto.setPhaseId(phasisOrganizationBo.getPhasis().getId());
		phasisOrganizationDto.setPhaseName(phasisOrganizationBo.getPhasis().getName());
		phasisOrganizationDto.setCompetitionName(phasisOrganizationBo.getPhasis().getCompetition().getName());
		phasisOrganizationDto.setCompetitionId(phasisOrganizationBo.getPhasis().getCompetition().getId());
		phasisOrganizationDto.setStartdate(phasisOrganizationBo.getStartdate());
		phasisOrganizationDto.setEnddate(phasisOrganizationBo.getEnddate());
		return phasisOrganizationDto;
	}

	public static PhasisParticipationDto toPhasisParticipationDto(PhasisParticipationBO phasisParticipationBo) {
		PhasisParticipationDto phasisParticipationDto = new PhasisParticipationDto();
		phasisParticipationDto.setId(phasisParticipationBo.getId());
		phasisParticipationDto.setPhaseId(phasisParticipationBo.getPhasisOrganization().getPhasis().getId());
		phasisParticipationDto.setPhaseName(phasisParticipationBo.getPhasisOrganization().getPhasis().getName());
		phasisParticipationDto.setCompetitionName(phasisParticipationBo.getPhasisOrganization().getPhasis().getCompetition().getName());
		phasisParticipationDto.setCompetitionId(phasisParticipationBo.getPhasisOrganization().getPhasis().getCompetition().getId());
		phasisParticipationDto.setStartdate(phasisParticipationBo.getPhasisOrganization().getStartdate());
		phasisParticipationDto.setEnddate(phasisParticipationBo.getPhasisOrganization().getEnddate());
		phasisParticipationDto.setRosterId(phasisParticipationBo.getRoster().getId());
		phasisParticipationDto.setTeamName(phasisParticipationBo.getRoster().getTeam().getName());
		return phasisParticipationDto;
	}

	public static GameDto toGameDto(GameBO g,boolean withBoxscore) {
		GameDto gameDto = new GameDto();
		gameDto.setId(g.getId());
		gameDto.setArenaName(g.getArena().getName());
		gameDto.setArenaCity(g.getArena().getCity().getName());
		gameDto.setLocalRosterId(g.getLocalRoster().getId());
		gameDto.setLocalScore(g.getLocalscore());
		gameDto.setLocalTeamId(g.getLocalRoster().getTeam().getId());
		gameDto.setLocalTeamName(g.getLocalRoster().getTeam().getName());
		gameDto.setVisitRosterId(g.getVisitorRoster().getId());
		gameDto.setVisitScore(g.getVisitscore());
		gameDto.setVisitTeamId(g.getVisitorRoster().getTeam().getId());
		gameDto.setVisitTeamName(g.getVisitorRoster().getTeam().getName());
		gameDto.setPhaseId(g.getPhasisOrganization().getPhasis().getId());
		gameDto.setPhasisName(g.getPhasisOrganization().getPhasis().getName());
		gameDto.setPhasisOrganizationId(g.getPhasisOrganization().getId());
		gameDto.setGamedate(g.getGamedate());
		gameDto.setCompetitionId(g.getPhasisOrganization().getPhasis().getCompetition().getId());
		gameDto.setCompetitionName(g.getPhasisOrganization().getPhasis().getCompetition().getName());
		
		if (withBoxscore) {
			List<BoxlineDto> localBoxlines = new ArrayList<BoxlineDto>();
			List<BoxlineDto> visitBoxlines = new ArrayList<BoxlineDto>();
			
			for (Iterator iterator = g.getBoxLines().iterator(); iterator.hasNext();) {
				BoxlineBO boxlineBo = (BoxlineBO) iterator.next();
				if(boxlineBo.getRoster().getId() == g.getLocalRoster().getId()) {
					localBoxlines.add(toBoxlineDto(boxlineBo));
				}
				else if(boxlineBo.getRoster().getId() == g.getVisitorRoster().getId()) {
					visitBoxlines.add(toBoxlineDto(boxlineBo));
				}
			}
			
			gameDto.setLocalBoxlines(localBoxlines);
			gameDto.setVisitBoxlines(visitBoxlines);
		}
		
		return gameDto;
	}
	
	public static BoxlineDto toBoxlineDto(BoxlineBO boxlineBo) {
		BoxlineDto boxlineDto = new BoxlineDto();
		boxlineDto.setId(boxlineBo.getId());
		boxlineDto.setFirstname(boxlineBo.getPerson().getFirstname());
		boxlineDto.setLastname(boxlineBo.getPerson().getLastname());
		boxlineDto.setPoints(boxlineBo.getPoints());
		boxlineDto.setTeamname(boxlineBo.getRoster().getTeam().getName());
		return boxlineDto;
	}

	public static TeamListDto toTeamListDto(List<TeamBO> teamBOs) {
		List<TeamDto> teamDTOs = new ArrayList<TeamDto>();
		List<CountryDto> countries = new ArrayList<CountryDto>();
		
		for (Iterator iterator = teamBOs.iterator(); iterator.hasNext();) {
			TeamBO teamBO = (TeamBO) iterator.next();
			teamDTOs.add(ObjectMapper.toTeamDto(teamBO));
			
			CountryDto countryDto = toCountryDto(teamBO.getCity1().getCountry());
			System.out.println("countryDto = "+countryDto+" for team : "+teamBO.getName());
			boolean contains = countries.contains(countryDto);
			if(!contains) {
				countries.add(countryDto);
			}
		}

		TeamListDto teamListDto = new TeamListDto();
		teamListDto.setTeams(teamDTOs);
		teamListDto.setCountries(countries);
		teamListDto.setNbTeams(teamDTOs.size());
		teamListDto.setDifferentCountries(countries.size());
		return teamListDto;
	}


}
