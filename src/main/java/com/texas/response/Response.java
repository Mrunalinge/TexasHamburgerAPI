package com.texas.response;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "from")
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Response<T> {
	
	@JsonProperty("meta")
	@NonNull
	ResponseMetaData responseMeta;

	@JsonProperty("data")
	@JsonInclude(NON_NULL)
	T data;
	
}
