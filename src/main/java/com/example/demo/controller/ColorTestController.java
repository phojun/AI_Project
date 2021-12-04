package com.example.demo.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("getColor")
	@ResponseBody
	public String getColor() {
		int[] a = new int[3];
		JSONObject jo = new JSONObject();
		ColorTestVO colorTestVO;
		colorTestVO = getColorService.getColor(); 
		if(colorTestVO!=null) {
			try {
				return colorTestService.selectPeronalType(colorTestVO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("colorTestVO is null");
		}
	}
}
