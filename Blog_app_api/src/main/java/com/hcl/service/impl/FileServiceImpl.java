package com.hcl.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hcl.service.FileService;
@Service
public class FileServiceImpl  implements FileService{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		
		//file Name;
		String filename = file.getOriginalFilename();
		
		
		   
		
		  String randomId=UUID.randomUUID().toString(); 
		  String fileName1 =randomId.concat(filename.substring(filename.lastIndexOf('.')));
		 
		 //full path
		   String filepath=path+File.separator+fileName1;
		   
		   //create folder if not created
		   File f=new File(path);
		   if(!f.exists())
		   {
			   f.mkdir();
		   }
		   
		   //file copy
		   
		   Files.copy(file.getInputStream(), Paths.get(filepath),StandardCopyOption.REPLACE_EXISTING);
		return filename;
	}

	@Override
	public InputStream getResource( String Resource,String path) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String fullpath= path+ File.separator+Resource;
		InputStream is=new FileInputStream(fullpath);
		
		
		return is;
	}

}
