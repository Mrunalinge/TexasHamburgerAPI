package com.texas.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(fluent=true, chain=true)
public class UserDTO implements Serializable {
	
    private long id;
	@JsonProperty(value="firstName")
	private String firstName;	
	@JsonProperty(value="lastName")
	private String lastName;
	@JsonProperty(value="contactNumber")
	private String contactNumber;
	@JsonProperty(value="userName")
	private String userName;
	@JsonProperty(value="password")
	private String password;
	
	private CustomerOrderDTO order;
	

}
