package com.texas.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name="reservations")
@Data
//@Accessors(fluent=true, chain=true)
public class Reservations implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@OneToOne(fetch = FetchType.LAZY)
	private Users user;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Locations location;
	
	private int tableNumber;
	private Timestamp time;
	
	

}
