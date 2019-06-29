<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
<title>signUp</title>
 <style>
       
        #wrapper{margin: auto; width: 330px; height: 300px; margin-top:150px;}
        header{text-align: center; background-color: #ABD0CE; height: 10%; line-height: 30px; font-weight: bold; color: #7C7877;}
        #contents{background-color: #F0E5DE; height: 75%;}
        #contents div{float: left; position: relative; top: 10px;}
        #contents div:first-child{width: 30%; height: 100%; text-align: right;}
        #contents div:nth-child(2){width: 70%; height: 100%; position: relative; left: 10px;}
        input[type="text"],input[type="password"]{background-color: #D9D4CF; border: none; height: 17px;}
       
        footer{height: 15%; background-color: #F0E5DE; line-height: 50px;}
        footer div{position:relative; top: 5px;}
        input[type="button"],input[type="reset"]{background-color: #ABD0CE; border: none; font-size: 13px;}
    </style>
<script>
	$(function(){
		$(".signUp-btn").on("click",function(){
			if($("#id").val() == ""){alert("아이디를 입력해주세요.")}
			else if($("#password").val() == ""){alert("비밀번호를 입력해주세요.")}
			else if($(".name").val() == ""){alert("이름을 입력해주세요.")}
			else if($(".phone").val() == ""){alert("연락처를 입력해주세요.")}
			else if($("#idDupleCheckBtn").attr("flag") == "false"){dkdkdkdk}
			$("#signUpForm").submit();
			
		})
		$(".cancel-btn").on("click",function(){
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
        <header>S I G N U P</header>
        <form action="signUpProc" method="post" id="signUpForm" enctype="multipart/form-data">
        <div id="contents">
            <div>
                아이디<br><br>
                비밀번호<br><br>
                비밀번호 확인<br><br>
                이름<br><br>
                전화번호<br><br>
                프로필 사진
            </div>
            <div>
                <input type="text" name="id" id="id"><br>
                <span class="idSpan"></span><br>
                <input type="password" name="password" id="password"><br><br>
                <input type="password" name="checkPw" id="checkPw"><br><br>
                <input type="text" name="name" class="name"><br><br>
                <input type="text" name="phone" class="phone"><br><br>
                <input type="file" name="image" accept=".jpg, .png, .gif">
            </div>
        </div>
        <footer>
            <input type="button" class="signUp-btn" value="완료">
            <input type="reset" value="초기화">
            <input type="button" class="cancel-btn" value="취소">
        </footer>
        </form>
    </div>
</body>
</html>