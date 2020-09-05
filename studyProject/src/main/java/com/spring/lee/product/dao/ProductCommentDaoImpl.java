package com.spring.lee.product.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.lee.product.dto.ProductCommentDto;

@Repository
public class ProductCommentDaoImpl implements ProductCommentDao {

	@Autowired
	private SqlSession session;
	
	@Override
	public List<ProductCommentDto> getList(ProductCommentDto dto) {
		List<ProductCommentDto> list = session.selectList("productComment.getList", dto);
		return list;
	}

	@Override
	public void delete(int num) {
		session.delete("productComment.delete", num);
	}

	@Override
	public void insert(ProductCommentDto dto) {
		session.insert("productComment.insert", dto);
	}

	@Override
	public int getSequence() {
		return session.selectOne("productComment.getSequence");
	}

	@Override
	public void update(ProductCommentDto dto) {
		session.update("productComment.update", dto);
	}

	@Override
	public ProductCommentDto getData(int num) {
		ProductCommentDto dto = session.selectOne("peoductComment.getData", num);
		return dto;
	}

	@Override
	public int getCount(int ref_group) {
		int count=session.selectOne("productComment.getCount", ref_group);
		return count;
	}

}
