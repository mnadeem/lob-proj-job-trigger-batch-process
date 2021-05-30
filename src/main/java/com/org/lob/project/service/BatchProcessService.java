package com.org.lob.project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.org.lob.project.messaging.BatchProcessTriggeredEventProducer;
import com.org.lob.project.messaging.model.BatchProcessEvent;

@Service
public class BatchProcessService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BatchProcessService.class);
	
	private BatchProcessTriggeredEventProducer eventProducer;

	public BatchProcessService(BatchProcessTriggeredEventProducer eventProducer) {
		this.eventProducer = eventProducer;
	}

	public void process() {
		LOGGER.info("Hello, World!");
		eventProducer.sendMessage(new BatchProcessEvent());
	}
}
