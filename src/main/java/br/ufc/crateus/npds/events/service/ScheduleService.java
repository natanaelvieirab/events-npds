package br.ufc.crateus.npds.events.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.ufc.crateus.npds.events.exception.InvalidDateException;
import br.ufc.crateus.npds.events.exception.InvalidEndDateException;
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
    private EventService eventService;

	public Schedule insert(Schedule schedule, Integer eventId) throws RecordNotFoundException,  InvalidEndDateException, InvalidDateException {
        Event event = eventService.getById(eventId);
        
        schedule.setEvent(event);
        
        if(schedule.getEndDate().before(schedule.getBeginDate())){
            throw new InvalidEndDateException();
        }
        
        if((schedule.getBeginDate().after(event.getEndDate()) || schedule.getBeginDate().before(event.getBeginDate())) || (schedule.getEndDate().after(event.getEndDate()) || schedule.getEndDate().before(event.getBeginDate()))){
            throw new InvalidDateException();
        }
        
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getByEvent(Integer eventId, int pageNumber, int pageSize) throws RecordNotFoundException {
        Event event = eventService.getById(eventId);

        return scheduleRepository.findByEvent(event, PageRequest.of(pageNumber, pageSize));
    }


	
}
