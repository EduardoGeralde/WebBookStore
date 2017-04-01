package com.eduardoportfolio.store.infra;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Component
public class FileSaver {
	
	/**
	@Autowired
	private HttpServletRequest request;

	public String write(String baseFolder, MultipartFile file){
		String realPath = request.getServletContext().getRealPath("/"+baseFolder);
		
		File targetDirectory = new File(realPath);
        targetDirectory.mkdirs();
        System.out.println(realPath);
		
		try{
			String path = realPath + File.separator+file.getOriginalFilename();
			file.transferTo(new File(path));
			System.out.println(path);
			return baseFolder+File.separator+file.getOriginalFilename();
		} catch (IOException e){
			throw new RuntimeException(e);
		}
	}
	*/
	
	@Autowired
	private AmazonS3Client s3;
		
	public String write(String baseFolder, MultipartFile multipartFile) {
		
		try{
			s3.putObject("weebbookstore",multipartFile.getOriginalFilename(),
										 multipartFile.getInputStream(),new ObjectMetadata());
			return "https://s3.amazonaws.com/webbookstore/" + multipartFile.getOriginalFilename();
			
		} catch (AmazonClientException | IOException e){
			throw new RuntimeException(e);
		}
	}
}