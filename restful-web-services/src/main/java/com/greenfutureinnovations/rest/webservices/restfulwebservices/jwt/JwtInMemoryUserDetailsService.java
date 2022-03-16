package com.greenfutureinnovations.rest.webservices.restfulwebservices.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greenfutureinnovations.rest.webservices.restfulwebservices.model.Users;
import com.greenfutureinnovations.rest.webservices.restfulwebservices.repository.UsersRepository;



@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository loginRepository;
	
	private List<JwtUserDetails> inMemoryUserList = new ArrayList<>();
	
	public List<Users> retrieveUserCredentials(){
	
		return loginRepository.getActiveUsers();
	}
	public List<JwtUserDetails> userList(){
		List<Users> user = retrieveUserCredentials();
		inMemoryUserList.clear();
		for (int i = 0; i < user.size();i++) {
			inMemoryUserList.add(new JwtUserDetails(user.get(i).getId(),user.get(i).getUsername(),user.get(i).getPassword(),"ROLE_USER"));
		}		
		return inMemoryUserList;
	}
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<JwtUserDetails> findFirst = userList().stream()
        .filter(user -> user.getUsername().equals(username)).findFirst();
    if (!findFirst.isPresent()) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }    
    return findFirst.get();
  } 
}


