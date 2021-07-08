package com.texas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.texas.Service.CustomerOrderProducerServiceImpl;
import com.texas.dto.CustomerOrderDTO;
import com.texas.response.Response;
import com.texas.response.ResponseMetaData;
import com.texas.response.StatusMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@RestController
@RequestMapping("/order")
@Api(tags={"Customer Order"})
@SwaggerDefinition(tags = {@Tag(name="Customer Order", description = "Order Endpoint to create and get orders for Texas Hamburger API")})
public class CustomerOrderController {

	public static final Logger log = LoggerFactory.getLogger(CustomerOrderController.class);
	@Autowired
	private CustomerOrderProducerServiceImpl customerOrderProducerServiceImpl;
	@PostMapping("/placeOrder")
	public Response<String> createOrder(@RequestBody CustomerOrderDTO orderDTO){
		
		   customerOrderProducerServiceImpl.placeOrder(orderDTO);
		   
		  return Response.<String>builder().responseMeta(ResponseMetaData.builder().statusCode(200).message(StatusMessage.SUCCESS.name()).build())
		   .data("Order Placed successfully").build();
		   
		
	}
}
