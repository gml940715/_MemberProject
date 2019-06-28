<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chat</title>
<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
<script>
	$(function(){
		
		var socket = new WebSocket("ws://192.168.60.11/webchat");
		socket.onmessage = function(evt){
			$(".container").append(evt.data);
			$(".container").scrollTop($(".container")[0].scrollHeight);//스크롤 제일 아래로 

		}
		
		$(".btn").on("click",function(){
			var msg = $(".textarea").html();
				$(".textarea").html("");
				socket.send(msg);
			
		})
		$(".textarea").keypress(function(e){
			var msg = $(".textarea").html();
			if(e.keyCode==13){
				$(".textarea").html("");
				socket.send(msg);
				return(false);
			}
		});
		$(".toHome-btn").on("click",function(){
			location.href="/";
		})
		
	})

</script>
 <style>
        
        .domain{margin: auto; margin-top:150px; width: 500px; height: 50px; background-color: #a79c8e;
                text-align: center; line-height: 50px; font-size: 30px; font-weight: bold;}
        #wrapper{width: 500px; height: 600px; margin: auto; box-sizing: border-box; 
                border:15px solid #eb9f9f; border-top:none; margin-bottom: 50px;}
        .container{height: 75%; width: 100%;overflow-y:auto; word-wrap: break-word;
                    background-color: #f8ecc9; border-bottom: 15px solid #eb9f9f; scroll-snap-align: start;}
        .input-box{height: 25%; width: 100%; box-sizing: border-box; overflow: hidden; 
                    background-color:#f8ecc9; border-bottom: 15px solid #eb9f9f;}
        .textarea{width: 70%; height: 100%; float: left; word-wrap: break-word;}
        .input-box>input[type='button']{width: 30%; float: left; height: 100%; background-color: #f1bbba; border: none; font-size:20px; }
        .you{text-align:left; background-color:#f1bbba; clear:both; float:left; margin:5px; border-radius:5px; max-width:225px; }
        .me{ text-align:right; background-color:#f1bbba; clear:both; float:right; margin:5px; border-radius:5px; max-width:225px;}
        #wrapper>div:last-child{ text-align:right; width:100%; }
        .toHome-btn{background-color: #f1bbba; width:70px; height:30px; border:none; margin-top:5px;}
        .toHome-btn:hover{font-weight:bold; }
        input[type="button"]{cursor: pointer;}
    </style>
</head>
<body>
   <div class="domain">C H A T</div>
    <div id="wrapper">
       
        <div class="container"></div>
        <div class="input-box">
            <div class="textarea" contenteditable="true"></div>
            <input type="button" value="S E N D" class="btn">            
        </div>
       <div><input type="button" class="toHome-btn" value="홈으로"></div>
    </div>
    
</body>
</html>