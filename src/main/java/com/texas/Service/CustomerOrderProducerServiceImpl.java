package com.texas.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.texas.dto.CustomerOrderDTO;
import com.texas.repository.CustomerOrderRepository;

@Service
public class CustomerOrderProducerServiceImpl  {

	@Autowired
	private  KafkaTemplate<String, JsonNode> customerOrderKafkaTemplate;
	
	@Autowired
	private CustomerOrderRepository customerOrderRepository;
	
	@Value("${kafka.topic.name.orders}")
	private String kafkaTopicName;

	public static final Logger log = LoggerFactory.getLogger(CustomerOrderProducerServiceImpl.class);
	
	public boolean placeOrder(CustomerOrderDTO orderDTO) {
		log.info("Create order Producer");
		
		sendMesaages(kafkaTopicName, Long.toString(orderDTO.getId()), new ObjectMapper().convertValue(orderDTO, JsonNode.class));
		return true;
	}

	
	private void sendMesaages(String topic, String key, JsonNode message) {
		
		log.info("In Kafka producer OrderServiceImpl sending message");
		customerOrderKafkaTemplate.executeInTransaction(transaction ->  {
			
			ListenableFuture<SendResult<String,JsonNode>> future = transaction.send(topic,key,message);
			
			//to chk for success or not
			
//			future.addCallback(new ListenableFutureCallback() {
//				
//
//	                @Override
//	                public void onFailure(Throwable ex) {
//	                    log.info("Failed to produce message=[ {} ] due to : {}", message, ex.getMessage());
//	                }
//
//					@Override
//					public void onSuccess(Object result) {
//						// TODO Auto-generated method stub
//						
//						
//						
//					}
//			
//				
//			});
			
			return true;
		
			
		});
	}

}
