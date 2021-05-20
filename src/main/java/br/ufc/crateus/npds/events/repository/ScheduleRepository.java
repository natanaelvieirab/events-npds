package br.ufc.crateus.npds.events.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufc.crateus.npds.events.models.Event;
import br.ufc.crateus.npds.events.models.Schedule;

@Repository
public interface ScheduleRepository  extends CrudRepository<Schedule, Integer>{


	List<Schedule> findByEvent(Event event, Pageable pageable);
	
	
}
