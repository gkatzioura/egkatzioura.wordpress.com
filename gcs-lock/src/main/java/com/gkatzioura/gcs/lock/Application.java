package com.gkatzioura.gcs.lock;

import com.google.cloud.storage.StorageOptions;

public class Application {

	public static void main(String[] args) {
		var storage = StorageOptions.getDefaultInstance().getService();

		final String bucketName = "bucketName";
		final String lockFileName = "lockFileName";
		final String configFileName = "configFileName";

		var lock = new GCSLock(storage, bucketName, lockFileName);
		var gcsConfig = new GCSConfiguration(storage, bucketName, configFileName);

		var lockAcquired = lock.acquire();
		if(lockAcquired) {
			gcsConfig.addProperty("test", "newProperty");
			lock.release();
		}

		var config = gcsConfig.properties();

		for(var key: config.keySet()) {
			System.out.println("Key "+key+" value "+config.get(key));
		}

	}

}
