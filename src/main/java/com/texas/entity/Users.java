package com.texas.entity;

import java.io.Serializable;

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

@Entity
@Table(name="users")
@Data
//@Accessors(fluent=true, chain=true)
public class Users implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name= "contact_number")
	private String contactNumber;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="roles")
	private String roles;
	
	@Column(name="active")
	private boolean active;

	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = "customer_order_id")
	@JsonBackReference
	private CustomerOrder order;
	

}
