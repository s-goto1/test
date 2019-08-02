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
<script type="text/javascript" src="subtest.js"></script>
</head>
<body>

	<div class="container col-md-10 col-md-offset-2 mt-4">

		<form action="Register" id="form" name="form" method="post">

			<table border="3" class="table table-striped">
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
						<div class="text-center">着地点</div>
					</th>
				</tr>
				<tr>
					<!-- table-stripedのための空列 -->
				</tr>
				<tr>

					<td><input type="number" name="month" id="month"
						value="${month}" min="1" max="12" required>

						<span style="display: inline-block;">月</span></td>
					<td><input type="number" name="day" id="day"
						value="" min="1" max="31" required>

						<span style="display: inline-block;">日</span></td>
					<td><input type="text" name="depature" id="depature"
						value="" size="10" required></td>

					<td><input type="text" name="destination" id="destination"
						value="" size="10" required></td>



					<td><select name="transportation" id="transportation">
							<option value="地下鉄">地下鉄</option>
							<option value="地下鉄/JR">地下鉄/JR</option>
							<option value="JR">JR</option>
						</select></td>

					<td><input type="text" name="place" id="place"
						value="" size="15" required></td>

	                <td><input type="text" name="division" id="division"
						value="片道" size="5" readonly></td>

					<td><input type="number" name="money" id="money"
						value="100" min="100" max="9990" step="10" required></td>

					<td><input type="text" name="purpose" id="purpose"
						value="" size="15" required></td>

				</tr>
			</table>
		    <input type="hidden" name="id" value="${id}" size="">

			<input type="submit" id="regist" name="確認用" class="btn btn-primary mr-2" value="登録確定">
			<input type="button" id="back" name="" class="btn btn-warning mr-4" onclick="location.href='./home.jsp'" value="戻る">
			<input type="button" id="today" name="today" class="btn btn-secondary mr-2" value="今日の日付">
			<input type="button" id="train" name="" class="btn btn-info" onclick="clickBtn1()" value="往復">

		</form>

	</div>

</body>
</html>