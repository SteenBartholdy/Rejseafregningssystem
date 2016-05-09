package dk.dtu.smmac.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FileSystemServiceAsync {
	
	void uploadFile(String bucketName, String keyName, String filePath, AsyncCallback<Void> callback);
	void chooseFile(AsyncCallback<Void> callback);
}
