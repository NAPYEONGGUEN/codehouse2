package com.itbuddy.codehouse.serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.itbuddy.codehouse.controller.UploadController;
import com.itbuddy.codehouse.service.IUploadService;


@Service
public class UploadService implements IUploadService{

	private static final Logger logger = LoggerFactory.getLogger(UploadService.class);

	@Value("${uploads.location}")
	private String uploadsLocation;
	@Value("${uploads.uri_path}")
	private String uploadsUriPath;





	@Override
	public String imgUpload(String email,			
			MultipartFile file, 
			Model model, 
			HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub

		logger.info("File uploaded loading.");


		//REAL_PATH : /opt/codehouse/uploads
		logger.info("REAL_PATH : " + uploadsLocation);
		String SINGLE_FILE_UPLOAD_PATH = "test";


		File dir = new File(uploadsLocation,SINGLE_FILE_UPLOAD_PATH);

		if(dir.exists()) {
			logger.info(dir.getPath()+ " is Exist");
		}else {

			dir.mkdir();		
			logger.info(dir.getPath()+" is not Exist so create dir");
		}

		// Save mediaFile on system
		if (!file.getOriginalFilename().isEmpty()) {
			File saveFile = new File(dir.getPath(), file.getOriginalFilename());
			file.transferTo(saveFile);
			model.addAttribute("msg", "이미지 업로드 완효");

			logger.info("File uploaded successfully.");


			//http://itbuddy.iptime.org
			String host = request.getRequestURL().toString().replace(request.getRequestURI(),"");
			//http://itbuddy.iptime.org/codehouse/uploads/test/InkspaceFile.jpeg
			String fileURL = host + "/codehouse/uploads/"+SINGLE_FILE_UPLOAD_PATH+"/"+file.getOriginalFilename();
			logger.info("return : "+fileURL);
			return fileURL;
			
		} else {
			model.addAttribute("msg", "이미지 파일을 선택해주세요.");
			logger.info("이미지 파일을 선택해주세요");
			return null;
		}
	}




}
