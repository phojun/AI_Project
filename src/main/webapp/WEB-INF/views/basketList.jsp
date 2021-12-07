<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import= "com.example.demo.vo.ColorBoxVO"%>
<% String id=(String)session.getAttribute("id"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<script type="text/javascript">
function a(url){
	const id=getCookie("id");
	if(id){
		location.href=url;
	}else{
	    alert("로그인 후 이용 가능합니다.")
	    window.close();

	}
	
}
function getCookie(cname) {
	  let name = cname + "=";
	  let decodedCookie = decodeURIComponent(window.opener.document.cookie);
	  let ca = decodedCookie.split(';');
	  for(let i = 0; i <ca.length; i++) {
	    let c = ca[i];
	    while (c.charAt(0) == ' ') {
	      c = c.substring(1);
	    }
	    if (c.indexOf(name) == 0) {
	      return c.substring(name.length, c.length);
	    }
	  }
	  return "";
	}
</script>

</head>
<body>
	<%
	List<ColorBoxVO> list = (List<ColorBoxVO>)session.getAttribute("blist");
	System.out.println("list = " + list);
	Object obj = session.getAttribute("msg");
	if(obj=="no"){
		out.println("장바구니에 상품이 없습니다.");
	}else{
	%>
	<div align="center">
	<h3>[<%= id %>님의 ColorBox]</h3>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>퍼스널컬러</th>
			<th>모자색상</th>
			<th>상의색상</th>
			<th>하의색상</th>
			<th>신발색상</th>
		</tr>
		<%
		for(ColorBoxVO bvo:list){
		%>
		<tr>
			<td><%= bvo.getId() %></td>
			<td><%= bvo.getPColor() %> </td>
			
		</tr>
		<%
		}
		%>
	<%} %>
	</table>
	<button >수정하기</button>
	<button >돌아가기</button>
</div>
</body>
</html>