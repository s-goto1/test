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

	public List<TotalM> findAllByMonthForId(String id, Integer year, Integer month, int offset) {

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
				"jdbc:postgresql:axiz_db",
				"axizuser",
				"axiz");) {
			PreparedStatement presmt = null;
			String sql = "SELECT * FROM totalm WHERE id = ? AND year = ? AND month = ? "
					+ "ORDER BY day, totalm_id LIMIT 5 OFFSET ?";
			presmt = conn.prepareStatement(sql);
			presmt.setString(1, id);
			presmt.setInt(2, year);
			presmt.setInt(3, month);
			presmt.setInt(4, offset);
			ResultSet rset = presmt.executeQuery();

			// データベースから取得した値がある間、
			while (rset.next()) {

				TotalM tom = new TotalM();

				tom.setId(rset.getString("id"));
				tom.setTotalM_id(rset.getInt("totalM_id"));
				tom.setYear(rset.getInt("year"));
				tom.setMonth(rset.getInt("month"));
				tom.setDay(rset.getInt("day"));
				tom.setTransportation(rset.getString("transportation"));
				tom.setDepature(rset.getString("depature"));
				tom.setDestination(rset.getString("destination"));
				tom.setDivision(rset.getString("division"));
				tom.setMoney(rset.getInt("money"));
				tom.setPlace(rset.getString("place"));
				tom.setPurpose(rset.getString("purpose"));
				list.add(tom);
				// while文で次のレコードの処理へ?

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return list;
	}

	//名前で絞って全部表示するメソッド（仮）
	public List<TotalM> findAllByMonthForName(String name, Integer year, Integer month, int offset) {

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
				"jdbc:postgresql:axiz_db",
				"axizuser",
				"axiz");) {
			PreparedStatement presmt = null;
			String sql = "SELECT * FROM totalm AS m JOIN userinfo AS u ON m.id = u.id "
					+ "WHERE u.name = ? AND m.year = ? AND m.month = ? "
					+ "ORDER BY m.day, m.totalm_id LIMIT 5 OFFSET ?";
			presmt = conn.prepareStatement(sql);
			presmt.setString(1, name);
			presmt.setInt(2, year);
			presmt.setInt(3, month);
			presmt.setInt(4, offset);
			ResultSet rset = presmt.executeQuery();

			// データベースから取得した値がある間、
			while (rset.next()) {

				TotalM tom = new TotalM();

				tom.setId(rset.getString("id"));
				tom.setTotalM_id(rset.getInt("totalM_id"));
				tom.setYear(rset.getInt("year"));
				tom.setMonth(rset.getInt("month"));
				tom.setDay(rset.getInt("day"));
				tom.setTransportation(rset.getString("transportation"));
				tom.setDepature(rset.getString("depature"));
				tom.setDestination(rset.getString("destination"));
				tom.setDivision(rset.getString("division"));
				tom.setMoney(rset.getInt("money"));
				tom.setPlace(rset.getString("place"));
				tom.setPurpose(rset.getString("purpose"));
				list.add(tom);
				// while文で次のレコードの処理へ?

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return list;
	}

	//レコード数取得（ページング用）
	public int countRow(String id, Integer year, Integer month) {

		int count = 0;
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
			String sql = "SELECT COUNT (*) FROM totalm WHERE id = ? AND year = ? "
					+ "AND month = ? ";
			presmt = conn.prepareStatement(sql);
			presmt.setString(1, id);
			presmt.setInt(2, year);
			presmt.setInt(3, month);

			ResultSet rset = presmt.executeQuery();

			// データベースから取得した値がある間、
			while (rset.next()) {
				count = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return count;
	}

	//月ごとに全員のリスト表示（仮）
	public List<TotalM> findAllUserListByMonth(Integer year, Integer month) {

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
				"jdbc:postgresql:axiz_db",
				"axizuser",
				"axiz");) {
			PreparedStatement presmt = null;
			String sql = "SELECT * FROM totalm WHERE year = ? AND month = ? "
					+ "ORDER BY id, day, totalm_id";
			presmt = conn.prepareStatement(sql);
			presmt.setInt(1, year);
			presmt.setInt(2, month);
			ResultSet rset = presmt.executeQuery();

			// データベースから取得した値がある間、
			while (rset.next()) {

				TotalM tom = new TotalM();

				tom.setId(rset.getString("id"));
				tom.setTotalM_id(rset.getInt("totalM_id"));
				tom.setYear(rset.getInt("year"));
				tom.setMonth(rset.getInt("month"));
				tom.setDay(rset.getInt("day"));
				tom.setTransportation(rset.getString("transportation"));
				tom.setDepature(rset.getString("depature"));
				tom.setDestination(rset.getString("destination"));
				tom.setDivision(rset.getString("division"));
				tom.setMoney(rset.getInt("money"));
				tom.setPlace(rset.getString("place"));
				tom.setPurpose(rset.getString("purpose"));
				list.add(tom);
				// while文で次のレコードの処理へ?

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return list;
	}

	public List<TotalM> findAllByMonthForIdFromAdmin(String id, Integer year, Integer month) {

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
				"jdbc:postgresql:axiz_db",
				"axizuser",
				"axiz");) {
			PreparedStatement presmt = null;
			String sql = "SELECT * FROM totalm WHERE id = ? AND year = ? AND month = ? "
					+ "ORDER BY day, totalm_id";
			presmt = conn.prepareStatement(sql);
			presmt.setString(1, id);
			presmt.setInt(2, year);
			presmt.setInt(3, month);
			ResultSet rset = presmt.executeQuery();

			// データベースから取得した値がある間、
			while (rset.next()) {

				TotalM tom = new TotalM();

				tom.setId(rset.getString("id"));
				tom.setTotalM_id(rset.getInt("totalM_id"));
				tom.setYear(rset.getInt("year"));
				tom.setMonth(rset.getInt("month"));
				tom.setDay(rset.getInt("day"));
				tom.setTransportation(rset.getString("transportation"));
				tom.setDepature(rset.getString("depature"));
				tom.setDestination(rset.getString("destination"));
				tom.setDivision(rset.getString("division"));
				tom.setMoney(rset.getInt("money"));
				tom.setPlace(rset.getString("place"));
				tom.setPurpose(rset.getString("purpose"));
				list.add(tom);
				// while文で次のレコードの処理へ?

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return list;
	}

	public List<TotalM> findAllByMonthForNameFromAdmin(String name, Integer year, Integer month) {

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
				"jdbc:postgresql:axiz_db",
				"axizuser",
				"axiz");) {
			PreparedStatement presmt = null;
			String sql = "SELECT * FROM totalm AS m JOIN userinfo AS u ON m.id = u.id "
					+ "WHERE u.name = ? AND m.year = ? AND m.month = ? "
					+ "ORDER BY m.day, m.totalm_id";
			presmt = conn.prepareStatement(sql);
			presmt.setString(1, name);
			presmt.setInt(2, year);
			presmt.setInt(3, month);
			ResultSet rset = presmt.executeQuery();

			// データベースから取得した値がある間、
			while (rset.next()) {

				TotalM tom = new TotalM();

				tom.setId(rset.getString("id"));
				tom.setTotalM_id(rset.getInt("totalM_id"));
				tom.setYear(rset.getInt("year"));
				tom.setMonth(rset.getInt("month"));
				tom.setDay(rset.getInt("day"));
				tom.setTransportation(rset.getString("transportation"));
				tom.setDepature(rset.getString("depature"));
				tom.setDestination(rset.getString("destination"));
				tom.setDivision(rset.getString("division"));
				tom.setMoney(rset.getInt("money"));
				tom.setPlace(rset.getString("place"));
				tom.setPurpose(rset.getString("purpose"));
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
