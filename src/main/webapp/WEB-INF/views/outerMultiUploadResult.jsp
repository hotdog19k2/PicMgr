<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	img{
		display: inline-block;
		margin:5px;
		border: 2px solid black;
		
	}
	
	h1,h4{
		color : white;
		
	}
	#header{
		height: 100px;
		width:80%;
		background-color: #4374D9;
		margin: 0 auto;
		display: block;
		text-align: center;
		
	}
	#join{
		width : 400px;
		margin: 0 auto;
		display: block;
	}
	#content{
		height:80%;
		width:800px;
		display: block;
		margin: 0 auto;
			
	}
	
	#footer{
		height: 50px;
		width:80%;
		background-color: #4374D9;
		margin: 0 auto;
		display: block;
		text-align: center;
	}
	.del{
		bottom:10px;
		font-weight:bold;
		display: inline-block;
		position: absolute;
		z-index: 100px;
		left : 5px;
		}
		.imgBox{
		position: relative;
		display: inline-block;
		}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
<div id="header">
		<h1>사진 관리 프로그램</h1>
	</div>
	<div id="content">
	작성자 : ${login.uid } <br>
	
	<c:forEach items="${fullList }" var="item">
		<div class="imgBox">
	<img src="displayFile?fullList=${item }"><button type="button" class="del">삭제</button>
	<input type="hidden"value="${item }" class="fullname">
		</div>
	</c:forEach>
	
	<script>
	$(document).on("click", ".del", function(){
		var $button = $(this);
		var filename = $(this).next(".fullname").val();
		console.log(filename);
		 $.ajax({
			url:"deleteFile",
			type:"post",
			data:{filename : filename},
			success:function(result){
				$button.parent("div").remove();
			}
		}); 
	});
	</script>
	
	<!-- 브라우저가 이미지의 파일 경로를 가져오려는데 src가 서버의 command이면 server에 display시킴 -->
	</div>
	<div id="footer">
		<h4>HOTDOG @ 2017</h4>
	</div>
</body>
</html>