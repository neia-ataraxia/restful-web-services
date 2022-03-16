package com.greenfutureinnovations.rest.webservices.restfulwebservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greenfutureinnovations.rest.webservices.restfulwebservices.model.Images;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Long>{
	@Query(value="SELECT u FROM Images u WHERE u.type = 1")
	List<Images> ImagesForSliders();
	
	@Query(value="SELECT u FROM Images u WHERE u.type = 2")
	List<Images> ImagesForEvents();
}
