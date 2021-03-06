package br.ufc.crateus.npds.events.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufc.crateus.npds.events.models.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer>{
	
	List<Event> findByNameContainingIgnoreCase(String name);
	List<Event> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrLocalizationContainingIgnoreCaseOrOrganizerContainingIgnoreCase(String name, String description,String organizer,String localization ,Pageable pageable);
	
	List<Event> findAll(Pageable pageable);
}
