package com.org.lob.project.repository;

import com.hierynomus.smbj.share.DiskShare;

@FunctionalInterface
public interface SambaShareSessionCallback {

	void doInSession(DiskShare share);
}
