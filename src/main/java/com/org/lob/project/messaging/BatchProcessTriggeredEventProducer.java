package com.org.lob.project.messaging;

import com.org.lob.project.messaging.model.BatchProcessEvent;

public interface BatchProcessTriggeredEventProducer {
	
	void sendMessage(BatchProcessEvent event);
}
