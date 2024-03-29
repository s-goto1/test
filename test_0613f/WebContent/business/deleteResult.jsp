<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出張精算削除結果</title>
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
					<th colspan="2" rowspan="3">
						<div class="text-center">月日</div>
					</th>

					<th colspan="2">
						<div class="text-center">区間</div>
					</th>

					<th rowspan="3">
						<div class="text-center">交通機関</div>
					</th>

					<th rowspan="3">
						<div class="text-center">訪問先</div>
					</th>

					<th colspan="2" rowspan="3">
						<div class="text-center">金額</div>
					</th>

					<th rowspan="3">
						<div class="text-center">用件</div>
					</th>
				</tr>
				<tr>
					<th rowspan="2">
						<div class="text-center">発地</div>
					</th>

					<th rowspan="2">
						<div class="text-center">着地</div>
					</th>
				</tr>
				<tr>
					<!-- table-stripedのための空列 -->
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
								<c:out value="${item.depature}" />
							</c:if></td>

						<td><c:if test="${not empty deleteList}">
								<c:out value="${item.destination}" />
							</c:if></td>

						<td><c:if test="${not empty deleteList}">
								<c:out value="${item.transportation}" />
							</c:if></td>

						<td><c:if test="${not empty deleteList}">
								<c:out value="${item.place}" />
							</c:if></td>

						<td><c:if test="${not empty deleteList}">
								<c:out value="${item.division}" />
							</c:if></td>

						<td><c:if test="${not empty deleteList}">
								<c:out value="${item.money}" />
							</c:if></td>

						<td><c:if test="${not empty deleteList}">
								<c:out value="${item.purpose}" />
							</c:if></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>

	<input type="button" class="btn btn-warning" onclick="location.href='PagingServTest?page=1'" value="戻る">

	</div>
</body>
</html>