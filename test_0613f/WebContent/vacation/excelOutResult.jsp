<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>休暇申請出力結果</title>
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="../css/circle.css"/>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container col-md-9 col-md-offset-2 mt-4 mb-4">

		<p><c:out value="${year}年分" />の休暇申請データを${excel}に出力しました。</p>

		<br>

		<p>※出力イメージ</p>

		<c:forEach var="item" items="${list}" varStatus="status" begin="0" end="${size - 1}" step="1">
			<table border="1" class="mb-2" style="font-size: 13px; border: solid 3px;">
				<tbody style="border: black 2px">
					<tr>
						<td width="25" height="50">
							<div class="text-center align-items-center">
								期<br>間
							</div>
						</td>

						<td width="200" style="border-right: hidden;">
							<c:if test="${not empty list}">
								<div class="text-center" style="font-size:15px;">
									<c:out value="${item.year}年" />
									<c:out value="${item.fromMonth}月" />
									<c:out value="${item.fromDay}日" />
									<div class="mb-2"></div>
									<c:out value="${item.year}年" />
									<c:out value="${item.toMonth}月" />
									<c:out value="${item.toDay}日" />
								</div>
							</c:if></td>

						<td width="50">
							<div class="text-right">
								より
								<div class="mb-2"></div>
								まで
							</div>
						</td>

						<td width="250" class="align-items-center">
							<c:if test="${not empty list}">
								(　<c:out value="${item.totalDay}　日間" />)
							</c:if>
						</td>
					</tr>

					<tr>
						<td width="25" height="80">
							<div class="text-center align-items-center">
								区<br>分
							</div>
						</td>

						<td colspan="3">
							<div class="mt-2 mb-2">
								<c:choose>
									<c:when test="${item.division eq '有給休暇'}">
										<div class="maru size_normal black">
											<div class="letter">①有給休暇</div>
										</div>
										&ensp;②生理休暇　　③慶弔休暇　　④産前産後休暇
										<div class="mb-3"></div>
										&ensp;　⑤転勤休暇　　⑥特別休暇　　⑦その他（　　　　　　　）
									</c:when>
									<c:when test="${item.division eq '生理休暇'}">
										&ensp;　①有給休暇&ensp;
										<div class="maru size_normal black">
											<div class="letter">②生理休暇</div>
										</div>
										&ensp;③慶弔休暇　　④産前産後休暇
										<div class="mb-3"></div>
										&ensp;　⑤転勤休暇　　⑥特別休暇　　⑦その他（　　　　　　　）
									</c:when>
									<c:when test="${item.division eq '慶弔休暇'}">
										&ensp;　①有給休暇　　②生理休暇&ensp;
										<div class="maru size_normal black">
											<div class="letter">③慶弔休暇</div>
										</div>
										&ensp;④産前産後休暇
										<div class="mb-3"></div>
										&ensp;　⑤転勤休暇　　⑥特別休暇　　⑦その他（　　　　　　　）
									</c:when>
									<c:when test="${item.division eq '産前産後休暇'}">
										&ensp;　①有給休暇　　②生理休暇　　③慶弔休暇&ensp;
										<div class="maru size_large black">
											<div class="letter">④産前産後休暇</div>
										</div>
										<div class="mb-3"></div>
										&ensp;　⑤転勤休暇　　⑥特別休暇　　⑦その他（　　　　　　　）
									</c:when>
									<c:when test="${item.division eq '転勤休暇'}">
										&ensp;　①有給休暇　　②生理休暇　　③慶弔休暇　　④産前産後休暇
										<div class="mb-3"></div>
										<div class="maru size_normal black">
											<div class="letter">⑤転勤休暇</div>
										</div>
										&ensp;⑥特別休暇　　⑦その他（　　　　　　　）
									</c:when>
									<c:when test="${item.division eq '特別休暇'}">
										&ensp;　①有給休暇　　②生理休暇　　③慶弔休暇　　④産前産後休暇
										<div class="mb-3"></div>
										&ensp;　⑤転勤休暇&ensp;
										<div class="maru size_normal black">
											<div class="letter">⑥特別休暇</div>
										</div>
										&ensp;⑦その他（　　　　　　　）
									</c:when>
									<c:otherwise>
										&ensp;　①有給休暇　　②生理休暇　　③慶弔休暇　　④産前産後休暇
										<div class="mb-3"></div>
										&ensp;　⑤転勤休暇　　⑥特別休暇&ensp;
										<div class="maru black" style="width: ${lengthList.get(status.index) * 7 + 100}px; height: 25px;">
											<div class="letter">⑦その他（${item.division}）</div>
										</div>
									</c:otherwise>
								</c:choose>
							</div>
						</td>
					</tr>
				</tbody>
			</table>

			<table border="1" class="mb-4" style="font-size: 13px; border: solid 3px;">
				<tbody style="border: black 2px">
					<tr>
						<td width="25" height="100">
							<div class="text-center align-items-center">
								事<br>由
							</div>
						</td>

						<td width="500"><c:if test="${not empty list}">
								<div class="text-center align-items-center">
									<c:out value="${item.reason}" />
								</div>
							</c:if></td>
					</tr>

					<tr>
						<td width="25" height="70">
							<div class="text-center align-items-center">
								備<br>考
							</div>
						</td>

						<td></td>
					</tr>
				</tbody>
			</table>
		</c:forEach>

	<input type="button" class="btn btn-warning" onclick="location.href='Paging?page=${currentpage}'" value="戻る">

	</div>
</body>
</html>