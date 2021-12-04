package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.ColorTestVO;


@Mapper
@Repository
public interface ColorTestDAO {
	public String selectPeronalType(ColorTestVO colorTestVO) throws Exception;

}
