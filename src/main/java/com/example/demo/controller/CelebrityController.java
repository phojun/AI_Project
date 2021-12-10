package com.example.demo.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
	
	@PostMapping("celeImg")
	@ResponseBody
	public void celebrityImg(String celebrity) {
		//System.out.println(celebrity);
		String URL = "https://www.google.com/search?q="+celebrity+"&sxsrf=AOaemvIjhb1NgGRF5kcdWUV53WrtFFaa9Q:1639099768794&source=lnms&tbm=isch&sa=X&sqi=2&ved=2ahUKEwifl-G7itj0AhUStaQKHVhXB1IQ_AUoAXoECAIQAw&biw=1059&bih=668&dpr=1&sfr=gws&gbv=1&sei=5a2yYaqZMqHyhwOV55b4Aw";
		//System.out.println(URL);
		
		Connection con = Jsoup.connect(URL);
		//System.out.println(con);
		Document doc;
		ArrayList<String> imgURL=new ArrayList<String>();
		
		try {
			doc = con.get();
			Elements imgs=doc.getElementsByClass("yWs4tf"); 
			System.out.println(imgs.size());
			for (Element element : imgs) {
	            String url = element.attr("src");
	            imgURL.add(url);
			}
			//System.out.println(imgURL.toString());
			//System.out.println(imgURL.size());
			Random r = new Random();
			int random = r.nextInt(imgURL.size());
			System.out.println(random); 
			String getURL=imgURL.get(random);
			System.out.println(getURL);
			clerbrityDetection.getCeleImg(getURL);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	

}
