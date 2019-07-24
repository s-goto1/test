package test_0613f;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateDao {
	public void update(String date, String depature, String destination, int money, int totalM_id) {

		Connection con = null;
		PreparedStatement presmt = null;
		ResultSet rs = null;

		String sql = "UPDATE totalm SET date= ?,depature= ?,destination= ? ,money=? WHERE totalm_id =? ";

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(
					"jdbc:postgresql:postgres",
					"postgres",
					"Asdf123");

			presmt = con.prepareStatement(sql);


			presmt.setString(1, date);
			presmt.setString(2, depature);
			presmt.setString(3, destination);
			presmt.setInt(4, money);
			presmt.setInt(5, totalM_id);


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
