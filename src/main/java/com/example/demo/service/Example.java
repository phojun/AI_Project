package com.example.demo.service;

import java.io.*;
import java.awt.image.BufferedImage;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.awt.Color;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.example.demo.vo.ColorTestVO;

// 네이버 얼굴인식 API 예제
@Service
public class Example {
		ColorTestVO colorTestVO;
    public ColorTestVO getColor() {
    	JSONObject jo1 = new JSONObject();
    	int[] a = new int[3];
    	
       
        StringBuffer reqStr = new StringBuffer();
        String clientId = "c4gwa4h9p0";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "JXTKc5OX3vBneS31NxblU5amf3RlYcOSVXcIxZBM";//애플리케이션 클라이언트 시크릿값";

        try {
            String paramName = "image"; // 파라미터명은 image로 지정
            String imgFile = "c:\\temp\\me3.png";
            File uploadFile = new File(imgFile);
        
            
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
                
               JSONObject jo=new JSONObject(response.toString());
               JSONArray ja= (JSONArray) jo.get("faces");
               JSONObject jo2= (JSONObject) ja.get(0);
               JSONObject jo3=  (JSONObject) jo2.get("landmark");
           
              
              int x1 =(((int)((JSONObject) jo3.get("leftEye")).get("x"))+((int) ((JSONObject)jo3.get("leftMouth")).get("x")))/2;
              int y1=(((int)((JSONObject) jo3.get("leftEye")).get("y"))+((int) ((JSONObject)jo3.get("leftMouth")).get("y")))/2;
              int x2 =(((int)((JSONObject) jo3.get("rightEye")).get("x"))+((int) ((JSONObject)jo3.get("rightMouth")).get("x")))/2;
              int y2=(((int)((JSONObject) jo3.get("rightEye")).get("y"))+((int) ((JSONObject)jo3.get("rightMouth")).get("y")))/2;
              
               System.out.println(jo3.toString());
               JSONObject jo4=(JSONObject) jo3.get("nose");
              
             // JSONObject jo5=new JSONObject();
             // jo5.put("x", x);
             // jo5.put("y",y);
               
              BufferedImage img = ImageIO.read(uploadFile);
              for(int i=0;i<3;i++) {
            	  x1 += i+1;
            	  x2 += i+1;
            	  for(int j=1;j<=3;j++) {
            		  y1 += j;
            		  y2 += j;
            		  Color color=new Color(img.getRGB(x1,y1),true);
            		  Color color1=new Color(img.getRGB(x2,y2),true);
            		  a[0] += color.getRed() + color1.getRed();
            		  a[1] += color.getGreen()+color1.getGreen();
            		  a[2] += color.getBlue()+color1.getBlue();
            		  System.out.println("x1:"+x1+","+"y1:"+y1);
            		  System.out.println("x2:"+x2+","+"y2:"+y2);
            		  System.out.println(color.getRed()+","+color.getGreen()+","+color.getBlue());
            		  System.out.println(color1.getRed()+","+color1.getGreen()+","+color1.getBlue());
            	  }
              }

           
           
            System.out.println("result : "+a[0]/18+","+a[1]/18+","+a[2]/18);
            colorTestVO.setBlue(a[2]);
    		colorTestVO.setGreen(a[1]);
    		colorTestVO.setRed(a[0]);
    		//jo1.put("colorTestVO",colorTestVO);
            
            
            
            } else {
                System.out.println("error !!!");
                //jo1.put("msg", "error");
            }
        } catch (Exception e) {
            System.out.println(e);
           // jo1.put("msg", e);
        }
        return colorTestVO;
    }
}