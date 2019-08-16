<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>休暇申請（全社員）</title>
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" />
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

		<form id="date" name="date" action="Date" method="post">

			<%@ include file="../year.jsp"%>

			<input type="submit" id="yearChange" class="btn btn-info ml-2"
				value="該当年表示">

		</form>

		<c:if test="${not empty map}">
			<button type="button" class="btn btn-success mt-3" data-toggle="collapse"
				data-target=".multi-collapse" aria-expanded="false">
				全開閉
			</button>
		</c:if>

		<form id="form" name="form" action="" method="post">
			<p class="mt-3">
				<b>全社員</b>の休暇申請一覧データ
				（<c:out value="${year}年分" />）
			</p>

			<c:if test="${not empty map}">
				<c:forEach var="i" items="${map}" varStatus="status">
					<div class="accordion mb-4" id="accordion" role="tablist">
						<div class="card">
							<div class="card-header" id="header-${status.count}" role="tab">
								<button class="btn btn-link btn-block text-left" type="button"
									data-toggle="collapse" data-target="#card-${status.count}"
										aria-expanded="false"
									aria-controls="card-${status.count}">
									<b><c:out value="${nameList.get(status.index)}さん" /></b>
								</button>
							</div>
							<div id="card-${status.count}" class="collapse show multi-collapse" role="tabpanel"
								aria-labelledby="header-${status.count}">
								<div class="card-body">
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

											<c:forEach var="item" items="${i.value}" begin="0" end="${i.value.size()}">
												<tr>
													<td style="border-right: hidden;"><c:if test="${not empty map}">
														<c:out value="${item.fromMonth}月" />
														<c:out value="${item.fromDay}日より" />
													</c:if></td>

												<td style="border-right: hidden;"><c:if test="${not empty map}">
														<c:out value="${item.toMonth}月" />
														<c:out value="${item.toDay}日まで" />
													</c:if></td>

												<td><c:if test="${not empty map}">
														<c:out value="(${item.totalDay}日間)" />
													</c:if></td>

												<td><c:if test="${not empty map}">
														<c:out value="${item.division}" />
													</c:if></td>

												<td><c:if test="${not empty map}">
														<c:out value="${item.reason}" />
													</c:if></td>
												</tr>
											</c:forEach>

										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>

			<c:if test="${map.isEmpty()}">
				<p>
					<c:out value=" ${nomap}" />
				</p>
			</c:if>

			<c:if test="${not empty map}">
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
										href="PagingServTest?page=${currentpage - 1}">
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
											href="PagingServTest?page=${status.index}">
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
										href="PagingServTest?page=${currentpage + 1}">
										Next
									</a>
								</li>
							</c:otherwise>
						</c:choose>
					</ul>
				</nav>
			</c:if>


			<!--<input type="button" id="excelout"
				class="btn btn-warning mr-2" value="Excelに出力">-->
			<input type="button" id="admin"
				class="btn btn-primary mr-2" value="管理画面">
			<input type="button" id="menu"
				class="btn btn-light mr-2" onclick="location.href='../menu.jsp'"
				value="メニュー">
			<input type="button" id="logout"
				class="btn btn-secondary" onclick="location.href='../logout.jsp'"
				value="ログアウト">

		</form>

	</div>
</body>
</html>