package com.eduardoportfolio.store.infra;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
	/**
	@Autowired
	private HttpServletRequest request;
	

	public String write(String baseFolder, MultipartFile file){
		String realPath = request.getServletContext().getRealPath("/"+baseFolder);
		
		File targetDirectory = new File(realPath);
        targetDirectory.mkdirs();
		
		try{
			String path = realPath + File.separator+file.getOriginalFilename();
			file.transferTo(new File(path));
			return baseFolder+File.separator+file.getOriginalFilename();
		} catch (IOException e){
			throw new RuntimeException(e);
		}
	}
	*/
	
	public String write(String baseFolder, MultipartFile multipartFile) {
		
		AmazonS3Client s3 = client();
		try{
			s3.putObject("weebbookstore",multipartFile.getOriginalFileName(),
										 multipartFile.getInputStream(),new ObjectMetadata());
			return "https://s3.amazonaws.com/webbookstore/" + multipartFile.getOriginalFilename();
			
		} catch (AmazonClientException | IOException e){
			throw new RuntimeException(e);
		}
	}
	
	private AmazonS3Client client(){
		AWSCredentials credentials = new BasicAWSCredentials(,);
		
		AmazonS3Client newClient = new AmazonS3Client(credentials,new ClientConfiguration());
		newClient.setS3ClientOptions(new S3ClientOptions().withPathStyleAccess(true));
		return newClient;
	}
	
	
}