package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDAO;
import com.example.demo.vo.MemberVO;

@Service
public class MemberService {
	
	@Autowired
	MemberDAO memberDAO;
	
	public void memberInsert(MemberVO memberVO) throws DataAccessException{
		memberDAO.insertMember(memberVO);
	}
	
	public MemberVO login(MemberVO memberVO)  throws DataAccessException{
		return memberDAO.login(memberVO);
	}
}
