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

	var update_onclick = function(event) {
		$("#form").attr("action", "./update.jsp").submit();
	};

	var timetable_onclick = function(event) {
		$("#form").attr("action", "./timeTable.jsp").submit();
	};

	var admin_onclick = function(event) {
		$("#form").attr("action", "./search.jsp").submit();
	};

	// ドキュメント読み込み完了時に呼び出されます。
	var document_onready = function(event) {
		$("#register").on("click", register_onclick);
		$("#delete").on("click", delete_onclick);
		$("#modify").on("click", modify_onclick);
		$("#excelout").on("click", excelout_onclick);
		$("#update").on("click", update_onclick);
		$("#timetable").on("click", timetable_onclick);
		$("#admin").on("click", admin_onclick);
	};

	$(document).ready(document_onready);
})();

function clickBtn1() {
	// 入力フォームを取得
    var money = document.getElementById("money");
    var division = document.getElementById("division");

    // ボタンを取得
    var element = document.getElementById('train');

    // 往復運賃を表示
    money.value = money.value * 2;

    // onclickの関数名を変更
    element.onclick = new Function("clickBtn2()");

    // valueを「片道」に変更
    element.value = "片道";

    // valueを「往復」に変更
    division.value = "往復";

}

function clickBtn2() {
	// 入力フォームを取得
    var money = document.getElementById("money");
    var division = document.getElementById("division");

    // ボタンを取得
    var element = document.getElementById('train');

    // 片道運賃を表示
    money.value = money.value / 2;

    // onclickの関数名を変更
    element.onclick = new Function("clickBtn1()");

    // valueを「往復」に変更
    element.value = "往復";

    // valueを「片道」に変更
    division.value = "片道";
}

function clickTrainBtn(button) {
	// ボタンの行数を取得
	const data = button.dataset['index'];

	// 入力フォームを取得
	var money = document.getElementById('money' + data);
	var division = document.getElementById('division' + data);

    // ボタンを取得
	var element = document.getElementById('train' + data);

    // ボタンのvalue値が「片道」？
	if(element.value == "片道") {
		// 片道運賃を表示
		money.value = money.value / 2;

		// valueを「往復」に変更
		element.value = "往復";

		// valueを「片道」に変更
		division.value = "片道";
	// ボタンのvalue値が「往復」？
	} else {
		// 往復運賃を表示
		money.value = money.value * 2;

		// valueを「片道」に変更
		element.value = "片道";

		// valueを「往復」に変更
		division.value = "往復";
	}
}

function checkInput1(){
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

function checkInput2(){
	// 配列を設定
	const arr2 = [];

	// チェックボックスを取得
	const vacation_id = document.form.vacation_id;

	// 入力フォームを取得
	var id = document.getElementById('modal_vacation_id');

	//チェックボックスの数分ループを回す
	for (let i = 0; i < vacation_id.length; i++){
		// チェックボックスが付いてる？
		if(vacation_id[i].checked){ //(vacation_id[i].checked === true)と同じ
			// チェックボックスのvalue値を配列にセット
			arr2.push(vacation_id[i].value);
		}
	}

	// 入力フォームに値をセット
	id.value = arr2;
}

function checkInput3(){
	// 配列を設定
	const arr3 = [];

	// チェックボックスを取得
	const work_id = document.form.work_id;

	// 入力フォームを取得
	var id = document.getElementById('modal_work_id');

	//チェックボックスの数分ループを回す
	for (let i = 0; i < work_id.length; i++){
		// チェックボックスが付いてる？
		if(work_id[i].checked){ //(work_id[i].checked === true)と同じ
			// チェックボックスのvalue値を配列にセット
			arr2.push(workn_id[i].value);
		}
	}

	// 入力フォームに値をセット
	id.value = arr2;
}

jQuery(function($) {
	$(document).on('click', '#today', function() {
		// 今日の日時を取得
		var date = new Date();

		// 入力フォームを取得
		var month = document.getElementById('month');
		var day = document.getElementById('day');

		// 今日の月日を表示
		month.value = date.getMonth() + 1;
		day.value = date.getDate();
	});
});

jQuery(function($) {
	$(document).on('click', '#tomorrow', function() {
		// 今日の日時を取得
		var date = new Date();

		// 明日の日時をセット
		date.setDate(date.getDate() + 1);

		// 入力フォームを取得
		var month = document.getElementById('fromMonth');
		var day = document.getElementById('fromDay');

		// 明日の月日を表示
		month.value = date.getMonth() + 1;
		day.value = date.getDate();
	});
});

jQuery(function($) {
	$(document).on('click', '#yesterday', function() {
		// 今日の日時を取得
		var date = new Date();

		// 昨日の日時をセット
		date.setDate(date.getDate() - 1);

		// 入力フォームを取得
		var month = document.getElementById('toMonth');
		var day = document.getElementById('toDay');

		// 昨日の月日を表示
		month.value = date.getMonth() + 1;
		day.value = date.getDate();
	});
});

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


$(function(){

	  var checkAll = '#all'; //「すべて」のチェックボックスのidを指定
	  var checkBox = 'input[class="list"]'; //チェックボックスのnameを指定

	  $( checkAll ).on('click', function() {
	    $( checkBox ).prop('checked', $(this).is(':checked') );
	  });

	  $( checkBox ).on( 'click', function() {
	    var boxCount = $( checkBox ).length; //全チェックボックスの数を取得
	    var checked  = $( checkBox + ':checked' ).length; //チェックされているチェックボックスの数を取得
	    if( checked === boxCount ) {
	      $( checkAll ).prop( 'checked', true );
	    } else {
	      $( checkAll ).prop( 'checked', false );
	    }
	  });

	});
