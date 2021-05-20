package br.ufc.crateus.npds.events.controller.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ufc.crateus.npds.events.models.Event;

public class EventDTO {

	private Integer id;
	
	private String name;
	
	private String description;
	
	private String localization ;

	private Date beginDate ;
		
	private Date endDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocalization() {
		return localization;
	}

	public void setLocalization(String localization) {
		this.localization = localization;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public static EventDTO toDTO(Event event) {
		EventDTO eventDTO = new EventDTO();
		
		eventDTO.setId(event.getId());
		eventDTO.setName(event.getName());
		eventDTO.setDescription(event.getDescription());
		eventDTO.setLocalization(event.getLocalization());
		eventDTO.setBeginDate(event.getBeginDate());
		eventDTO.setEndDate(event.getEndDate());
		
		return eventDTO;
	}
	
	public static List<EventDTO> toDTOList(List<Event> events) {
		List<EventDTO> eventDTOs = new ArrayList<>();
		
		for (Event event: events) {
			eventDTOs.add(toDTO(event));
		}
		
		return eventDTOs;
	}
	
	
}
