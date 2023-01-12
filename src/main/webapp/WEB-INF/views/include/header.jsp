<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Insert title here</title>
​
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>


</head>

<body>

<% 
		String sessionId = (String)session.getAttribute("hspId");
		//로그인 중이라면 로그인한 아이디가 저장되고 비로그인 중이면 sessionId==null이다.	
%>


	<!-- Button trigger modal -->
	<button style="float: right;" type="button" class="btn btn-close btn-danger" aria-label="Close" data-bs-toggle="modal" data-bs-target="#closeAll"></button>
	
	<!-- Modal -->
	<div class="modal fade" id="closeAll" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h1 class="modal-title fs-5" id="exampleModalLabel">시스템 종료창</h1>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        정말로 종료하시겠습니까?
	      </div>
	      <div class="modal-footer">
	        <button type="submit" class="btn btn-danger" onclick="script:window.location='login'">네</button>
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">아니요</button>
	      </div>
	    </div>
	  </div>
	</div>


<nav class="navbar navbar-dark bg-primary">
  <div class="container-fluid d-flex justify-content-start">
    <a href="main" class="navbar-brand">예방접종등록시스템</a> 
    &nbsp;&nbsp;&nbsp;	 	 
	<div class="dropdown">
		<button class="btn btn-dark dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
	   	 &nbsp; &nbsp;  접종관리  &nbsp; &nbsp; 
 	    </button>
		  <ul class="dropdown-menu">
		    <li><button class="dropdown-item" type="button" onclick="script:window.location='check'">예방접종조회</button></li>
		  </ul>
	</div>
	&nbsp;	  
	<div class="dropdown">
		<button class="btn btn-dark dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
		 &nbsp; &nbsp;  기관관리  &nbsp; &nbsp; 
		</button>
		  <ul class="dropdown-menu">
		    <li><button class="dropdown-item" type="button" onclick="script:window.location='modify'">의료기관 정보수정</button></li>
		    <li><button class="dropdown-item" type="button" onclick="script:window.location='vaccine'">백신 잔여량</button></li>
		  </ul>
	</div>
	&nbsp;	  
	<div class="dropdown">	  
	 	<button class="btn btn-dark dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
	      &nbsp; &nbsp;  예약관리  &nbsp; &nbsp; 
	    </button>
		  <ul class="dropdown-menu">
		    <li><button class="dropdown-item" type="button" onclick="script:window.location='search'">예약조회</button></li>
		    <li><button class="dropdown-item" type="button" onclick="script:window.location='enrollment'">예방접종등록</button></li>
		  </ul>
	 </div>
	 <div>
	 	<div class="text-white">
	 		&nbsp; &nbsp; 시스템 이용시간: <span id="timerDiv"></span> <input type="button" value="연장" onclick="script:resetTime()">
	 	
	 	</div>
	 </div>
  </div>
</nav>


<script type="text/javascript">
var timeCounter = function(elem, initSecond){
    if(!(this instanceof timeCounter)){
        return new timeCounter(elem, initSecond);
    }
    var svTime = '<%= session.getAttribute("loginTime") %>';    
    console.log(svTime);
    
    this.elem = elem;
    this.initTime = parseInt(svTime)+parseInt(initSecond);
    this.si = -1;
}
timeCounter.prototype = {
    start: function(){
        if(this.si > -1){
            return;
        }
        this.resume();
    },
    stop: function(){
        this.initTime = 0;
        this.si = -1;
        clearInterval(this.si);
		sessionStorage.removeItem("hspId");	//세션아이디 지우기
        alert("접속시간만료되었습니다. 다시 로그인하세요.")	
        window.location = "/login";  

    },
    resume: function(){
        if(this.si > -1){
            return;
        }
        var _this = this;
        _this.si = setInterval(function(){
        	
                // 현재시간 구하기  
                var nowTime = parseInt((new Date()).getTime()/1000);
                console.log("nowTime : "+nowTime);
                // 초기설정시간과 현재시간 차이 구하기 
                var tcounter = this.initTime - nowTime;
                console.log("tcounter : "+tcounter);
                // 분 구하기
                var tempMin=Math.floor(tcounter/60); 
                if(tempMin < 10 ) { 
                    tempMin = '0'+tempMin; 
                }
                // 초 구하기
                var tempSec=tcounter%60;
                if(tempSec < 10 ) { 
                    tempSec = '0'+tempSec; 
                }
                // 화면에 보여주기 
                _this.print(tempMin+":"+tempSec);
                // 시간 초과면 멈춤. 
                if(tcounter<0) _this.stop();      // 60분후 종료
            }.bind(this), 100);
    },
    print: function(elapsed){
        $("#"+this.elem).html(elapsed);
    },
  }
function resetTime() {
	tempMin = 10;
	tempSec = tempMin * 60;
}
  
</script>

<script type="text/javascript">
$(document).ready(function() { 
var timeViewer = timeCounter("timerDiv", 10);
timeViewer.start();
});
</script>



</body>
</html>