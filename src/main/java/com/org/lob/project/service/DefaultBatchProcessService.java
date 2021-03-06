package com.org.lob.project.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hierynomus.msfscc.fileinformation.FileIdBothDirectoryInformation;
import com.hierynomus.smbj.share.DiskShare;
import com.org.lob.project.messaging.BatchProcessTriggeredEventProducer;
import com.org.lob.project.messaging.model.BatchProcessEvent;
import com.org.lob.project.repository.SambaFileRepository;
import com.org.lob.support.function.Throwing;

@Service
public class DefaultBatchProcessService implements BatchProcessService {

	@Value("${app.local-file-path}")
	private String localFilePath;

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultBatchProcessService.class);

	private final BatchProcessTriggeredEventProducer eventProducer;
	private final SambaFileRepository sambaFileRepository;

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
			sambaFileRepository.doForEachModifiedFile(share, lastModifiedDate, Throwing.rethrow(fileInfo -> {
				Path localFilePath = localPath(fileInfo);
				copyFileToLocal(share, fileInfo, localFilePath);
				eventProducer.sendMessage(newBatchEvent(localFilePath));
			}));
			saveLastModifiedDateTime(now);
		}));
	}

	private BatchProcessEvent newBatchEvent(Path localFilePath) {
		return new BatchProcessEvent();
	}

	private LocalDateTime getLastModifiedDateTime() {
		// Access some persistent store and get it
		return LocalDateTime.now().minusMinutes(2);
	}

	private void copyFileToLocal(DiskShare share, FileIdBothDirectoryInformation fileInfo, Path localFilePath) throws Exception {
		sambaFileRepository.copySambaFile(share, sambaFileToRead(fileInfo), localFilePath);		
	}

	private String sambaFileToRead(FileIdBothDirectoryInformation fileInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	private Path localPath(FileIdBothDirectoryInformation fileInfo) {
		return Paths.get(localFilePath);
	}

	private void saveLastModifiedDateTime(LocalDateTime now) {
		// TODO Auto-generated method stub		
	}
}
