<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<script>function clickBtn1() {
	// 入力フォームを取得
    var money = document.getElementById("money");

    // ボタンを取得
    var element = document.getElementById('train');

    // 往復運賃を表示
    money.value = money.value * 2;

    // onclickの関数名を変更
    element.onclick = new Function("clickBtn2()");

    // valueを「片道」に変更
    element.value = "片道";
}

function clickBtn2() {
	// 入力フォームを取得
    var money = document.getElementById("money");

    // ボタンを取得
    var element = document.getElementById('train');

    // 片道運賃を表示
    money.value = money.value / 2;

    // onclickの関数名を変更
    element.onclick = new Function("clickBtn1()");

    // valueを「往復」に変更
    element.value = "往復";
}</script>
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
				<td><input type="text" name="destination" id="test" value="" size=""></td>
				<td><input type="number" name="money" id="money" value="100" size=""></td>

			</tr>
		</table>
	    <input type="hidden" name="id" value="${id}" size="">

		<input type="submit" name="確認用" value="登録確定">
		<input	type="button" onclick="location.href='./home.jsp'" value="今日の日付">
		<input type="button" onclick="location.href='./home.jsp'" value="戻る">
		<input type="button" id="train" onclick="clickBtn1()" value="往復">
	</form>
</body>
</html>