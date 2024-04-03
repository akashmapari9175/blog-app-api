package com.blog.apis.fileserviceimple;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.apis.fileservice.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Override
	public String updoadImage(String path, MultipartFile file) throws IOException {
		
		 //file name ex.. abc.pnj
		String name = file.getOriginalFilename();
		
		
		///this is random name generate for the file
		String randomID=UUID.randomUUID().toString();
		 String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));
		
		//full path
		String filePath = path + File.separator + fileName1;  //  /image/abc.png
		
	
		//create folder if not created
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();   //if folder is not there than it will create 
		}
		
		//file copy
		Files.copy(file.getInputStream(),Paths.get(filePath));
		
		
		return fileName1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath=path+File.separator+fileName;
		InputStream is = new FileInputStream(fullPath);
		//we will write the db logic 
		return is;
	}

}
