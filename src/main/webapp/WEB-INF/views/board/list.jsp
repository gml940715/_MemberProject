<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Board List</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>


<script>
	$(function(){
		$(".toWriteForm-btn").on("click",function(){
			location.href="toWriteForm";
		});	
		$(".toHome-btn").on("click",function(){
			location.href="/";
		});
		$(".pageNum").each(function(index, items){
			if(${currentPage} == $(this).text()){
				$(this).css("color","#f8ecc9");
				$(this).css("font-weight","bold");
			};
			$("img").each(function(index, items){
				if($(this).attr("src") == ""){
					$(this).attr("src","/resources/noimage.png");
				}
			});
			$(".search-btn").on("click",function(){

				$("#searchForm").submit();
			})
		});
			
		
		
	});
	
</script>
  <style>
            #wrapper{border: 0px solid black; width: 70%; margin: auto; margin-top: 150px; margin-bottom:100px;}
            .domain{background-color: #a79c8e; height:50px; text-align:center; font-weight:bold; font-size:25px;}
            .contents{border-bottom: 1px solid #a79c8e;}
            .noRecord{background-color: #f8ecc9; text-align:center; font-weight:bold; height:50px;}
            .noRecord>div{ position:relative; top:12px;}
            .image{background-color: #eb9f9f; text-align:center;}
            .title{background-color: #f8ecc9; min-height: 140px; font-weight:bold;}
            .info{background-color: #f1bbba;}
            .footer{background-color: #a79c8e; height: 50px;}
            .searchBox{text-align:right;}
            .btnBox{text-align:right;}
            .pageNavi{background-color: #a79c8e; text-align:center;}
            input[type="text"],select{position: relative; top: 10px;}
            input[type="button"]{position: relative; top: 10px; background-color: #eb9f9f; border: none;}
            input[type="button"]:hover{font-weight:bold;}
            img{height: 163px; width:265.36px; padding:10px;}
            a{color:black;}
            a:hover{font-weight:bold; color:black; }
        </style>
</head>
 <body>
 	
        <div id="wrapper">
            <div class="container">
            <div class="row"><div class="col-lg-12 col-md-12 col-sm-12 col-12 domain">B O A R D</div></div>
			<c:choose>
				<c:when test="${count == 0}">
					<div class="row noRecord">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">등록된 게시글이 없습니다.</div>
					</div>
				</c:when>
				<c:otherwise>
					<c:forEach var="list" items="${list }">
						<div class="row contents">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="row">
									<div class="col-lg-4 col-md-5 col-sm-8 col-8 image">
										<img src='${list.img }'>
									</div>
									<div class="col-lg-8 col-md-7 col-sm-4 col-4 ">
										<div class="row info">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12 title"><a href="toContent?seq=${list.seq }">${list.title }</a></div>
											<div class="col-lg-3 col-md-3 col-sm-4 col-6">No.${list.seq }</div>
											<div class="col-lg-3 col-md-3 col-sm-4 col-6">${list.writer }</div>
											<div class="col-lg-3 col-md-3 d-none d-md-block">${list.writeDate }</div>
											<div class="col-lg-3 col-md-3 col-sm-4 d-none d-sm-block">${list.viewCount }</div>
										</div>

									</div>
								</div>
							</div>
						</div>
					</c:forEach>
					<div class="row pageNavi">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">${pageNavi }</div>
					</div>
				</c:otherwise>
			</c:choose>
		<form action="searchPost" method="get" id="searchForm">
			<div class="row footer">
				
				<div class="col-lg-8 col-md-8 col-sm-6 col-12 searchBox">
					<select name="opt">
						<option>작성자</option>
						<option>글제목</option>
					</select> 
					<input type="text" class="searchWord" name="searchWord"> <input type="button" class="search-btn"value="검색">
				</div>
				<div class="col-lg-4 col-md-4 col-sm-6 col-12 btnBox">
					<input type="button" class="toWriteForm-btn" value="글쓰기">
					<input type="button" class="toHome-btn" value="홈으로">
					<input type="hidden" value="1" name="searchPage" class="searchPage">
				</div>
				
			</div>
		</form>
		</div>
        </div>
    </body>
</html>