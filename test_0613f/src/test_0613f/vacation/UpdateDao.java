package test_0613f.vacation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateDao {
	public void update(Integer fromMonth, Integer fromDay, Integer toMonth,
			Integer toDay, Integer totalDay, String division, String reason,
			Integer vacation_id) {

		Connection con = null;
		PreparedStatement presmt = null;

		String sql = "UPDATE vacation SET from_month = ?, from_day = ?, to_month = ?, "
				+ "to_day = ?, total_day = ?, division = ?, reason = ? "
				+ "WHERE vacation_id = ? ";

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(
					"jdbc:postgresql:axiz_db",
					"axizuser",
					"axiz");

			presmt = con.prepareStatement(sql);

			presmt.setInt(1, fromMonth);
			presmt.setInt(2, fromDay);
			presmt.setInt(3, toMonth);
			presmt.setInt(4, toDay);
			presmt.setInt(5, totalDay);
			presmt.setString(6, division);
			presmt.setString(7, reason);
			presmt.setInt(8, vacation_id);

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
