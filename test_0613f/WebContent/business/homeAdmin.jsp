<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出張精算（全社員）</title>
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
	<div class="container col-md-10 col-md-offset-2 mt-4 mb-4">

		<form id="date" name="date" action="Date" method="post">

			<%@ include file="../year.jsp"%>
			<%@ include file="../month.jsp"%>
			<input type="hidden" name="admin" value="admin">
			 <input
				type="submit" id="Test" class="btn btn-info ml-2" value="該当年月表示">

		</form>

		<c:if test="${not empty map}">
			<button type="button" class="btn btn-success mt-3"
				data-toggle="collapse" data-target=".multi-collapse"
				aria-expanded="false">全開閉</button>
		</c:if>

		<form id="form" name="form" action="Search" method="post">
			<p class="mt-3">
				<b>全社員</b>の出張清算一覧データ
				 （<c:out value="${year}年${month}月分" />）
			</p>

			<c:if test="${not empty map}">
				<c:forEach var="i" items="${map}" varStatus="status">
					<div class="accordion mb-4" id="accordion" role="tablist">
						<div class="card">
							<div class="card-header" id="header-${status.count}" role="tab">
								<button class="btn btn-link btn-block text-left" type="button"
									data-toggle="collapse" data-target="#card-${status.count}"
									aria-expanded="false" aria-controls="card-${status.count}">
									<b><c:out value="${nameList.get(status.index)}さん" /></b>
								</button>
							</div>

							<input type='hidden' name='nameSearch' id='nameSearch'
								value="aaa">
							<button type='submit' name='name' id='name'
								value='${nameList.get(status.index)}'>
								${nameList.get(status.index)}だけ表示</button>

							<div id="card-${status.count}"
								class="collapse show multi-collapse" role="tabpanel"
								aria-labelledby="header-${status.count}">
								<div class="card-body">
									<table border="1" class="table table-striped"
										style="border: solid 3px;">
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

											<c:forEach var="item" items="${i.value}" begin="0"
												end="${i.value.size()}">
												<tr>
													<td><c:if test="${not empty map}">
															<c:out value="${item.month}月" />
														</c:if></td>

													<td><c:if test="${not empty map}">
															<c:out value="${item.day}日" />
														</c:if></td>

													<td><c:if test="${not empty map}">
															<c:out value="${item.depature}" />
														</c:if></td>

													<td><c:if test="${not empty map}">
															<c:out value="${item.destination}" />
														</c:if></td>

													<td><c:if test="${not empty map}">
															<c:out value="${item.transportation}" />
														</c:if></td>

													<td><c:if test="${not empty map}">
															<c:out value="${item.place}" />
														</c:if></td>

													<td><c:if test="${not empty map}">
															<c:out value="${item.division}" />
														</c:if></td>

													<td><c:if test="${not empty map}">
															<c:out value="${item.money}" />
														</c:if></td>

													<td><c:if test="${not empty map}">
															<c:out value="${item.purpose}" />
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
											<span class="sr-only"> (current) </span>
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
			<input type="button" id="admin" class="btn btn-primary mr-2"
				value="管理画面"> <input type="button" id="menu"
				class="btn btn-light mr-2" onclick="location.href='../menu.jsp'"
				value="メニュー"> <input type="button" id="logout"
				class="btn btn-secondary" onclick="location.href='../logout.jsp'"
				value="ログアウト">

		</form>

	</div>
</body>
</html>