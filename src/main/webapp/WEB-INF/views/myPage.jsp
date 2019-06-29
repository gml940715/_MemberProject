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
		$(".cancel-btn").on("click",function(){
			location.href="/";
		});
		$(".id").val("${myInfo.id}");
		$(".name").val("${myInfo.name}");
		$(".phone").val("${myInfo.phone}");
		$(".profileImage").html("<img src='${myInfo.image}'>");
	
		
		$(".toWithdrawal-btn").on("click",function(){
			var result = confirm("회원탈퇴를 하시겠습니까?");
			if(result == true){
				location.href="checkPw?num=2";
			}
		})
		
		$(".alter-btn").on("click",function(){
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
		$(".file-btn").on("change",function(){
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
 div{border:px solid black;}
        #wrapper{ width: 350px; height: 530px; margin: auto; margin-top: 100px; 
        			box-sizing: border-box;  margin-bottom: 100px;}
        header{width: 100%; text-align: center; background-color: #ABD0CE; color: #7C7877; font-size: 20px; font-weight: bold; line-height: 40px;}
        .profileImage{height: 40%; background-color: #F0E5DE; text-align: center; padding-top:10px;}
        .contents{height: 45%; background-color: #F0E5DE; padding-top:10px;}
        .contents div{float: left;}
        .contents div:first-child{text-align: right; width: 35%; height: 100%;}
        .contents div:nth-child(2){position: relative; left: 10px; width: 65%; height: 100%;}
        input[type="text"],input[type="password"]{background-color: #D9D4CF; border: none; height: 17px;}
        footer{background-color: #F0E5DE; height: 8%; text-align:center;}
        input[type="button"]{background-color: #ABD0CE; border: none; font-size: 15px; color: #7C7877;}}
        
        
    </style>
<title>My Page</title>
</head>
<body>
    <div id="wrapper">
        <header>M Y P A G E</header>
        <div class="profileImage"></div>
        <input type="hidden" pic="${myinfo.image}" class="picPath">
        <div class="contents">
        <form action="alterMyInfo" method="post" id="myInfoForm" enctype="multipart/form-data">
            <div>
            프로필사진 수정<br><br>
            아이디<br><br>
            비밀번호<br><br>
            비밀번호 확인<br><br>
            이름<br><br>
            전화번호<br><br>
            </div>
            <div>
                <input type="file" class="file-btn" accept=".jpg, .png, .gif" name="image"><br><br>
                <input type="text" class="id" name="id" readonly><br><br>
                <input type="password" class="password" name="password"><br><br>
                <input type="password" class="checkPw" name="checkPw"><br><br>
                <input type="text" class="name" name="name" readonly><br><br>
                <input type="text" class="phone" name="phone"><br><br>
            </div>
        </form>
        </div>
        <footer>
            <input type="button" value="수정" class="alter-btn">
            <input type="button" value="회원탈퇴" class="toWithdrawal-btn">
            <input type="button" value="홈으로" class="cancel-btn">
        </footer>
    </div>
</body>
</html>