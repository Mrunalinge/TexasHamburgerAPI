package com.texas.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.texas.enums.DishStatus;

import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name="dish")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
//@Accessors(fluent=true, chain=true)
public class Dish implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="dish_name")
	private String dishName;
	
	@Column(name="ingredients")
	private String ingredients;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="price")
	private int price;
		
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private DishStatus status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = "order_id")
	@JsonBackReference
	private CustomerOrder order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = "menu_id")
	//@JsonBackReference
   	private Menu menu;
//	@OneToMany(fetch = FetchType.LAZY)
//	@JoinColumn(columnDefinition = "order_id")
//	@JsonBackReference
//	private List<Locations> location;
}
