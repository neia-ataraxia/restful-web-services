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

import com.greenfutureinnovations.rest.webservices.restfulwebservices.model.Careers;

import com.greenfutureinnovations.rest.webservices.restfulwebservices.repository.CareersRepository;

@RestController
@CrossOrigin(origins = "*")
public class CareersController {

	@Autowired
	private CareersRepository careersRepository;
	
	@Autowired
	private UsersController user;
	
	@GetMapping("/careers")
	public List<Careers> retrieveAllCareers() {
		return careersRepository.getActiveCareers();
	}

	@GetMapping("/careers/{id}")
	public Careers getCareers(@PathVariable long id) {
		return careersRepository.findById(id).get();
	}

	@PutMapping("/careers/{id}")
	public ResponseEntity<Careers> updateCareers(@PathVariable long id, @RequestBody Careers career) {
		career.setEditor_id(user.loadUserById());
		career.setEdit_date(new Date());
		careersRepository.save(career);
		return new ResponseEntity<Careers>(career, HttpStatus.OK);
	}

	@PostMapping("/careers")
	public ResponseEntity<Void> createCareers(@RequestBody Careers career) {
		career.setEncoder_id(user.loadUserById());
		Careers createCareers = careersRepository.save(career);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createCareers.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

}
