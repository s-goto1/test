<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function clickBtn1() {

		const money = document.form.money;

		// 値を設定
		money.value = money.value * 2;
		//document.form1.number1.value = "1000";
		// 値を取得
		document.getElementById("span1").textContent = number1.value;
	}
	function clickBtn2() {
		document.form1.number1.value = ""; //クリア
		document.getElementById("span1").textContent = "";
	}
</script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js">

</script>
<script type="text/javascript" src="subtest.js"></script>
<script>
function CheckSearch() {
if (confirm("全件表示されます。")) {}
else {
alert("全件検索をやめました。");
return false;
}
}
</script>
</head>
<body>
	<form action="/test_0613f/Register" name="form" onsubmit="return CheckSearch()" method="post">
		<table border="3">
			<tr>
				<th rowspan="2">月日</th>
				<th colspan="2">区間</th>
				<th rowspan="2">金額</th>
			</tr>
			<tr>

				<th>発</th>
				<th>着</th>



			</tr>
			<tr>

				<td><input type="text" name="date" value="" size=""></td>
				<td><input type="text" name="depature" value="" size=""></td>
				<td><input type="text" name="destination" value="" size=""></td>
				<td><input type="number" name="money" value="" size=""></td>

			</tr>
		</table>
	    <input type="hidden" name="id" value="${id}" size="">

		<input type="submit" name="確認用" value="登録確定">
		<input	type="button" onclick="location.href='./home.jsp'" value="今日の日付">
		<input type="button" onclick="location.href='./home.jsp'" value="戻る">
		<input type="button" onclick="clickBtn1()" value="往復">
	</form>
</body>
</html>