<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出勤退勤削除結果</title>
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"/>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container col-md-10 col-md-offset-2 mt-4 mb-4">

		<p>下記の情報を削除しました。</p>

		<table border="1" class="table table-striped" style="border: solid 3px;">
			<tbody style="border: black 2px">
				<tr>
					<th colspan="2">
						<div class="text-center">月日</div>
					</th>

					<th>
						<div class="text-center">出社時刻</div>
					</th>

					<th>
						<div class="text-center">退社時刻</div>
					</th>

					<th>
						<div class="text-center">実働時間</div>
					</th>

					<th>
						<div class="text-center">残業時間</div>
					</th>

					<th>
						<div class="text-center">備考</div>
					</th>
				</tr>

				<c:forEach var="item" items="${deleteList}">
					<tr>
						<td><c:if test="${not empty deleteList}">
							<c:out value="${item.month}月" />
							</c:if></td>

						<td><c:if test="${not empty deleteList}">
								<c:out value="${item.day}日" />
							</c:if></td>

						<td><c:if test="${not empty deleteList}">
								<fmt:formatDate value="${item.comeTime}" type="TIME" timeStyle="SHORT" />
							</c:if></td>

						<td><c:if test="${not empty deleteList}">
								<fmt:formatDate value="${item.leaveTime}" type="TIME" timeStyle="SHORT" />
							</c:if></td>

						<td><c:if test="${not empty deleteList}">
								<fmt:formatDate value="${item.workTime}" type="TIME" timeStyle="SHORT" />
							</c:if></td>

						<td><c:if test="${not empty deleteList}">
								<fmt:formatDate value="${item.overTime}" type="TIME" timeStyle="SHORT" />
							</c:if></td>

						<td><c:if test="${not empty deleteList}">
								<c:out value="${item.notes}" />
							</c:if></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>

	<input type="button" class="btn btn-warning" onclick="location.href='Paging?page=1'" value="戻る">

	</div>
</body>
</html>