package com.example.demo.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.CelebrityDetectionService;

@Controller
public class CelebrityController {
	
	@Autowired
	CelebrityDetectionService clerbrityDetection;
	
	@PostMapping("celebrityDetect")
	@ResponseBody
	public String celebrityDetect(MultipartFile image) {

		System.out.println(image.getOriginalFilename());
		try {
			File uploadFile = new File("C:\\temp2\\" + image.getOriginalFilename());
			image.transferTo(uploadFile);
			return clerbrityDetection.celebrityDetect(uploadFile);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "upload fail!!!";
		}

	}

}
