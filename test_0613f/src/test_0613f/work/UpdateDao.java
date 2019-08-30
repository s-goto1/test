package test_0613f.work;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

public class UpdateDao {
	public void update(Integer month, Integer day, Time comeTime, Time leaveTime,
			Time brakeTime, Time workTime, Time overTime, Integer visit, String notes,
			String vacation, Integer work_id) {

		Connection con = null;
		PreparedStatement presmt = null;

		String sql = "UPDATE work SET month = ?, day = ?, come_time = ?, "
				+ "leave_time = ?, brake_time = ?, work_time = ?, over_time = ?, "
				+ "visit = ?, notes = ?, vacation = ? WHERE work_id = ? ";

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(
					"jdbc:postgresql:axiz_db",
					"axizuser",
					"axiz");

			presmt = con.prepareStatement(sql);

			presmt.setInt(1, month);
			presmt.setInt(2, day);
			presmt.setTime(3, comeTime);
			presmt.setTime(4, leaveTime);
			presmt.setTime(5, brakeTime);
			presmt.setTime(6, workTime);
			presmt.setTime(7,  overTime);
			presmt.setInt(8, visit);
			presmt.setString(9, notes);
			presmt.setString(10, vacation);
			presmt.setInt(11, work_id);

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
