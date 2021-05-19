package br.ufc.crateus.npds.events.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.crateus.npds.events.exception.InvalidEndDateException;
import br.ufc.crateus.npds.events.exception.RecordNotFoundException;
import br.ufc.crateus.npds.events.models.Event;
import br.ufc.crateus.npds.events.repository.EventRepository;

@Service
public class EventService {
	@Autowired
	private EventRepository eventRepository;
	
	
	public List<Event> getAll(){
		
		List<Event> events = (List<Event>) eventRepository.findAll();
		return events;
	}
	
	public Event insert(Event event) throws InvalidEndDateException{
		
		if(event.getEndDate().before(event.getBeginDate())) {
			throw new InvalidEndDateException();
		}
		
		return eventRepository.save(event);
	}
	
	
	public Event getById(Integer id) throws RecordNotFoundException {
		Optional<Event> event = eventRepository.findById(id);
		
		if(event.isPresent()) return event.get();
		
		else throw new RecordNotFoundException();
	}

	public List<Event> getByName(String name){
		List<Event> events = eventRepository.findByNameContainingIgnoreCase(name);
		return events;
	}
}
