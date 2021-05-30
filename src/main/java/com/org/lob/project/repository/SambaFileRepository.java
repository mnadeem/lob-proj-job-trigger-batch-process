package com.org.lob.project.repository;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.function.Consumer;

import com.hierynomus.msfscc.fileinformation.FileIdBothDirectoryInformation;
import com.hierynomus.smbj.share.DiskShare;

public interface SambaFileRepository {

	void execute(SambaShareSessionCallback callback) throws Exception;

	void doForEachModifiedFile(DiskShare share, LocalDateTime lastModifiedDate, Consumer<FileIdBothDirectoryInformation> consumer);

	void copySambaFile(DiskShare share, String sambaFile, Path localFilePath) throws Exception;
}
