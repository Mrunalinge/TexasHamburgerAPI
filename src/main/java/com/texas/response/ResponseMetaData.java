package com.texas.response;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "from")
@AllArgsConstructor
@Builder
public class ResponseMetaData {

	int statusCode;
	
	@NotNull
	String message;
	
	@JsonInclude(NON_EMPTY)
	Map<String, Object> tags;
	
	
	
	
	
	
}
