<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">

</head>
<body>

	<div id="wrap">

		<!-- //header -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>게시판</h2>
				<ul>
					<li><a href="">일반게시판</a></li>
					<li><a href="">댓글게시판</a></li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">일반게시판</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->
	
				<div id="board">
					<div id="list">
						<form action="${pageContext.request.contextPath }/board/list2" method="get">
							<div class="form-group text-right">
								<input type="text" name="keyword" value="">
								<button type="submit" id=btn_search>검색</button>
							</div>
						</form>
						<table >
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>조회수</th>
									<th>작성일</th>
									<th>관리</th>
								</tr>
							</thead>
							
							<c:forEach items="${requestScope.listMap.boardList }" var="boardVo">
							<tbody>
							
								<tr>
									<td>${boardVo.no } </td>
									<td class="text-left"><a href="${pageContext.request.contextPath }/board/read?no=${boardVo.no }">${boardVo.title }</a></td>
									<td>${boardVo.name }</td>
									<td>${boardVo.hit }</td>
									<td>${boardVo.regDate }</td>
									
									<c:if test="${sessionScope.authUser.no eq boardVo.userNo }">
										<td><a href="${pageContext.request.contextPath }/board/delete?no=${boardVo.no }">[삭제]</a></td>
									</c:if>
								</tr>

							</tbody>
							</c:forEach>
						</table>
			
						<div id="paging">
							<ul>
							
								<c:if test = "${listMap.prev == true}">   <!-- 괄호 주의 -->
                           			<li><a href="${pageContext.request.contextPath }/board/list2?crtPage=${listMap.startPageBtnNo-1}&keyword=${param.keyword}">◀</a></li>
                        		</c:if>
                        		
                        <!-- 반복문으로 돌림 -->
		                        <!-- var은 시작 페이지 변수명 만들면됨 -->
		                        <c:forEach begin = "${listMap.startPageBtnNo}" end = "${listMap.endPageBtnNo}" step = "1" var = "page"> 
		                        	<c:choose>
		                        		<c:when test="${param.crtPage eq page }">
		                        			<li class="active"><a href="${pageContext.request.contextPath }/board/list2?crtPage=${page }&keyword=${param.keyword}">${page }</a></li>
		                        		</c:when>
		                        	
			                        	<c:otherwise>
				                           <li><a href="${pageContext.request.contextPath }/board/list2?crtPage=${page }&keyword=${param.keyword}">${page }</a></li>
			                        	</c:otherwise>
		                        	</c:choose>
		                        	
		                        </c:forEach>
                        
		                        <c:if test = "${listMap.next == true}">
		                           <li><a href="${pageContext.request.contextPath }/board/list2?crtPage=${listMap.endPageBtnNo+1}&keyword=${param.keyword}">▶</a></li>
		                        </c:if>

								
							</ul>
							
							
							<div class="clear"></div>
						</div>
						
						<c:if test="${!empty sessionScope.authUser }">
							<a id="btn_write" href="${pageContext.request.contextPath }/board/writeForm">글쓰기</a>
						</c:if>
					
					</div>
					<!-- //list -->
				</div>
				<!-- //board -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->
		
		
		<!-- //footer -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div>
	<!-- //wrap -->


</body>
</html>