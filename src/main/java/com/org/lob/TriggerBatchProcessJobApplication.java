package com.org.lob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;

@SpringBootApplication
@EnableTask
public class TriggerBatchProcessJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(TriggerBatchProcessJobApplication.class, args);
	}

}
