<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	<div class="container col-md-9 col-md-offset-2 mt-4">
	出張精算の管理用ページです。<br>
	下記の検索フォームに閲覧したいユーザのIDまたは名前を入れてボタンを押すことで、その人物のページに遷移します。<br>
	（※該当者が見当たらなかった場合、近しいIDまたは名前の人物のページに遷移します）<br>
	また、未入力のままボタンを押すと社員全員分のデータを表示します。<br>

	<br>

		<form id="search" name="search" action="Search" method="post">

			<input type="text" id="id" name="id" value="">
			<input type="submit" id="idSearch" name="idSearch" class="btn btn-info ml-2 mb-3" value="ID検索">

			<input type="text" id="name" name="name" value="">
			<input type="submit" id="nameSearch" name="nameSearch" class="btn btn-info ml-2 mb-3" value="名前検索">

		</form>

		<button id="menu" class="btn btn-light mr-2"
			onclick="location.href='../menu.jsp'">メニュー</button>
		<button id="logout" class="btn btn-secondary"
			onclick="location.href='../logout.jsp'">ログアウト</button>
	</div>
</body>
</html>