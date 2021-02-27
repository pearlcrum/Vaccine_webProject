<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="utf-8"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/SelectAll.css" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/selectPage.js?ver=1"></script>
	
    <title>백신 현황 목록</title>
</head>
<body>
<div class='top'>
   <div class='headertop'>
    <header>
   	<nav id="topMenu" >
		<ul>
			<li><a class="menuLink" href="infoInsert">환자정보입력</a></li>
			<li>|</li>
			<li><a class="menuLink" href="selectAll">환자조회</a></li>
			<li>|</li>
			<li><a class="menuLink" href="vaccinePrice">백신접종가입력</a></li>
			<li>|</li>
			<li><a class="menuLink" href="coldPredict">백신수요예측</a></li>
			<li>|</li>
			<li><a class="menuLink" href="https://www.nhis.or.kr/nhis/index.do">국민건강보험</a></li>
			<li>|</li>
			<li><a class="menuLink" href="https://www.cdc.go.kr/index.es?sid=a2">질병관리본부</a></li>
			<li>|</li>
      		<li><a class="menuLink" href="main">홈</a></li>
		</ul>
	</nav>
  </header>

</div>

<div class='subtitle'>
  <h2>환자조회결과확인</h2>
</div>
<div class='subtitle2'>
  <h3>환자목록조회결과</h3>
 </div> 
<div class="pyo">
  <div class='howmanypeople'><h3>총 ${count}건 선택되었습니다. 총 이윤은 ${totalPrice}원 입니다.</h3>
    </div>
    
    <div class='pyo1'>
    <br>
    <br>
    <div>
    <table class="tablestyle">
      <thead>
        <tr>
          <th scope="cols">아이디</th>
          <th scope="cols">연령대</th>
          <th scope="cols">성별</th>
          <th scope="cols">백신</th>
          <th scope="cols">날짜/시간</th>
          <th scope="cols">수정</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${list}" var="member">
              <tr>
                <th scope="row">${member.memberId }</th>
                <td>${member.memberAge }</td>
                <td>${member.memberSex }</td>
                <td>${member.memberVac }</td>
                <fmt:parseDate value='${member.memberDate}' var='regDate' pattern="yyyy-MM-dd HH:mm:ss" />
                <td><fmt:formatDate value="${regDate}" pattern="yyyy/MM/dd HH:mm"/></td>
                <c:if test="${sessionScope.isAdmin == 'true'}">
                <td class="modify"> 
                <img onclick="delete_form(); location.href='deleteInSelectResult?id=${member.memberId}';" class="modifyimage" src="${pageContext.request.contextPath}/resources/img/deleteButton.JPG" alt="삭제버튼">                
                </c:if>
                </td>
              </tr>
        </c:forEach>
     </tbody>
   </table>
   </div>
	<div class="listnumber">
	<c:forEach items="${pageStartList}" var="pageIndex" varStatus="status">
		<a href="selectResult?start=${pageIndex}&change=${totalPrice}">${status.index +1 }</a>&nbsp; &nbsp;
	</c:forEach>
	</div>
	<div>
	<table class="tablestyle">
      <thead>
        <tr>
          <th class="exceptth" scope="cols">코로나</th>
          <th class="exceptth" scope="cols">독감</th>
          <th class="exceptth" scope="cols">프리베나</th>
          <th class="exceptth" scope="cols">대상포진</th>
          <th class="exceptth" scope="cols">VIT-D</th>
        </tr>
      </thead>
      <tbody>
      	<tr>
			<td class="excepttd">${total["covid19"]}</td>
      		<td class="excepttd">${total["dockgam"]}</td>
      		<td class="excepttd">${total["prevena"]}</td>
      		<td class="excepttd">${total["Daesang"]}</td>
      		<td class="excepttd">${total["vitamin"]}</td>

        </tr>
     </tbody>
   </table>
   </div>
  </div>
</div> 
	
</body>
<!--  <div class='pyo3'>
  </div>
-->

</html>