package com.greenfutureinnovations.rest.webservices.restfulwebservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greenfutureinnovations.rest.webservices.restfulwebservices.model.Users;


@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
	  @Query("SELECT u FROM Users u WHERE u.status = 1 AND u.deleted = 0")
	   List<Users> getActiveUsers();
	  
	  @Query("SELECT u FROM Users u WHERE u.deleted = 0")
	  List<Users> getUsers();
}