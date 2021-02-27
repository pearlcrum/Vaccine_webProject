<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/SelectAll.css?ver=1" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/selectPage.js?ver=1"></script>
<title>VaccinePrice</title>
</head>
<body>
 <div class='topprice'>
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
  <h2>백신접종가입력</h2>
</div>
<div class='subtitle2'>
  <h3>백신접종가조회</h3>
 </div>
 <br>

    <div class="totalprice">
    <%-- <form method="post" action="selectByCondition" id="selectForm">--%>
  <form method="post" name="selectForm" id="selectForm">
   
  <div class="searchprice">
  	<h3>백신접종가입력</h3>
  </div>
  <br>
  <div class="warning"><h6>같은 년도에 입력을 시도하는 경우 입력이 되지 않습니다. 동일 년도 값을 제거하고 입력을 시도하십시오.</h6></div>
<div class="dockprice">
  
  <div class="dockleft">
   <div class="docklist2"><select name="year" form="selectForm" id="yearb" onchange="yearb()" style="width: 155px;
    height: 30px;
    font-size: 15px;
    font-weight: bold;
    background-color: none;
    text-align-last: center;">
              <c:choose>
	                <c:when test="${a2019 eq 'true'}">
		               <option value='2019'>2019</option>
		            </c:when>
		            <c:otherwise>
		            	<option value='2019' disabled>2019</option>
		            </c:otherwise>
		      </c:choose>
		       <c:choose>
	                <c:when test="${a2020 eq 'true'}">
		               <option value='2020'>2020</option>
		            </c:when>
		            <c:otherwise>
		            	<option value='2020' disabled>2020</option>
		            </c:otherwise>
		      </c:choose>
		       <c:choose>
	                <c:when test="${a2021 eq 'true'}">
		               <option value='2021'>2021</option>
		            </c:when>
		            <c:otherwise>
		            	<option value='2021' disabled>2021</option>
		            </c:otherwise>
		      </c:choose>
		       <c:choose>
	                <c:when test="${a2022 eq 'true'}">
		               <option value='2022'>2022</option>
		            </c:when>
		            <c:otherwise>
		            	<option value='2022' disabled>2022</option>
		            </c:otherwise>
		      </c:choose>
		       <c:choose>
	                <c:when test="${a2023 eq 'true'}">
		               <option value='2023'>2023</option>
		            </c:when>
		            <c:otherwise>
		            	<option value='2023' disabled>2023</option>
		            </c:otherwise>
		      </c:choose>
		       <c:choose>
	                <c:when test="${a2024 eq 'true'}">
		               <option value='2024'>2024</option>
		            </c:when>
		            <c:otherwise>
		            	<option value='2024' disabled>2024</option>
		            </c:otherwise>
		      </c:choose>
		      	    

             </select> 년도</div>
             </div>
             <div class="dockright">
  <div class="docklist1">코로나 : <input type="text" name="corona" placeholder="0원"></div>
  <div class="docklist1">독감 : <input type="text" name="cold" placeholder="0원"></div>
  <div class="docklist1">대상포진 : <input type="text" name="shingles" placeholder="0원"></div>
  <div class="docklist1">프리베나 : <input type="text" name="prevenar" placeholder="0원"></div>
  <div class="docklist1">VIT-D : <input type="text" name="vitd" placeholder="0원"></div>
  <div class="docklist1">독Free : <input type="text" name="coldfree" placeholder="0원"></div>
  <div class="docklist1">특별가 : <input type="text" name="special" placeholder="0원"></div>
  <div class="docklist1">독+대 : <input type="text" name="docdae" placeholder="0원"></div>
  <div class="docklist1">독+프 : <input type="text" name="docpre" placeholder="0원"></div>
  <div class="docklist1">독+대+프 : <input type="text" name="docdaepre" placeholder="0원"></div>
  <div class="docklist1">독+VIT-D : <input type="text" name="docvitd" placeholder="0원"></div>
  </div>

  <div class="buttonposition">
  <button type="submit" name="submit" class='submitbutton3' onclick="javascript: selectForm.action='insertvaccineprice'; ">Submit</button>
  </form>
  </div>
</div>
<br>
</div>
</div>

 
<div class="pyo">
  <div class='howmanypeople'>
    </div>
    <div class='pyo1'>
    <div>
    <table class="tablestyle">
      <thead>
        <tr>
          <th scope="cols">연도</th>
          <th scope="cols">코로나</th>
          <th scope="cols">독감</th>
          <th scope="cols">대상포진</th>
          <th scope="cols">프리베나</th>
          <th scope="cols">VIT-D</th>
          <th scope="cols">독-Free</th>
          <th scope="cols">특가</th>
          <th scope="cols">독+대</th>
          <th scope="cols">독+프</th>         
          <th scope="cols">독+대+프</th>
          <th scope="cols">독+VIT-D</th>
          <th scope="cols">삭제</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${list}" var="price">
              <tr>
                <th scope="row">${price.vaccinepriceYear }</th>
                <td>${price.vaccinepriceCorona }</td>
                <td>${price.vaccinepriceCold }</td>
                <td>${price.vaccinepriceShingles }</td>
                <td>${price.vaccinepricePrevenar }</td>
                <td>${price.vaccinepriceVitd }</td>
                <td>${price.vaccinepriceColdfree }</td>
                <td>${price.vaccinepriceSpecial }</td>
                <td>${price.vaccinepriceDocdae }</td>
                <td>${price.vaccinepriceDocpre }</td>
                <td>${price.vaccinepriceDocdaepre }</td>
                <td>${price.vaccinepriceDocvitd }</td>
                <c:if test="${sessionScope.isAdmin == 'true'}">
                <td class="modify"> 
                <img onclick="delete_form(); location.href='deletevaccineprice?year=${price.vaccinepriceYear}';" class="modifyimage" src="${pageContext.request.contextPath}/resources/img/deleteButton.JPG" alt="삭제버튼">                
                </c:if>
                </td>
              </tr>
        </c:forEach>
     </tbody>
   </table>
   </div>
  </div>
</div> 
<br>
<br>
<body>

</html>