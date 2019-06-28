<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
<script>
	$(function(){
		$(".signUpBtn").on("click",function(){
			location.href="signUpForm";
		})
		
		$(".loginBtn").on("click",function(){
		if($("#id").val() == "" && $("#password").val() == ""){
			alert("아이디와 비밀번호를 입력해주세요.");
		}else{
				$("#loginForm").submit();
		}});
		
		$("#id").keypress(function(e){
			if(e.keyCode==13){
				if($("#id").val() == "" && $("#password").val() == ""){
					alert("아이디와 비밀번호를 입력해주세요.");
				}else{
						$("#loginForm").submit();
				}
			}
		});
		$("#password").keypress(function(e){
			if(e.keyCode==13){
				if($("#id").val() == "" && $("#password").val() == ""){
					alert("아이디와 비밀번호를 입력해주세요.");
				}else{
						$("#loginForm").submit();
				}
			}
		});
		
		
		
		$(".logoutBtn").on("click",function(){
			location.href="logoutProc";
		})
		$(".myPageBtn").on("click",function(){
			location.href="checkPw?num=1";
		})
		$(".boardBtn").on("click",function(){
			location.href="toBaordList?currentPage=1";
		})
		$(".chatBtn").on("click",function(){
			location.href="toChat";
		})
		if($("img").attr("src") == ""){
			$("img").attr("src","/resources/noimage.png");
		}
		
	})
</script>
<style>
	img{width:200px; height:200px;}
	input[type="button"]{cursor: pointer;}
</style>
</head>
<body>
<c:choose>
	<c:when test="${loginId != null}">
		<div id="wrapper">
		<div><img src='${profile.image }'></div>
		${loginId }님 환영합니다.<br>
		<input type="button" value="로그아웃" class="logoutBtn">
		<input type="button" value="마이페이지" class="myPageBtn">
		<input type="button" value="게시판" class="boardBtn">
		<input type="button" value="채팅하기" class="chatBtn">
		</div>
	</c:when>
	<c:otherwise>
		<div id="wrapper">
       <form action="loginProc" method="post" id="loginForm">
        <input type="text" name="id" placeholer="아이디" id="id"><br>
        <input type="password" name="password" placeholer="비밀번호" id="password"><br>
        <input type="button" value="로그인" class="loginBtn">
        <input type="button" value="회원가입" class="signUpBtn">
        </form>
    </div>
   ${msg}
	</c:otherwise>
</c:choose>
	
</body>
</html>