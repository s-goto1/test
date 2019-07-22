<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./Login" method="post">
		ユーザー名：<input type="text" name="id"><br> パスワード：<input
			type="password" name="pass"><br> <input type="submit"
			name="確認用" value="ログイン">
	</form>
	<c:if test="${not empty error}">
  ${error}
		</c:if>
</body>
</html>