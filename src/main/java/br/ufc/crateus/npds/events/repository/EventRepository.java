package br.ufc.crateus.npds.events.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufc.crateus.npds.events.models.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer>{
	
	List<Event> findByNameContainingIgnoreCase(String name);

}
