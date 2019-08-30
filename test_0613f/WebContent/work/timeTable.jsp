<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出勤退勤タイムテーブル変更</title>
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
	<div class="container col-md-8 col-md-offset-2 mt-4 mb-4">

		<c:if test="${not empty error}">
			<p class="mb-2" style="color: red;">
				<c:out value="${error}" />
			</p>
		</c:if>

		<form id="form" name="form" class="mb-4" action="TimeTable" method="post">

		<table border="1" class="table table-striped" style="border: solid 3px;">
			<tbody style="border: black 2px">
				<tr>
					<th>
						<div class="text-center">現場企業名</div>
					</th>

					<td>
						<input type="text" name="visitName" size="25" value="${timeTable.visitName}">
					</td>
				</tr>

				<tr>
					<th>
						<div class="text-center">出社時刻</div>
					</th>

					<td>
						<input type="time" name="visitComeTime" value="${timeTable.visitComeTime}">
					</td>
				</tr>

				<tr>
					<th>
						<div class="text-center">退社時刻</div>
					</th>

					<td>
						<input type="time" name="visitLeaveTime" value="${timeTable.visitLeaveTime}">
					</td>
				</tr>

				<tr>
					<th>
						<div class="text-center">休憩時間</div>
					</th>

					<td>
						<input type="time" name="visitBrakeTime" value="${timeTable.visitBrakeTime}">
					</td>
				</tr>

				<tr>
					<th>
						<div class="text-center">丸め時間（分）</div>
					</th>

					<td>
						<select name="roundTime" id="roundTime">
							<c:choose>
								<c:when test="${timeTable.roundTime eq '5'}">
									<option value="5" selected>5分</option>
								</c:when>
								<c:otherwise>
									<option value="5">5分</option>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${timeTable.roundTime eq '10'}">
									<option value="10" selected>10分</option>
								</c:when>
								<c:otherwise>
									<option value="10">10分</option>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${timeTable.roundTime eq '15'}">
									<option value="15" selected>15分</option>
								</c:when>
								<c:otherwise>
									<option value="15">15分</option>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${timeTable.roundTime eq '20'}">
									<option value="20" selected>20分</option>
								</c:when>
								<c:otherwise>
									<option value="20">20分</option>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${timeTable.roundTime eq '30'}">
									<option value="30" selected>30分</option>
								</c:when>
								<c:otherwise>
									<option value="30">30分</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr>

				<tr>
					<th>
						<div class="text-center">社員番号</div>
					</th>

					<td>
						<input type="number" name="companyNum"
							 value="${timeTable.companyNum}" min="0" max="999">
					</td>
				</tr>
			</tbody>
		</table>

		<input type="submit" name="timetable" class="btn btn-primary mr-2" value="変更確定">
		<input type="button" class="btn btn-warning" onclick="location.href='Paging?page=${currentpage}'" value="戻る">

		</form>

	</div>
</body>
</html>