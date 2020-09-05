<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
	<a class="navbar-brand" href="${pageContext.request.contextPath}/home.do">Main</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarsExampleDefault"
		aria-controls="navbarsExampleDefault" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarsExampleDefault">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a class="nav-link" href="product/list.do">상품</a></li>
			<li class="nav-item"><a class="nav-link" href="board/list.do">게시판</a></li>
		</ul>
		<a href="user/loginform.do">로그인</a>
	</div>
</nav>
