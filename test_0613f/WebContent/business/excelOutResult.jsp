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
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container col-md-9 col-md-offset-2 mt-4">

		<p><c:out value="${year}年${month}月分" />の出張精算データを${excel}に出力しました。</p>

		<br>

		<p>※出力イメージ</p>

		<table border="1" style="font-size: 13px; border: solid 3px;">
			<tbody style="border: black 2px">
				<tr>
					<th colspan="2" rowspan="2" width="50">
						<div style="text-align: left; float: left;">月</div>
						<div style="text-align: right;">日</div>
					</th>

					<th colspan="2" width="150">
						<div style="text-align: left; float: left;">区</div>
						<div style="text-align: right;">間</div>
					</th>

					<th rowspan="2" width="70">
						<div class="text-center">交通機関</div>
					</th>

					<th rowspan="2" width="150">
						<div class="text-center">訪問先</div>
					</th>

					<th rowspan="2" width="70">
						<div class="text-center">交通費①</div>
					</th>

					<th rowspan="2" width="70">
						<div class="text-center">日当②</div>
					</th>

					<th rowspan="2" width="150">
						<div class="text-center">用　件</div>
					</th>
				</tr>
				<tr>
					<th width="75">
						<div class="text-center">発　地</div>
					</th>

					<th width="75">
						<div class="text-center">着　地</div>
					</th>
				</tr>

				<c:forEach var="item" items="${list}">
					<tr>
						<td width="25"><c:if test="${not empty list}">
								<div class="text-right">
									<c:out value="${item.month}" />
								</div>
							</c:if></td>

						<td width="25"><c:if test="${not empty list}">
								<div class="text-right">
									<c:out value="${item.day}" />
								</div>
							</c:if></td>

						<td><c:if test="${not empty list}">
								<div class="text-center">
									<c:out value="${item.depature}" />
								</div>
							</c:if></td>

						<td><c:if test="${not empty list}">
								<div class="text-center">
									<c:out value="${item.destination}" />
								</div>
							</c:if></td>

						<td><c:if test="${not empty list}">
								<div class="text-center">
									<c:out value="${item.transportation}" />
								</div>
							</c:if></td>

						<td><c:if test="${not empty list}">
								<div class="text-center">
									<c:out value="${item.place}" />
								</div>
							</c:if></td>

						<td><c:if test="${not empty list}">
								<div class="text-right">
									<c:out value="${item.money}" />
								</div>
							</c:if></td>

						<td></td>

						<td><c:if test="${not empty list}">
								<div class="text-center">
									<c:out value="${item.purpose}" />
								</div>
							</c:if></td>
					</tr>
				</c:forEach>

				<c:forEach var="i" begin="1" end="${size}" step="1">
					<tr>
						<td>　<!-- ${i} --></td>
						<td>　<!-- ${i} --></td>
						<td>　</td>
						<td>　</td>
						<td>　</td>
						<td>　</td>
						<td>　</td>
						<td>　</td>
						<td>　</td>
					</tr>
				</c:forEach>

				<tr>
					<td colspan="6">
						<div style="display: flex; justify-content: space-between;">
							<span><c:out value="<<"/></span>
							<span>合</span>
							<span>計</span>
							<span><c:out value=">>"/></span>
						</div>
					</td>

					<td>
						<div style="text-align: left; float: left;">①</div>
						<div style="text-align: right;">${total}</div>
					</td>

					<td>
						<div style="text-align: left; float: left;">②</div>
						<div style="text-align: right;">0</div>
					</td>

					<td></td>
				</tr>
			</tbody>
		</table>

	<input type="button" class="btn btn-warning mt-4" onclick="location.href='PagingServTest?page=${currentpage}'" value="戻る">

	</div>
</body>
</html>