package br.ufc.crateus.npds.events.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.crateus.npds.events.controller.dto.EventDTO;
import br.ufc.crateus.npds.events.exception.InvalidEndDateException;
import br.ufc.crateus.npds.events.exception.RecordNotFoundException;
import br.ufc.crateus.npds.events.models.Event;
import br.ufc.crateus.npds.events.models.Schedule;
import br.ufc.crateus.npds.events.service.EventService;
import br.ufc.crateus.npds.events.service.ScheduleService;


@RestController
@RequestMapping("/api/events")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private ScheduleService scheduleService;
	
	@GetMapping
	private ResponseEntity<List<EventDTO>> getAll(@RequestParam(required = false) String name,
			@RequestParam(required = false) int pageNumber,
			@RequestParam(required = false) int pageSize){
		
		List<Event> events = null;
		if(name == null) {
			events = eventService.getAll(pageNumber, pageSize);
		}
		else {
			events = eventService.getByName(name, pageNumber, pageSize);
		}
		
		return new ResponseEntity<>(EventDTO.toDTOList(events), HttpStatus.OK);
		//EventDTO.toDTOList(
	}

	@PostMapping
	private ResponseEntity<EventDTO> insert(@RequestBody Event event) throws InvalidEndDateException{
		Event createdEvent = eventService.insert(event);
		
        return new ResponseEntity<>(EventDTO.toDTO(createdEvent), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{id}")
	private ResponseEntity<EventDTO> getId(@PathVariable Integer id) throws RecordNotFoundException{
		
		Event event = eventService.getById(id);
		
		return new ResponseEntity<>(EventDTO.toDTO(event), HttpStatus.OK);
	}
	
	@PostMapping("/{id}/schedule")
	private ResponseEntity<Schedule> insertSchedule(@RequestBody Schedule schedule, @PathVariable("id") Integer eventId) throws RecordNotFoundException{
		
		Schedule scheduleCreated = scheduleService.insert(schedule,eventId);
		
		return new ResponseEntity<>(scheduleCreated,HttpStatus.OK);
	}
	
	@GetMapping("/{id}/schedule")
	private ResponseEntity<List<Schedule>> getAllScheduleByEvent(
				@PathVariable("id") Integer eventId,
				@RequestParam Integer pageNumber,
				@RequestParam Integer pageSize) throws RecordNotFoundException{
		
		List<Schedule> schedules =  scheduleService.getAllScheduleByEvent(eventId, pageNumber, pageSize);
		
		return new ResponseEntity<>(schedules,HttpStatus.OK);
	}
	
}

