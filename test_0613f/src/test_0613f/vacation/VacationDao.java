package test_0613f.vacation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Vacation;

public class VacationDao {
	public List<Vacation> findAllByMonthForId(String id, Integer year, Integer fromMonth, int offset) {

		List<Vacation> list = new ArrayList<>();

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
			String sql = "SELECT * FROM vacation WHERE id = ? AND year = ? AND from_month = ? "
					+ "ORDER BY from_day, vacation_id LIMIT 5 OFFSET ?";
			presmt = conn.prepareStatement(sql);
			presmt.setString(1, id);
			presmt.setInt(2, year);
			presmt.setInt(3, fromMonth);
			presmt.setInt(4, offset);
			ResultSet rset = presmt.executeQuery();

			// データベースから取得した値がある間、
			while (rset.next()) {
				Vacation vacation = new Vacation();

				vacation.setVacation_id(rset.getInt("vacation_id"));
				vacation.setId(rset.getString("id"));
				vacation.setYear(rset.getInt("year"));
				vacation.setFromMonth(rset.getInt("from_month"));
				vacation.setFromDay(rset.getInt("from_day"));
				vacation.setToMonth(rset.getInt("to_month"));
				vacation.setToDay(rset.getInt("to_day"));
				vacation.setTotalDay(rset.getInt("total_day"));
				vacation.setDivision(rset.getString("division"));
				vacation.setReason(rset.getString("reason"));
				list.add(vacation);
				// while文で次のレコードの処理へ?
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return list;
	}

	//名前で絞って全部表示するメソッド（仮）
	public List<Vacation> findAllByMonthForName(String name, Integer year, Integer fromMonth, int offset) {

		List<Vacation> list = new ArrayList<>();

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
			String sql = "SELECT * FROM vacation AS v JOIN userinfo AS u ON v.id = u.id "
					+ "WHERE u.name = ? AND v.year = ? AND v.from_month = ? "
					+ "ORDER BY v.from_day, v.vacation_id LIMIT 5 OFFSET ?";
			presmt = conn.prepareStatement(sql);
			presmt.setString(1, name);
			presmt.setInt(2, year);
			presmt.setInt(3, fromMonth);
			presmt.setInt(4, offset);
			ResultSet rset = presmt.executeQuery();

			// データベースから取得した値がある間、
			while (rset.next()) {
				Vacation vacation = new Vacation();

				vacation.setVacation_id(rset.getInt("vacation_id"));
				vacation.setId(rset.getString("id"));
				vacation.setYear(rset.getInt("year"));
				vacation.setFromMonth(rset.getInt("from_month"));
				vacation.setFromDay(rset.getInt("from_day"));
				vacation.setToMonth(rset.getInt("to_month"));
				vacation.setToDay(rset.getInt("to_day"));
				vacation.setTotalDay(rset.getInt("total_day"));
				vacation.setDivision(rset.getString("division"));
				vacation.setReason(rset.getString("reason"));
				list.add(vacation);
				// while文で次のレコードの処理へ?
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return list;
	}

	//レコード数取得（ページング用）
	public int countRow(String id, Integer year, Integer fromMonth) {

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
			String sql = "SELECT COUNT (*) FROM vacation WHERE id = ? AND year = ? "
					+ "AND from_month = ? ";
			presmt = conn.prepareStatement(sql);
			presmt.setString(1, id);
			presmt.setInt(2, year);
			presmt.setInt(3, fromMonth);

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

	public List<Vacation> findAllByMonthForIdFromAdmin(String id, Integer year, Integer fromMonth) {

		List<Vacation> list = new ArrayList<>();

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
			String sql = "SELECT * FROM vacation WHERE id = ? AND year = ? AND from_month = ? "
					+ "ORDER BY from_day, vacation_id";
			presmt = conn.prepareStatement(sql);
			presmt.setString(1, id);
			presmt.setInt(2, year);
			presmt.setInt(3, fromMonth);
			ResultSet rset = presmt.executeQuery();

			// データベースから取得した値がある間、
			while (rset.next()) {
				Vacation vacation = new Vacation();

				vacation.setVacation_id(rset.getInt("vacation_id"));
				vacation.setId(rset.getString("id"));
				vacation.setYear(rset.getInt("year"));
				vacation.setFromMonth(rset.getInt("from_month"));
				vacation.setFromDay(rset.getInt("from_day"));
				vacation.setToMonth(rset.getInt("to_month"));
				vacation.setToDay(rset.getInt("to_day"));
				vacation.setTotalDay(rset.getInt("total_day"));
				vacation.setDivision(rset.getString("division"));
				vacation.setReason(rset.getString("reason"));
				list.add(vacation);
				// while文で次のレコードの処理へ?
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return list;
	}

	public List<Vacation> findAllByMonthForNameFromAdmin(String name, Integer year, Integer fromMonth) {

		List<Vacation> list = new ArrayList<>();

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
			String sql = "SELECT * FROM vacation AS v JOIN userinfo AS u ON v.id = u.id "
					+ "WHERE u.name = ? AND v.year = ? AND v.from_month = ? "
					+ "ORDER BY v.from_day, v.vacation_id";
			presmt = conn.prepareStatement(sql);
			presmt.setString(1, name);
			presmt.setInt(2, year);
			presmt.setInt(3, fromMonth);
			ResultSet rset = presmt.executeQuery();

			// データベースから取得した値がある間、
			while (rset.next()) {
				Vacation vacation = new Vacation();

				vacation.setVacation_id(rset.getInt("vacation_id"));
				vacation.setId(rset.getString("id"));
				vacation.setYear(rset.getInt("year"));
				vacation.setFromMonth(rset.getInt("from_month"));
				vacation.setFromDay(rset.getInt("from_day"));
				vacation.setToMonth(rset.getInt("to_month"));
				vacation.setToDay(rset.getInt("to_day"));
				vacation.setTotalDay(rset.getInt("total_day"));
				vacation.setDivision(rset.getString("division"));
				vacation.setReason(rset.getString("reason"));
				list.add(vacation);
				// while文で次のレコードの処理へ?
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return list;
	}
}
