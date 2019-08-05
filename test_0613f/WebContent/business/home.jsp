<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="../css/iziModal.css">
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/subtest.js"></script>
<script type="text/javascript" src="../js/iziModal.min.js"></script>
</head>
<body>
	<div class="container col-md-10 col-md-offset-2 mt-4">

		<form id="date" name="date" action="Date" method="post">

			<%@ include file="../year.jsp"%>
			<%@ include file="../month.jsp"%>

			<input type="submit" id="Test" class="btn btn-info ml-2"
				value="該当年月表示">

		</form>

		<form id="form" name="form" action="" method="post">
			<p class="mt-3">
				<b><c:out value="${name}さん" /></b>の出張清算一覧データ
				（<c:out value="${year}年${month}月分" />）
			</p>

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

						<th rowspan="3">
							<div class="text-center">削除</div>
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

					<c:forEach var="item" items="${list}">
						<tr>
							<td><c:if test="${not empty list}">
									<c:out value="${item.month}月" />
								</c:if></td>

							<td><c:if test="${not empty list}">
									<c:out value="${item.day}日" />
								</c:if></td>

							<td><c:if test="${not empty list}">
									<c:out value="${item.depature}" />
								</c:if></td>

							<td><c:if test="${not empty list}">
									<c:out value="${item.destination}" />
								</c:if></td>

							<td><c:if test="${not empty list}">
									<c:out value="${item.transportation}" />
								</c:if></td>

							<td><c:if test="${not empty list}">
									<c:out value="${item.place}" />
								</c:if></td>

							<td><c:if test="${not empty list}">
									<c:out value="${item.division}" />
								</c:if></td>

							<td><c:if test="${not empty list}">
									<c:out value="${item.money}" />
								</c:if></td>

							<td><c:if test="${not empty list}">
									<c:out value="${item.purpose}" />
								</c:if></td>

							<td><c:if test="${not empty list}">
									<div class="text-center">
										<input type="checkbox" name="totalM_id"
											value="${item.totalM_id}">
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

			<br>
	<section class="container">

		<nav class="pagination">
			<a href="index.html" class="prev">&lt;</a>
			<c:forEach begin="1" end="${number}" step="1" varStatus="status">
				<a href="PagingServTest?page=${status.index}">${status.index}</a>

			</c:forEach>

			<a href="index.html" class="next">&gt;</a>
		</nav>

	</section>
			<c:choose>
				<c:when test="${masterAuth eq 1}">
					<input type="button" id="excelout"
						class="btn btn-warning mr-2" value="Excelに出力">
					<input type="button" id="admin"
						class="btn btn-primary mr-2" value="管理画面">
				</c:when>
				<c:otherwise>
					<c:if test="${not empty list}">
						<input type="button" id="config"
							class="btn btn-success mr-2"
							onclick="location.href='./modify.jsp'" value="修正">
					</c:if>
					<input type="button" id="register"
						class="btn btn-primary mr-2" value="登録">
					<c:if test="${not empty list}">
						<input type="button" id="excelout"
							class="btn btn-warning mr-2" value="Excelに出力">
						<input type="button" id="modal"
							class="btn btn-danger open-options mr-2"
							onclick="checkInput()" value="削除">
					</c:if>
				</c:otherwise>
			</c:choose>
			<input type="button" id="menu" class="btn btn-light mr-2"
				onclick="location.href='../menu.jsp'" value="メニュー">
			<input type="button" id="logout" class="btn btn-secondary"
				onclick="location.href='../logout.jsp'" value="ログアウト">

		</form>

	</div>

	<div class="iziModal" id="modal-options" data-izimodal-title="選択データの削除"
		data-izimodal-subtitle="選択した出張精算データを削除します">
		<div class="text-center mt-3">
			一度削除したデータは再び復元する事ができません。<br>
			本当に削除しますか？<br>
			<div class="mb-3"></div>
			<form id="formDelete" name="formDelete" action="Delete" method="post">
				<input type="hidden" id="modal_totalM_id" name="totalM_id"
					value="" readonly>
				<ul class="text-center list-inline">
					<li class="list-inline-item">
						<button type="submit" id="delete" class="btn btn-danger">YES</button>
					</li>
					<li class="list-inline-item">
						<button id="no" class="btn btn-primary" data-izimodal-close="">NO</button>
					</li>
				</ul>
			</form>
		</div>
	</div>

</body>
</html>
