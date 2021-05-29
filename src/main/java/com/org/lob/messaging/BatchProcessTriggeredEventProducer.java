package com.org.lob.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.lob.messaging.model.BatchProcessTriggeredEvent;

@Component
public class BatchProcessTriggeredEventProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(BatchProcessTriggeredEventProducer.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${rabbitmq.batch_process.dg.exchange}")
	private String exchangeName;

	@Value("${rabbitmq.batch_process.triggered.routingkey}")
	private String routingKey;

	@Autowired
	private ObjectMapper objectMapper;

	public void sendMessage(BatchProcessTriggeredEvent event) {
		try {
			String jsonMessage = objectMapper.writeValueAsString(event);
			rabbitTemplate.convertAndSend(exchangeName, routingKey, jsonMessage);
			LOGGER.debug("Sent message for {} ", routingKey);
		} catch (Exception e) {
			LOGGER.error("Unable to send Message ", e);
		}
	}
}
