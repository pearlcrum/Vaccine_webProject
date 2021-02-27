<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MainPage</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/MainPage.css?ver=1" type="text/css">
</head>
<body class="hole">
<div>
  <div class="topdeco">
  </div>

  <div class="content">
    <span class="underline">CONTENTS</span>

  </div>
  <div class="line">
  	<form style="display:inline-block" name="addPatient" method="get" action="addPatient">
      <div class="asd" onclick="document.forms['addPatient'].submit();" >
        <img src="${pageContext.request.contextPath}/resources/img/add.JPG" alt="환자정보추가" width="150px" height="150px"><br><b>환자정보입력</b>
      </div>
    </form>
    <form style="display:inline-block" name="selectPatient" method="get" action="selectPatient">
      <div class="asd" onClick="document.forms['selectPatient'].submit();">
        <img src="${pageContext.request.contextPath}/resources/img/select.JPG" alt="환자조회"width="150px" height="150px"><br><b>환자조회</b>
      </div>
    </form>
    <form style="display:inline-block" name="vaccinePriceAddition" method="get" action="vaccinePriceAddition">
      <div class="asd" onClick="document.forms['vaccinePriceAddition'].submit();">
        <img src="${pageContext.request.contextPath}/resources/img/vaccinePrice.JPG" alt="백신접종가입력"width="150px" height="150px"><br><b>백신접종가입력</b>
      </div>
    </form>
    <br>
    <form style="display:inline-block" name="coldvaccine" method="get" action="coldvaccine">
      <div class="asd" onClick="document.forms['coldvaccine'].submit();">
        <img src="${pageContext.request.contextPath}/resources/img/predict.JPG" alt="백신수요예측"width="150px" height="150px"><br><b>백신수요예측</b>
      </div>
    </form>
    <form style="display:inline-block">
      <div class="asd" onclick= moveURL() class="linkToNHI">
        <img src="${pageContext.request.contextPath}/resources/img/link.JPG" alt="국민건강보험"width="150px" height="150px"><br><b>국민건강보험</b>
      </div>
    </form>
    <form style="display:inline-block">
      <div class="asd" onclick= moveURL2() class="linkToCDC">
        <img src="${pageContext.request.contextPath}/resources/img/newCDC.JPG" alt="질병관리본부"width="150px" height="150px"><br><b>질병관리본부</b>
      </div>
    </form>
    <script>
	    function moveURL() {
	        location.href="https://www.nhis.or.kr/nhis/index.do";
	
	      }
	    function moveURL2(){
	    	location.href="https://www.cdc.go.kr/index.es?sid=a2";
	    }
    </script>
</div>
    </div>
    </body>
    </html>