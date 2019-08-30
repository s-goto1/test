package test_0613f.work;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import entity.TimeTable;

public class TimeTableDao {
	public TimeTable findUser(String id) {
		// TimeTable型のインスタンス生成
		TimeTable timeTable = new TimeTable();

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
			String sql = "SELECT * FROM timetable WHERE id = ?";

			// SQL文をセット
			presmt = conn.prepareStatement(sql);

			// プレースホルダに埋め込み
			presmt.setString(1, id);

			// SQL文実行
			ResultSet rset = presmt.executeQuery();

			// データベースから取得したレコード分必要な情報をセット
			while (rset.next()) {
				timeTable.setTimeTableId(rset.getInt("timetable_id"));
				timeTable.setId(rset.getString("id"));
				timeTable.setCompanyNum(rset.getInt("company_num"));
				timeTable.setVisitName(rset.getString("visit_name"));
				timeTable.setVisitComeTime(rset.getTime("visit_come_time"));
				timeTable.setVisitLeaveTime(rset.getTime("visit_leave_time"));
				timeTable.setVisitBrakeTime(rset.getTime("visit_brake_time"));
				timeTable.setRoundTime(rset.getInt("round_time"));
				timeTable.setVacationDay(rset.getDouble("vacation_day"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのTimeTableを返す
		return timeTable;
	}

	public void insert(String id, Integer companyNum, String visitName,
			Time visitComeTime, Time visitLeaveTime, Time visitBrakeTime,
			Integer roundTime, Double vacationDay) {
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
			String sql = "INSERT INTO timetable (id, company_num, visit_name, "
					+ "visit_come_time, visit_leave_time, visit_brake_time, round_time, "
					+ "vacation_day) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

			// SQL文をセット
			presmt = conn.prepareStatement(sql);

			// プレースホルダに埋め込み
			presmt.setString(1, id);
			presmt.setInt(2, companyNum);
			presmt.setString(3, visitName);
			presmt.setTime(4, visitComeTime);
			presmt.setTime(5, visitLeaveTime);
			presmt.setTime(6, visitBrakeTime);
			presmt.setInt(7, roundTime);
			presmt.setDouble(8, vacationDay);

			// SQL文実行
			presmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateTable(Integer companyNum, String visitName, Time visitComeTime,
			Time visitLeaveTime, Time visitBrakeTime, Integer roundTime, Integer timeTableId) {
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
			String sql = "UPDATE timetable SET company_num = ?, visit_name = ?, "
					+ "visit_come_time = ?, visit_leave_time = ?, visit_brake_time = ?,"
					+ " round_time = ?, WHERE timetable_id = ?";

			// SQL文をセット
			presmt = conn.prepareStatement(sql);

			// プレースホルダに埋め込み
			presmt.setInt(1, companyNum);
			presmt.setString(2, visitName);
			presmt.setTime(3, visitComeTime);
			presmt.setTime(4, visitLeaveTime);
			presmt.setTime(5, visitBrakeTime);
			presmt.setInt(6, roundTime);
			presmt.setInt(7, timeTableId);

			// SQL文実行
			presmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateDay(Double vacationDay, Integer timeTableId) {
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
			String sql = "UPDATE timetable SET vacaton_day = ? WHERE timetable_id = ?";

			// SQL文をセット
			presmt = conn.prepareStatement(sql);

			// プレースホルダに埋め込み
			presmt.setDouble(1, vacationDay);
			presmt.setInt(2, timeTableId);

			// SQL文実行
			presmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
