<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出勤退勤修正・登録結果</title>
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="../css/cp_arrows.css">
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container col-md-10 col-md-offset-2 mt-4 mb-4">

		<p>下記の情報を更新しました。</p>

		<table border="1" class="table table-striped" style="border: solid 3px;">
			<tbody style="border: black 2px">
				<tr>
					<th colspan="2">
						<div class="text-center">月日</div>
					</th>

					<th>
						<div class="text-center">出社時刻</div>
					</th>

					<th>
						<div class="text-center">退社時刻</div>
					</th>

					<th>
						<div class="text-center">休憩時間</div>
					</th>

					<th>
						<div class="text-center">実働時間</div>
					</th>

					<th>
						<div class="text-center">残業時間</div>
					</th>

					<th>
						<div class="text-center">備考</div>
					</th>

					<th>
						<div class="text-center">休暇</div>
					</th>
				</tr>

				<c:forEach var="item" items="${workListUp}" varStatus="status">
					<tr>
						<td
						<c:if test="${item.month != workListComp.get(status.index).month}">
							 style="background-color: #ffcccc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.month}月" />
							</c:if></td>

						<td
						<c:if test="${item.day != workListComp.get(status.index).day}">
							 style="background-color: #ffcccc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.day}日" />
							</c:if></td>

						<td
						<c:if test="${item.comeTime != workListComp.get(status.index).comeTime}">
							 style="background-color: #ffcccc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<fmt:formatDate value="${item.comeTime}" type="TIME" timeStyle="SHORT" />
							</c:if></td>

						<td
						<c:if test="${item.leaveTime != workListComp.get(status.index).leaveTime}">
							 style="background-color: #ffcccc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<fmt:formatDate value="${item.leaveTime}" type="TIME" timeStyle="SHORT" />
							</c:if></td>

						<td
						<c:if test="${item.brakeTime != workListComp.get(status.index).brakeTime}">
							 style="background-color: #ffcccc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<fmt:formatDate value="${item.brakeTime}" type="TIME" timeStyle="SHORT" />
							</c:if></td>

						<td
						<c:if test="${item.workTime != workListComp.get(status.index).workTime}">
							 style="background-color: #ffcccc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<fmt:formatDate value="${item.workTime}" type="TIME" timeStyle="SHORT" />
							</c:if></td>

						<td
						<c:if test="${item.overTime != workListComp.get(status.index).overTime}">
							 style="background-color: #ffcccc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<fmt:formatDate value="${item.overTime}" type="TIME" timeStyle="SHORT" />
							</c:if></td>

						<td
						<c:if test="${item.notes != workListComp.get(status.index).notes}">
							 style="background-color: #ffcccc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.notes}" />
							</c:if></td>

						<td
						<c:if test="${item.vacation != workListComp.get(status.index).vacation}">
							 style="background-color: #ffcccc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.vacation}" />
							</c:if></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>

		<div class="cp_arrows">
			<div class="cp_arrow cp_arrowfirst"></div>
			<div class="cp_arrow cp_arrowsecond"></div>
		</div>

		<table border="1" class="table table-striped" style="border: solid 3px;">
			<tbody style="border: black 2px">
				<tr>
					<th colspan="2">
						<div class="text-center">月日</div>
					</th>

					<th>
						<div class="text-center">出社時刻</div>
					</th>

					<th>
						<div class="text-center">退社時刻</div>
					</th>

					<th>
						<div class="text-center">休憩時間</div>
					</th>

					<th>
						<div class="text-center">実働時間</div>
					</th>

					<th>
						<div class="text-center">残業時間</div>
					</th>

					<th>
						<div class="text-center">備考</div>
					</th>

					<th>
						<div class="text-center">休暇</div>
					</th>
				</tr>

				<c:forEach var="item" items="${workListComp}" varStatus="status">
					<tr>
						<td
						<c:if test="${item.month != workListUp.get(status.index).month}">
							 style="background-color: #ccffcc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.month}月" />
							</c:if></td>

						<td
						<c:if test="${item.day != workListUp.get(status.index).day}">
							 style="background-color: #ccffcc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.day}日" />
							</c:if></td>

						<td
						<c:if test="${item.comeTime != workListUp.get(status.index).comeTime}">
							 style="background-color: #ccffcc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<fmt:formatDate value="${item.comeTime}" type="TIME" timeStyle="SHORT" />
							</c:if></td>

						<td
						<c:if test="${item.leaveTime != workListUp.get(status.index).leaveTime}">
							 style="background-color: #ccffcc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<fmt:formatDate value="${item.leaveTime}" type="TIME" timeStyle="SHORT" />
							</c:if></td>

						<td
						<c:if test="${item.brakeTime != workListUp.get(status.index).brakeTime}">
							 style="background-color: #ccffcc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<fmt:formatDate value="${item.brakeTime}" type="TIME" timeStyle="SHORT" />
							</c:if></td>

						<td
						<c:if test="${item.workTime != workListUp.get(status.index).workTime}">
							 style="background-color: #ccffcc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<fmt:formatDate value="${item.workTime}" type="TIME" timeStyle="SHORT" />
							</c:if></td>

						<td
						<c:if test="${item.overTime != workListUp.get(status.index).overTime}">
							 style="background-color: #ccffcc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<fmt:formatDate value="${item.overTime}" type="TIME" timeStyle="SHORT" />
							</c:if></td>

						<td
						<c:if test="${item.notes != workListUp.get(status.index).notes}">
							 style="background-color: #ccffcc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.notes}" />
							</c:if></td>

						<td
						<c:if test="${item.vacation != workListUp.get(status.index).vacation}">
							 style="background-color: #ccffcc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.vacation}" />
							</c:if></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>

	<input type="button" class="btn btn-warning" onclick="location.href='Paging?page=${currentpage}'" value="戻る">

	</div>
</body>
</html>