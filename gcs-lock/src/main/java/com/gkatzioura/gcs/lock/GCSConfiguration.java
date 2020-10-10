package com.gkatzioura.gcs.lock;

import java.util.HashMap;
import java.util.Map;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.json.JSONObject;

public class GCSConfiguration {

	private final Storage storage;
	private final String bucket;
	private final String keyName;

	GCSConfiguration(Storage storage, String bucket, String keyName) {
		this.storage = storage;
		this.bucket = bucket;
		this.keyName = keyName;
	}

	public void addProperty(String key, String value) {
		var blobId = BlobId.of(bucket, keyName);
		var blob = storage.get(blobId);

		final JSONObject configJson;

		if(blob==null) {
			configJson = new JSONObject();
		} else {
			configJson = new JSONObject(new String(blob.getContent()));
		}

		configJson.put(key, value);

		var blobInfo = BlobInfo.newBuilder(blobId).build();
		storage.create(blobInfo, configJson.toString().getBytes());
	}

	public Map<String,String> properties() {

		var blobId = BlobId.of(bucket, keyName);
		var blob = storage.get(blobId);

		var map = new HashMap<String,String>();

		if(blob!=null) {
			var jsonObject = new JSONObject(new String(blob.getContent()));
			for(var key: jsonObject.keySet()) {
				map.put(key, jsonObject.getString(key));
			}
		}

		return map;
	}

}
