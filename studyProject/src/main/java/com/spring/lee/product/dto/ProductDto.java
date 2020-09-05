package com.spring.lee.product.dto;

public class ProductDto {
	private int num;
	private String name;
	private String content;
	private long price;
	private int classify;
	private String image;
	private String regdate;
	
	public ProductDto() {}

	public ProductDto(int num, String name, String content, long price, int classify, String image, String regdate) {
		this.num = num;
		this.name = name;
		this.content = content;
		this.price = price;
		this.classify = classify;
		this.image = image;
		this.regdate = regdate;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getClassify() {
		return classify;
	}

	public void setClassify(int classify) {
		this.classify = classify;
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
