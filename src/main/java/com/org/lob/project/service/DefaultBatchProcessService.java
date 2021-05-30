package com.org.lob.project.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.org.lob.project.messaging.BatchProcessTriggeredEventProducer;
import com.org.lob.project.messaging.model.BatchProcessEvent;
import com.org.lob.project.repository.SambaFileRepository;

@Service
public class DefaultBatchProcessService implements BatchProcessService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultBatchProcessService.class);

	private BatchProcessTriggeredEventProducer eventProducer;
	private SambaFileRepository sambaFileRepository;

	public DefaultBatchProcessService(BatchProcessTriggeredEventProducer eventProducer, SambaFileRepository sambaFileRepository) {
		this.eventProducer = eventProducer;
		this.sambaFileRepository = sambaFileRepository;
	}

	@Override
	public void process() throws Exception {
		LOGGER.info("Hello, World!");

		sambaFileRepository.execute(((share) -> {
			LocalDateTime lastModifiedDate = getLastModifiedDateTime();
			LocalDateTime now = LocalDateTime.now();
			sambaFileRepository.doForEachModifiedFile(share, lastModifiedDate, (fileInfo) -> {
				eventProducer.sendMessage(new BatchProcessEvent());
			});
			saveLastModifiedDateTime(now);
			
		}));		
	}

	private LocalDateTime getLastModifiedDateTime() {
		// Access some persistent store and get it
		return LocalDateTime.now().minusMinutes(2);
	}

	private void saveLastModifiedDateTime(LocalDateTime now) {
		// TODO Auto-generated method stub		
	}
}
