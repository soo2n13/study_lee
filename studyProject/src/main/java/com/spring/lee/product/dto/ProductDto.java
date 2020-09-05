package com.spring.lee.product.dto;

import java.sql.Clob;

public class ProductDto {
	private int num;
	private String writer;
	private String name;
	private Clob content;
	private long price;
	private String image;
	private String regdate;
	
	public ProductDto() {}

	public ProductDto(int num, String writer, String name, Clob content, long price, String image, String regdate) {
		this.num = num;
		this.writer=writer;
		this.name = name;
		this.content = content;
		this.price = price;
		this.image = image;
		this.regdate = regdate;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Clob getContent() {
		return content;
	}

	public void setContent(Clob content) {
		this.content = content;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
}
