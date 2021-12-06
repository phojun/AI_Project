package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ColorTestService;
import com.example.demo.service.Example;
import com.example.demo.vo.ColorTestVO;

@Controller
public class ColorTestController {
	
	@Autowired
	Example getColorService;
	
	@Autowired
	ColorTestService colorTestService;
	
	List<ColorTestVO> testList;
	
	@PostMapping("getPcolor")
	@ResponseBody
	public String getPcolor() {
		int no = 0, sum=0, temp = 1000;
		int [] a = new int[3];
		JSONObject jo = new JSONObject();
		a = getColorService.getColor(); 
		if(testList!=null) {
			for(int i=0;i<8;i++) {
				sum += Math.abs(testList.get(i).getRed() - a[0]);
				sum += Math.abs(testList.get(i).getBlue() - a[2]);
				sum += Math.abs(testList.get(i).getGreen() - a[1]);
				
				
				
				System.out.println(sum);
				if(sum<temp) {
					temp = sum;
					no = i+1;
					System.out.println("no"+no);
					System.out.println("sum"+sum);
				}
				sum=0;
			}
			try {
				System.out.println(colorTestService.selectPeronalType(no));
				jo.put("pColor", colorTestService.selectPeronalType(no));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				jo.put("msg", "error");
			}
		}else {
			System.out.println("colorTestVO is null");
			jo.put("msg", "퍼스널컬러 기준 테이블 없음");
		}
		return jo.toString();
	}
	
	@RequestMapping("selectAllType")
	@ResponseBody
	public List<ColorTestVO> selectAllType(){
		System.out.println("selectAllType.java");
		try {
			testList = colorTestService.selectAllType();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(testList);
		return testList;
	}
	
}
