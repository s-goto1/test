<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content="5;./index.jsp" charset="UTF-8">
<title>ログアウト</title>
<link rel="stylesheet" type="text/css" href="css/cssPreloader.css"/>
</head>
<body>
	<div style="text-align: center;">
		ログアウトしました。<br>
		5秒後に画面遷移します。<br>
		少々お待ち下さい．．．<br>
	</div>

	<div class="wrapper">
		<div class="line line1">
			<span class="circle circle-top"></span>
			<div class="dotted">
				<span class="dot dot-top"></span>
				<span class="dot dot-middle-top"></span>
				<span class="dot dot-middle-bottom"></span>
				<span class="dot dot-bottom"></span>
			</div>
			<span class="circle circle-bottom"></span>
		</div>
		<div class="line line2">
			<span class="circle circle-top"></span>
			<div class="dotted">
				<span class="dot dot-top"></span>
				<span class="dot dot-middle-top"></span>
				<span class="dot dot-middle-bottom"></span>
				<span class="dot dot-bottom"></span>
			</div>
			<span class="circle circle-bottom"></span>
		</div>
		<div class="line line3">
			<span class="circle circle-top"></span>
			<div class="dotted">
				<span class="dot dot-top"></span>
				<span class="dot dot-middle-top"></span>
				<span class="dot dot-middle-bottom"></span>
				<span class="dot dot-bottom"></span>
			</div>
			<span class="circle circle-bottom"></span>
		</div>
		<div class="line line4">
			<span class="circle circle-top"></span>
			<div class="dotted">
				<span class="dot dot-top"></span>
				<span class="dot dot-middle-top"></span>
				<span class="dot dot-middle-bottom"></span>
				<span class="dot dot-bottom"></span>
			</div>
			<span class="circle circle-bottom"></span>
		</div>
		<div class="line line5">
			<span class="circle circle-top"></span>
			<div class="dotted">
				<span class="dot dot-top"></span>
				<span class="dot dot-middle-top"></span>
				<span class="dot dot-middle-bottom"></span>
				<span class="dot dot-bottom"></span>
			</div>
			<span class="circle circle-bottom"></span>
		</div>
		<div class="line line6">
			<span class="circle circle-top"></span>
			<div class="dotted">
				<span class="dot dot-top"></span>
				<span class="dot dot-middle-top"></span>
				<span class="dot dot-middle-bottom"></span>
				<span class="dot dot-bottom"></span>
			</div>
			<span class="circle circle-bottom"></span>
		</div>
		<div class="line line7">
			<span class="circle circle-top"></span>
			<div class="dotted">
				<span class="dot dot-top"></span>
				<span class="dot dot-middle-top"></span>
				<span class="dot dot-middle-bottom"></span>
				<span class="dot dot-bottom"></span>
			</div>
			<span class="circle circle-bottom"></span>
		</div>
		<div class="line line8">
			<span class="circle circle-top"></span>
			<div class="dotted">
				<span class="dot dot-top"></span>
				<span class="dot dot-middle-top"></span>
				<span class="dot dot-middle-bottom"></span>
				<span class="dot dot-bottom"></span>
			</div>
			<span class="circle circle-bottom"></span>
		</div>
		<div class="line line9">
			<span class="circle circle-top"></span>
			<div class="dotted">
				<span class="dot dot-top"></span>
				<span class="dot dot-middle-top"></span>
				<span class="dot dot-middle-bottom"></span>
			<span class="dot dot-bottom"></span>
			</div>
			<span class="circle circle-bottom"></span>
		</div>
		<div class="line line10">
			<span class="circle circle-top"></span>
			<div class="dotted">
				<span class="dot dot-top"></span>
				<span class="dot dot-middle-top"></span>
				<span class="dot dot-middle-bottom"></span>
				<span class="dot dot-bottom"></span>
			</div>
		<span class="circle circle-bottom"></span>
		</div>
		<div class="line line11">
			<span class="circle circle-top"></span>
			<div class="dotted">
				<span class="dot dot-top"></span>
				<span class="dot dot-middle-top"></span>
				<span class="dot dot-middle-bottom"></span>
				<span class="dot dot-bottom"></span>
			</div>
			<span class="circle circle-bottom"></span>
		</div>
	</div>
<% session.invalidate();%>
</body>
</html>