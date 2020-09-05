package com.spring.lee.product.dao;

import java.util.List;

import com.spring.lee.product.dto.ProductDto;

public interface ProductDao {
	public List<ProductDto> getList(ProductDto dto);
	public int getCount(ProductDto dto);
	public ProductDto getData(int num);
	public void delete(int num);
	public void update(ProductDto dto);
	public void insert(ProductDto dto);
}
