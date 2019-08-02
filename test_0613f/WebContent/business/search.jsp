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
	下記の検索フォームに閲覧したい人物名を入れてボタンを押すことでその人物のページに遷移します。<br>

	<br>

		<form id="search" name="search" action="Search" method="post">

			<input type="text" id="name" name="name" value="">

			<input type="submit" id="" class="btn btn-info ml-2" value="名前検索">

		</form>

		<br>

		<button id="menu" class="btn btn-light mr-2"
			onclick="location.href='../menu.jsp'">メニュー</button>
		<button id="logout" class="btn btn-secondary"
			onclick="location.href='../logout.jsp'">ログアウト</button>
	</div>
</body>
</html>