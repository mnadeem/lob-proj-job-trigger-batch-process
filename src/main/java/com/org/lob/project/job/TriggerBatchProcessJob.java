package com.org.lob.project.job;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.org.lob.project.service.BatchProcessService;

@Component
public class TriggerBatchProcessJob implements CommandLineRunner {

	private BatchProcessService batchProcessService;

	public TriggerBatchProcessJob(BatchProcessService batchProcessService) {
		super();
		this.batchProcessService = batchProcessService;
	}

	@Override
	public void run(String... args) throws Exception {
		batchProcessService.process();
	}
}
