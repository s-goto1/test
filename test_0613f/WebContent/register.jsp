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

		<form action="/test_0613f/Register" name="form" onsubmit="return CheckSearch()" method="post">

			<table border="3" class="table table-striped">
				<tr>
					<th colspan="2" rowspan="3">月日</th>
					<th colspan="2">区間</th>
					<th rowspan="3">区分</th>
					<th rowspan="3">金額</th>
				</tr>
				<tr>

					<th rowspan="2">発</th>
					<th rowspan="2">着</th>

				</tr>
				<tr>
					<!-- table-stripedのための空列 -->
				</tr>
				<tr>

					<td><input type="text" name="month" value="" size="5"><span style="display: inline-block;">月</span></td>
					<td><input type="text" name="date" value="" size="5"><span style="display: inline-block;">日</span></td>
					<td><input type="text" name="depature" value="" size=""></td>
					<td><input type="text" name="destination" id="test" value="" size=""></td>
					<td><input type="text" name="division" id="division" value="片道" size="5" readonly></td>
					<td><input type="number" name="money" id="money" value="0" step="10"></td>

				</tr>
			</table>
		    <input type="hidden" name="id" value="${id}" size="">

			<input type="submit" id="regist" name="確認用" class="btn btn-primary mr-2" value="登録確定">
			<!--<input type="button" id="today" name="" class="btn btn-secondary mr-2" onclick="location.href='./home.jsp'" value="今日の日付">-->
			<input type="button" id="back" name="" class="btn btn-warning mr-2" onclick="location.href='./home.jsp'" value="戻る">
			<input type="button" id="train" name="" class="btn btn-info"  onclick="clickBtn1()" value="往復">

		</form>

	</div>

</body>
</html>