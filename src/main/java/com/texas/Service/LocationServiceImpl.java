package com.texas.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texas.dto.LocationsDTO;
import com.texas.entity.Locations;
import com.texas.repository.LocationRepo;
@Service
public class LocationServiceImpl implements LocationService {
	
	
	public static final Logger log = LoggerFactory.getLogger(LocationServiceImpl.class);

	
	LocationRepo loc;
	@Autowired
	public LocationServiceImpl(LocationRepo loc) {
		// TODO Auto-generated constructor stub
		this.loc=loc;
	}
	
	
	@Override
	public LocationsDTO addLocation(LocationsDTO location) {
		// TODO Auto-generated method stub
		
		log.info("Adding new location"+location);
		
		Locations locate = new Locations();		
		
//		locate.city(location.city()).zipCode(location.zipCode()).endTime(location.endTime()).
//		startTime(location.startTime()).streetAddress(location.streetAddress());
		
		BeanUtils.copyProperties(location, locate);
		log.info("location before saving! "+locate);
		
		Locations location1 = new Locations();
	
	    
		location1= loc.save(locate);
		
		log.info("location after saving! "+location1);
		
		BeanUtils.copyProperties(location1, location);
		

		
		return location;
	}
	
	
	

}
