package com.org.lob.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.org.lob.job.BatchProcessTaskListener;

@Configuration
public class TaskConfig {

	@Bean
	public BatchProcessTaskListener taskListener() {
	    return new BatchProcessTaskListener();
	}

}
