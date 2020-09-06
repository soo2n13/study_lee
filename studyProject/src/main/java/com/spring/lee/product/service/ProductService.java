package com.spring.lee.product.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.spring.lee.product.dto.ProductCommentDto;
import com.spring.lee.product.dto.ProductDto;


public interface ProductService {
	public void getList(HttpServletRequest request);
	public void getDetail(HttpServletRequest request);
	public void saveProduct(ProductDto dto);
	public void updateProduct(ProductDto dto);
	public void deleteProduct(int num);
	public void saveComment(HttpServletRequest request);//댓글 저장 
	public void deleteComment(HttpServletRequest request);//댓글 삭제
	public void updateComment(ProductCommentDto dto);//댓글 수정
	public void moreCommentList(HttpServletRequest request);//댓글 추가 응답
	public Map<String, Object> saveImage(HttpServletRequest request, MultipartFile mFile);
}
