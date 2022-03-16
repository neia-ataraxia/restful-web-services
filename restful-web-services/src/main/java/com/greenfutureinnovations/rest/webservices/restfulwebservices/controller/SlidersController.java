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

import com.greenfutureinnovations.rest.webservices.restfulwebservices.model.Sliders;
import com.greenfutureinnovations.rest.webservices.restfulwebservices.repository.SlidersRepository;

@RestController
@CrossOrigin(origins = "*")
public class SlidersController {

	@Autowired
	private SlidersRepository slidersRepository;

	@Autowired
	private UsersController user;

	@GetMapping("/sliders")
	public List<Sliders> retrieveAllSliders() {
		return slidersRepository.getActiveSliders();
	}

	@GetMapping("/sliders/{id}")
	public Sliders getSlider(@PathVariable long id) {
		return slidersRepository.findById(id).get();
	}

	@PutMapping("/sliders/{id}")
	public ResponseEntity<Sliders> updateSliders(@PathVariable long id, @RequestBody Sliders slider) {
		slider.setEditor_id(user.loadUserById());
		slider.setEdit_date(new Date());
		slidersRepository.save(slider);
		return new ResponseEntity<Sliders>(slider, HttpStatus.OK);
	}

	@PostMapping("/sliders")
	public ResponseEntity<Void> createSliders(@RequestBody Sliders slider) {
		slider.setEncoder_id(user.loadUserById());
		Sliders createSliders = slidersRepository.save(slider);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createSliders.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

}
