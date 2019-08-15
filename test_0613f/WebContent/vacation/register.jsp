<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>休暇申請登録</title>
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
	<div class="container col-md-9 col-md-offset-2 mt-4 mb-4">

		<form action="Register" id="form" name="form" method="post">

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
					<tr>
						<td><input type="number" name="fromMonth" id="fromMonth"
							value="${fromMonth}" min="1" max="12" required>
							<span style="display: inline-block;">月</span>

						<input type="number" name="fromDay" id="fromDay"
							value="" min="1" max="31" required>
						<span style="display: inline-block;">日より</span></td>

						<td><input type="number" name="toMonth" id="toMonth"
							value="${fromMonth}" min="1" max="12" required>
							<span style="display: inline-block;">月</span>

						<input type="number" name="toDay" id="toDay"
							value="" min="1" max="31" required>
						<span style="display: inline-block;">日まで</span></td>

						<td><select name="division" id="division">
								<option value="有給休暇">有給休暇</option>
								<option value="生理休暇">生理休暇</option>
								<option value="慶弔休暇">慶弔休暇</option>
								<option value="産前産後休暇">産前産後休暇</option>
								<option value="転勤休暇">転勤休暇</option>
								<option value="特別休暇">特別休暇</option>
								<option value="その他">その他</option>
							</select></td>

						<td><input type="text" name="reason" id="reason"
							value="" size="15" required></td>
					</tr>
				</tbody>
			</table>

		    <input type="hidden" name="id" value="${id}" size="">

			<input type="submit" id="regist" name="確認用" class="btn btn-primary mr-2" value="登録確定">
			<input type="button" id="back" name="" class="btn btn-warning mr-4" onclick="location.href='Paging?page=${currentpage}'" value="戻る">
			<input type="button" id="tomorrow" name="tomorrow" class="btn btn-light mr-2" value="明日の日付">
			<input type="button" id="yesterday" name="yesterday" class="btn btn-secondary" value="昨日の日付">

		</form>

	</div>
</body>
</html>