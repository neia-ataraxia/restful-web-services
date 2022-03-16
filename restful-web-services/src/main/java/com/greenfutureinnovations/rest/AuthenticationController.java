package com.greenfutureinnovations.rest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class AuthenticationController {
	
	@GetMapping("/authentication")
	public AuthenticationBean AuthenticationBean() {
		return new AuthenticationBean("You are authenticated");
	}
	
}
