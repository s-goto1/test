package test_0613f.vacation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//import entity.TotalM;

public class InsertDao {
	public void insert(String id, Integer year, Integer fromMonth, Integer fromDay,
			Integer toMonth, Integer toDay, Integer totalDay, String division, String reason) {

		Connection con = null;
		PreparedStatement presmt = null;

		String sql = "INSERT INTO vacation (id, year, from_month, from_day, to_month, to_day, "
				+ "total_day, division, reason) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(
					"jdbc:postgresql:axiz_db",
					"axizuser",
					"axiz");

			presmt = con.prepareStatement(sql);

			presmt.setString(1, id);
			presmt.setInt(2, year);
			presmt.setInt(3, fromMonth);
			presmt.setInt(4, fromDay);
			presmt.setInt(5, toMonth);
			presmt.setInt(6, toDay);
			presmt.setInt(7, totalDay);
			presmt.setString(8, division);
			presmt.setString(9, reason);

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
