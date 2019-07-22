<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js">

</script>
<script type="text/javascript" src="subtest.js"></script>
</head>
<body>
	<form id="form" name="form" action="" method="post">
		<select name="linkselect" onChange="kakunin()">
			<option value="">選択して下さい</option>
			<option value="http://www.yahoo.co.jp/">Yahoo!Japan</option>
			<option value="http://www.google.co.jp/">Google</option>
		</select>

		<c:if test="${not empty list}">
			<p><c:out value="${list.get(0).name}です" /></p>
		</c:if>
		<table border="3">
			<tr>
				<th colspan="2" rowspan="2">月日</th>
				<th colspan="2">区間</th>
				<th rowspan="2">金額</th>
				<th rowspan="2">削除用（仮）</th>
			</tr>
			<tr>
				<th>発</th>
				<th>着</th>
			</tr>

			<c:forEach var="item" items="${list}">
				<tr>
						<c:if test="${not empty list}">
							<td><c:out value="${item.date}月" /></td>
						</c:if>
						<td><c:if test="${not empty list}">
								<c:out value="${item.date}日" />
							</c:if></td>
						<td><c:if test="${not empty list}">
								<c:out value="${item.depature}" />
							</c:if></td>
						<td><c:if test="${not empty list}">
								<c:out value="${item.destination}" />
							</c:if></td>
						<td><c:if test="${not empty list}">
								<c:out value="${item.money}" />
							</c:if></td>
						<td><c:if test="${not empty list}">
								<input type="checkbox" name="totalM_id"
									value="${item.totalM_id}">
							</c:if></td>
				</tr>
			</c:forEach>

		</table>
		<c:choose>
			<c:when test="${not empty list}">
				<input type="hidden" name="test" value="${list.size()}">
				<input type="hidden" name="testId" value="${list.get(0).id}">
			</c:when>
			<c:otherwise>
				<p><c:out value=" ${nolist}" /></p>
			</c:otherwise>
		</c:choose>

		<input type="button" onclick="location.href='./modify.jsp'"
			value="修正(テスト)">
			 <input type="button" id="register"
			value="登録テスト">
			 <input type="button" id="excelout" value="出力">
		<input type="button" id="delete" value="削除（テスト)">
		<input
			type="button" onclick="location.href='./logout.jsp'" value="ログアウト">
	</form>

</body>
</html>