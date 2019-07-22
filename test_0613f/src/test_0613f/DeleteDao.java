package test_0613f;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteDao {

	public void delete( int totalM_id) {

		Connection con = null;
		PreparedStatement presmt = null;
		ResultSet rs = null;

		String sql = "DELETE FROM totalm WHERE totalm_id = ?";

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(
					"jdbc:postgresql:postgres",
					"postgres",
					"Asdf123");

			presmt = con.prepareStatement(sql);

			//presmt.setString(1, id);

			presmt.setInt(1, totalM_id);

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
