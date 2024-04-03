package com.blog.apis.fileservice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {
	
	String updoadImage(String path,MultipartFile file) throws IOException;  //file and path on which path have to store
	
	// serving the file 
	InputStream getResource(String path,String fileName) throws FileNotFoundException;
	 
}
