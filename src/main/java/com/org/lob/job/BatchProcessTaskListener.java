package com.org.lob.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.task.listener.annotation.AfterTask;
import org.springframework.cloud.task.listener.annotation.BeforeTask;
import org.springframework.cloud.task.listener.annotation.FailedTask;
import org.springframework.cloud.task.repository.TaskExecution;


public class BatchProcessTaskListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BatchProcessTaskListener.class);

	@BeforeTask
    public void beforeTask(TaskExecution taskExecution) {
		LOGGER.debug("Starting Job `{}`", taskExecution.getTaskName());
    }

    @AfterTask
    public void afterTask(TaskExecution taskExecution) {
    	LOGGER.debug("Finished Job `{}`", taskExecution.getTaskName());
    }

    @FailedTask
    public void failedTask(TaskExecution taskExecution, Throwable throwable) {
    	LOGGER.error("Error for job `{}`", taskExecution.getTaskName(), throwable);
    }
}
