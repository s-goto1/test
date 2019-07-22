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
		$("#form").attr("action", "./Delete").submit();
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

jQuery(function($) {
	// セレクトボックスが変更されたら処理をする
	$('#pref-select').change(function() {

		// 選択した値を取得
		var select_val = $('#pref-select option:selected').val();

		// tbodyのtr数回 処理をする
		$.each($("#pref-table  tr"), function(index, element) {

			// 選択した値が空欄だったら、全ての行を表示する為の処理
			if (select_val == "") {
				$(element).css("display", "table-row");
				return true; // 次のtrへ
			}

			// 1行をテキストとして取り出し、セレクトボックスで選択した値があるかをチェック
			var row_text = $(element).text();

			if (row_text.indexOf(select_val) != -1) {
				// 見つかった場合は表示する
				$(element).css("display", "table-row");
			} else {
				// 見つからなかった場合は非表示に
				$(element).css("display", "none");
			}

		});
	});
});



_showAlert('メールアドレスを更新しました。');

function _showAlert(msg) {
  $('form').on('submit', function(e) {
    var $alert = $('<div>').prependTo($('#container')).addClass('alert');
    e.preventDefault();
    $alert.css('display', 'none').text(msg).fadeIn('slow', function() {
      setTimeout(function(){
        $('form').off('submit').submit();
      },1000);
    });
  });
}