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