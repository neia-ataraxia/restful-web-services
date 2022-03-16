package com.greenfutureinnovations.rest.webservices.restfulwebservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greenfutureinnovations.rest.webservices.restfulwebservices.model.Careers;


@Repository
public interface CareersRepository extends JpaRepository<Careers, Long> {
	  @Query("SELECT u FROM Careers u WHERE u.status = 1")
	   List<Careers> getActiveCareers();
}
