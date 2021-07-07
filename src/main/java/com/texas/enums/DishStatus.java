package com.texas.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DishStatus {

	@JsonProperty("available")
	AVAILABLE,
	@JsonProperty("unavailable")
	UNAVAILABLE
}
