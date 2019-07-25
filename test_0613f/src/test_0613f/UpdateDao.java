package test_0613f;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateDao {
	public void update(String month, String date, String depature, String destination, int money, int totalM_id) {

		Connection con = null;
		PreparedStatement presmt = null;
		ResultSet rs = null;

		String sql = "UPDATE totalm SET month= ?,date= ?,depature= ?,destination= ? ,money=? WHERE totalm_id =? ";

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(
					"jdbc:postgresql:axiz_db",
					"axizuser",
					"axiz");

			presmt = con.prepareStatement(sql);

			presmt.setString(1, month);
			presmt.setString(2, date);
			presmt.setString(3, depature);
			presmt.setString(4, destination);
			presmt.setInt(5, money);
			presmt.setInt(6, totalM_id);


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
