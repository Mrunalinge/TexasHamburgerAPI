package com.texas.entity;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name="locations")
@Data
//@Accessors(fluent=true, chain=true)
public class Locations implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="city")
	private String city;
	
	@Column(name="street_address")
	private String streetAddress;
	
	@Column(name="zip_code")
	private String zipCode;
	
	@Column(name="start_time")
	private Time startTime;

	
	@Column(name="end_time")
	private Time endTime;
	
	
	//assuming that one order will be from one location always. One order cannot come from multiple places
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = "order_id")
	@JsonBackReference
	private CustomerOrder order;

	
	@OneToOne(mappedBy = "locations", fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = "menu_id")
	//@JsonBackReference
	private Menu menu;
	
    public Locations() {
    	
    }
	
	

}
