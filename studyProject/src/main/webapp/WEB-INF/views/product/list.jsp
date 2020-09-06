<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/cafe/list.jsp</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
<jsp:include page="/resources/head/header.jsp"></jsp:include>
	<div class="container">
		<a href="private/insertform.do">상품 등록</a>
		<h1>상품 판매</h1>
		
		<c:forEach var="tmp" items="${list}">
			<div class="card">
				<img class="card-img-top"
					src="${pageContext.request.contextPath } ${tmp.image}" />
				<div class="card-body">
					<h3 class="card-title">${tmp.name }</h3>
					<p class="card-text">
						가격 : <strong>${tmp.price }</strong>원
					</p>
					<a href="detail.do?num=${tmp.num }" class="card-link">자세히</a>
				</div>
			</div>
		</c:forEach>
		<div class="page-display">
			<ul class="pagination pagination-sm">
				<c:if test="${startPageNum ne 1 }">
					<li class="page-item"><a class="page-link"
						href="list.do?pageNum=${startPageNum-1 }&condition=${condition }&keyword=${encodedK }">Prev</a></li>
				</c:if>
				<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
					<c:choose>
						<c:when test="${i eq pageNum }">
							<li class="page-item active"><a class="page-link"
								href="list.do?pageNum=${i }&condition=${condition }&keyword=${encodedK }">${i }</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link"
								href="list.do?pageNum=${i }&condition=${condition }&keyword=${encodedK }">${i }</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${endPageNum lt totalPageCount }">
					<li class="page-item"><a class="page-link"
						href="list.do?pageNum=${endPageNum+1 }&condition=${condition }&keyword=${encodedK }">Next</a></li>
				</c:if>
			</ul>
		</div>
		<hr style="clear: left;" />
		<form action="list.do" method="get">
			<label for="keyword">검색</label> 
			<input value="${keyword }" type="text" name="keyword" id="keyword"
				placeholder="제목만 검색..." />
			<button type="submit">검색</button>
		</form>
	</div>
</body>
</html>






