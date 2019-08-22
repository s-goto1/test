<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>休暇申請更新</title>
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"/>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/subtest.js"></script>
</head>
<body>
	<div class="container col-md-9 col-md-offset-1 mt-4 mb-2">

		<form id="form1" name="form1" action="Update" method="post">
			<p class="mt-3">
				<b><c:out value="${name}さん" /></b>の休暇申請一覧データ
				（<c:out value="${year}年分" />）
			</p>

			<table border="1" class="table table-striped" style="border: solid 3px;">
				<tbody style="border: black 2px">
					<tr>
						<th colspan="2">
							<div class="text-center">期間</div>
						</th>

						<th>
							<div class="text-center">区分</div>
						</th>

						<th>
							<div class="text-center">事由</div>
						</th>
					</tr>

					<c:forEach var="item" items="${list}" varStatus="status">
						<tr>

							<td><input type="number" name="fromMonth"
								value="${item.fromMonth}" min="1" max="12" required>
								<span style="display: inline-block;">月</span>

							<input type="number" name="fromDay"
								value="${item.fromDay}" min="1" max="31" required>
								<span style="display: inline-block;">日より</span></td>

							<td><input type="number" name="toMonth"
								value="${item.toMonth}" min="1" max="12" required>
								<span style="display: inline-block;">月</span>

							<input type="number" name="toDay"
								value="${item.toDay}" min="1" max="31" required>
								<span style="display: inline-block;">日まで</span></td>

							<td><input type="text" name="division" id="division"
									list="data" value="${item.division}">
								<datalist id="data">
									<c:forEach var="var" items="${divisionList}">
										<option value="${var}">
											${var}
										</option>
									</c:forEach>
								</datalist></td>

							<td><input type="text" name="reason" id="reason"
								value="${item.reason}" size="25" required>

								<input type="hidden" name="vacation_id"
								value="${item.vacation_id}" size="5" readonly></td>

						</tr>
					</c:forEach>

				</tbody>
			</table>

			<p class="mb-4">
		    	※休暇の区分がリストの選択肢に当てはまらない場合はご自身で記入して下さい。
		    </p>

			<input type="submit" name="uptest" class="btn btn-success mr-2" value="修正確定">
			<input type="button" class="btn btn-warning" onclick="location.href='Paging?page=${currentpage}'" value="戻る">

		</form>

	</div>
</body>
</html>