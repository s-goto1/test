<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"/>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="subtest.js"></script>
</head>
<body>

	<div class="container col-md-9 col-md-offset-2 mt-4">

		<form id="form1" name="form1" action="Update" method="post">
			<p class="mt-3">
				<b><c:out value="${name}さん" /></b>の出張清算一覧データ
			</p>

			<table border="3" class="table table-striped">
				<tr>

					<th colspan="2" rowspan="3"><div class="text-center">月日</div></th>

					<th colspan="2"><div class="text-center">区間</div></th>

					<th rowspan="3"><div class="text-center">区分</div></th>

					<th rowspan="3"><div class="text-center">金額</div></th>

					<th rowspan="3"><div class="text-center">片道・往復ボタン</div></th>
				</tr>
				<tr>

					<th rowspan="2"><div class="text-center">発</div></th>
					<th rowspan="2"><div class="text-center">着</div></th>

				</tr>
				<tr>
					<!-- table-stripedのための空列 -->
				</tr>

				<c:forEach var="item" items="${list}" varStatus="status">
					<tr>

						<td><input type="text" name="month"
							value="${item.month}" size="5" required>
							<span style="display: inline-block;">月</span></td>

						<td><input type="text" name="date"
							value="${item.date}" size="5" required>
							<span style="display: inline-block;">日</span></td>

						<td><input type="text" name="depature"
							value="${item.depature}" required></td>

						<td><input type="text" name="destination"
							value="${item.destination}" required></td>

						<td><input type="text" name="division" id="division${status.count}"
							value="${item.division}" size="5" readonly></td>

						<td><input type="number" name="money" id="money${status.count}"
							value="${item.money}" max="99999" step="10" required></td>

						<td><input type="button" name="train" id="train${status.count}"
							data-index="${status.count}" class="btn btn-info"
							onclick="clickTrainBtn(this)" value="${divisionList.get(status.index)}">

							<input type="hidden" name="totalM_id"
							value="${item.totalM_id}" size="5" readonly></td>

					</tr>
				</c:forEach>

			</table>
			<input type="submit" name="uptest" class="btn btn-success mr-2" value="修正(テスト)">
			<input type="button" class="btn btn-warning" onclick="location.href='./home.jsp'" value="戻る(まだ使えない)">
		</form>

	</div>

</body>
</html>