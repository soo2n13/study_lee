package com.spring.lee.board.dao;

import java.util.List;

import com.spring.lee.board.dto.BoardDto;


public interface BoardDao {
	public List<BoardDto> getList(BoardDto dto);
	public int getCount(BoardDto dto);
	public void insert(BoardDto dto);
	public BoardDto getData(int num);
	public BoardDto getData(BoardDto dto);
	public void addViewCount(int num);
	public void delete(int num);
	public void update(BoardDto dto);
}
