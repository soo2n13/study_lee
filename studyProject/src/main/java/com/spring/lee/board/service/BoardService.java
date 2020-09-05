package com.spring.lee.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.spring.lee.board.dto.BoardDto;


public interface BoardService {
	public void getList(HttpServletRequest request);
	public void getDetail(HttpServletRequest request);
	public void saveContent(BoardDto dto);
	public void updateContent(BoardDto dto);
	public void deleteContent(int num);
}






