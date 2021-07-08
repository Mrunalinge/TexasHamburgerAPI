package com.texas.configuration.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;

import com.fasterxml.jackson.databind.JsonNode;

@Configuration
@EnableKafka
public class Consumer {

	@Value("${spring.kafka.consumer.bootstrap-servers}")
	private String bootstrapServers;
	
	@Value("${spring.kafka.consumer.client-id}")
	private String clientId;
	
	@Value("${spring.kafka.consumer.key-deserializer}")
	private String keyDeserializer;
	
	@Value("${spring.kafka.consumer.value-deserializer}")
	private String valueDeserializer;
	
	@Value("${spring.kafka.consumer.auto-offset-reset}")
	private String autoOffsetReset;
	
	@Value("${spring.kafka.consumer.isolation-level}")
	private String isolationLevel;
	
	@Value("${spring.kafka.consumer.heartbeat-interval}")
	private String heartbeatInterval;
	
	@Bean
	public DefaultKafkaConsumerFactory< String, JsonNode> consumerFactory(){
		Map<String, Object> properties = new HashMap<String, Object>();
		
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.put(ConsumerConfig.CLIENT_ID_CONFIG, clientId);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
		properties.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, isolationLevel);
		properties.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, heartbeatInterval);
		
		return new DefaultKafkaConsumerFactory<>(properties, new StringDeserializer(), new JsonDeserializer<>(JsonNode.class));
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, JsonNode> kafkaListnerContainerFactory(){
		
		ConcurrentKafkaListenerContainerFactory<String, JsonNode> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
		factory.getContainerProperties().setSyncCommits(true);
		factory.setErrorHandler(new SeekToCurrentErrorHandler());
		
		return factory;
		
	}

}
