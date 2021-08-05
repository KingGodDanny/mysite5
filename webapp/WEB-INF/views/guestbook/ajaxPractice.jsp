<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>



</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<div id="container" class="clearfix">
		
			<!-- 방명록 aside -->
			<c:import url="/WEB-INF/views/includes/asideGuestBook.jsp"></c:import>
			<!-- //방명록 aside -->

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form action="" method="">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label>
									</td>
									<td>
										<input id="input-uname" type="text" name="name" value="">
									</td>
									<th><label class="form-text" for="input-pass">패스워드</label>
									</td>
									<td>
										<input id="input-pass" type="password" name="pass" value="">
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<textarea name="content" cols="72" rows="5"></textarea>
									</td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center">
										<button id="btnSubmit" type="submit">등록</button>
									</td>
								</tr>
							</tbody>

						</table>

					</form>
	
					<div id="listArea">
<!-- 						jquery 리스트 그리는 영역 -->
					
					</div>
	
			

					
				</div>
				<!-- //guestbook -->

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<!-- 푸터 -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

	</div>
	<!-- //wrap -->

</body>

<script type="text/javascript">


//화면이 로딩되기 직전  --첫번째 순서
$(document).ready(function () {
	console.log("화면 로딩되기 직전 출력!")
	
	//ajax 요청하기
	$.ajax({
			url : "${pageContext.request.contextPath }/api/guestbook/list",		//요청을위한 4줄->먼저 요청한다!
			type : "post",
// 			contentType : "application/json",
// 			data : {name: ”홍길동"},
	
			dataType : "json",
			success : function(guestbookList){	//요청후에 정보가 괄호안에 담길것이고 이름은 내맘대로!
				/*성공시 처리해야될 코드 작성*/
				console.log(guestbookList); 	//확인을 항상해주기
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	
	
});




//로딩이 끝난후에 -- 딱 링크를 치고나온후에
//등록버튼 클릭!
$("#btnSubmit").on("click", function () { //그냥 등록이벤트만 해서 콘솔을 띄우려하면 기존방식으로 처리되려한다.
	event.preventDefault();				  //그래서 반드시 이 코드를 넣어줘야한다.
	console.log("등록버튼 클릭!!!")
	
	
});




</script>