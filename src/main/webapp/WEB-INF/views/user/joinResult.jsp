<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
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

</head>
<body>
	<div id="header">
		<h1>사진 관리 프로그램</h1>
	</div>
		<div id="content">
			<div id="join">
				테스트중..
			 </div>		
			 	 <script type="text/javascript">
	self.location = "${pageContext.request.contextPath}/";
	</script> 
				
		
	
		
		</div>
		<script>
	var result = '${result}';
	if(result == "success")
	{
	alert("처리가 완료되었습니다.");
	}
		</script>
	<div id="footer">
		<h4>HOTDOG @ 2017</h4>
	</div>
</body>
</html>