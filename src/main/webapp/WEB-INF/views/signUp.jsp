<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
<title>signUp</title>
<style>
	#wrapper{border: 1px solid black; width: 400px; margin: auto; text-align:center;}
	
</style>
<script>
	$(function(){
		$(".signUpBtn").on("click",function(){
			if($("#id").val() == ""){alert("아이디를 입력해주세요.")}
			else if($("#password").val() == ""){alert("비밀번호를 입력해주세요.")}
			else if($(".name").val() == ""){alert("이름을 입력해주세요.")}
			else if($(".phone").val() == ""){alert("연락처를 입력해주세요.")}
			else if($("#idDupleCheckBtn").attr("flag") == "false"){dkdkdkdk}
			$("#signUpForm").submit();
			
		})
		$(".cancelBtn").on("click",function(){
			location.href="/";
		})
// 		$("#idDupleCheckBtn").on("click",function(){
// 			$(this).attr("flag","true");
// 			open("idDupleCheck?id="+$("#id").val(),"","height=400px, width=400px");
// 		})
		$("#checkPw").on("input",function(){
			var pw = $("#password").val();
			var pwCheck = $("#checkPw").val();
			
			if( pw == pwCheck){
				$(".pwSpan").html("");
			}else{
				$(".pwSpan").html("비밀번호가 일치하지 않습니다");
				$(".pwSpan").css("color","red");
			}
		});
		$("#id").on("focusout",function(){
			var id = $("#id").val();
			var regex = /[a-z][a-z0-9]{4,10}$/g;
			var result = regex.exec(id);
			
			
			
			if(result == null){
				$(".idSpan").html("아이디는 알파벳 소문자로 시작하고 5글자 이상 10글자 이하로 입력해주세요. 한글은 사용할 수 없습니다.")
			}else{
			$.ajax({
				url:"idDupleCheck",
				data:{id:$("#id").val()}
			}).done(function(resp){
				console.log(resp);
				$(".idSpan").html(resp);
			});
			}
			
		});
		
		
	})
</script>
</head>
<body>
	<div id="wrapper">
	<form action="signUpProc" method="post" id="signUpForm" enctype="multipart/form-data">
		아이디<input type="text" name="id" id="id">
		<span class="idSpan"></span><br>
		비밀번호<input type="password" name="password" id="password"><br>
		비밀번호 확인 <input type="password" name="checkPw" id="checkPw">
		<span class="pwSpan"></span><br>
		이름<input type="text" name="name" class="name"><br>
		전화번호 <input type="text" name="phone" class="phone"><br>
		프로필 사진<input type="file" name="image" accept=".jpg, .png" >
		<input type="button" value="완료" class="signUpBtn">
		<input type="reset" value="초기화">
		<input type="button" value="취소" class="cancelBtn">
	</form>
	
	</div>
</body>
</html>