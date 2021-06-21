package br.ufc.crateus.npds.events.tests.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.ufc.crateus.npds.events.models.Event;

public class EventCreator {

	// Salvando um novo Event
	public static Event createEventToBeSaved() {

		return newEvent();
	}

	private static Event newEvent() {

		Event event = new Event();

		event.setName("Teste");
		event.setDescription("Description Test");
		event.setOrganizer("Organizer Teste");
		event.setLocalization("Localization teste");

		event.setBeginDate(creatorDate("10/06/2021"));
		event.setEndDate(creatorDate("16/06/2021"));

		return event;
	}

	public static Date creatorDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		Date dateFormated = null;

		try {
	
			dateFormated =  format.parse(date);
					
		} catch (ParseException e) {

			e.printStackTrace();
		}
		 
		return dateFormated;
	}

	// Event Valido, ou seja, possui um id
	public static Event createValidEvent() {

		Event event = newEvent();

		event.setId(1);

		return event;
	}

	// Event que teve seus dados atualizados
	public static Event createValidUpdateEvent() {
		Event event = newEvent();

		event.setId(1);
		event.setName("name updated");

		return event;
	}
}
