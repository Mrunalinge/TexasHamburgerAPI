package com.texas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.texas.entity.Locations;

public interface LocationRepo extends JpaRepository<Locations, Long> {
	


	List<Locations> findAll();
	Locations findLocationsByzipCode(String zipCode);
	
}
