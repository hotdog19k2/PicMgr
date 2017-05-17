<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table,tr,td{
		border: 1px solid black;
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
		height:500px;
		width:300px;
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
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$("form[name='f1']").submit(function() { 
		if($("#password").val() != $("#password2").val()){
            alert("패스워드가 일치하지 않습니다.");
			return false;            
        }
	});
});
</script>
</head>
<body>
	<div id="header">
		<h1>사진 관리 프로그램</h1>
	</div>
		<div id="content">
			<a href="user/join">회원가입</a>
			<a href="user/login">로그인</a>
		</div>		
		 ${LOGIN.uid }
		 	<div id="footer">
		<h4>HOTDOG @ 2017</h4>
	</div>
</body>
</html>