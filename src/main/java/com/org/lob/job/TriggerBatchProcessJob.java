package com.org.lob.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TriggerBatchProcessJob implements CommandLineRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TriggerBatchProcessJob.class);

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Hello, World!");		
	}
}
