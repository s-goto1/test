<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="test.css"/>
</head>
<body>
	<fieldset>
		<h1>Login Form</h1>
		<form action="./Login" method="post">
			<div class="iconUser"></div>
			<input type="text" name="id" placeholder="UserId" required>
			<div class="iconPassword"></div>
			<input type="password" name="pass" placeholder="Password" required>
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
