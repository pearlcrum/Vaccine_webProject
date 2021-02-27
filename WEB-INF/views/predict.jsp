<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
  <h2>백신원가입력</h2>
</div>
<div class='subtitle2'>
  <h3>백신목록</h3>
 <h3>
 </div>
 <div class="egi">
   <div class='add'>
     <div class='body1'>
       <div class='body1title'>
         <h1>백신원가추가</h1>
       </div>
         <br>
         <br>
         <br>
         <br>
		<form method="post" action="insertall">
         <div class="radiocheck">
         <h2> 연령대</h2>
         <br>
         
         <input type="radio" name="age" value="13세이하">
         13세이하
         <input type="radio" name="age" value="14~19세">
         14~19세
         <input type="radio" name="age" value="20대">
         20대
         <input type="radio" name="age" value="30대">
         30대
         <input type="radio" name="age" value="40대">
         40대
         <input type="radio" name="age" value="50대">
         50대
         <input type="radio" name="age" value="60~64세">
         60~64세
          <input type="radio" name="age" value="65세이상">
         65세이상
		 <br>
		 <br>
		 
         <h2>성별</h2>
         <br>
         <input type="radio" name="gender" value="남">
         남
          <input type="radio" name="gender" value="여">
          여
          <br>
          <br>
          
         <h2>맞은 백신</h2>
         <br>
         <input type="radio" name="Vaccine" value="코로나">
         코로나
         <input type="radio" name="Vaccine" value="독감">
         독감
         <input type="radio" name="Vaccine" value="대상포진">
         대상포진
         <input type="radio" name="Vaccine" value="프리베나">
         프리베나
         <input type="radio" name="Vaccine" value="독+대">
         독+대
         <input type="radio" name="Vaccine" value="독+프">
         독+프
         <br>
         <input type="radio" name="Vaccine" value="독+VIT-D">
         독+VITD
          <input type="radio" name="Vaccine" value="독+대+프">
          독+대+프
          <input type="radio" name="Vaccine" value="VIT-D">
          VIT-D
          <input type="radio" name="Vaccine" value="독-Free">
          독-Free
          <input type="radio" name="Vaccine" value="특별가">
          특별가<br>
          <br>
          <br>
          </div>
          <br>
          <br>
          <br>
          <div class="submitradio">
		<button type="submit" class='radiobutton'> submit</button>
		</div>
		</form>
		
          
        </div>
        
        <div class="body2">
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
                <th scope="row" class="oksusu">${member.memberId }</th>
                <td class="oksusu">${member.memberAge }</td>
                <td class="oksusu">${member.memberSex }</td>
                <td class="oksusu">${member.memberVac }</td>
                <fmt:parseDate value='${member.memberDate}' var='regDate' pattern="yyyy-MM-dd HH:mm:ss" />
                <td><fmt:formatDate value="${regDate}" pattern="yyyy/MM/dd HH:mm"/></td>
                <c:if test="${sessionScope.isAdmin == 'true'}">
                <td class="oksusu"> 
                <img onclick="delete_form(); location.href='delete?id=${member.memberId}';" class="modifyimage" src="${pageContext.request.contextPath}/resources/img/deleteButton.JPG" alt="삭제버튼">
                </c:if>
                </td>
              </tr>
             </c:forEach>
        </tbody>
      </table>

  </div>
  </div>
</div>
</body>
</html>