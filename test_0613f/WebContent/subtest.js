/**
 *
 */
(function() {
	// 「戻る」ボタン押下時に呼び出されます。
	var register_onclick = function(event) {
		$("#form").attr("action", "./register.jsp").submit();
	};

	// 「登録」ボタン押下時に呼び出されます。
	var excelout_onclick = function(event) {
		$("#form").attr("action", "./ExcelOut").submit();
	};

	var delete_onclick = function(event) {
		$("#formDelete").attr("action", "./Delete").submit();
	};

	var modify_onclick = function(event) {
		$("#form").attr("action", "./modify.jsp").submit();
	};

	// ドキュメント読み込み完了時に呼び出されます。
	var document_onready = function(event) {
		$("#register").on("click", register_onclick);
		$("#delete").on("click", delete_onclick);
		$("#modify").on("click", modify_onclick);
		$("#excelout").on("click", excelout_onclick);
	};

	$(document).ready(document_onready);
})();

function clickBtn1() {
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
}

function checkInput(){
	// 配列を設定
	const arr1 = [];

	// チェックボックスを取得
	const totalM_id = document.form.totalM_id;

	// 入力フォームを取得
	var id = document.getElementById('modal_totalM_id');

	//チェックボックスの数分ループを回す
	for (let i = 0; i < totalM_id.length; i++){
		// チェックボックスが付いてる？
		if(totalM_id[i].checked){ //(totalM_id[i].checked === true)と同じ
			// チェックボックスのvalue値を配列にセット
			arr1.push(totalM_id[i].value);
		}
	}

	// 入力フォームに値をセット
	id.value = arr1;
}

jQuery(function($) {
	$(document).on('click', '.open-options', function(event) {
		// モーダルウィンドウを開ける
		event.preventDefault();
		$('#modal-options').iziModal('open');
	});
});

jQuery(function($) {
	$('#modal-options').iziModal({
		// モーダルウィンドウの設定
		headerColor: '#26A69A', //ヘッダー部分の色
		width: 500, //横幅
		overlayColor: 'rgba(0, 0, 0, 0.5)', //モーダルの背景色
		fullscreen: true, //全画面表示
		transitionIn: 'fadeInUp', //表示される時のアニメーション
		transitionOut: 'fadeOutDown' //非表示になる時のアニメーション
	});
});


