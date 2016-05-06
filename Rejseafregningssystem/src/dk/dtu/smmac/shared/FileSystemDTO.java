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
    	super();
    	this.existingBucketName = bucketName;
    	this.keyName = keyName;
    	this.filePath = filePath;
    }

    public String getBucketName()
    {
    	return this.existingBucketName;
    }
    
    public String getKeyName()
    {
    	return this.keyName;
    }
    
    public String getFilePath()
    {
    	return this.filePath;
    }
    
    public void setBucketName(String bucketName)
    {
    	this.existingBucketName = bucketName;
    }
    
    public void setKeyName(String keyName)
    {
    	this.keyName = keyName;
    }
    
    public void setFilePath(String filePath)
    {
    	this.filePath = filePath;
    }

    
    

}
