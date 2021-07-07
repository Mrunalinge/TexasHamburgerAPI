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
import lombok.experimental.Accessors;

@Entity
@Table(name="billing")
@Data
@Accessors(fluent=true, chain=true)
public class Billing implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="sub_total")
	private double subTotal;

	@Column(name="tax")
	private double tax;
	
	@Column(name="total")
	private double total;
	

	
	//assuming that one order will have one bill always.
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = "order_id")
	@JsonBackReference
	private CustomerOrder order;
}
