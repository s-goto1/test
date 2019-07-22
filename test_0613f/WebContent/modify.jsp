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

</head>
<body>
	<form id="form1" name="form1" action="Update" method="post">
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
					<td><input type="text" name="date"
						value="${item.date}"></td>

					<td><input type="text" name="destination"
						value="${item.destination}"></td>

					<td><input type="text" name="depature"
						value="${item.depature}"></td>

					<td><input type="text" name="money"
						value="${item.money}"></td>

				</tr>
                   <input type = "text" name="totalM_id" value="${item.totalM_id}">
			</c:forEach>

		</table>
		<input type="submit" name="uptest" value="修正(テスト)">
		<input type="button" onclick="location.href='./home.jsp'"value="戻る(まだ使えない)">
	</form>

</body>
</html>