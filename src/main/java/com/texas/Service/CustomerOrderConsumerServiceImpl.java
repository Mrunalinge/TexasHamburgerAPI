package com.texas.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.texas.dto.CustomerOrderDTO;
import com.texas.exception.CustomerOrderServiceException;

@Service
public class CustomerOrderConsumerServiceImpl {
	
	public static final Logger log = LoggerFactory.getLogger(CustomerOrderConsumerServiceImpl.class);
	@Value("${kafka.topic.name.orders}")
	private String kafkaTopic;

	
	private CustomerOrderService orderService;
	
	public  CustomerOrderConsumerServiceImpl(CustomerOrderService orderService) {
		
		this.orderService= orderService;
		
	}

	
	public void CustomerOrderServiceDataListner(JsonNode jsonNode, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key, 
			@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partiton, @Header(KafkaHeaders.TOPIC) String topic, 
			@Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts) {
		
		log.info("Consuming Message: {} {} {} {}", jsonNode.toString(), key, partiton,ts);
		try {
		if(topic.equals("customerOrder")) {
			orderService.placeOrder(new ObjectMapper().convertValue(jsonNode, CustomerOrderDTO.class));
		}
		}catch(Exception e) {
			throw new CustomerOrderServiceException("Failed to process request", e);
		}
		
	}
	
}
