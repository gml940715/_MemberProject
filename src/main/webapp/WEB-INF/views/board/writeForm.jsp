<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Write Form</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
<script>
	$(function(){
		$(".toHome-btn").on("click",function(){
			location.href="toBaordList?currentPage=${currentPage}";
			
		});
		$(".complate-btn").on("click",function(){
			$(".hd").attr("value",$(".textarea").html());
			$(".cp").attr("value",${currentPage});
			$("#writeForm").submit();
		});
		
		$(".img").on("change",function(){
			var form = new FormData(document.getElementById("writeForm"));
			$.ajax({
				url:"uploadImage",
				data:form,
				contentType: false,
				processData: false,
				type:"post"
				}).done(function(resp){
					console.log("이미지 : " + resp);
					$(".textarea").append("<img src='"+resp+"'>");
				});
		});
	});
	
</script>
        <style>
            #wrapper{border: 0px solid black; width: 70%; margin: auto; margin-top: 150px;}
            .header{background-color: #f1bbba; height: 90px;  padding-top:5px;}
            .header div:first-child{text-align: center;}
            .content div:first-child{padding: 0;}
            .textarea{height: 500px; background-color: #f8ecc9; 
                      overflow: hidden; word-wrap: break-word;}
            .footer{text-align: center; background-color: #f1bbba; height: 40px;}
             input[type="button"]{background-color: #a79c8e; border: none; position:relative; top: 6.5px;}
             input[type="button"]:hover{font-weight: bold;}
             .title{border-style:none; border-bottom: 3px solid #eb9f9f;  background:none; width:80%;}
        </style>
</head>
<body>
	<div id="wrapper">
		<form action="writeProc.board" method="post" id="writeForm" enctype="multipart/form-data">
			<input type="hidden" class="cp" name="currentPage" value="">
			<div class="container">
				<div class="row header">
					<div class="col-lg-3 col-md-3 col-sm-3 col-3">제목</div>
					<div class="col-lg-9 col-md-9 col-sm-9 col-9">
						<input type="text" class="title" name="title">
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
						<input type="file" class="img" name="img" accept=".jpg, .png, .gif">
					</div>
				</div>
				<div class="row content">
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="textarea" contenteditable="true"></div>
						<input type="hidden" value="" class="hd" name="contents">
					</div>
				</div>
				<div class="row footer">
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
						<input type="button" class="complate-btn" value="완료"> <input
							type="button" class="toHome-btn" value="목록">
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>