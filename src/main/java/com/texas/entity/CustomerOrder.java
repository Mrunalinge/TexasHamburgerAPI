package com.texas.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.texas.enums.OrderStatus;

import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name="customer_order")
@Data
//@Accessors(fluent=true, chain=true)
public class CustomerOrder implements Serializable {
	

	@Id
	@GeneratedValue(strategy  = GenerationType.AUTO)
	private long id;
	
	@Column(name ="table_number")
	private int tableNumber;
	
	@OneToMany(mappedBy = "order", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<Dish> dishes;
	
	@OneToOne(mappedBy = "order", cascade = {CascadeType.ALL} , fetch = FetchType.LAZY)
	@JsonManagedReference
	private Users user;
	
	@Column(name="order_status")
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	@OneToOne(mappedBy = "order", cascade = {CascadeType.ALL} , fetch = FetchType.LAZY)
	@JsonManagedReference
	private Locations location;
	
	@Column(name="reviews")
	private String reviews;
	
	
	@OneToOne(mappedBy = "order", cascade = {CascadeType.ALL} , fetch = FetchType.LAZY)
	@JsonManagedReference
	private Billing bill;
	
	@Column(name="created_at")
	@CreationTimestamp
	private Timestamp createdAt;
	
	
}
