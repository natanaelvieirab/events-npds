package br.ufc.crateus.npds.events.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.ufc.crateus.npds.events.exception.RecordNotFoundException;
import br.ufc.crateus.npds.events.models.Event;
import br.ufc.crateus.npds.events.models.Schedule;
import br.ufc.crateus.npds.events.repository.EventRepository;
import br.ufc.crateus.npds.events.repository.ScheduleRepository;

@Service
public class ScheduleService {
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	

	@Autowired
	private EventRepository eventRepository;

	public Schedule insert(Schedule schedule, Integer eventId) throws RecordNotFoundException {
		
		Optional<Event> event =  eventRepository.findById(eventId);
		
		if(event.isEmpty()) {
			throw new RecordNotFoundException();
		}
		
		schedule.setEvent(event.get());
		
		Schedule scheduteCreated = scheduleRepository.save(schedule);
		
		return scheduteCreated;
	}

	public List<Schedule> getAllScheduleByEvent(Integer eventId,Integer pageNumber,Integer pageSize) throws RecordNotFoundException {
		Optional<Event> event =  eventRepository.findById(eventId);
		
		if(event.isEmpty()) {
			throw new RecordNotFoundException();
		}		
	
		return scheduleRepository.findByEvent(event.get(),PageRequest.of(pageNumber, pageSize));
	}

	
}
