<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko-KR">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset="utf-8"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/SelectAll.css?ver=1" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/selectPage.js?ver=1"></script>
	
    <title>백신 현황 목록</title>
</head>
<body>
  <div class='top4'>
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
  <h2>환자조회</h2>
</div>
<div class='subtitle2'>
  <h3>환자목록조회</h3>
 </div>
 <br>
 <div class="searchList">
 	<h3>환자정보입력</h3>
 </div>
 
  <div class="patinfo">
    <div class="totallist">
    <%-- <form method="post" action="selectByCondition" id="selectForm">--%>
    <form method="post" name="selectForm" id="selectForm">
    <label for="ageOption"></label>
    나이 : <select name="ageOption" form="selectForm" id="ageOption">
            <option value="13세이하">13세이하</option>
            <option value="14~19세">14~19세</option>
            <option value="20대">20대</option>
            <option value="30대">30대</option>
            <option value="40대">40대</option>
            <option value="50대">50대</option>
            <option value="60~64세">60~64세</option>
            <option value="65세이상">65세이상</option>
            <option value="전체" selected>전체</option>

          </select>
    성별 : <select name="sexOption" form="selectForm">
            <option value='남'>남</option>
            <option value="여">여</option>
                <option value="전체" selected>전체</option>
          </select>
   백신 : <select name="vaccineOption" form="selectForm">
            <option value='코로나'>코로나</option>
            <option value='독감'>독감</option>
            <option value='대상포진'>대상포진</option>
            <option value='프리베나'>프리베나</option>
            <option value='독+대'>독+대</option>
            <option value='독+프'>독+프</option>
            <option value='독+VIT-D'>독+vitD</option>
            <option value='독+대+프'>독+대+프</option>
            <option value='VIT-D'>vitD</option>
            <option value='독-Free'>독free</option>
            <option value='특별가'>특별가</option>
            <option value="전체" selected>전체</option>
          </select>

    날짜 :
      <select name="startYear" form="selectForm" id="yearb" onchange="yearb()">
               <option value='2019'>2019</option>
               <option value='2020'>2020</option>
               <option value='2021'>2021</option>
               <option value='2022'>2022</option>
               <option value='2023'>2023</option>
               <option value='2024'>2024</option>
              

             </select> 년도

      <select name="startMonth" form="selectForm" id="monthb" onchange="monthb()">
               <option value='1'>1</option>
               <option value='2'>2</option>
               <option value='3'>3</option>
               <option value='4'>4</option>
               <option value='5'>5</option>
               <option value='6'>6</option>
               <option value='7'>7</option>
               <option value='8'>8</option>
               <option value='9'>9</option>
               <option value='10'>10</option>
               <option value='11'>11</option>
               <option value='12'>12</option>
              

             </select> 월
      <select name="startDay" form="selectForm" id="dayb" onchange="dayb()">
               <option value='1'>1</option>
               <option value='2'>2</option>
               <option value='3'>3</option>
               <option value='4'>4</option>
               <option value='5'>5</option>
               <option value='6'>6</option>
               <option value='7'>7</option>
               <option value='8'>8</option>
               <option value='9'>9</option>
               <option value='10'>10</option>
               <option value='11'>11</option>
               <option value='12'>12</option>
               <option value='13'>13</option>
               <option value='14'>14</option>
               <option value='15'>15</option>
               <option value='16'>16</option>
               <option value='17'>17</option>
               <option value='18'>18</option>
               <option value='19'>19</option>
               <option value='20'>20</option>
               <option value='21'>21</option>
               <option value='22'>22</option>
               <option value='23'>23</option>
               <option value='24'>24</option>
               <option value='25'>25</option>
               <option value='26'>26</option>
               <option value='27'>27</option>
               <option value='28'>28</option>
               <option value='29'>29</option>
               <option value='30'>30</option>
               <option value='31'>31</option>
              

             </select> 일 00시

            ~

              <select name="endYear" form="selectForm" id="yeara" onchange="yeara()">
                       <option value='2019'>2019</option>
                       <option value='2020'>2020</option>
                       <option value='2021'>2021</option>
                       <option value='2022'>2022</option>
                       <option value='2023'>2023</option>
                       <option value='2024' selected>2024</option>
                    

                     </select> 년도
         
              <select name="endMonth" form="selectForm" id="montha" onchange="montha()">
                       <option value='1'>1</option>
                       <option value='2'>2</option>
                       <option value='3'>3</option>
                       <option value='4'>4</option>
                       <option value='5'>5</option>
                       <option value='6'>6</option>
                       <option value='7'>7</option>
                       <option value='8'>8</option>
                       <option value='9'>9</option>
                       <option value='10'>10</option>
                       <option value='11'>11</option>
                       <option value='12' selected>12</option>
                        

                     </select> 월
    
              <select name="endDay" form="selectForm" id="daya" onchange="daya()">
                       <option value='1'>1</option>
                       <option value='2'>2</option>
                       <option value='3'>3</option>
                       <option value='4'>4</option>
                       <option value='5'>5</option>
                       <option value='6'>6</option>
                       <option value='7'>7</option>
                       <option value='8'>8</option>
                       <option value='9'>9</option>
                       <option value='10'>10</option>
                       <option value='11'>11</option>
                       <option value='12'>12</option>
                       <option value='13'>13</option>
                       <option value='14'>14</option>
                       <option value='15'>15</option>
                       <option value='16'>16</option>
                       <option value='17'>17</option>
                       <option value='18'>18</option>
                       <option value='19'>19</option>
                       <option value='20'>20</option>
                       <option value='21'>21</option>
                       <option value='22'>22</option>
                       <option value='23'>23</option>
                       <option value='24'>24</option>
                       <option value='25'>25</option>
                       <option value='26'>26</option>
                       <option value='27'>27</option>
                       <option value='28'>28</option>
                       <option value='29'>29</option>
                       <option value='30'>30</option>
                       <option value='31' selected>31</option>
                      

                     </select> 일 00시
            </div>
	</div>
  <br>
  <div class="searchlist">
  	<h3>순이익입력</h3>
  </div>
