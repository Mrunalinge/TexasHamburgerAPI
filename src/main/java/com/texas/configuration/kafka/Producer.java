package com.texas.configuration.kafka;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.fasterxml.jackson.databind.JsonNode;

@Configuration
public class Producer {

	@Value("${spring.kafka.producer.bootstrap-servers}")
	private String bootstrapServers;
	
	@Value("${spring.kafka.producer.client-id}")
	private String clientId;
	
	@Value("${spring.kafka.producer.key-serializer}")
	private String keySerializer;
	
	@Value("${spring.kafka.producer.value-serializer}")
	private String valueSerializer;
	
	@Value("${spring.kafka.producer.acks}")
	private String acks;
	
	@Value("${spring.kafka.producer.retries}")
	private String retries;
	
	@Value("${spring.kafka.producer.batch-size}")
	private String batchSize;
	
	@Value("${spring.kafka.producer.buffer-memory}")
	private String buffer;
	
	@Value("{spring.kafka.producer.linger-ms}")   
	private String linger;
	
    @Value("{spring.kafka.producer.enable-idempotence}")	
	private String idempotence;
	
	@Value("{spring.kafka.producer.transaction-id}")
	private String transctionId;
	
	
	@Bean
	public ProducerFactory<String, JsonNode> producerFactory() throws UnknownHostException{
		
		Map<String, Object> properties = new HashMap<String, Object>();
		
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
		properties.put(ProducerConfig.ACKS_CONFIG, acks);
		properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, buffer);
		properties.put(ProducerConfig.LINGER_MS_CONFIG, linger);
		properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, idempotence);
		properties.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
		properties.put(ProducerConfig.RETRIES_CONFIG, retries);
		properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, transctionId);
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
		
		DefaultKafkaProducerFactory<String, JsonNode> producerFactory = new DefaultKafkaProducerFactory<>(properties);
		
		if(transctionId!=null) {
			
			producerFactory.setTransactionIdPrefix(transctionId+"_"+ InetAddress.getLocalHost().getHostName());
			producerFactory.setProducerPerConsumerPartition(false);
			
		}
		
		return producerFactory;
	}
   @Bean
   public KafkaTemplate<String, JsonNode> customerOrderKafkaTemplate() throws UnknownHostException{
		
		return new KafkaTemplate<>(producerFactory());
	}
	
}
