package com.greenfutureinnovations.rest.webservices.restfulwebservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greenfutureinnovations.rest.webservices.restfulwebservices.model.Sliders;

@Repository
public interface SlidersRepository extends JpaRepository<Sliders, Long>{
	  @Query("SELECT u FROM Sliders u WHERE u.status = 1")
	   List<Sliders> getActiveSliders();
	  
	  Sliders findTopByOrderByIdDesc();
}
