package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardDAO;
import com.example.demo.vo.BoardVO;

@Service
public class BoardService {
	
	@Autowired
	BoardDAO boardDAO;
	
	public List<BoardVO> boardList(){
		return boardDAO.boardList();
	}

	public void boardWrite(BoardVO boardVO) {
		boardDAO.boardWrite(boardVO);
		
	}

	public BoardVO viewArticle(int no) {
		return boardDAO.viewArticle(no);
		
	}

	public void replyWrite(BoardVO replyVO) {
		boardDAO.replyWrite(replyVO);
		
	}
}
