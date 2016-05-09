package dk.dtu.smmac.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("filesystemservice")
public interface FileSystemService extends RemoteService{
	
	public void uploadFile(String bucketName, String keyName, String filePath) throws Exception;
	public void chooseFile() throws Exception;
	
}
