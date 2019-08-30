package test_0613f.work;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Work;

public class WorkDao {
	public List<Work> findAllByMonthForId(String id, Integer year, Integer month) {
		List<Work> list = new ArrayList<>();

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
			String sql = "SELECT * FROM work WHERE id = ? AND year = ? AND month = ? "
					+ "ORDER BY day";
			presmt = conn.prepareStatement(sql);
			presmt.setString(1, id);
			presmt.setInt(2, year);
			presmt.setInt(3, month);
			ResultSet rset = presmt.executeQuery();

			// データベースから取得した値がある間、
			while (rset.next()) {
				Work work = new Work();

				work.setWork_id(rset.getInt("work_id"));
				work.setId(rset.getString("id"));
				work.setYear(rset.getInt("year"));
				work.setMonth(rset.getInt("month"));
				work.setDay(rset.getInt("day"));
				work.setComeTime(rset.getTime("come_time"));
				work.setLeaveTime(rset.getTime("leave_time"));
				work.setBrakeTime(rset.getTime("brake_time"));
				work.setWorkTime(rset.getTime("work_time"));
				work.setOverTime(rset.getTime("over_time"));
				work.setVisit(rset.getInt("visit"));
				work.setNotes(rset.getString("notes"));
				work.setVacation(rset.getString("vacation"));
				list.add(work);
				// while文で次のレコードの処理へ?
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return list;
	}

	public List<Work> findAllByMonthForName(String name, Integer year, Integer month) {
		List<Work> list = new ArrayList<>();

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
			String sql = "SELECT * FROM work AS w JOIN userinfo AS u ON w.id = u.id "
					+ "WHERE u.name = ? AND w.year = ? AND w.month = ? "
					+ "ORDER BY w.day";
			presmt = conn.prepareStatement(sql);
			presmt.setString(1, name);
			presmt.setInt(2, year);
			presmt.setInt(3, month);
			ResultSet rset = presmt.executeQuery();

			// データベースから取得した値がある間、
			while (rset.next()) {
				Work work = new Work();

				work.setWork_id(rset.getInt("work_id"));
				work.setId(rset.getString("id"));
				work.setYear(rset.getInt("year"));
				work.setMonth(rset.getInt("month"));
				work.setDay(rset.getInt("day"));
				work.setComeTime(rset.getTime("come_time"));
				work.setLeaveTime(rset.getTime("leave_time"));
				work.setBrakeTime(rset.getTime("brake_time"));
				work.setWorkTime(rset.getTime("work_time"));
				work.setOverTime(rset.getTime("over_time"));
				work.setNotes(rset.getString("notes"));
				work.setVacation(rset.getString("vacation"));
				list.add(work);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return list;
	}

	public List<Work> findAllByMonthForIdFromAdmin(String id, Integer year, Integer month) {
		List<Work> list = new ArrayList<>();

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
			String sql = "SELECT * FROM work WHERE id = ? AND year = ? AND month = ? "
					+ "ORDER BY day";
			presmt = conn.prepareStatement(sql);
			presmt.setString(1, id);
			presmt.setInt(2, year);
			presmt.setInt(3, month);
			ResultSet rset = presmt.executeQuery();

			// データベースから取得した値がある間、
			while (rset.next()) {
				Work work = new Work();

				work.setWork_id(rset.getInt("work_id"));
				work.setId(rset.getString("id"));
				work.setYear(rset.getInt("year"));
				work.setMonth(rset.getInt("month"));
				work.setDay(rset.getInt("day"));
				work.setComeTime(rset.getTime("come_time"));
				work.setLeaveTime(rset.getTime("leave_time"));
				work.setBrakeTime(rset.getTime("brake_time"));
				work.setWorkTime(rset.getTime("work_time"));
				work.setOverTime(rset.getTime("over_time"));
				work.setNotes(rset.getString("notes"));
				work.setVacation(rset.getString("vacation"));
				list.add(work);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return list;
	}

	public List<Work> findAllByMonthForNameFromAdmin(String name, Integer year, Integer month) {
		List<Work> list = new ArrayList<>();

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
			String sql = "SELECT * FROM work AS w JOIN userinfo AS u ON w.id = u.id "
					+ "WHERE u.name = ? AND w.year = ? AND w.month = ? "
					+ "ORDER BY w.day";
			presmt = conn.prepareStatement(sql);
			presmt.setString(1, name);
			presmt.setInt(2, year);
			presmt.setInt(3, month);
			ResultSet rset = presmt.executeQuery();

			// データベースから取得した値がある間、
			while (rset.next()) {
				Work work = new Work();

				work.setWork_id(rset.getInt("work_id"));
				work.setId(rset.getString("id"));
				work.setYear(rset.getInt("year"));
				work.setMonth(rset.getInt("month"));
				work.setDay(rset.getInt("day"));
				work.setComeTime(rset.getTime("come_time"));
				work.setLeaveTime(rset.getTime("leave_time"));
				work.setBrakeTime(rset.getTime("brake_time"));
				work.setWorkTime(rset.getTime("work_time"));
				work.setOverTime(rset.getTime("over_time"));
				work.setNotes(rset.getString("notes"));
				work.setVacation(rset.getString("vacation"));
				list.add(work);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return list;
	}
}
