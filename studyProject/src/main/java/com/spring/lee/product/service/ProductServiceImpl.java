package com.spring.lee.product.service;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.lee.product.dao.ProductCommentDao;
import com.spring.lee.product.dao.ProductDao;
import com.spring.lee.product.dto.ProductCommentDto;
import com.spring.lee.product.dto.ProductDto;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductCommentDao productCommentDao;

	final int PAGE_ROW_COUNT = 9;
	final int PAGE_DISPLAY_COUNT = 5;

	@Override
	public void getList(HttpServletRequest request) {
		// 보여줄 페이지의 번호
		int pageNum = 1;
		// 보여줄 페이지의 번호가 파라미터로 전달되는지 읽어와 본다.
		String strPageNum = request.getParameter("pageNum");
		if (strPageNum != null) {// 페이지 번호가 파라미터로 넘어온다면
			// 페이지 번호를 설정한다.
			pageNum = Integer.parseInt(strPageNum);
		}
		// 보여줄 페이지 데이터의 시작 ResultSet row 번호
		int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
		// 보여줄 페이지 데이터의 끝 ResultSet row 번호
		int endRowNum = pageNum * PAGE_ROW_COUNT;
		/*
		 * 검색 키워드에 관련된 처리
		 */
		String keyword = request.getParameter("keyword"); // 검색 키워드
		String condition = request.getParameter("condition"); // 검색 조건
		if (keyword == null) {// 전달된 키워드가 없다면
			keyword = ""; // 빈 문자열을 넣어준다.
			condition = "";
		}
		// 인코딩된 키워드를 미리 만들어 둔다.
		String encodedK = URLEncoder.encode(keyword);

		// 검색 키워드와 startRowNum, endRowNum 을 담을 FileDto 객체 생성
		ProductDto dto = new ProductDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);

		if (!keyword.equals("")) { // 만일 키워드가 넘어온다면

		}
		// 파일 목록 얻어오기
		List<ProductDto> list = productDao.getList(dto);
		// 전체 row 의 갯수
		int totalRow = productDao.getCount(dto);

		// 전체 페이지의 갯수 구하기
		int totalPageCount = (int) Math.ceil(totalRow / (double) PAGE_ROW_COUNT);
		// 시작 페이지 번호
		int startPageNum = 1 + ((pageNum - 1) / PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
		// 끝 페이지 번호
		int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;
		// 끝 페이지 번호가 잘못된 값이라면
		if (totalPageCount < endPageNum) {
			endPageNum = totalPageCount; // 보정해준다.
		}

		// EL 에서 사용할 값을 미리 request 에 담아두기
		request.setAttribute("list", list);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("condition", condition);
		request.setAttribute("keyword", keyword);
		request.setAttribute("encodedK", encodedK);

	}

	@Override
	public void getDetail(HttpServletRequest request) {
		// 파라미터로 전달되는 글번호
		int num = Integer.parseInt(request.getParameter("num"));
		/*
		 * 검색 키워드에 관련된 처리
		 */
		String keyword = request.getParameter("keyword"); // 검색 키워드
		
		if (keyword == null) {// 전달된 키워드가 없다면
			keyword = ""; // 빈 문자열을 넣어준다.
		}
		// 인코딩된 키워드를 미리 만들어 둔다.
		String encodedK = URLEncoder.encode(keyword);

		// 글번호와 검색 키워드를 담을 CafeDto 객체 생성
		ProductDto dto = new ProductDto();
		dto.setNum(num);// 글번호 담기

		if (!keyword.equals("")) { // 만일 키워드가 넘어온다면
			dto.setName(keyword);
		}
		// 자세히 보여줄 글 정보
		ProductDto resultDto = productDao.getData(num);

		// view 페이지에서 필요한 내용 HttpServletRequest 에 담기
		request.setAttribute("dto", resultDto);
		request.setAttribute("keyword", keyword);
		request.setAttribute("encodedK", encodedK);

		/* 아래는 댓글 페이징 처리 관련 비즈니스 로직 입니다. */
		final int PAGE_ROW_COUNT = 5;
		final int PAGE_DISPLAY_COUNT = 5;

		// 전체 row 의 갯수를 읽어온다.
		// 자세히 보여줄 글의 번호가 ref_group 번호 이다.
		int totalRow = productCommentDao.getCount(num);

		// 보여줄 페이지의 번호(만일 pageNum 이 넘어오지 않으면 가장 마지막 페이지)
		String strPageNum = request.getParameter("pageNum");
		// 전체 페이지의 갯수 구하기
		int totalPageCount = (int) Math.ceil(totalRow / (double) PAGE_ROW_COUNT);
		// 일단 마지막 페이지의 댓글 목록을 보여주기로 하고
		int pageNum = totalPageCount;
		// 만일 페이지 번호가 넘어온다면
		if (strPageNum != null) {
			// 넘어온 페이지에 해당하는 댓글 목록을 보여주도록 한다.
			pageNum = Integer.parseInt(strPageNum);
		}
		// 보여줄 페이지 데이터의 시작 ResultSet row 번호
		int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
		// 보여줄 페이지 데이터의 끝 ResultSet row 번호
		int endRowNum = pageNum * PAGE_ROW_COUNT;

		// 시작 페이지 번호
		int startPageNum = 1 + ((pageNum - 1) / PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
		// 끝 페이지 번호
		int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;
		// 끝 페이지 번호가 잘못된 값이라면
		if (totalPageCount < endPageNum) {
			endPageNum = totalPageCount; // 보정해준다.
		}

		// CafeCommentDto 객체에 위에서 계산된 startRowNum 과 endRowNum 을 담는다.
		ProductCommentDto commentDto = new ProductCommentDto();
		commentDto.setStartRowNum(startRowNum);
		commentDto.setEndRowNum(endRowNum);
		// ref_group 번호도 담는다.
		commentDto.setRef_group(num);

		// DB 에서 댓글 목록을 얻어온다.
		List<ProductCommentDto> commentList = productCommentDao.getList(commentDto);
		// request 에 담아준다.
		request.setAttribute("commentList", commentList);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("pageNum", pageNum);
	}

	@Override
	public void saveProduct(ProductDto dto) {
		productDao.insert(dto);
	}

	@Override
	public void updateProduct(ProductDto dto) {
		productDao.update(dto);

	}

	@Override
	public void deleteProduct(int num) {
		productDao.delete(num);
		productCommentDao.groupDelete(num);

	}

	@Override
	public void saveComment(HttpServletRequest request) {
		// TODO Auto-generated method stub
		// 댓글 작성자
		String writer = (String) request.getSession().getAttribute("id");
		// 폼 전송되는 댓글의 정보 얻어내기
		int ref_group = Integer.parseInt(request.getParameter("ref_group"));
		String target_id = request.getParameter("target_id");
		String content = request.getParameter("content");
		/*
		 * 원글의 댓글은 comment_group 번호가 전송이 안되고 댓글의 댓글은 comment_group 번호가 전송이 된다. 따라서 null
		 * 여부를 조사하면 원글의 댓글인지 댓글의 댓글인지 판단할수 있다.
		 */
		String comment_group = request.getParameter("comment_group");
		// 새 댓글의 글번호는 dao 를 이용해서 미리 얻어낸다.
		int seq = productCommentDao.getSequence();

		// 저장할 댓글 정보를 dto 에 담기
		ProductCommentDto dto = new ProductCommentDto();
		dto.setNum(seq);
		dto.setWriter(writer);
		dto.setTarget_id(target_id);
		dto.setContent(content);
		dto.setRef_group(ref_group);
		if (comment_group == null) {// 원글의 댓글인 경우
			// 댓글의 글번호가 comment_group 번호가 된다.
			dto.setComment_group(seq);
		} else {// 댓글의 댓글인 경우
				// 폼 전송된 comment_group 번호를 숫자로 바꿔서 dto 에 넣어준다.
			dto.setComment_group(Integer.parseInt(comment_group));
		}
		// 댓글 정보를 DB 에 저장한다.
		productCommentDao.insert(dto);
	}

	@Override
	public void deleteComment(HttpServletRequest request) {
		// GET 방식 파라미터로 전달되는 삭제할 댓글 번호
		int num = Integer.parseInt(request.getParameter("num"));
		// 세션에 저장된 로그인된 아이디
		String id = (String) request.getSession().getAttribute("id");
		// 댓글의 정보를 얻어와서 댓글의 작성자와 같은지 비교 한다.
		String writer = productCommentDao.getData(num).getWriter();
		if (!writer.equals(id)) {
			// throw new NotDeleteException("남의 댓글을 삭제할수 없습니다.");
		}
		productCommentDao.delete(num);

	}

	@Override
	public void updateComment(ProductCommentDto dto) {
		productCommentDao.update(dto);
	}

	@Override
	public void moreCommentList(HttpServletRequest request) {
		// 파라미터로 전달된 pageNum 과 ref_group 번호를 읽어온다.
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int ref_group = Integer.parseInt(request.getParameter("ref_group"));

		ProductDto dto = productDao.getData(ref_group);
		request.setAttribute("dto", dto);

		/* 아래는 댓글 페이징 처리 관련 비즈니스 로직 입니다. */
		final int PAGE_ROW_COUNT = 5;

		// 보여줄 페이지 데이터의 시작 ResultSet row 번호
		int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
		// 보여줄 페이지 데이터의 끝 ResultSet row 번호
		int endRowNum = pageNum * PAGE_ROW_COUNT;

		// 전체 row 의 갯수를 읽어온다.
		// 자세히 보여줄 글의 번호가 ref_group 번호 이다.
		int totalRow = productCommentDao.getCount(ref_group);
		// 전체 페이지의 갯수 구하기
		int totalPageCount = (int) Math.ceil(totalRow / (double) PAGE_ROW_COUNT);

		// ProductCommentDto 객체에 위에서 계산된 startRowNum 과 endRowNum 을 담는다.
		ProductCommentDto commentDto = new ProductCommentDto();
		commentDto.setStartRowNum(startRowNum);
		commentDto.setEndRowNum(endRowNum);
		// ref_group 번호도 담는다.
		commentDto.setRef_group(ref_group);

		// DB 에서 댓글 목록을 얻어온다.
		List<ProductCommentDto> commentList = productCommentDao.getList(commentDto);
		// request 에 담아준다.
		request.setAttribute("commentList", commentList);
		request.setAttribute("totalPageCount", totalPageCount);
	}
	
	@Override
	public Map<String, Object> saveImage(HttpServletRequest request, MultipartFile mFile) {
		//원본 파일명
		String orgFileName=mFile.getOriginalFilename();
		// webapp/upload 폴더 까지의 실제 경로(서버의 파일시스템 상에서의 경로)
		String realPath=request.getServletContext().getRealPath("/upload");
		//저장할 파일의 상세 경로
		String filePath=realPath+File.separator;
		//디렉토리를 만들 파일 객체 생성
		File upload=new File(filePath);
		if(!upload.exists()) {//만일 디렉토리가 존재하지 않으면 
			upload.mkdir(); //만들어 준다.
		}
		//저장할 파일 명을 구성한다.
		String saveFileName=
				System.currentTimeMillis()+orgFileName;
		try {
			//upload 폴더에 파일을 저장한다.
			mFile.transferTo(new File(filePath+saveFileName));
			System.out.println(filePath+saveFileName);
		}catch(Exception e) {
			e.printStackTrace();
		}
		//Map 에 업로드된 이미지 파일의 경로를 담아서 리턴한다
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("imageSrc","/upload/"+saveFileName);
		
		return map;
	}

}
