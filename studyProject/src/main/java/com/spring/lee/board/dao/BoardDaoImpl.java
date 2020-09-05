package com.spring.lee.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.lee.board.dto.BoardDto;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSession session;
	@Override
	public List<BoardDto> getList(BoardDto dto) {
		List<BoardDto> list = session.selectList("board.getList", dto);
		return list;
	}

	@Override
	public int getCount(BoardDto dto) {
		int count = session.selectOne("board.getCount", dto);
		return count;
	}

	@Override
	public void insert(BoardDto dto) {
		session.insert("board.insert", dto);
	}

	@Override
	public BoardDto getData(int num) {
		BoardDto data = session.selectOne("board.getData", num);
		return data;
	}

	@Override
	public BoardDto getData(BoardDto dto) {
		BoardDto data = session.selectOne("board.getData2", dto);
		return data;
	}

	@Override
	public void addViewCount(int num) {
		session.update("board.addViewCount", num);
	}

	@Override
	public void delete(int num) {
		session.delete("board.delete", num);
	}

	@Override
	public void update(BoardDto dto) {
		session.update("board.update", dto);
	}

}
