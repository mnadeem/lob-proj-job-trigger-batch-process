package com.org.lob.project.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.org.lob.project.messaging.BatchProcessTriggeredEventProducer;
import com.org.lob.project.messaging.model.BatchProcessTriggeredEvent;

@Component
public class TriggerBatchProcessJob implements CommandLineRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TriggerBatchProcessJob.class);

	private BatchProcessTriggeredEventProducer eventProducer;

	public TriggerBatchProcessJob(BatchProcessTriggeredEventProducer eventProducer) {
		this.eventProducer = eventProducer;
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Hello, World!");
		eventProducer.sendMessage(new BatchProcessTriggeredEvent());
	}
}
