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
	<div class="container col-md-10 col-md-offset-2 mt-4">

		<p>下記の情報を登録しました。</p>

		<table border="1" class="table table-striped"  style="border: solid 3px;">
			<tbody style="border: black 2px">
				<tr>
					<th colspan="2" rowspan="3">
						<div class="text-center">月日</div>
					</th>

					<th rowspan="3">
						<div class="text-center">区分</div>
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

				<tr>
					<td><c:out value="${month}月" /></td>
					<td><c:out value="${date}日" /></td>
					<td><c:out value="${depature}" /></td>
					<td><c:out value="${destination}" /></td>
					<td><c:out value="${transportation}" /></td>
					<td><c:out value="${place}" /></td>
					<td><c:out value="${division}" /></td>
					<td><c:out value="${money}" /></td>
					<td><c:out value="${purpose}" /></td>
				</tr>
			</tbody>
		</table>

	<input type="button" class="btn btn-warning" onclick="location.href='./home.jsp'" value="戻る">

	</div>
</body>
</html>