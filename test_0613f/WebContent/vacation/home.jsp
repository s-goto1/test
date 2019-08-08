<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>休暇申請</title>
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="iziModal.css">
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="subtest.js"></script>
<script type="text/javascript" src="iziModal.min.js"></script>
</head>
<body>
	<div class="container col-md-9 col-md-offset-2 mt-4 mb-4">

		<form id="date" name="date" action="Date" method="post">

			<%@ include file="../year.jsp"%>

			<input type="submit" id="Test" class="btn btn-info ml-2"
				value="該当年表示">

		</form>

		<form id="form" name="form" action="" method="post">
			<p class="mt-3">
				<b><c:out value="${name}さん" /></b>の休暇一覧データ
				（<c:out value="${year}年分" />）
			</p>

			<table border="1" class="table table-striped"
				style="border: solid 3px;">
				<tbody style="border: black 2px">
					<tr>
						<th colspan="2">
							<div class="text-center">月日</div>
						</th>
						<th colspan="2">
							<div class="text-center">期間</div>
						</th>
						<th>
							<div class="text-center">理由</div>
						</th>
						<th>
							<div class="text-center">削除</div>
						</th>
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
									<c:out value="${item.day}日から" />
								</c:if></td>
							<td><c:if test="${not empty list}">
									<c:out value="${item.month}月" />
									<c:out value="${item.day}日まで" />
								</c:if></td>
							<td><c:if test="${not empty list}">
									<c:out value="${item.purpose}" />
								</c:if></td>
							<td><c:if test="${not empty list}">
									<div class="text-center">
										<input type="checkbox" name="totalM_id" value="${item.totalM_id}">
									</div>
								</c:if></td>
						</tr>
					</c:forEach>

				</tbody>
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

			<c:choose>
				<c:when test="${masterAuth eq 1}">
					<input type="button" id="excelout" class="btn btn-warning mr-2"
						value="Excelに出力">
					<input type="button" id="admin" class="btn btn-primary mr-2"
						value="管理画面">
				</c:when>
				<c:otherwise>
					<c:if test="${not empty list}">
						<input type="button" id="config" class="btn btn-success mr-2"
							onclick="location.href='./modify.jsp'" value="修正">
					</c:if>
					<input type="button" id="register" class="btn btn-primary mr-2"
						value="登録">
					<c:if test="${not empty list}">
						<input type="button" id="excelout" class="btn btn-warning mr-2"
							value="Excelに出力">
						<input type="button" id="modal"
							class="btn btn-danger open-options mr-2" onclick="checkInput()"
							value="削除">
					</c:if>
				</c:otherwise>
			</c:choose>
			<input type="button" id="menu" class="btn btn-light mr-2"
				onclick="location.href='../menu.jsp'" value="メニュー">
			<input type="button" id="logout" class="btn btn-secondary"
				onclick="location.href='../logout.jsp'" value="ログアウト">

		</form>

	</div>
</body>
</html>