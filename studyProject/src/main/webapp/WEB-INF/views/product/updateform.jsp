<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/cafe/updateform.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<style>
	#content{
		width: 100%;
		height: 300px;
	}
</style>
</head>
<body>
<jsp:include page="/resources/head/header.jsp"></jsp:include>
<div class="container">
	<h1>상품 수정 양식 입니다.</h1>
	<form action="image_upload.do" method="post" enctype="multipart/form-data" id="imgForm">
		<div class="form-group">
			<label for="img">첨부 이미지</label>
			<input class="form-control" type="file" name="img" 
			accept=".jpg, .jpeg, .png, .JPG, .JPEG" id="img"/>
		</div>
	</form>
	<form action="update.do" method="post">
		<input type="hidden" name="image" id="image" value="${dto.image }"/>
		<input type="hidden" name="num" value="${dto.num }"/>
		<div class="form-group">
			<label for="num">상품번호</label>
			<input class="form-control" type="text" id="num" value="${dto.num }" disabled/>
		</div>
		<div class="form-group">
			<label for="title">상품명</label>
			<input class="form-control" type="text" id="name" name="name" 
				value="${dto.name }"/>
		</div>
		<div class="form-group">
			<label for="price">상품가</label>
			<input class="form-control" type="text" id="price" name="price" 
				value="${dto.price }"/>
		</div>
		<div class="form-group">
			<label for="content">내용</label>
			<textarea class="form-control" name="content" id="content">${dto.content }</textarea>
		</div>
		<button class="btn btn-outline-primary" type="submit" onclick="submitContents(this);">수정확인</button>
		<button class="btn btn-outline-warning" type="reset">취소</button>
	</form>
</div>
<!-- SmartEditor 에서 필요한 javascript 로딩  -->
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.5.1.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/jquery.form.min.js"></script>
<script>
	//이미지를 선택했을때 실행할 함수 등록
	$("#img").on("change", function(){
		$("#imgForm").submit();
	});
	
	//폼이 ajax 로 제출될수 있도록 플러그인을 동작 시킨다.
	$("#imgForm").ajaxForm(function(data){
		console.log(data);
		$("#image").val(data.imageSrc);
	});
</script>
<script src="${pageContext.request.contextPath }/SmartEditor/js/HuskyEZCreator.js"></script>
<script>
	var oEditors = [];
	
	//추가 글꼴 목록
	//var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];
	
	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: "content",
		sSkinURI: "${pageContext.request.contextPath}/SmartEditor/SmartEditor2Skin.html",	
		htParams : {
			bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
			fOnBeforeUnload : function(){
				//alert("완료!");
			}
		}, //boolean
		fOnAppLoad : function(){
			//예제 코드
			//oEditors.getById["ir1"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
		},
		fCreator: "createSEditor2"
	});
	
	function pasteHTML() {
		var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
		oEditors.getById["content"].exec("PASTE_HTML", [sHTML]);
	}
	
	function showHTML() {
		var sHTML = oEditors.getById["content"].getIR();
		alert(sHTML);
	}
		
	function submitContents(elClickedObj) {
		oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
		
		// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("content").value를 이용해서 처리하면 됩니다.
		
		try {
			elClickedObj.form.submit();
		} catch(e) {}
	}
	
	function setDefaultFont() {
		var sDefaultFont = '궁서';
		var nFontSize = 24;
		oEditors.getById["content"].setDefaultFont(sDefaultFont, nFontSize);
	}
</script>
</body>
</html>





