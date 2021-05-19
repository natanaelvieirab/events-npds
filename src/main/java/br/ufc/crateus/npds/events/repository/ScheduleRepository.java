package br.ufc.crateus.npds.events.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufc.crateus.npds.events.models.Schedule;

@Repository
public interface ScheduleRepository  extends CrudRepository<Schedule, Integer>{

}
