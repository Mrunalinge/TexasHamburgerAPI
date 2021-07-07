package com.texas.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.texas.entity.Locations;
import com.texas.enums.DishStatus;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
//@Accessors(fluent=true, chain=true)
public class DishDTO implements Serializable {

	
	private long id;
	@JsonProperty(value="dishName")
	private String dishName;
	@JsonProperty(value="ingredients")
	private String ingredients;
	@JsonProperty(value="quantity")
	private int quantity;
	@JsonProperty(value="price")
	private int price;
		
	private DishStatus status;
	
	private CustomerOrderDTO order;
	
	private MenuDTO menu;
	
	private List<Locations> location;

}

