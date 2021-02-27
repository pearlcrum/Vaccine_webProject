<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="UTF-8">
    <title>ColdVaccinePredict</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/SelectAll.css?ver=1" type="text/css">
   <script>
      window.onload = function () {
        var chart = new CanvasJS.Chart("chartContainer", {
          animationEnabled: true,
          title: {
            text: "백신 예측결과",
            fontFamily: "arial black",
            fontColor: "#695A42",
          },
          axisX: {
            interval: 1,
            intervalType: "none",
          },
          axisY: {
            valueFormatString: "#0EA",
            gridColor: "#B6B1A8",
            tickColor: "#B6B1A8",
          },
          toolTip: {
            shared: true,
            content: toolTipContent,
          },
          data: [
            {
              type: "stackedColumn",
              showInLegend: true,
              color: "#a79c8e",
              name: "코로나",
              dataPoints: [
                { y: ${numvaccine1.numvaccineCorona}, x: ${numvaccine1.numvaccineYear} },
                { y: ${numvaccine2.numvaccineCorona}, x: ${numvaccine2.numvaccineYear} },
                { y: ${numvaccine3.numvaccineCorona}, x: ${numvaccine3.numvaccineYear} },
                { y: ${numvaccine4.numvaccineCorona}, x: ${numvaccine4.numvaccineYear} },
                { y: ${numvaccine5.numvaccineCorona}, x: ${numvaccine5.numvaccineYear} },
                { y: ${numvaccine6.numvaccineCorona}, x: ${numvaccine6.numvaccineYear} },
              ],
            },
            {
              type: "stackedColumn",
              showInLegend: true,
              name: "독감",
              color: "#efd280",
              dataPoints: [
                 { y: ${numvaccine1.numvaccineCold}, x: ${numvaccine1.numvaccineYear} },
                 { y: ${numvaccine2.numvaccineCold}, x: ${numvaccine2.numvaccineYear} },
                 { y: ${numvaccine3.numvaccineCold}, x: ${numvaccine3.numvaccineYear} },
                 { y: ${numvaccine4.numvaccineCold}, x: ${numvaccine4.numvaccineYear} },
                 { y: ${numvaccine5.numvaccineCold}, x: ${numvaccine5.numvaccineYear} },
                 { y: ${numvaccine6.numvaccineCold}, x: ${numvaccine6.numvaccineYear} },
              ],
            },
            {
              type: "stackedColumn",
              showInLegend: true,
              name: "대상포진",
              color: "#f1adac",
              dataPoints: [
                { y: ${numvaccine1.numvaccineShingles}, x: ${numvaccine1.numvaccineYear} },
                { y: ${numvaccine2.numvaccineShingles}, x: ${numvaccine2.numvaccineYear} },
                { y: ${numvaccine3.numvaccineShingles}, x: ${numvaccine3.numvaccineYear} },
                { y: ${numvaccine4.numvaccineShingles}, x: ${numvaccine4.numvaccineYear} },
                { y: ${numvaccine5.numvaccineShingles}, x: ${numvaccine5.numvaccineYear} },
                { y: ${numvaccine6.numvaccineShingles}, x: ${numvaccine6.numvaccineYear} },
              ],
            },
            {
              type: "stackedColumn",
              showInLegend: true,
              name: "프리베나",
              color: "#ec8080",
              dataPoints: [
                { y: ${numvaccine1.numvaccinePrevenar}, x: ${numvaccine1.numvaccineYear} },
                { y: ${numvaccine2.numvaccinePrevenar}, x: ${numvaccine2.numvaccineYear} },
                { y: ${numvaccine3.numvaccinePrevenar}, x: ${numvaccine3.numvaccineYear} },
                { y: ${numvaccine4.numvaccinePrevenar}, x: ${numvaccine4.numvaccineYear} },
                { y: ${numvaccine5.numvaccinePrevenar}, x: ${numvaccine5.numvaccineYear} },
                { y: ${numvaccine6.numvaccinePrevenar}, x: ${numvaccine6.numvaccineYear} },
              ],
            },
            {
              type: "stackedColumn",
              showInLegend: true,
              name: "VIT-D",
              color: "#6b5344",
              dataPoints: [
                { y: ${numvaccine1.numvaccineVitd}, x: ${numvaccine1.numvaccineYear} },
                { y: ${numvaccine2.numvaccineVitd}, x: ${numvaccine2.numvaccineYear} },
                { y: ${numvaccine3.numvaccineVitd}, x: ${numvaccine3.numvaccineYear} },
                { y: ${numvaccine4.numvaccineVitd}, x: ${numvaccine4.numvaccineYear} },
                { y: ${numvaccine5.numvaccineVitd}, x: ${numvaccine5.numvaccineYear} },
                { y: ${numvaccine6.numvaccineVitd}, x: ${numvaccine6.numvaccineYear} },
              ],
            },
          ],
        });
        chart.render();

        function toolTipContent(e) {
          var str = "";
          var total = 0;
          var str2, str3;
          for (var i = 0; i < e.entries.length; i++) {
            var str1 =
              '<span style= "color:' +
              e.entries[i].dataSeries.color +
              '"> ' +
              e.entries[i].dataSeries.name +
              "</span>: <strong>" +
              e.entries[i].dataPoint.y +
              "</strong>개<br/>";
            total = e.entries[i].dataPoint.y + total;
            str = str.concat(str1);
          }
          str2 =
            '<span style = "color:DodgerBlue;"><strong>' +
            e.entries[0].dataPoint.x +
            "</strong></span><br/>";
          total = Math.round(total * 100) / 100;
          str3 =
            '<span style = "color:Tomato">Total:</span><strong> ' +
            total +
            "</strong>개<br/>";
          return str2.concat(str).concat(str3);
        }
      };
    </script>
  </head>
  <body>
  <div class='topgraph'>
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
  <h2>백신수요예측</h2>
</div>
<div class='subtitle2'>
  <h3>백신수요예측결과</h3>
 </div> 
	<br>
	<br>
	<br>
	</div>
   
    </div>
    <div class="graphshow">
     <div id="chartContainer" style="height: 450px; width: 80%; justify-content: center; display: inline-block; margin-top: 50px;"></div>
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
    </div>
    <div class="descriptionforgraph">
    <span>* 금년의 경우 독감 백신 개수를 예측합니다.(현재 2021년일 경우 2021년 값은 필요한 독감 백신 예상값 입니다.)<br></span>
    <span>* 연도가 바뀔경우 작년의 데이터는 기존에 입력해두신 값으로 대체되며, 해당 값을 활용해서 금년의 새로운 예상값을 도출합니다.<br></span>
    &nbsp;&nbsp;<span>(2022년1월1일 이후 해당 페이지 조회 시 2021년 값은 사용자가 입력되었던 값으로 대체되며, 2022년 백신 수요 예상값이 새로 도출됩니다.)</span>
    </div>
  </body>
</html>
