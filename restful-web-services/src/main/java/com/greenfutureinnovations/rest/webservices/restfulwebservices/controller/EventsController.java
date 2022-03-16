package com.greenfutureinnovations.rest.webservices.restfulwebservices.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.greenfutureinnovations.rest.webservices.restfulwebservices.model.Events;
import com.greenfutureinnovations.rest.webservices.restfulwebservices.repository.EventsRepository;

@RestController
@CrossOrigin(origins = "*")
public class EventsController {

	@Autowired
	private EventsRepository eventsRepository;
	
	@Autowired
	private UsersController user;
	
	@GetMapping("/events")
	public List<Events> retrieveAllEvents() {
		return eventsRepository.getActiveEvents();
	}

	@GetMapping("/events/{id}")
	public Events getEvents(@PathVariable long id) {
		return eventsRepository.findById(id).get();
	}

	@PutMapping("/events/{id}")
	public ResponseEntity<Events> updateEvents(@PathVariable long id, @RequestBody Events event) {
		event.setEditor_id(user.loadUserById());
		event.setEdit_date(new Date());
		eventsRepository.save(event);
		return new ResponseEntity<Events>(event, HttpStatus.OK);
	}

	@PostMapping("/events")
	public ResponseEntity<Void> createEvents(@RequestBody Events event) {
		event.setEncoder_id(user.loadUserById());
		Events createEvents = eventsRepository.save(event);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createEvents.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
}
