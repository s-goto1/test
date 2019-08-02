/**
 *
 */
function getCookie(name) {
	// Cookie文字列のnameインデックス位置を取得
	var start = document.cookie.indexOf(name + "=");

	// Cookie文字列の長さを取得
	var len = start + name.length + 1;

	// Cookieが非存在？
	if ((!start) && (name != document.cookie.substring(0, name.length))) {
		// nullを返却
		return null;
	}

	// Cookie文字列のnameインデックス位置がない場合もnullを返却
	if (start == -1) return null;

	// Cookie文字列の終端インデックス位置を取得
	var end = document.cookie.indexOf(';', len);

	// Cookie文字列の終端インデックス位置がない場合もnullを返却
	if (end == -1) end = document.cookie.length;

	// デコードしたCookieを返却
	return unescape(document.cookie.substring(len, end));
}

function setCookie(name, value, expires, path, domain, secure) {
	// 今日の日時を取得
	var today = new Date();
	today.setTime(today.getTime());

	// 期限が存在？
	if (expires) {
		// 期限を1日単位で設定
		expires = expires * 1000 * 60 * 60 * 24;
	}
	// 期限を設定
	var expires_date = new Date(today.getTime() + (expires));

	// Cookie情報の読み込み
	document.cookie = name+'='+escape(value) +	// 名前
		((expires) ? ';expires='+expires_date.toGMTString() : '') +	// 期限
		((path) ? ';path=' + path : '') +	// パス
		((domain) ? ';domain=' + domain : '') +	// Webサーバ名
		((secure) ? ';secure' : '');	// セキュア接続判定
}

function deleteCookie(name, path, domain) {
	// 名前が存在？
	if (getCookie(name)) document.cookie = name + '=' +	// 名前
			((path) ? ';path=' + path : '') +	// パス
			((domain) ? ';domain=' + domain : '') +	// Webサーバ名
			';expires=Thu, 01-Jan-1970 00:00:01 GMT';	// 期限（過去の日時にすることによりCoookie削除）
}

window.onload = function() {
	// UserIdとPasswordのCookieを読み込み
	loadPreset('id', 'pass', 'loginForm')
};

function savePreset(thisform) {
	// チェックボックスにチェックが付いてる？7
	if(thisform.save.checked) {
		// UserIDの入力値を取得
		var preId = thisform.id.value;

		// Passwordの入力値を取得
		var prePass = thisform.pass.value;

		// UserIdのCookieをセット
		setCookie('id', preId, 14, '', '', '');

		// PasswordのCoookieをセット
		setCookie('pass', prePass, 14, '', '', '');
	}
}

function loadPreset(id, pass, targetForm) {
	// UserIdのCookieを取得
	var preId = getCookie(id);

	// PasswordのCookieを取得
	var prePass = getCookie(pass);

	// Cookieが存在する？
	if(document.cookie) {
		// UserIdの入力欄にUserIdをセット
		document.getElementById(targetForm).id.value = preId;

		// Passwordの入力欄にPasswordをセット
		document.getElementById(targetForm).pass.value = prePass;
	}
}



