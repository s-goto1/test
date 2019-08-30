<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出勤退勤修正・登録</title>
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
	<div class="container col-md-11 col-md-offset-1 mt-4 mb-4">

		<form id="form1" name="form1" action="Update" method="post">
			<p class="mt-3">
				<b><c:out value="${name}さん" /></b>の出勤退勤一覧データ
				（<c:out value="${year}年${month}月分" />）
			</p>

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
							<div class="text-center">出社先</div>
						</th>

						<th>
							<div class="text-center">備考</div>
						</th>

						<th>
							<div class="text-center">休暇</div>
						</th>
					</tr>

					<c:forEach var="item" items="${list}" varStatus="status" begin="${indexNum}" end="${lastNum}" step="1">
						<tr>

							<td><input type="number" name="month"
								value="${month}" min="1" max="12" readonly>
								<span style="display: inline-block;">月</span></td>

							<td><input type="number" name="day"
								value="${status.index + 1}" min="1" max="31" readonly>
								<span style="display: inline-block;">日</span></td>

							<td><input type="time" name="comeTime"
								value="${item.comeTime}" required></td>

							<td><input type="time" name="leaveTime"
								value="${item.leaveTime}" required></td>

							<td><select name="visit" id="visit">
								<c:choose>
									<c:when test="${item.visit eq 1}">
										<option value="1" selected>JSD東京支店</option>
										<option value="2">${info.visit}</option>
									</c:when>
									<c:otherwise>
										<option value="1">JSD東京支店</option>
										<option value="2" selected>${info.visit}</option>
									</c:otherwise>
								</c:choose>
								</select></td>

							<td><input type="text" name="notes" id="notes"
								value="${item.notes}" size="25"></td>

							<td><select name="vacation" id="vacation">
								<c:choose>
									<c:when test="${item.vacation eq 'なし'}">
										<option value="なし" selected>----------</option>
									</c:when>
									<c:otherwise>
										<option value="なし">----------</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${item.vacation eq '休業日'}">
										<option value="休業日" selected>休業日</option>
									</c:when>
									<c:otherwise>
										<option value="休業日">休業日</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${item.vacation eq '有給休暇'}">
										<option value="有給休暇" selected>有給休暇</option>
									</c:when>
									<c:otherwise>
										<option value="有給休暇">有給休暇</option>
									</c:otherwise>
								</c:choose>
								<%-- <c:choose>
									<c:when test="${item.vacation eq '生理休暇'}">
										<option value="生理休暇" selected>生理休暇</option>
									</c:when>
									<c:otherwise>
										<option value="生理休暇">生理休暇</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${item.vacation eq '慶弔休暇'}">
										<option value="慶弔休暇" selected>慶弔休暇</option>
									</c:when>
									<c:otherwise>
										<option value="慶弔休暇">慶弔休暇</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${item.vacation eq '産前産後休暇'}">
										<option value="産前産後休暇" selected>産前産後休暇</option>
									</c:when>
									<c:otherwise>
										<option value="産前産後休暇">産前産後休暇</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${item.vacation eq '出張休暇'}">
										<option value="出張休暇" selected>出張休暇</option>
									</c:when>
									<c:otherwise>
										<option value="出張休暇">出張休暇</option>
									</c:otherwise>
								</c:choose> --%>
								<c:choose>
									<c:when test="${item.vacation eq '特別休暇'}">
										<option value="特別休暇" selected>特別休暇</option>
									</c:when>
									<c:otherwise>
										<option value="特別休暇">特別休暇</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${item.vacation eq '欠勤'}">
										<option value="欠勤" selected>欠勤</option>
									</c:when>
									<c:otherwise>
										<option value="欠勤">欠勤</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${item.vacation eq '午前休'}">
										<option value="午前休" selected>午前休</option>
									</c:when>
									<c:otherwise>
										<option value="午前休">午前休</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${item.vacation eq '午後休'}">
										<option value="午後休" selected>午後休</option>
									</c:when>
									<c:otherwise>
										<option value="午後休">午後休</option>
									</c:otherwise>
								</c:choose>
								</select>

								<input type="hidden" name="work_id"
								value="${item.work_id}" size="5" readonly></td>

						</tr>
					</c:forEach>

				</tbody>
			</table>

			<div class="mb-2">
				※休日の場合は休暇欄の「休業日」を選択して下さい。出社時刻、退社時刻は適当に埋めて下さい。<br>
				※有給休暇は残り<span style="color: red;">${timeTable.vacationDay}</span>日存在します。
			</div>

			<input type="submit" name="update" class="btn btn-success mr-2" value="修正確定">
			<input type="button" class="btn btn-warning" onclick="location.href='Paging?page=${currentpage}'" value="戻る">

		</form>

	</div>
</body>
</html>