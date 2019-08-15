<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>休暇申請削除結果</title>
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
	<div class="container col-md-9 col-md-offset-2 mt-4 mb-4">

		<p>下記の情報を削除しました。</p>

		<table border="1" class="table table-striped" style="border: solid 3px;">
			<tbody style="border: black 2px">
				<tr>
					<th colspan="3">
						<div class="text-center">期間</div>
					</th>

					<th>
						<div class="text-center">区分</div>
					</th>

					<th>
						<div class="text-center">事由</div>
					</th>
				</tr>

				<c:forEach var="item" items="${deleteList}">
					<tr>
						<td><c:if test="${not empty deleteList}">
								<c:out value="${item.fromMonth}月" />
								<c:out value="${item.fromDay}日より" />
							</c:if></td>

						<td><c:if test="${not empty deleteList}">
								<c:out value="${item.toMonth}月" />
								<c:out value="${item.toDay}日まで" />
							</c:if></td>

						<td><c:if test="${not empty deleteList}">
								<c:out value="(${item.totalDay}日間)" />
							</c:if></td>

						<td><c:if test="${not empty deleteList}">
								<c:out value="${item.division}" />
							</c:if></td>

						<td><c:if test="${not empty deleteList}">
								<c:out value="${item.reason}" />
							</c:if></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>

	<input type="button" class="btn btn-warning" onclick="location.href='Paging?page=1'" value="戻る">

	</div>
</body>
</html>