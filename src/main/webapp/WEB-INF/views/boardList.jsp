<!DOCTYPE html>
<html lang="ko">
<head>
<style type="text/css">
/* 03_community.css */
/* 아래에 코드를 작성해 주세요. */

.writeBtn{
float:right;
} 

.main {
  margin-top: 120px;
  margin-right: 70px;
  margin-bottom: 120px;
  margin-left: 70px;
}

.a_style {
  text-decoration: none;
}

h1 {
  font-weight: bold;
  text-align: center;
}

aside {
  float: left;
}

section {
  float: right;
  width: 900px;
  
 
/* 01_nav_footer.css */
/* 아래에 코드를 작성해 주세요. */


.nav_factor {
  text-decoration: none;
  color: white;
  margin-right: 2rem;
}

footer {
  height: 60px;
}  
}
</style>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="com.example.demo.vo.BoardVO, java.util.List" %>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
  <!-- Custom CSS -->
  <link rel="stylesheet" href="01_nav_footer.css">
  <link rel="stylesheet" href="03_community.css">

  <title>Community</title>
<script type="text/javascript">
function a(url){
	const id=getCookie("id");
	if(id){
		location.href=url;
	}else{
	    alert("로그인 후 글쓰기가 가능합니다.")
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

  <!-- 01_nav_footer.html -->
  <!-- 01_nav_footer 에서 완성한 Navigation bar 코드를 붙여넣어 주세요. -->
  <nav class="d-flex fixed-top align-items-center justify-content-between navbar navbar-expand-md navbar-dark bg-dark fixed-top ">
    <a href="02_home.html"><img  src="./images/logo.png"   height="50px" alt=""></img></a>
    <div class="me-2">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="02_home.html">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-white" href="03_community.html">Community</a>

      
          </li>
          <li class="nav-item">
            <a class="nav-link" href="exampleModal" data-bs-toggle="modal" data-bs-target="#exampleModal">Login</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Login</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form>
            <div class="mb-3">
              <label for="exampleInputEmail1" class="form-label">Email address</label>
              <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
              <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
            </div>
            <div class="mb-3">
              <label for="exampleInputPassword1" class="form-label">Password</label>
              <input type="password" class="form-control" id="exampleInputPassword1">
            </div>
            <div class="mb-3 form-check">
              <input type="checkbox" class="form-check-input" id="exampleCheck1">
              <label class="form-check-label" for="exampleCheck1">Check me out</label>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="submit" class="btn btn-primary">Submit</button>
        </div>
      </div>
    </div>
  </div>

  <!-- 03_community.html -->
  <div class="main">
    <h1>Community</h1>
      
    <!-- Sidebar -->   
    <aside class="row row-cols-lg-1 mt-5">
      <ul class="list-group">
        <li class="list-group-item text-primary col-lg-12">
          <a href="#" class="a_style">Boxoffice</a>
        </li>
        <li class="list-group-item text-primary col-lg-12">
          <a href="#" class="a_style">Movies</a>
        </li>
        <li class="list-group-item text-primary col-lg-12">
          <a href="#" class="a_style">Genres</a> 
        </li>
        <li class="list-group-item text-primary col-lg-12">
          <a href="#" class="a_style">Actors</a>
        </li>
      </ul>
    </aside>
    <!-- Board -->
 
    <section>
   
      <div class="row row-cols-lg-10 mt-5">
        <table class="table table-striped table-hover">
          <thead class="table-dark">
      <tr><th>글번호</th><th>제목</th><th>작성자</th><th>작성일</th><tr>
	<c:forEach items="${boardList}" var="article">
	<tr>
		<td>${article.no }</td><td><a href="viewArticle?no=${article.no }">
		<c:choose>
               <c:when test='${article.lvl > 0 }'>  
                  <c:forEach begin="1" end="${article.lvl }" step="1">
         <!--   <span style="padding-left:20px"></span> -->   ↪  
                  </c:forEach>
              </c:when>
             </c:choose>
		${article.title }</a></td>
		<td>${article.id }</td><td>${article.writeDate }</td>
	</tr>
	</c:forEach>
          </thead>
          <tbody>
            <tr>
              <th scope="row" class="fw-bold">Greatest Movie Title</th>
              <td>Best movie ever!!</td>
              <td>user</td>
              <td>1 minutes ago</td>
            </tr>
            <tr>
              <th scope="row" class="fw-bold">Greatest Movie Title</th>
              <td>Best movie ever!!</td>
              <td>user</td>
              <td>1 minutes ago</td>
            </tr>
            <tr>
              <th scope="row" class="fw-bold">Greatest Movie Title</th>
              <td>Best movie ever!!</td>
              <td>user</td>
              <td>1 minutes ago</td>
            </tr>
            <tr>
              <th scope="row" class="fw-bold">Greatest Movie Title</th>
              <td>Best movie ever!!</td>
              <td>user</td>
              <td>1 minutes ago</td>
            </tr>
            <tr>
              <th scope="row" class="fw-bold">Greatest Movie Title</th>
              <td>Best movie ever!!</td>
              <td>user</td>
              <td>1 minutes ago</td>
            </tr>
            <tr>
              <th scope="row" class="fw-bold">Greatest Movie Title</th>
              <td>Best movie ever!!</td>
              <td>user</td>
              <td>1 minutes ago</td>
            </tr>
          </tbody>
        </table>
    
      </div>


      <footer>
      <nav aria-label="Page navigation example" class="d-flex justify-content-around mt-3">
        <ul class="pagination">
         <a href="javascript:a('boardWriteForm')"><li class="btn btn-light writeBtn"> 글쓰기</li></a>
          <li class="page-item"><a class="page-link" href="#">Previous</a></li>
          <li class="page-item"><a class="page-link" href="#">1</a></li>
          <li class="page-item"><a class="page-link" href="#">2</a></li>
          <li class="page-item"><a class="page-link" href="#">3</a></li>
          <li class="page-item"><a class="page-link" href="#">Next</a></li>
        </ul>
      </nav>
    </section>
  </div>
  </footer>

  
  
  <!-- Bootstrap JS -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>  
</body>
</html>