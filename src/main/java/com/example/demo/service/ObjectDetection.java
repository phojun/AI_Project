package com.example.demo.service;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
// Object Detection API 예제
public class ObjectDetection {

    public String detectPerson(File file) {

        StringBuffer reqStr = new StringBuffer();
        String clientId = "YOUR_CLIENT_ID";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "YOUR_CLIENT_SECRET";//애플리케이션 클라이언트 시크릿값";

        try {
        	Files.copy(file.toPath(), new File("src\\main\\webapp\\media\\upload.png").toPath(),StandardCopyOption.REPLACE_EXISTING );

            String paramName = "image"; // 파라미터명은 image로 지정
            String imgFile = file.getPath();
            System.out.println(imgFile);
            File uploadFile = new File(imgFile);
            String apiURL = "https://naveropenapi.apigw.ntruss.com/vision-obj/v1/detect"; // 객체 인식
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            // multipart request
            String boundary = "---" + System.currentTimeMillis() + "---";
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "c4gwa4h9p0");
            con.setRequestProperty("X-NCP-APIGW-API-KEY", "JXTKc5OX3vBneS31NxblU5amf3RlYcOSVXcIxZBM");
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
                System.out.println("error!!!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }
            String inputLine;
            if(br != null) {
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
                JSONObject jo = new JSONObject(response.toString());
//                JSONObject jo1 = new JSONObject(response.toString());
//                System.out.println(jo1.toString());
//                JSONArray ja = (JSONArray)jo1.get("faces");
//                jo1 = (JSONObject)ja.get(0);
//                ja = (JSONArray)jo1.get("landmark");
//                jo1 = (JSONObject)ja.get(0);
//                System.out.println(jo1.get("roi"));
                
                
                BufferedImage bimg = ImageIO.read(uploadFile); 
                System.out.println(response.toString());
                jo = new JSONObject(response.toString());
                System.out.println(jo.toString());
                JSONArray ja = (JSONArray)jo.get("predictions");
                jo = (JSONObject)ja.get(0);
                ja = (JSONArray)jo.get("detection_names");
                System.out.println(ja.toString());
                String result = ja.toString();
                System.out.println(result);
                if(result.contains("person")) {
                	return "사람탐지";
                }else {
                	return "사람없음";
                }
                
            } else {
                System.out.println("error !!!");
                return "error";
            }
        } catch (Exception e) {
            System.out.println(e);
            return e.getMessage();
        }
		
    }
}
