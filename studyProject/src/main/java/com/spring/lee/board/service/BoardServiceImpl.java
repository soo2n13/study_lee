package com.spring.lee.board.service;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.lee.board.dao.BoardDao;
import com.spring.lee.board.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao dao;

	final int PAGE_ROW_COUNT = 5;
	final int PAGE_DISPLAY_COUNT = 5;

	@Override
	public void getList(HttpServletRequest request) {
		int pageNum = 1;
		String strPageNum = request.getParameter("pageNum");

		if (strPageNum != null) {
			pageNum = Integer.parseInt(strPageNum);
		}

		int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
		int endRowNum = pageNum * PAGE_ROW_COUNT;

		String keyword = request.getParameter("keyword");
		String condition = request.getParameter("condition");
		if (keyword == null) {
			keyword = "";
			condition = "";
		}

		String encodedK = URLEncoder.encode(keyword);

		BoardDto dto = new BoardDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);

		if (!keyword.equals("")) {
			if (condition.equals("title_content")) {
				dto.setTitle(keyword);
				dto.setContent(keyword);
			} else if (condition.equals("title")) {
				dto.setTitle(keyword);
			} else if (condition.equals("writer")) {
				dto.setWriter(keyword);
			}
		}

		List<BoardDto> list = dao.getList(dto);
		int totalRow = dao.getCount(dto);
		int totalPageCount = (int) Math.ceil(totalRow / (double) PAGE_ROW_COUNT);
		int startPageNum = 1 + ((pageNum - 1) / PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
		int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;

		if (totalPageCount < endPageNum) {
			endPageNum = totalPageCount;
		}

		request.setAttribute("list", list);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("condition", condition);
		request.setAttribute("keyword", keyword);
		request.setAttribute("encodedK", encodedK);
	}
	
	@Override
	public void getDetail(HttpServletRequest request) {

		int num=Integer.parseInt(request.getParameter("num"));
		String keyword=request.getParameter("keyword");
		String condition=request.getParameter("condition");
		if(keyword==null){
			keyword="";
			condition="";
		}

		String encodedK=URLEncoder.encode(keyword);
		BoardDto dto=new BoardDto();
		dto.setNum(num);
		
		if(!keyword.equals("")){
			if(condition.equals("title_content")){
				dto.setTitle(keyword);
				dto.setContent(keyword);	
			}else if(condition.equals("title")){
				dto.setTitle(keyword);
			}else if(condition.equals("writer")){
				dto.setWriter(keyword);
			}
		}
		BoardDto resultDto=dao.getData(dto);
		
		request.setAttribute("dto", resultDto);
		request.setAttribute("condition", condition);
		request.setAttribute("keyword", keyword);
		request.setAttribute("encodedK", encodedK);
		

		dao.addViewCount(num);
		
	}
	
	@Override
	public void saveContent(BoardDto dto) {
		dao.insert(dto);
	}

	@Override
	public void updateContent(BoardDto dto) {
		dao.update(dto);
	}

	@Override
	public void deleteContent(int num) {
		dao.delete(num);

	}
}
