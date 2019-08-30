package test_0613f.work;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

public class InsertDao {
	public void insert(String id, Integer year, Integer month, Integer day,
			Time comeTime, Time leaveTime, Time brakeTime, Time workTime,
			Time overTime, Integer visit, String notes, String vacation) {

		Connection con = null;
		PreparedStatement presmt = null;

		String sql = "INSERT INTO work (id, year, month, day, come_time, leave_time, "
				+ "brake_time, work_time, over_time, visit, notes, vacation) VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(
					"jdbc:postgresql:axiz_db",
					"axizuser",
					"axiz");

			presmt = con.prepareStatement(sql);

			presmt.setString(1, id);
			presmt.setInt(2, year);
			presmt.setInt(3, month);
			presmt.setInt(4, day);
			presmt.setTime(5, comeTime);
			presmt.setTime(6, leaveTime);
			presmt.setTime(7, brakeTime);
			presmt.setTime(8, workTime);
			presmt.setTime(9,  overTime);
			presmt.setInt(10, visit);
			presmt.setString(11, notes);
			presmt.setString(12, vacation);

			presmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			presmt.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
