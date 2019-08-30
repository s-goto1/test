<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>週勤退勤</title>
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="../css/iziModal.css">
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/subtest.js"></script>
<script type="text/javascript" src="../js/iziModal.min.js"></script>
</head>
<body>
	<div class="container col-md-10 col-md-offset-2 mt-4 mb-4">

		<form id="date" name="date" action="Date" method="post">

			<%@ include file="../year.jsp"%>
			<%@ include file="../month.jsp"%>

			<input type="submit" id="Test" class="btn btn-info ml-2"
				value="該当年月表示">

		</form>

		<form id="form" name="form" class="mb-4" action="" method="post">
			<p class="mt-3">
				<b><c:out value="${name}さん" /></b>の出勤退勤一覧データ
				（<c:out value="${year}年${month}月分" />）
			</p>

			<c:if test="${not empty list}">
				<table border="1" class="table table-striped"
					style="border: solid 3px;">
					<tbody style="border: black 2px">
						<tr>
							<th colspan="3">
								<div class="text-center">月日</div>
							</th>

							<th>
								<div class="text-center">出社時刻</div>
							</th>

							<th>
								<div class="text-center">退社時刻</div>
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

							<c:if test="${auth == 2}">
								<th>
									<div class="text-center">削除</div>
								</th>
							</c:if>
						</tr>

						<c:forEach var="item" items="${list}" varStatus="status" begin="${indexNum}" end="${lastNum}" step="1">
							<tr>
								<td>
									<c:out value="${month}月" />
								</td>

								<td>
									<c:out value="${status.index + 1}日" />
								</td>

								<td>
									<c:out value="(${weekList.get(status.index)})" />
								</td>

								<td><c:if test="${not empty list}">
										<c:if test="${item.vacation.equals('なし') || item.vacation.equals('午前休') ||
											item.vacation.equals('午後休')}">
											<fmt:formatDate value="${item.comeTime}" type="TIME" timeStyle="SHORT" />
										</c:if>
									</c:if></td>

								<td><c:if test="${not empty list}">
										<c:if test="${item.vacation.equals('なし') || item.vacation.equals('午前休') ||
											item.vacation.equals('午後休')}">
											<fmt:formatDate value="${item.leaveTime}" type="TIME" timeStyle="SHORT" />
										</c:if>
									</c:if></td>

								<td><c:if test="${not empty list}">
										<c:if test="${item.vacation.equals('なし') || item.vacation.equals('午前休') ||
											item.vacation.equals('午後休')}">
											<fmt:formatDate value="${item.workTime}" type="TIME" timeStyle="SHORT" />
										</c:if>
									</c:if></td>

								<td><c:if test="${not empty list}">
										<c:if test="${item.vacation.equals('なし') || item.vacation.equals('午前休') ||
											item.vacation.equals('午後休')}">
											<fmt:formatDate value="${item.overTime}" type="TIME" timeStyle="SHORT" />
										</c:if>
									</c:if></td>

								<td><c:if test="${not empty list}">
										<c:out value="${item.notes}" />
									</c:if></td>

								<td><c:if test="${not empty list}">
										<c:out value="${item.vacation}" />
									</c:if></td>

								<c:if test="${auth == 2}">
									<td><c:if test="${not empty list}">
											<div class="text-center">
												<input type="checkbox" name="work_id"
													value="${item.work_id}">
											</div>
										</c:if></td>
								</c:if>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</c:if>

			<c:if test="${empty list}">
				<p>
					<c:out value=" ${nolist}" />
				</p>
			</c:if>

			<c:if test="${not empty list}">
				<nav class="mb-4" aria-label="Page navigation">
					<ul class="pagination justify-content-center">
						<c:choose>
							<c:when test="${currentpage == 1}">
								<li class="page-item disabled">
									<a class="page-link" href="#">
										Prev
									</a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="page-item">
									<a class="page-link"
										href="Paging?page=${currentpage - 1}">
										Prev
									</a>
								</li>
							</c:otherwise>
						</c:choose>

						<c:forEach begin="1" end="${number}" step="1" varStatus="status">
							<c:choose>
								<c:when test="${currentpage == status.index}">
									<li class="page-item active">
										<span class="page-link">
											${status.index}
											<span class="sr-only">
												(current)
											</span>
										</span>
									</li>
								</c:when>
								<c:otherwise>
									<li class="page-item">
										<a class="page-link"
											href="Paging?page=${status.index}">
											${status.index}
										</a>
									</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:choose>
							<c:when test="${currentpage == number}">
								<li class="page-item disabled">
									<a class="page-link" href="#">
										Next
									</a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="page-item">
									<a class="page-link"
										href="Paging?page=${currentpage + 1}">
										Next
									</a>
								</li>
							</c:otherwise>
						</c:choose>
					</ul>
				</nav>
			</c:if>

			<c:choose>
				<c:when test="${masterAuth eq 1}">
					<input type="button" id="excelout" class="btn btn-warning mr-2"
						value="Excelに出力">
					<input type="button" id="admin" class="btn btn-primary mr-2"
						value="管理画面">
				</c:when>
				<c:otherwise>
					<input type="button" id="timetable" class="btn btn-primary mr-2"
						value="タイムテーブル変更">
					<input type="button" id="update" class="btn btn-success mr-2"
						value="修正・登録">
					<c:if test="${not empty list}">
						<input type="button" id="excelout" class="btn btn-warning mr-2"
							value="Excelに出力">
						<input type="button" id="modal"
							class="btn btn-danger open-options mr-2" onclick="checkInput3()"
							value="削除">
					</c:if>
				</c:otherwise>
			</c:choose>
			<input type="button" id="menu" class="btn btn-light mr-2"
				onclick="location.href='../menu.jsp'" value="メニュー">
			<input type="button" id="logout" class="btn btn-secondary"
				onclick="location.href='../logout.jsp'" value="ログアウト">

		</form>

	</div>

	<div class="iziModal" id="modal-options" data-izimodal-title="選択データの削除"
		data-izimodal-subtitle="選択した出張精算データを削除します">
		<div class="text-center mt-3">
			一度削除したデータは再び復元する事ができません。<br> 本当に削除しますか？<br>
			<div class="mb-3"></div>
			<form id="formDelete" name="formDelete" action="Delete" method="post">
				<input type="hidden" id="modal_work_id" name="work_id" value=""
					readonly>
				<ul class="text-center list-inline">
					<li class="list-inline-item">
						<button type="submit" id="delete" class="btn btn-danger">YES</button>
					</li>
					<li class="list-inline-item">
						<button id="no" class="btn btn-primary" data-izimodal-close="">NO</button>
					</li>
				</ul>
			</form>
		</div>
	</div>

</body>
</html>
