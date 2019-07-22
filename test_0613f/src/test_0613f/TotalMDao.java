package test_0613f;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.TotalM;

public class TotalMDao {
	public List<TotalM> findAll(String id) {

		List<TotalM> list = new ArrayList<TotalM>();

		// JDBCドライバ読み込み
		try {
			// PostgreSQLドライバの読み込み
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// データベースへの接続
		try (Connection conn = DriverManager.getConnection(
				"jdbc:postgresql:postgres",
				"postgres",
				"Asdf123");) {
			PreparedStatement presmt = null;
			String sql = "SELECT * FROM totalm WHERE id = ? ";
			presmt = conn.prepareStatement(sql);
			presmt.setString(1, id);
			ResultSet rset = presmt.executeQuery();

			// データベースから取得した値がある間、
			while (rset.next()) {

				TotalM tom = new TotalM();

				tom.setId(rset.getString("id"));
				tom.setTotalM_id(rset.getInt("totalM_id"));
				tom.setMoney(rset.getInt("money"));
				tom.setName(rset.getString("name"));
				tom.setDate(rset.getString("date"));
				tom.setDepature(rset.getString("depature"));
				tom.setDestination(rset.getString("destination"));
				list.add(tom);
				// while文で次のレコードの処理へ?

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return list;
	}

	public List<TotalM> findAllByMonth(String id, String month) {

		List<TotalM> list = new ArrayList<TotalM>();

		// JDBCドライバ読み込み
		try {
			// PostgreSQLドライバの読み込み
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// データベースへの接続
		try (Connection conn = DriverManager.getConnection(
				"jdbc:postgresql:postgres",
				"postgres",
				"Asdf123");) {
			PreparedStatement presmt = null;
			String sql = "SELECT * FROM totalm WHERE id = ? AND date = ? ";
			presmt = conn.prepareStatement(sql);
			presmt.setString(1, id);
			presmt.setString(2, month);
			ResultSet rset = presmt.executeQuery();

			// データベースから取得した値がある間、
			while (rset.next()) {

				TotalM tom = new TotalM();

				tom.setId(rset.getString("id"));
				tom.setTotalM_id(rset.getInt("totalM_id"));
				tom.setMoney(rset.getInt("money"));
				tom.setName(rset.getString("name"));
				tom.setDate(rset.getString("date"));
				tom.setDepature(rset.getString("depature"));
				tom.setDestination(rset.getString("destination"));
				list.add(tom);
				// while文で次のレコードの処理へ?

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return list;
	}

}
