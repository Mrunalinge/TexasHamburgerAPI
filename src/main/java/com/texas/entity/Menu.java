package com.texas.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name="menu")
@Data
//@Accessors(fluent=true, chain=true)
public class Menu implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToMany(mappedBy = "menu", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JsonBackReference(value="dishes")
	private List<Dish> dish;
	
	@OneToOne()
	@JsonBackReference(value="locate")
	@JoinColumn(name="locations_id", referencedColumnName = "id")
	private Locations locations;
}
