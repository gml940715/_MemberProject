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
		$(".signUp-btn").on("click",function(){
			location.href="signUpForm";
		})
		
		$(".login-btn").on("click",function(){
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
		
		
		
		$(".logout-btn").on("click",function(){
			location.href="logoutProc";
		})
		$(".myPage-btn").on("click",function(){
			location.href="checkPw?num=1";
		})
		$(".board-btn").on("click",function(){
			location.href="toBaordList?currentPage=1";
		})
		$(".chat-btn").on("click",function(){
			location.href="toChat";
		})
		if($("img").attr("src") == ""){
			$("img").attr("src","/resources/noimage.png");
		}
		
	})
</script>
<style>
	img{width:200px; height:100%; margin-top:10px;}
	 #wrapper1{ width: 300px; height: 400px; margin: auto; margin-top: 150px; box-sizing: border-box; overflow: hidden;}
        .home{background-color:#ABD0CE; text-align: center; font-size: 20px; font-weight: bold; color:#7C7877; 
        		height:10%; width: 100%; line-height: 30px;}
        .profileImg{height: 40%; width: 100%; text-align: center; background-color: #F0E5DE;}
        .welcome{height: 20%; width: 100%; text-align: center; line-height: 60px; background-color: #F0E5DE;}
        .btn-box{height: 30%; width: 100%; background-color: #F0E5DE;}
        .btn-box div{float: left; width: 50%; height: 100%; text-align: center;}
        input[type="button"]{background-color: #ABD0CE; border: none; height: 30px; width: 100px; font-size: 15px; color: #7C7877;} 
	/*----------------------------------------------------------------------------------*/
   #wrapper2{width: 300px; height: 200px; margin: auto; margin-top: 150px; box-sizing: border-box; overflow: hidden;}
    #header{text-align: center; height: 30px; line-height: 32px; background-color: #ABD0CE; font-weight: bold; color: #7C7877;}
    #contents{background-color: #F0E5DE; height: 130px; width: 100%;}
    .title{width: 30%; height: 100%; }
    .title div{height: 50%; width: 100%; text-align: right; }
    .title div:first-child{line-height: 100px;}
    .title div:nth-child(2){line-height: 50px;}
    #id{position:relative; top: 35px;}
    #password{position: relative; top:15px;}
    .input-box{width: 70%; height: 100%; box-sizing: border-box;}
    .input-box div{height: 50%; position: relative; left: 10px;} 
    .title,.input-box{float: left; height: 130px;}
    input[type="text"],input[type="password"]{background-color: #D9D4CF; border: none; height: 20px;}
    #footer{height:40px; background-color: #F0E5DE; text-align: center;}
    input[type="button"]{background-color: #ABD0CE; border: none; height: 25px;  color: #7C7877; font-size: 15px; cursor: pointer;}
    .signUp-btn{width: 65px;}
</style>
</head>
<body>
<c:choose>
	<c:when test="${loginId != null}">
		 <div id="wrapper1">
		  <div class="home">H O M E</div>
        <div class="profileImg"><img src='${profile.image }'></div>
        <div class="welcome">${loginId }님 환영합니다.<br></div>
        <div class="btn-box">
            <div>
                <input type="button" value="로그아웃" class="logout-btn"><br><br>
                <input type="button" value="게시판" class="board-btn">
            </div>
            <div>
                <input type="button" value="마이페이지" class="myPage-btn"><br><br>
                <input type="button" value="채팅" class="chat-btn">
            </div>
        </div>
    </div>
	</c:when>
	<c:otherwise>
	  <form action="loginProc" method="post" id="loginForm">
    <div id="wrapper2">
        <div id="header">L O G I N</div>
       
        <div id="contents">
        <div class="title">
            <div>아이디</div>
            <div>비밀번호</div>
            
        </div>
        <div class="input-box">
            <div><input type="text" name="id" id="id" placeholder="아이디"></div>
            <div><input type="password"name="password" id="password" placeholder="비밀번호"></div>
        </div>
    </div>
    <div id="footer">
        <input type="button" class="login-btn" value="로그인">
        <input type="button" class="signUp-btn" value="회원가입">
    </div>
    </div>
     </form>
   ${msg}
	</c:otherwise>
</c:choose>
	
</body>
</html>