package com.texas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.texas.entity.Users;


public interface UsersRespository extends JpaRepository<Users, Long>{

	//Optional< Users> finByUsersName(String userName);

	Optional<Users> findUsersByUserName(String username);
	 
	  
	
}
