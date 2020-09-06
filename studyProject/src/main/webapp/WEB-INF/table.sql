CREATE TABLE users(
	id VARCHAR2(100) PRIMARY KEY,
	pwd VARCHAR2(100) NOT NULL,
	email VARCHAR2(100),
	profile VARCHAR2(100),
	regdate DATE
);

create table board (
	num number primary key,
	writer VARCHAR2(100) NOT NULL,
	title VARCHAR2(100) NOT NULL,
	content CLOB,
	viewCount NUMBER,
	regdate DATE
);

create sequence board_seq;

create table product (
	num number primary key,
	writer varchar2(100) not null,
	name varchar2(100) not null,
	content clob,
	price long not null,
	image varchar2(300),
	regdate date
);

create sequence product_seq;


create table product_comment (
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(100),
	content VARCHAR2(500), 
	target_id VARCHAR2(100),
	ref_group NUMBER,
	comment_group NUMBER,
	deleted CHAR(3) DEFAULT 'no',
	regdate DATE
);

CREATE SEQUENCE product_comment_seq;