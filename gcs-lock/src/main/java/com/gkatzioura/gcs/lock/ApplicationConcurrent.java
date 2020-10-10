package com.gkatzioura.gcs.lock;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class ApplicationConcurrent {

	private static final String bucketName = "bucketName";
	private static final String lockFileName = "lockFileName";
	private static final String configFileName = "configFileName";

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		var storage = StorageOptions.getDefaultInstance().getService();

		final int threads = 10;
		var service = Executors.newFixedThreadPool(threads);
		var futures = new ArrayList<Future>(threads);

		for (var i = 0; i < threads; i++) {
			futures.add(service.submit(update(storage, "property-"+i, "value-"+i)));
		}

		for (var f : futures) {
			f.get();
		}

		service.shutdown();

		var gcsConfig = new GCSConfiguration(storage, bucketName, configFileName);
		var properties = gcsConfig.properties();

		for(var i=0; i < threads; i++) {
			System.out.println(properties.get("property-"+i));
		}


	}

	private static Runnable update(final Storage storage, String property, String value) {
		return () -> {
			var lock = new GCSLock(storage, bucketName, lockFileName);
			var gcsConfig = new GCSConfiguration(storage, bucketName, configFileName);

			boolean lockAcquired = false;

			while (!lockAcquired) {
				lockAcquired = lock.acquire();
				System.out.println("Could not acquire lock");
			}

			gcsConfig.addProperty(property, value);
			lock.release();
		};
	}
}
