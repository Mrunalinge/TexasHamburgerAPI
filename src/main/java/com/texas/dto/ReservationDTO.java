package com.texas.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.texas.entity.Locations;
import com.texas.entity.Users;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(fluent=true, chain=true)
public class ReservationDTO implements Serializable {
	
	private Users user;
	private Locations location;
	private int tableNumber;
	private Timestamp time;

}
