package com.gkatzioura.gcs.lock;

import java.util.Optional;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageException;

public class GCSLock {

	public static final String LOCK_STRING = "_lock";
	private final Storage storage;
	private final String bucket;
	private final String keyName;

	private Optional<Blob> acquired = Optional.empty();

	GCSLock(Storage storage, String bucket, String keyName) {
		this.storage = storage;
		this.bucket = bucket;
		this.keyName = keyName;
	}

	public boolean acquire() {
		try {
			var blobInfo = BlobInfo.newBuilder(bucket, keyName).build();
			var blob = storage.create(blobInfo, LOCK_STRING.getBytes(), Storage.BlobTargetOption.doesNotExist());
			acquired = Optional.of(blob);
			return true;
		} catch (StorageException storageException) {
			return false;
		}
	}

	public void release() {
		if(!acquired.isPresent()) {
			throw new IllegalStateException("Lock was never acquired");
		}
		storage.delete(acquired.get().getBlobId());
	}

}