<div class="dock1">
  <div class="docklist1">코로나 : <input class="dockinput" type="text" name="corona" placeholder="0원"><br> </div>
  <div class="docklist1">독감 : <input class="dockinput" type="text" name="doc" placeholder="0원"><br></div>
  <div class="docklist1">대상포진 : <input class="dockinput" type="text" name="de" placeholder="0원"><br></div>
  <div class="docklist1">프리베나 : <input class="dockinput" type="text" name="pre" placeholder="0원"><br></div>
  <div class="docklist1">VIT-D : <input class="dockinput" type="text" name="vit" placeholder="0원"><br></div>
  <div class="docklist1">독Free : <input class="dockinput" type="text" name="docfree" placeholder="0원"><br></div>
  <div class="docklist1">특별가 : <input class="dockinput" type="text" name="special" placeholder="0원"><br></div>
  <div class="docklist1">독+대 : <input class="dockinput" type="text" name="docdae" placeholder="0원"><br></div>
  <div class="docklist1">독+프 : <input class="dockinput" type="text" name="docpe" placeholder="0원"><br></div>
  <div class="docklist1">독+대+프 : <input class="dockinput" type="text" name="docdaepe" placeholder="0원"><br></div>
  <div class="docklist1">독+VIT-D : <input class="dockinput" type="text" name="docvitd" placeholder="0원"><br></div>
  
<br>
<br>
  <div class="buttonposition">
  <button type="submit" name="submit" class='submitbutton2' onclick="javascript: selectForm.action='selectByCondition'; datevalidation();">웹으로 보기</button>
  <button type="submit" name="submit2" class='submitbutton2' onclick="javascript: selectForm.action='selectToExcel'; datevalidation();">Excel로 보기</button>
  </form>
  </div>
</div>
<br>
</div>


 
<div class="pyo">
  <div class='howmanypeople'><h3>총 ${count}건 선택되었습니다.</h3>
    </div>
    <div class='pyo1'>
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
                <img onclick="delete_form(); location.href='deleteInSelect?id=${member.memberId}';" class="modifyimage" src="${pageContext.request.contextPath}/resources/img/deleteButton.JPG" alt="삭제버튼">                
                </c:if>
                </td>
              </tr>
        </c:forEach>
     </tbody>
   </table>
   </div>
	<div class="listnumber">
	<c:forEach items="${pageStartList}" var="pageIndex" varStatus="status">
		<a href="selectAll?start=${pageIndex}&change=${totalPrice}">${status.index +1 }</a>&nbsp; &nbsp;
	</c:forEach>
	</div>
  </div>
</div> 
<br>
<br>
<body>
<!--  <div class='pyo3'>
  </div>
-->

</html>
