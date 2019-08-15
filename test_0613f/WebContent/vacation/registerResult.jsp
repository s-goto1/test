<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>休暇申請登録結果</title>
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" />
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../js/subtest.js"></script>
</head>
<body>
	<div class="container col-md-10 col-md-offset-2 mt-4 mb-4">

		<p>下記の情報を登録しました。</p>

		<table border="1" class="table table-striped"
			style="border: solid 3px;">
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
				<tr>
					<td style="border-right: hidden;">
						<c:out value="${fromMonth}月${fromDay}日より" />
					</td>

					<td style="border-right: hidden;">
						<c:out value="${toMonth}月${toDay}日まで" />
					</td>

					<td>
						<c:out value="(${totalDay}日間)" />
					</td>

					<td>
						<c:out value="${division}" />
					</td>

					<td>
						<c:out value="${reason}" />
					</td>
				</tr>
			</tbody>
		</table>

		<input type="button"  class="btn btn-primary mr-2"
			onclick="location.href='./register.jsp'" value="続けて登録">
		<input type="button" class="btn btn-warning"
			onclick="location.href='Paging?page=1'" value="戻る">

	</div>
</body>
</html>