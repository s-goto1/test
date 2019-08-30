<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出勤退勤タイムテーブル変更結果</title>
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" />
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container col-md-9 col-md-offset-2 mt-4 mb-4">

		<p>下記の情報を更新しました。</p>

		<table border="1" class="table table-striped" style="border: solid 3px;">
			<tbody style="border: black 2px">
				<tr>
					<th>
						<div class="text-center">現場企業名</div>
					</th>

					<td>
						<div class="text-center">
							<c:out value="${timeTable.visitName}" />
						</div>
					</td>
				</tr>

				<tr>
					<th>
						<div class="text-center">出社時刻</div>
					</th>

					<td>
						<c:if test="${timeTable.visitComeTime != null}">
							<div class="text-center">
								<fmt:formatDate value="${timeTable.visitComeTime}" type="TIME" timeStyle="SHORT" />
							</div>
						</c:if>
					</td>
				</tr>

				<tr>
					<th>
						<div class="text-center">退社時刻</div>
					</th>

					<td>
						<c:if test="${timeTable.visitLeaveTime != null}">
							<div class="text-center">
								<fmt:formatDate value="${timeTable.visitLeaveTime}" type="TIME" timeStyle="SHORT" />
							</div>
						</c:if>
					</td>
				</tr>

				<tr>
					<th>
						<div class="text-center">休憩時間</div>
					</th>

					<td>
						<c:if test="${timeTable.visitBrakeTime != null}">
							<div class="text-center">
								<fmt:formatDate value="${timeTable.visitBrakeTime}" type="TIME" timeStyle="SHORT" />
							</div>
						</c:if>
					</td>
				</tr>

				<tr>
					<th>
						<div class="text-center">丸め時間（分）</div>
					</th>

					<td>
						<div class="text-center">
							<c:out value="${timeTable.roundTime}分" />
						</div>
					</td>
				</tr>

				<tr>
					<th>
						<div class="text-center">社員番号</div>
					</th>

					<td>
						<div class="text-center">
							<c:out value="${timeTable.companyNum}分" />
						</div>
					</td>
				</tr>
			</tbody>
		</table>

		<input type="button" class="btn btn-warning" onclick="location.href='Paging?page=${currentpage}'" value="戻る">

	</div>
</body>
</html>