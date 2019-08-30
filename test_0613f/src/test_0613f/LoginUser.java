package test_0613f;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.UserInfo;

public class LoginUser {

	public UserInfo findUser(String id) {
		UserInfo ui = new UserInfo();
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
			PreparedStatement presmt = null;
			String sql = "SELECT * FROM userinfo WHERE id = ?";
			presmt = conn.prepareStatement(sql);
			presmt.setString(1, id);
			ResultSet rset = presmt.executeQuery();

			// データベースから取得した値がある間、
			while (rset.next()) {

				ui.setId(rset.getString("id"));
				ui.setPass(rset.getString("pass"));
				ui.setName(rset.getString("name"));
				ui.setJoinCompany(rset.getDate("join_company"));
				ui.setAuth(rset.getInt("auth"));

				// while文で次のレコードの処理へ?

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return ui;
	}

}

