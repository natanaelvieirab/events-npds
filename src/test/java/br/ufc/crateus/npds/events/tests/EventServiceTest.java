package br.ufc.crateus.npds.events.tests;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.ufc.crateus.npds.events.exception.InvalidEndDateException;
import br.ufc.crateus.npds.events.models.Event;
import br.ufc.crateus.npds.events.repository.EventRepository;
import br.ufc.crateus.npds.events.service.EventService;
import br.ufc.crateus.npds.events.tests.util.EventCreator;

@ExtendWith(SpringExtension.class)
public class EventServiceTest {

	@InjectMocks
	private EventService eventService;
	
	@Mock
	private EventRepository eventRepositoryMock;
	
	@BeforeEach
	void setUp() {
		List<Event> eventPage = List.of(EventCreator.createEventToBeSaved());
		BDDMockito.when(eventRepositoryMock.findAll(ArgumentMatchers.any()))
				.thenReturn(eventPage);
		
		BDDMockito.when(eventRepositoryMock.save(ArgumentMatchers.any()))
				.thenReturn(EventCreator.createValidEvent());
	}
	
	private static int PAGE_SIZE = 1;
	private static int PAGE_NUMBER = 0;
	
	@Test
	@DisplayName("Should be able to list Events")
	public void getAllEventsTest() {
		
		String expectedName = EventCreator.createValidEvent().getName();
		
		List<Event> listEvents = eventService.getAll(PAGE_NUMBER,PAGE_SIZE);
		
		//import org.assertj.core.api.Assertions
		Assertions.assertThat(listEvents).isNotNull();
		
		Assertions.assertThat(listEvents).isNotEmpty();
		
		Assertions.assertThat(listEvents.get(0).getName()).isEqualTo(expectedName);
		
	}
	
	@Test
	@DisplayName("Should be able to create Event")
	public void insertEvent() throws InvalidEndDateException {
		
		Integer expectedId = EventCreator.createValidEvent().getId();
		Event event = eventService.insert(EventCreator.createEventToBeSaved());
		
		Assertions.assertThat(event).isNotNull();
		
		Assertions.assertThat(event.getId()).isEqualTo(expectedId);

	}
	
	@Test
	@DisplayName("Should not be able to create Event if date for invalid")
	public void doNotInsertEventWithInvalidDate() throws InvalidEndDateException {
		
		Event eventCreator = EventCreator.createEventToBeSaved();
		
		String beginDate = "16/06/2021";
		String endDate = "10/06/2021";
		
		eventCreator.setBeginDate(EventCreator.creatorDate(beginDate));
		eventCreator.setEndDate(EventCreator.creatorDate(endDate));
		
		eventService.insert(eventCreator);
	}
	
	
}
