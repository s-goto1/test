package test_0613f;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateDao {
	public void update(Integer month, Integer day, String depature,
			String transportation, String destination, String division,
			int money, String place, String purpose, int totalM_id) {

		Connection con = null;
		PreparedStatement presmt = null;

		String sql = "UPDATE totalm SET month= ?,date= ?,transportation= ?,depature= ?,destination= ?,division= ?,money= ?, place= ?,purpose= ? WHERE totalm_id =? ";

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(
					"jdbc:postgresql:axiz_db",
					"axizuser",
					"axiz");

			presmt = con.prepareStatement(sql);

			presmt.setInt(1, month);
			presmt.setInt(2, day);
			presmt.setString(3, transportation);
			presmt.setString(4, depature);
			presmt.setString(5, destination);
			presmt.setString(6, division);
			presmt.setInt(7, money);
			presmt.setString(8, place);
			presmt.setString(9, purpose);
			presmt.setInt(10, totalM_id);


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
