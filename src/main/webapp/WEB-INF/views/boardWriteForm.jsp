<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	const id=$.cookie("id");
	
	if(id){
		$("#id").val(id);
	}else{
		alert("로그인후 글쓰기가 가능합니다.");
	}
	
});
</script>
</head>
<body>
	<div  class="container">
<h3 style="text-align:center">글쓰기</h3>
<form action="boardWrite" method="post" enctype="multipart/form-data">
<table class="table table-border">
	<tr><td>작성자</td><td><input name="id"id="id" readonly style="background:lightgray"></td></tr>
	<tr><td>글제목</td><td><input name="title"></td></tr>
	<tr><td>글내용</td><td><textarea rows ="8" cols="10" name="content"></textarea></td></tr>
	<tr><td>파일첨부</td><td><input type="file" name="file"></td><tr>


</table>
<center>
<input type="submit" value="글쓰기" class="btn btn-info">

</center>
</form>
</div>
</body>
</html>