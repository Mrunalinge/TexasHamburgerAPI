package com.texas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.texas.entity.Menu;

public interface MenuRepo extends JpaRepository<Menu, Long>  {
	
	List<Menu> findAll();
	
//	@Query(nativeQuery = true, value="select menu from Menu menu, Locations loc where menu.locations_id = loc.locations_id and"
//			+ " loc.zip=:zip")
////	@Query("SELECT menu FROM MENU WHERE menu.locations_id=location")
//	Menu findMenuByZipCode(@Param("zip") String zip);
//	
	
	@Query(nativeQuery = true, value= "SELECT * FROM Menu where locations_id=:id")
	Menu findMenuByLocationsID(@Param("id")Long  id);
	
    

}
