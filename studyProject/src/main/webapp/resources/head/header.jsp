<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-5">
	<a class="navbar-brand" href="${pageContext.request.contextPath}/home.do">Main</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarsExampleDefault"
		aria-controls="navbarsExampleDefault" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarsExampleDefault">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/product/list.do">상품</a></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/board/list.do">게시판</a></li>
		</ul>
		<c:choose>
			<c:when test="${empty id}">
				<a href="${pageContext.request.contextPath}/users/loginform.do" class="mr-3">로그인</a>
				<a href="${pageContext.request.contextPath}/users/signup_form.do">회원가입</a>
			</c:when>
			<c:otherwise>
				<span class="text-white mr-3"><a href="${pageContext.request.contextPath}/users/private/info.do">${id}</a>님 로그인을 환영합니다.</span>
				<a href="${pageContext.request.contextPath}/users/logout.do">로그아웃</a>
			</c:otherwise>
		</c:choose>
		
	</div>
</nav>
