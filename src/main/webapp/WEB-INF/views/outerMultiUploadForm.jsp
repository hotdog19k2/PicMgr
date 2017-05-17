<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
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
</head>
<body>
	<div id="header">
		<h1>사진 관리 프로그램</h1>
	</div>
	<div id="content">
	<form action="multiUploadForm" method="post" enctype="multipart/form-data">
		<fieldset>
			
			<input type="hidden" name="uid" value="${login.uid }"> 
			작성자 : ${login.uid } <br>
			<input type="file" name="files" multiple="multiple"><br>
			<input type="submit" value="추가" /><input type="reset" value="취소">
		</fieldset>
	</form>	
	</div>
	<div id="footer">
		<h4>HOTDOG @ 2017</h4>
	</div>
</body>
</html>