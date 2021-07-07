package com.texas.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(fluent=true, chain=true)
public class BillingDTO implements Serializable {
	
	private long id;
	@JsonProperty(value="subTotal")
	private double subTotal;
	@JsonProperty(value="tax")
	private double tax;
	@JsonProperty(value="total")
	private double total;

	private CustomerOrderDTO order;

}
