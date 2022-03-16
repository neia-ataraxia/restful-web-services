package com.greenfutureinnovations.rest.webservices.restfulwebservices.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.greenfutureinnovations.rest.webservices.restfulwebservices.jwt.JwtInMemoryUserDetailsService;
import com.greenfutureinnovations.rest.webservices.restfulwebservices.jwt.JwtUserDetails;
import com.greenfutureinnovations.rest.webservices.restfulwebservices.model.Users;
import com.greenfutureinnovations.rest.webservices.restfulwebservices.repository.UsersRepository;

@RestController
@CrossOrigin(origins = "*")
public class UsersController {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtInMemoryUserDetailsService jwtInMemoryUserDetailsService;

	@GetMapping("/users")
	public List<Users> retrieveAllUsers() {
		return usersRepository.getUsers();
	}

	@GetMapping("/users/{id}")
	public Users getUser(@PathVariable long id) {
		return usersRepository.findById(id).get();
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<Users> updateUser(@PathVariable long id, @RequestBody Users user) {
		user.setEdit_date(new Date());
		user.setEditor_id(getLoggedInUser().getId());
		if (user.getPassword().compareTo(usersRepository.findById(id).get().getPassword()) == 0) {
			usersRepository.save(user);
		} else {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			usersRepository.save(user);
		}

		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}

	@PostMapping("/users")
	public ResponseEntity<Void> createUser(@RequestBody Users user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEncoder_id(getLoggedInUser().getId());
		Users createUser = usersRepository.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createUser.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping(value = { "/*", "/**" })
	public Users getLoggedInUser() {
		return usersRepository.findById(loadUserById()).get();
	}

	public Long loadUserById() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Optional<JwtUserDetails> findFirst = jwtInMemoryUserDetailsService.userList().stream()
				.filter(user -> user.getUsername().equals(userDetails.getUsername())).findFirst();
		return findFirst.get().getId();
	}

}
