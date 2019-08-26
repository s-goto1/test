package test_0613f.vacation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.UserInfo;
import entity.Vacation;

public class SearchDao {
	public List<String> findIdAll(String name) {
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
			String sql = "SELECT id FROM userinfo WHERE name != ?";

			// SQL文をセット
			presmt = conn.prepareStatement(sql);

			// プレースホルダに埋め込み
			presmt.setString(1, name);

			// SQL文実行
			ResultSet rset = presmt.executeQuery();

			// データベースから取得したレコード分必要な情報をセット
			while (rset.next()) {
				UserInfo user = new UserInfo();

				user.setId(rset.getString("id"));

				list.add(user.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return list;
	}

//	※L212～
//	public List<String> findNameAll(String id) {
//		// String型のListの宣言
//		List<String> list = new ArrayList<>();
//
//		// JDBCドライバ読み込み
//		try {
//			// PostgreSQLドライバの読み込み
//			Class.forName("org.postgresql.Driver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		// データベースへの接続
//		try (Connection conn = DriverManager.getConnection(
//				"jdbc:postgresql:postgres",
//				"postgres",
//				"Asdf123");) {
//			// PreparedStatementの宣言
//			PreparedStatement presmt = null;
//
//			// SQL文
//			String sql = "SELECT name FROM userinfo WHERE id != ?";
//
//			// SQL文をセット
//			presmt = conn.prepareStatement(sql);
//
//			// プレースホルダに埋め込み
//			presmt.setString(1, id);
//
//			// SQL文実行
//			ResultSet rset = presmt.executeQuery();
//
//			// データベースから取得したレコード分必要な情報をセット
//			while (rset.next()) {
//				UserInfo user = new UserInfo();
//
//				user.setName(rset.getString("name"));
//
//				list.add(user.getName());
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		// DTOクラスのインスタンスのListを返す
//		return list;
//	}

	public List<String> findIdDistinct(Integer year) {
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
			String sql = "SELECT DISTINCT id FROM vacation "
					+ "WHERE year = ?";

			// SQL文をセット
			presmt = conn.prepareStatement(sql);

			// プレースホルダに埋め込み
			presmt.setInt(1, year);

			// SQL文実行
			ResultSet rset = presmt.executeQuery();

			// データベースから取得したレコード分必要な情報をセット
			while (rset.next()) {
				Vacation vacation = new Vacation();

				vacation.setId(rset.getString("id"));

				list.add(vacation.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return list;
	}

	public List<String> findNameDistinct(Integer year) {
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
			String sql = "SELECT DISTINCT u.name FROM userinfo AS u "
					+ "JOIN vacation AS v ON u.id = v.id "
					+ "WHERE v.year = ?";

			// SQL文をセット
			presmt = conn.prepareStatement(sql);

			// プレースホルダに埋め込み
			presmt.setInt(1, year);

			// SQL文実行
			ResultSet rset = presmt.executeQuery();

			// データベースから取得したレコード分必要な情報をセット
			while (rset.next()) {
				UserInfo info = new UserInfo();

				info.setName(rset.getString("name"));

				list.add(info.getName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return list;
	}

	//上のfindNameAll()とそんなに変わらないけど今後のこと考えてこっちに
    //やってることは一緒、引数受け取る必要がなかったので消去、どうしても上が必要なら復活させてください。
	public List<String> findNameAll() {
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
			String sql = "SELECT name FROM userinfo WHERE auth = ?";

			// SQL文をセット
			presmt = conn.prepareStatement(sql);

			// プレースホルダに埋め込み
			presmt.setInt(1, 2);

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
