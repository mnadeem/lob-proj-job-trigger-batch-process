package com.org.lob.project.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.lob.project.messaging.model.BatchProcessEvent;

@Component
public class DefaultBatchProcessTriggeredEventProducer implements BatchProcessTriggeredEventProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultBatchProcessTriggeredEventProducer.class);

	private final RabbitTemplate rabbitTemplate;
	private final ObjectMapper objectMapper;

	@Value("${rabbitmq.batch-process.dg.exchange}")
	private String exchangeName;

	@Value("${rabbitmq.batch-process.triggered.routingkey}")
	private String routingKey;

	public DefaultBatchProcessTriggeredEventProducer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
		this.rabbitTemplate = rabbitTemplate;
		this.objectMapper = objectMapper;
	}

	@Override
	public void sendMessage(BatchProcessEvent event) {
		try {
			LOGGER.trace("Sending message: {} to {} ", event, routingKey);
			String jsonMessage = objectMapper.writeValueAsString(event);
			rabbitTemplate.convertAndSend(exchangeName, routingKey, jsonMessage);
			LOGGER.debug("Sent message for {} ", routingKey);
		} catch (Exception e) {
			LOGGER.error("Unable to send Message ", e);
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}
}
