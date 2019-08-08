<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

				<c:forEach var="item" items="${totalMListUp}" varStatus="status">
					<tr>
						<td
						<c:if test="${item.month != totalMListComp.get(status.index).month}">
							 style="background-color: #ffcccc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.month}月" />
							</c:if></td>

						<td
						<c:if test="${item.day != totalMListComp.get(status.index).day}">
							 style="background-color: #ffcccc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.day}日" />
							</c:if></td>

						<td
						<c:if test="${item.depature != totalMListComp.get(status.index).depature}">
							 style="background-color: #ffcccc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.depature}" />
							</c:if></td>

						<td
						<c:if test="${item.destination != totalMListComp.get(status.index).destination}">
							 style="background-color: #ffcccc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.destination}" />
							</c:if></td>

						<td
						<c:if test="${item.transportation != totalMListComp.get(status.index).transportation}">
							 style="background-color: #ffcccc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.transportation}" />
							</c:if></td>

						<td
						<c:if test="${item.place != totalMListComp.get(status.index).place}">
							 style="background-color: #ffcccc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.place}" />
							</c:if></td>

						<td
						<c:if test="${item.division != totalMListComp.get(status.index).division}">
							 style="background-color: #ffcccc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.division}" />
							</c:if></td>

						<td
						<c:if test="${item.money != totalMListComp.get(status.index).money}">
							 style="background-color: #ffcccc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.money}" />
							</c:if></td>

						<td
						<c:if test="${item.purpose != totalMListComp.get(status.index).purpose}">
							 style="background-color: #ffcccc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.purpose}" />
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

					<th colspan="2"	rowspan="3">
						<div class="text-center">金額</div>
					</th>

					<th rowspan="3">
						<div class="text-center">用件</div>
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

				<c:forEach var="item" items="${totalMListComp}" varStatus="status">
					<tr>
						<td
						<c:if test="${item.month != totalMListUp.get(status.index).month}">
							 style="background-color: #ccffcc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.month}月" />
							</c:if></td>

						<td
						<c:if test="${item.day != totalMListUp.get(status.index).day}">
							 style="background-color: #ccffcc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.day}日" />
							</c:if></td>

						<td
						<c:if test="${item.depature != totalMListUp.get(status.index).depature}">
							 style="background-color: #ccffcc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.depature}" />
							</c:if></td>

						<td
						<c:if test="${item.destination != totalMListUp.get(status.index).destination}">
							 style="background-color: #ccffcc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.destination}" />
							</c:if></td>

						<td
						<c:if test="${item.transportation != totalMListUp.get(status.index).transportation}">
							 style="background-color: #ccffcc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.transportation}" />
							</c:if></td>

						<td
						<c:if test="${item.place != totalMListUp.get(status.index).place}">
							 style="background-color: #ccffcc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.place}" />
							</c:if></td>

						<td
						<c:if test="${item.division != totalMListUp.get(status.index).division}">
							 style="background-color: #ccffcc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.division}" />
							</c:if></td>

						<td
						<c:if test="${item.money != totalMListUp.get(status.index).money}">
							 style="background-color: #ccffcc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.money}" />
							</c:if></td>

						<td
						<c:if test="${item.purpose != totalMListUp.get(status.index).purpose}">
							 style="background-color: #ccffcc;"
						</c:if>
						>
							<c:if test="${not empty list}">
								<c:out value="${item.purpose}" />
							</c:if></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>

	<input type="button" class="btn btn-warning" onclick="location.href='PagingServTest?page=1'" value="戻る">

	</div>
</body>
</html>