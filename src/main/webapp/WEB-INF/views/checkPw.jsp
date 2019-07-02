<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
<script>
	$(function(){
		$(".Btn").on("click",function(){
			if($(".checkPw").val() == ""){
				alert("비밀번호를 입력해주세요.");
				}else{
			$("#checkForm").submit();}
		})
		$(".toHome-btn").on("click",function(){
			location.href="/";
		})
		
	})
</script>
 <style>
        div{border: 0px solid black;}
        #wrapper{box-sizing: border-box; overflow: hidden; height: 100px; width: 300px; margin: auto; margin-top: 150px;}
        
        
        
        header{text-align: center; height: 25%; width: 100%; color: #7C7877; background-color: #ABD0CE; font-weight: bold; font-size: 20px; line-height: 25px;}
        section{height: 40%; width: 100%; background-color: #F0E5DE;}
        section div{float: left;}
        section div:first-child{height: 100%; width: 30%; text-align: right; line-height: 50px;} 
        section div:last-child{height: 100%; width: 70%; }
        input[type="password"]{ background-color: #D9D4CF; border: none; height: 17px; position: relative; top: 13px; left: 10px;}
        footer{background-color: #F0E5DE; height: 35%; width: 100%; text-align:center;} 
        input[type="button"]{position: relative; top: 5px; background-color: #ABD0CE; border: none; color: #7C7877; font-size: 15px;}
        
        
    </style>
<title>Insert title here</title>
</head>
<body>
 <form action="checkPwProc" id="checkForm" method="post">
    <div id="wrapper">
        <header>P A S S W O R D</header>
        <section>
           
            <div>비밀번호</div>
            
            <div>
                <input type="password" class="checkPw" name="pw">
            </div>
          
        </section>
        <footer>
            <input type="button" value="확인" class="btn">
            <input type="button" value="홈으로" class="toHome-btn">  
             
        </footer>  
        
    </div>
     </form>
</body>
</html>