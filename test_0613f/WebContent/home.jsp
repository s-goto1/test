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

		<form id="month" name="month" action="Month" method="post">

			<select name="month">

				<option value="">選択して下さい</option>
				<option value="1">1月</option>
				<option value="2">2月</option>
				<option value="3">3月</option>
				<option value="4">4月</option>
				<option value="5">5月</option>
				<option value="6">6月</option>
				<option value="7">7月</option>
				<option value="8">8月</option>
				<option value="9">9月</option>
				<option value="10">10月</option>
				<option value="11">11月</option>
				<option value="12">12月</option>
			</select>

			<input type="submit" id="Test" class="btn btn-info ml-2" value="月ごとテスト">

		</form>

		<form id="form" name="form" action="" method="post">
			<p class="mt-3">
				<b><c:out value="${name}さん" /></b>の出張清算一覧データ
			</p>

			<p class="mt-3">
				<c:out value="${deleteComplete}" />
			</p>

			<table border="3" class="table table-striped">
				<tr>
					<th colspan="2" rowspan="3"><div class="text-center">月日</div></th>
					<th colspan="2"><div class="text-center">区間</div></th>
					<th rowspan="3"><div class="text-center">金額</div></th>
					<th rowspan="3"><div class="text-center">削除用（仮）</div></th>
				</tr>
				<tr>
					<th rowspan="2"><div class="text-center">発</div></th>
					<th rowspan="2"><div class="text-center">着</div></th>
				</tr>
				<tr>
					<!-- table-stripedのための空列 -->
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

			<br>

			<input type="button" id="config" class="btn btn-success mr-2" onclick="location.href='./modify.jsp'" value="修正(テスト)">
			<input type="button" id="register" class="btn btn-primary mr-2" value="登録テスト">
			<input type="button" id="excelout" class="btn btn-warning mr-2" value="出力">
			<input type="button" id="modal" class="btn btn-danger open-options mr-2" onclick="checkInput()" value="削除（テスト)">
			<input type="button" id="logout" class="btn btn-secondary" onclick="location.href='./logout.jsp'" value="ログアウト">

		</form>

	</div>


	<div class="iziModal" id="modal-options" data-izimodal-title="選択データの削除" data-izimodal-subtitle="選択した出張精算データを削除します">
		<div class="text-center mb-3">
			一度削除したデータは再び復元する事ができません。<br>
			本当に削除しますか？<br>
			<div class="mb-3"></div>
			<form id="formDelete" name="formDelete" action="Delete" method="post">
				<input type="text" id="modal_totalM_id" name="totalM_id" value="" readonly>
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