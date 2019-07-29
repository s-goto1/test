<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="test.css"/>
<script type="text/javascript" src="jstest.js"></script>
</head>
<body>
	<fieldset>
		<h1>Login Form</h1>
		<form action="./Login" id="loginForm" name="loginForm" method="post" onsubmit="return savePreset(this);">
			<div class="iconUser"></div>
			<input type="text" id="id" name="id" placeholder="UserId" required>
			<div class="iconPassword"></div>
			<input type="password" id="pass" name="pass" placeholder="Password" required>
			<label for="save">
			<input type="checkbox" value="yes" id="save" name="save" checked="checked">
			Save input information
			</label>
			<input type="submit" name="確認用" value="Login">
		</form>
		<c:if test="${not empty error}">
			<div style="color: red;">
				${error}
			</div>
		</c:if>
    </fieldset>
</body>
</html>
