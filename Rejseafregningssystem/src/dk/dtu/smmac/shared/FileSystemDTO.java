package dk.dtu.smmac.shared;

import java.io.Serializable;

public class FileSystemDTO implements Serializable {
	
	private String existingBucketName;
    private String keyName;
    private String filePath;
    
    public FileSystemDTO()
    {
    	super();
    }
    
    public FileSystemDTO(String bucketName, String keyName, String filePath)
    {
    	this.existingBucketName = bucketName;
    	this.keyName = keyName;
    	this.filePath = filePath;
    }
    
    

}
