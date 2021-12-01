package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ColorBoxDAO;

import com.example.demo.vo.ColorBoxVO;

@Service
public class ColorBoxService {

	@Autowired
	ColorBoxDAO colorBoxDAO;
	
	public void insertColorBox(ColorBoxVO colorBoxVO) throws Exception {
		colorBoxDAO.insertColorBox(colorBoxVO);
	}
	public List<ColorBoxVO> selectAllBasketList(ColorBoxVO colorBoxVO){
		return colorBoxDAO.selectAllColorBoxList(colorBoxVO);
		
	}
}
