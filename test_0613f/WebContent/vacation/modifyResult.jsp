<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>休暇申請更新結果</title>
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
	<div class="container col-md-9 col-md-offset-2 mt-4 mb-4">

		<p>下記の情報を更新しました。</p>

		<table border="1" class="table table-striped" style="border: solid 3px;">
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

				<c:forEach var="item" items="${vacationListUp}" varStatus="status">
					<tr>
						<td style="border-right: hidden;
						<c:if test="${item.fromMonth != vacationListComp.get(status.index).fromMonth}">
							 background-color: #ffcccc;
						</c:if>

						<c:if test="${item.fromDay != vacationListComp.get(status.index).fromDay}">
							 background-color: #ffcccc;
						</c:if>
						">
							<c:if test="${not empty list}">
								<c:out value="${item.fromMonth}月${item.fromDay}日より" />
							</c:if></td>

						<td style="border-right: hidden;
						<c:if test="${item.toMonth != vacationListComp.get(status.index).toMonth}">
							 background-color: #ffcccc;
						</c:if>

						<c:if test="${item.toDay != vacationListComp.get(status.index).toDay}">
							 background-color: #ffcccc;
						</c:if>
						">
							<c:if test="${not empty list}">
								<c:out value="${item.toMonth}月${item.toDay}日まで" />
							</c:if></td>

						<td
						<c:if test="${item.totalDay != vacationListComp.get(status.index).totalDay}">
							 style="background-color: #ffcccc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="(${item.totalDay}日間)" />
							</c:if></td>

						<td
						<c:if test="${item.division != vacationListComp.get(status.index).division}">
							 style="background-color: #ffcccc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.division}" />
							</c:if></td>

						<td
						<c:if test="${item.reason != vacationListComp.get(status.index).reason}">
							 style="background-color: #ffcccc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.reason}" />
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

				<c:forEach var="item" items="${vacationListComp}" varStatus="status">
					<tr>
						<td style="border-right: hidden;
						<c:if test="${item.fromMonth != vacationListUp.get(status.index).fromMonth}">
							 background-color: #ffcccc;
						</c:if>

						<c:if test="${item.fromDay != vacationListUp.get(status.index).fromDay}">
							 background-color: #ffcccc;
						</c:if>
						">
							<c:if test="${not empty list}">
								<c:out value="${item.fromMonth}月${item.fromDay}日より" />
							</c:if></td>

						<td style="border-right: hidden;
						<c:if test="${item.toMonth != vacationListUp.get(status.index).toMonth}">
							 background-color: #ffcccc;
						</c:if>

						<c:if test="${item.toDay != vacationListup.get(status.index).toDay}">
							 background-color: #ffcccc;
						</c:if>
						">
							<c:if test="${not empty list}">
								<c:out value="${item.toMonth}月${item.toDay}日まで" />
							</c:if></td>

						<td
						<c:if test="${item.totalDay != vacationListUp.get(status.index).totalDay}">
							 style="background-color: #ffcccc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="(${item.totalDay}日間)" />
							</c:if></td>

						<td
						<c:if test="${item.division != vacationListUp.get(status.index).division}">
							 style="background-color: #ffcccc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.division}" />
							</c:if></td>

						<td
						<c:if test="${item.reason != vacationListUp.get(status.index).reason}">
							 style="background-color: #ffcccc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.reason}" />
							</c:if></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>

	<input type="button" class="btn btn-warning" onclick="location.href='Paging?page=1'" value="戻る">

	</div>
</body>
</html>