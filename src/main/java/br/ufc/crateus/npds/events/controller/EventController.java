package br.ufc.crateus.npds.events.controller;

import java.util.ArrayList;
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

import br.ufc.crateus.npds.events.exception.InvalidEndDateException;
import br.ufc.crateus.npds.events.exception.RecordNotFoundException;
import br.ufc.crateus.npds.events.models.Event;
import br.ufc.crateus.npds.events.service.EventService;


@RestController
@RequestMapping("/api/events")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@GetMapping
	private ResponseEntity<List<Event>> getAll(@RequestParam(required = false) String name){
		
		List<Event> events = null;
		if(name == null) {
			events = eventService.getAll();
		}
		else {
			events = eventService.getByName(name);
		}
		
		return new ResponseEntity<>(events, HttpStatus.OK);		
	}

	@PostMapping
	private ResponseEntity<Event> insert(@RequestBody Event event) throws InvalidEndDateException{
		Event createdEvent = eventService.insert(event);
		
        return new ResponseEntity<Event>(createdEvent, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{id}")
	private ResponseEntity<Event> getId(@PathVariable Integer id) throws RecordNotFoundException{
		
		Event event = eventService.getById(id);
		
		return new ResponseEntity<>(event, HttpStatus.OK);
	}
}



