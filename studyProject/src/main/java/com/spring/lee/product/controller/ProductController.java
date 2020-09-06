package com.spring.lee.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.lee.product.dto.ProductCommentDto;
import com.spring.lee.product.dto.ProductDto;
import com.spring.lee.product.service.ProductService;


@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	//카페 글 목록 보기 요청 처리 
	@RequestMapping("/product/list")
	public ModelAndView getList(HttpServletRequest request, 
			ModelAndView mView) {
		productService.getList(request);
		mView.setViewName("product/list");
		return mView;
	}
	
	@RequestMapping("/product/detail")
	public ModelAndView detail(HttpServletRequest request,
			ModelAndView mView) {
		productService.getDetail(request);
		mView.setViewName("product/detail");
		return mView;
	}
	
	@RequestMapping("/product/private/insertform")
	public ModelAndView insertForm(ModelAndView mView) {
		
		mView.setViewName("product/insertform");
		return mView;
	}
	
	@RequestMapping(value = "/product/private/insert", method=RequestMethod.POST)
	public ModelAndView insert(ProductDto dto, ModelAndView mView, HttpSession session) {
		//dto 에 글 작성자 담기 	
		String id=(String)session.getAttribute("id");
		dto.setWriter(id);
		
		productService.saveProduct(dto);
		mView.setViewName("product/insert");
		return mView;
	}
	
	@RequestMapping(value = "/product/private/image_upload",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> image_upload
				(HttpServletRequest request,@RequestParam MultipartFile image){
		//service 객체를 이용해서 이미지를 upload 폴더에 저장하고 Map 을 리턴 받는다.
		Map<String, Object> map=productService.saveImage(request, image);
		//{"imageSrc":"/upload/xxx.jpg"} 형식의 JSON 문자열을 출력하기 위해
		//Map 을 @ResponseBody 로 리턴해준다. 
		return map;
	}
	
	@RequestMapping("/product/private/updateform")
	public ModelAndView updateform(HttpServletRequest request,
			ModelAndView mView) {
		productService.getDetail(request);
		mView.setViewName("product/updateform");
		return mView;
	}
	@RequestMapping(value="/product/private/update", method=RequestMethod.POST)
	public ModelAndView update(ProductDto dto, ModelAndView mView) {
		productService.updateProduct(dto);
		mView.setViewName("product/update");
		return mView;
	}
	
	@RequestMapping("/product/private/delete")
	public ModelAndView delete(int num, ModelAndView mView) {
		productService.deleteProduct(num);
		mView.setViewName("redirect:/product/list.do");
		return mView;
	}
	
	@RequestMapping(value = "/product/private/comment_insert", 
			method=RequestMethod.POST)
	public ModelAndView commentInsert(HttpServletRequest request,
			ModelAndView mView, @RequestParam int ref_group) {
		//새 댓글을 저장하고 
		productService.saveComment(request);
		//보고 있던 글 자세히 보기로 다시 리다일렉트 이동 시킨다.
		mView.setViewName("redirect:/product/detail.do?num="+ref_group);
		return mView;
	}
	@RequestMapping("/product/private/comment_delete")
	public ModelAndView commentDelete(HttpServletRequest request,
			ModelAndView mView, @RequestParam int ref_group) {
		productService.deleteComment(request);
		mView.setViewName("redirect:/product/detail.do?num="+ref_group);
		return mView;
	}
	
	//댓글 수정 ajax 요청에 대한 요청 처리 
	@RequestMapping(value = "/product/private/comment_update", 
			method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> commentUpdate(ProductCommentDto dto){
		//댓글을 수정 반영하고 
		productService.updateComment(dto);
		//JSON 문자열을 클라이언트에게 응답한다.
		Map<String, Object> map=new HashMap<>();
		map.put("num", dto.getNum());
		map.put("content", dto.getContent());
		return map;
	}
	
	@RequestMapping("/product/ajax_comment_list")
	public ModelAndView ajaxCommentList(HttpServletRequest request,
			ModelAndView mView) {
		productService.moreCommentList(request);
		mView.setViewName("product/ajax_comment_list");
		return mView;
	}
	
}
