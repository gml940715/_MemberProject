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
		$(".cancelBtn").on("click",function(){
			location.href="/";
		});
		$(".id").val("${myInfo.id}");
		$(".name").val("${myInfo.name}");
		$(".phone").val("${myInfo.phone}");
		$(".profileImage").html("<img src='${myInfo.image}'>");
	
		
		$(".toWithdrawal-Btn").on("click",function(){
			var result = confirm("회원탈퇴를 하시겠습니까?");
			if(result == true){
				location.href="checkPw?num=2";
			}
		})
		
		$(".alterBtn").on("click",function(){
			//alert($(".picPath").attr("pic"));
			$.ajax({
				url:'alterMyInfo',
				type:'post',
				data:{myInfo:JSON.stringify({pw:$(".password").val(),
							 				 phone:$(".phone").val(),
							 				 image:$(".picPath").attr("pic")})}
			}).done(function(resp){
				if(resp == "정보 수정에 실패하였습니다."){
					alert("정보 수정에 실패하였습니다.");
				}else{
					var result = JSON.parse(resp)
					console.log(result);
					console.log(result.image);
					$(".password").val("");
					$(".checkPw").val("");
					$(".phone").val(result.phone);
					alert("수정이 완료 되었습니다.");
				}
			})
		});
		$(".fileBtn").on("change",function(){
			var form = new FormData(document.getElementById("myInfoForm"));
			$.ajax({
				url:"changeProPic",
				data:form,
				processData:false,
				contentType:false,
				type:"post"
			}).done(function(resp){
				console.log(resp);
				var time = new Date().getTime();
				$(".profileImage").html("");
				$(".profileImage").html("<img src='"+resp+"?time="+time+"'>");
				$(".picPath").attr("pic",resp);
			})
		})
		
		
		
	});

</script>
<style>
	img{width:200px; height:200px;}
</style>
<title>My Page</title>
</head>
<body>
	<div id="wrapper">
	<div class="profileImage"></div>
	<input type="hidden" pic="${myinfo.image}" class="picPath">
		
		<form action="alterMyInfo" method="post" id="myInfoForm">
		<div><input type="file" class="fileBtn" accept=".jpg, .png" value="프로필 사진 수정" name="image"></div>
			아이디<input type="text" class="id" name="id" readonly><br>
			비밀번호<input type="password" class="password" name="password"><br>
			비밀번호 확인<input type="password" class="checkPw" name="checkPw"><br>
			이름<input type="text" class="name" name="name" readonly><br>
			전화번호<input type="text" class="phone" name="phone"><br>
			<input type="button" class="alterBtn" value="수정">
			<input type="button" class="cancelBtn" value="취소">
			<input type="button" class="toWithdrawal-Btn" value="회원탈퇴">
		</form>
	</div>
</body>
</html>