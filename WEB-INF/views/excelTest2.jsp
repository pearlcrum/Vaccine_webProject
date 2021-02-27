<%@ page language="java" contentType="application/vnd.ms-excel;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%
    //중요한 사항 : "attachment; filename=excel.xls" 로 적으면 excel.xls 파일이 생성되고 다운로드된다.
    
    Date date=new Date();
    SimpleDateFormat dayFormat=new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
    SimpleDateFormat hourFormat=new SimpleDateFormat("HHmmss", Locale.KOREA);
    String day=dayFormat.format(date);
    String hour=hourFormat.format(date);
    String fileName="patientList"+"_"+day+"_"+hour;
    
    response.setHeader("Content-Disposition", "attachment; filename="+new String((fileName).getBytes("KSC5601"),"8859_1")+".xls");
    response.setHeader("Content-Description", "JSP Generated Data");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="utf-8"/>
	
    <title>백신 현황 목록</title>
<title>Insert title here</title>
</head>
<div class='top'>
   <div class='headertop'>
   
</div>

<div class='subtitle'>
  <h2>환자조회결과확인</h2>
</div>
<div class='subtitle2'>
  <h3>환자목록조회결과</h3>
 </div> 
<div class="pyo">
  <div class='howmanypeople'><h3>총 ${count}건 선택되었습니다. 총 이윤은 ${totalPrice}원 입니다.</h3>
  <h5>데이터는 총 1만개까지만 표시됩니다.</h5>
    </div>
    <div class='pyo1'>
    <div>
    <table class="tablestyle">
      <thead>
        <tr align="center">
          <th scope="cols">코로나</th>
          <th scope="cols">독감</th>
          <th scope="cols">프리베나</th>
          <th scope="cols">대상포진</th>
          <th scope="cols">VIT-D</th>
        </tr>
      </thead>
      <tbody>
      	<tr>
			<td style="text-align:center;">${total["covid19"]}</td>
      		<td style="text-align:center;">${total["dockgam"]}</td>
      		<td style="text-align:center;">${total["prevena"]}</td>
      		<td style="text-align:center;">${total["Daesang"]}</td>
      		<td style="text-align:center;">${total["vitamin"]}</td>
        </tr>
     </tbody>
   </table>
   <br>
    <table class="tablestyle">
      <thead>
        <tr>
          <th scope="cols">아이디</th>
          <th scope="cols">연령대</th>
          <th scope="cols">성별</th>
          <th scope="cols">백신</th>
          <th scope="cols">날짜/시간</th>
        </tr>
      </thead>
      <tbody>
      <%-- 페이지 번호만큼 돌것이다. 이때 start 값을 바꿔줘야 한다. 어떤 방식으로? --%>
       <c:forEach items="${list}" var="member">
              <tr align="center">
                <th scope="row" style="text-align:center;">${member.memberId }</th>
                <td style="text-align:center;">${member.memberAge }</td>
                <td style="text-align:center;">${member.memberSex }</td>
                <td style="text-align:center;">${member.memberVac }</td>
                <fmt:parseDate value='${member.memberDate}' var='regDate' pattern="yyyy-MM-dd HH:mm:ss" />
                <td style="mso-number-format:\@"><fmt:formatDate value="${regDate}" pattern="yyyy/MM/dd HH:mm"/></td>
              </tr>
        </c:forEach>
     </tbody>
   
   </div>
  </div>
</div>
</body> 
</html>