<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="iziModal.css">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="subtest.js"></script>
<script type="text/javascript" src="iziModal.min.js"></script>
</head>
<body>
	<div class="container col-md-9 col-md-offset-2 mt-4">

		<form id="form" name="form" action="" method="post"  enctype="multipart/form-data">
			<p class="mt-3">
				<b><c:out value="${name}さん" /></b>の休暇一覧データ（2019年分）
			</p>

			<table border="3" class="table table-striped">
				<tr>
					<th colspan="2"><div class="text-center">月日</div></th>
					<th colspan="2"><div class="text-center">期間</div></th>
					<th><div class="text-center">理由</div></th>
					<th><div class="text-center">削除用（仮）</div></th>
				</tr>
				<c:forEach var="item" items="${list}">
					<tr>
						<td><c:if test="${not empty list}">
								<c:out value="${item.month}月" />
							</c:if></td>
						<td><c:if test="${not empty list}">
								<c:out value="${item.day}日" />
							</c:if></td>
						<td><c:if test="${not empty list}">
								<c:out value="${item.month}月" />
								<c:out value="${item.date}日から" />
							</c:if></td>
						<td><c:if test="${not empty list}">
								<c:out value="${item.month}月" />
								<c:out value="${item.date}日まで" />
							</c:if></td>
						<td><c:if test="${not empty list}">
								<c:out value="${item.money}" />
							</c:if></td>
						<td><c:if test="${not empty list}">
								<div class="text-center">
									<input type="checkbox" name="totalM_id" value="${item.totalM_id}">
								</div>
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
					<p>
						<c:out value=" ${nolist}" />
					</p>
				</c:otherwise>
			</c:choose>

		</form>

	<input type="button" id="logout" class="btn btn-secondary" onclick="location.href='./logout.jsp'" value="ログアウト">

	</div>
</body>
</html>