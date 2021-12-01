<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.example.demo.vo.BoardVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>

	<div  class="container">
<h3 style="text-align:center">${article.no }번 글보기</h3>

<form action="replyWriteForm" method="post">
      <input type="hidden" name="no" value="${article.no }" >
<table class="table table-border">
	<tr><td>작성자</td><td><input value="${article.id }"  readonly style="background:lightgray"></td></tr>
	<tr><td>글제목</td><td><input  value="${article.title }" readonly style="background:lightgray"></td></tr>
	<tr><td>글내용</td><td><textarea rows ="8" cols="10" readonly style="background:lightgray" >${article.content}</textarea></td></tr>
	<tr><td>첨부된파일</td><td><img src="uploadImg/${article.fileName }" height="50"></td><tr>


</table>
<center>
<input type="submit" value="답글쓰기" class="btn btn-info">
&nbsp;&nbsp; <p class="btn btn-info" onclick="history.back()">목록으로 가기</p>
&nbsp;&nbsp; <p class="btn btn-info" onclick="window.close()">홈으로</p>
</center>
</form>
</div>
</body>
</html>