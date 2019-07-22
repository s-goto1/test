
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
		<p>${list.get(0).name}です</p>

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


					<td><c:out value="${item.date}月" /></td>
					<td><c:out value="${item.totalM_id}日" /></td>
					<td><c:out value="${item.depature}" /></td>
					<td><c:out value="${item.destination}" /></td>
					<td><c:out value="${item.money}" /></td>
					<td><input type="checkbox" name="totalM_id" value="${item.toltam_id}" size=""></td>

				</tr>
				<hr>
			</c:forEach>

		</table>
		<input type="hidden" name="test" value="${list.size()}"> <input
			type="button" onclick="setAction('./ExcelOut')" value="登録(まだ使えない)">
		<input type="button" onclick="location.href='./register.jsp'"
			value="登録テスト"> <input type="button" id="excelout" value="出力">
		<input type="button" id="delete" value="削除（テスト）"> <input
			type="button" onclick="location.href='./logout.jsp'" value="ログアウト">
	</form>

</body>
</html>