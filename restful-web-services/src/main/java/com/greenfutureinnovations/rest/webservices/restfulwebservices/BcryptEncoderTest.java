package com.greenfutureinnovations.rest.webservices.restfulwebservices;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptEncoderTest {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
		
		for(int i=1; i<=10; i++) {
			String encodedString = encoder.encode("smitherins");
			System.out.println(encodedString);
		}
		// TODO Auto-generated method stub

	}

}
