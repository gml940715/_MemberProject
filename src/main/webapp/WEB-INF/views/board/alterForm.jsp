<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alter Form</title>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
<script>
	$(function(){
		$(".title").val("${content.title}");
		$(".textarea").html('${content.contents}');
		
		$(".alter-btn").on("click",function(){
			$(".hd").attr("value",$(".textarea").html());
			$(".seq").attr("value",${content.seq});
			$("#writeForm").submit();
		});
		$(".toHome-btn").on("click",function(){
			location.href="toBaordList?currentPage=${currentPage}";
			
		});
	});
</script>

    <style>
            #wrapper{border: 0px solid black; width: 70%; margin: auto; margin-top: 150px;}
            .header{background-color: #f1bbba; height: 90px;  padding-top:5px;}
            .header div:first-child{text-align: center;}
            .content div:first-child{padding: 0;}
            .textarea{height: 500px; background-color: #f8ecc9; 
                      overflow: hidden; word-wrap: break-word; overflow-y:auto;}
            .footer{text-align: center; background-color: #f1bbba; height: 40px;}
             input[type="button"]{background-color: #a79c8e; border: none; position:relative; top: 6.5px;}
             input[type="button"]:hover{font-weight: bold;}
             .title{border-style:none; border-bottom: 3px solid #eb9f9f;  background:none; width:80%;}
             img{height: 300px; width: 300px; content-algin: center;}
        </style>
</head>
<body>
	<div id="wrapper">
		<form action="alterProc.board" method="post" id="writeForm" enctype="multipart/form-data">
			<input type="hidden" class="seq" name="seq" value=""> <!--  글 번호 -->
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
						<input type="hidden" value="" class="hd" name="contents"> <!-- 파일경로담기 -->
					</div>
				</div>
				<div class="row footer">
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
						<input type="button" class="alter-btn" value="수정">
						<input type="button" class="toHome-btn" value="목록">
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>