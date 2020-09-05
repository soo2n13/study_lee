package com.spring.lee.product.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.lee.product.dto.ProductDto;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SqlSession session;
	
	@Override
	public List<ProductDto> getList(ProductDto dto) {
		List<ProductDto> list = session.selectList("product.getList", dto);
		return list;
	}

	@Override
	public int getCount(ProductDto dto) {
		int count = session.selectOne("product.getCount", dto);
		return count;
	}

	@Override
	public ProductDto getData(int num) {
		ProductDto data = session.selectOne("product.getData", num);
		return data;
	}

	@Override
	public void delete(int num) {
		session.delete("product.delete", num);
		
	}

	@Override
	public void update(ProductDto dto) {
		session.update("product.update", dto);
		
	}

	@Override
	public void insert(ProductDto dto) {
		session.insert("product.insert", dto);
		
	}

}
