package com.spring.lee.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.lee.board.dto.BoardDto;
import com.spring.lee.board.service.BoardService;
import com.spring.lee.product.dto.ProductDto;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@RequestMapping("/board/list")
	public ModelAndView getList(HttpServletRequest request, 
			ModelAndView mView) {
		service.getList(request);
		mView.setViewName("board/list");
		return mView;
	}
	
	@RequestMapping("/board/insertform")
	public ModelAndView insertForm(ModelAndView mView) {
		
		mView.setViewName("board/insertform");
		return mView;
	}
	
	@RequestMapping("/board/insert")
	public ModelAndView insertData(BoardDto dto, ModelAndView mView) {
		dto.setWriter("lee");
		service.saveContent(dto);
		mView.setViewName("board/insert");
		return mView;
	}
	
	@RequestMapping("/board/detail")
	public ModelAndView detail(HttpServletRequest request,
			ModelAndView mView) {
		service.getDetail(request);
		mView.setViewName("board/detail");
		return mView;
	}
}
