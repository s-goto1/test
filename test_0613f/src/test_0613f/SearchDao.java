package test_0613f;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.UserInfo;

public class SearchDao {
	public List<String> findNameAll(String id) {
		// String型のListの宣言
		List<String> list = new ArrayList<>();

		// JDBCドライバ読み込み
		try {
			// PostgreSQLドライバの読み込み
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// データベースへの接続
		try (Connection conn = DriverManager.getConnection(
				"jdbc:postgresql:axiz_db",
				"axizuser",
				"axiz");) {
			// PreparedStatementの宣言
			PreparedStatement presmt = null;

			// SQL文
			String sql = "SELECT id, name FROM userinfo WHERE id != ?";

			// SQL文をセット
			presmt = conn.prepareStatement(sql);

			// プレースホルダに埋め込み
			presmt.setString(1, id);

			// SQL文実行
			ResultSet rset = presmt.executeQuery();

			// データベースから取得したレコード分必要な情報をセット
			while (rset.next()) {
				UserInfo user = new UserInfo();

				user.setName(rset.getString("name"));

				list.add(user.getName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return list;
	}
}
