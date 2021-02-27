<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <title>Login page </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/LoginPage.css" type="text/css">
    <link href="<c:url value="https://fonts.googleapis.com/css2?family=Alata&display=swap"/>" rel="stylesheet" type="text/css">
</head>
<body>
	<script defer src="login.js"></script>
    <div class="page-container">
        <div class="login-form-container">
            <div class="login-form-left-side">
                <div class="top-logo-wrap"></div>
                <h1>Login</h1>
                <p id="pid">This site helps you enter patient information, manage it, and predict the amount of vaccines you need.</p>
            </div>
            <div class="login-form-right-side">
                <div class="login-top-wrap">
                    <span>welcome back!</span>
                </div>
                <form method="post" action="login">
                <div class="login-input-container">
                    <div class="login-input-wrap input-id">
                        <input id="id" placeholder="Email을 입력하시오" type="text" name="theEmail">
                    </div>
                    <div class="login-input-wrap input-password">
                        <i class="fas fa-key"></i>
                        <input id="pw" placeholder="Password을 입력하시오"  type="password" name="password">
                    </div>
                    <br><br>
    	              ${errorMessage}<br>
                </div>
                <div class="login-btn-wrap">
                    <button id="login-btn" type="submit" onclick="로그인 되었습니다" class="login-btn" href><b>Login</b></button>
                    <script>
		                    var id = document.getElementById("id");
		                    var pw = document.getElementById("pw");
		                    var root= document.getElementById("login-btn");
		                    console.log(id.value,pw.value,root);
		                    function loginValidation(){
		                      if(id.value==="원하는 ID"&& pw.value==="원하는 비밀번호"){
		                        alert("확인되었습니다.");}
		                      else{
		                        alert("비밀번호를 확인해주세요");}
		                    };
		                    root.addEventListener('click',loginValidation);
                    </script>
                </div>
              </form>
            </div>
        </div>
    </div>
</body>
</html>