package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.ColorTestVO;


@Mapper
@Repository
public interface ColorTestDAO {
	public String selectPeronalType(int no) throws Exception;
	public List<ColorTestVO> selectAllType() throws Exception;

}
