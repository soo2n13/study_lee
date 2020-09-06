package com.spring.lee.product.dao;

import java.util.List;

import com.spring.lee.product.dto.ProductCommentDto;


public interface ProductCommentDao {
	public List<ProductCommentDto> getList(ProductCommentDto dto);
	public void delete(int num);
	public void insert(ProductCommentDto dto);
	public int getSequence();
	public void update(ProductCommentDto dto);
	public ProductCommentDto getData(int num);
	public int getCount(int ref_group);
	public void groupDelete(int ref_group);
}
