package dk.dtu.smmac.server.dal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AbortMultipartUploadRequest;
import com.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadResult;
import com.amazonaws.services.s3.model.PartETag;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.smmac.client.service.FileSystemService;

public class FileSystemDAO extends RemoteServiceServlet implements FileSystemService {

	@Override
	public void uploadFile(String bucketName, String keyName, String filePath) throws Exception {

		AmazonS3 s3Client = new AmazonS3Client(new ProfileCredentialsProvider());        

		// Create a list of UploadPartResponse objects. You get one of these
		// for each part upload.
		List<PartETag> partETags = new ArrayList<PartETag>();

		// Step 1: Initialize.
		InitiateMultipartUploadRequest initRequest = new 
				InitiateMultipartUploadRequest(bucketName, keyName);
		InitiateMultipartUploadResult initResponse = 
				s3Client.initiateMultipartUpload(initRequest);

		File file = new File(filePath);
		long contentLength = file.length();
		long partSize = 5242880; // Set part size to 5 MB

		try {
			// Step 2: Upload parts.
			long filePosition = 0;
			for (int i = 1; filePosition < contentLength; i++) {
				// Last part can be less than 5 MB. Adjust part size.
				partSize = Math.min(partSize, (contentLength - filePosition));

				// Create request to upload a part.
				UploadPartRequest uploadRequest = new UploadPartRequest()
						.withBucketName(bucketName).withKey(keyName)
						.withUploadId(initResponse.getUploadId()).withPartNumber(i)
						.withFileOffset(filePosition)
						.withFile(file)
						.withPartSize(partSize);

				// Upload part and add response to our list.
				partETags.add(
						s3Client.uploadPart(uploadRequest).getPartETag());

				filePosition += partSize;
			}

			// Step 3: Complete.
			CompleteMultipartUploadRequest compRequest = new 
					CompleteMultipartUploadRequest(
							bucketName, 
							keyName, 
							initResponse.getUploadId(), 
							partETags);

			s3Client.completeMultipartUpload(compRequest);
		} catch (Exception e) {
			s3Client.abortMultipartUpload(new AbortMultipartUploadRequest(
					bucketName, keyName, initResponse.getUploadId()));
		}

	}

	@Override
	public void chooseFile() throws Exception {
		
	}

}
