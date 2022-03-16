package com.greenfutureinnovations.rest.webservices.restfulwebservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.greenfutureinnovations.rest.webservices.restfulwebservices.model.Events;


@Repository
public interface EventsRepository extends JpaRepository<Events, Long> {
	  @Query("SELECT u FROM Events u WHERE u.status = 1")
	   List<Events> getActiveEvents();
	
	   Events findTopByOrderByIdDesc();
}
