package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.ColorBoxVO;

@Mapper
@Repository
public interface ColorBoxDAO {

	public void insertColorBox(ColorBoxVO colorBoxVO) throws Exception;
	public List<ColorBoxVO> selectAllColorBoxList(ColorBoxVO colorBoxVO)throws DataAccessException;

}
