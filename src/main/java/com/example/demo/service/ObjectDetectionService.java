package com.example.demo.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

// Object Detection API 예제
@Service
public class ObjectDetectionService {

    public  String objectDetect(File uploadFile) {
    	JSONObject jo1 = new JSONObject();
    	int[] a = new int[3];
    	
        StringBuffer reqStr = new StringBuffer();
        String clientId = "s9vnr4drhx";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "KqROzDH0ggHpaiZvRq5byUECOjLCA8VSDvgBoh8a";//애플리케이션 클라이언트 시크릿값";

        try {
        	Files.copy(uploadFile.toPath(), new File("src\\main\\webapp\\media\\upload.png").toPath(),StandardCopyOption.REPLACE_EXISTING );
            String paramName = "image"; // 파라미터명은 image로 지정
           //String imgFile = "D:\\temp\\smile.jpg";
           // File uploadFile = new File(imgFile);
            String apiURL = "https://naveropenapi.apigw.ntruss.com/vision/v1/face"; // 얼굴 감지
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            // multipart request
            String boundary = "---" + System.currentTimeMillis() + "---";
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            OutputStream outputStream = con.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
            String LINE_FEED = "\r\n";
            // file 추가
            String fileName = uploadFile.getName();
            writer.append("--" + boundary).append(LINE_FEED);
            writer.append("Content-Disposition: form-data; name=\"" + paramName + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
            writer.append("Content-Type: "  + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
            writer.append(LINE_FEED);
            writer.flush();
            FileInputStream inputStream = new FileInputStream(uploadFile);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
            writer.append(LINE_FEED).flush();
            writer.append("--" + boundary + "--").append(LINE_FEED);
            writer.close();
            BufferedReader br = null;
            int responseCode = con.getResponseCode();
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 오류 발생
//                System.out.println("error!!!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }
            String inputLine;
            if(br != null) {
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
//                System.out.println(response.toString());
                
				BufferedImage bimg = ImageIO.read(uploadFile); 
				int width = bimg.getWidth();
				int height = bimg.getHeight();
				
                JSONObject jo=new JSONObject(response.toString());    
                jo.put("width", width);
                jo.put("height", height);
                
                return jo.toString();
            }
        } catch (Exception e) {
//            e.printStackTrace();
            
        }
    	return null;
    }
}


