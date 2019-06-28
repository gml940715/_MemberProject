<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
<script>
	$(function(){
		$(".toList-btn").on("click",function(){
			location.href="toBaordList?currentPage=${currentPage}";
		})
		$(".delete-btn").on("click",function(){
			var result = confirm("글을 삭제하시겠습니까?");
			if(result == true){
				location.href="deletePost?seq=${content.seq}";
			}
		})
		
	})
</script>
<style>
    
    .container{width: 800px; min-height: 500px; margin: auto; margin-top:250px; margin-bottom:100px;}
    .domain{ height: 50px; background-color: #a79c8e;}
    .domain div:first-child{font-size: 30px; font-weight: bold;}
    .title{height: 50px;background-color: #f1bbba; }
    .title div:first-child{background-color:#eb9f9f; font-size:25px; line-height: 45px;}
    .title div:nth-child(2){font-size:20px; line-height: 45px;}
    .title div:first-child,.domain div:first-child{text-align: center;}
    .contents{background-color: #f8ecc9; box-sizing: border-box; }
    .contents div{overflow:auto; word-wrap: break-word; height:600px;}
    .footer{text-align:right;}
     input[type="button"]{position: relative; top: 10px; background-color: #eb9f9f; border: none;}
     input[type="button"]:hover{font-weight:bold;}
    </style>
</head>
<body>
    
        <div class="container">
            <div class="row domain">
                 <div class="col-lg-12 col-md-12  col-sm-12 col-12 ">P O S T</div>
            </div>
            <div class="row title">
                <div class="col-lg-3 col-md-3  col-sm-3 col-12 ">T I T L E</div>
                <div class="col-lg-9 col-md- col-sm-9 col-12">${content.title }</div>
            </div>
            <div class="row  contents">
                <div class="col-lg-12 col-md-12 col-sm-12 col-12 ">${content.contents }</div>
            </div>
             <div class="row footer">
             	<div class="col-lg-12 col-md-12 col-sm-12 col-12">
             		<input type="button" class="toList-btn" value="목록">
             		<c:choose>
             			<c:when test="${content.writer == loginId }">
             				<input type="button" class="delete-btn" value="삭제">
             				<input type="button" class="alter-btn" value="수정">
             			</c:when>
             			<c:otherwise>
             				<input type="button" class="delete-btn" value="삭제" hidden>
             				<input type="button" class="alter-btn" value="수정" hidden>
             			</c:otherwise>
             		</c:choose>
             	</div>
             </div>
        </div>
        
    
</body>
</html>