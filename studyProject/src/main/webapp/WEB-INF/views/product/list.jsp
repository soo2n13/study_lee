<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/cafe/list.jsp</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<style>
	.card-img-top {
		height: 400px;
	}
</style>
</head>
<body>
<jsp:include page="/resources/head/header.jsp"></jsp:include>
	<div class="container">
		<a href="private/insertform.do">상품 등록</a>
		<h1>상품 판매</h1>
		
		<c:forEach var="tmp" items="${list}" varStatus="status">
			<c:if test="${status.count mod 3 eq 1 }">
				<div class="row">
			</c:if>
					<div class="card col-4">
						<img class="card-img-top"
							src="${pageContext.request.contextPath }${tmp.image}" />
						<div class="card-body">
							<h3 class="card-title">${tmp.name }</h3>
							<p class="card-text">
								가격 : <fmt:formatNumber type="currency" value="${tmp.price }" />	
							</p>
							<a href="detail.do?num=${tmp.num }" class="card-link">자세히</a>
						</div>
					</div>
			<c:if test="${status.count mod 3 eq 0 or status.last }">
				</div>
			</c:if>
		</c:forEach>
		<div class="page-display">
			<ul class="pagination pagination-sm justify-content-center">
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
		<div class="d-flex justify-content-center">
			<form action="list.do" method="get">
				<label for="keyword">Search</label> 
				<input value="${keyword }" type="text" name="keyword" id="keyword"
					placeholder="제목만 검색..." />
				<button type="submit">검색</button>
			</form>
		</div>
	</div>
</body>
</html>






