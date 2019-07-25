package test_0613f;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import entity.TotalM;

public class InsertDao {
	public void insert(String id, String month, String date, String depature, String destination, int money) {

		Connection con = null;
		PreparedStatement presmt = null;
		ResultSet rs = null;

		String sql = "INSERT INTO totalm (ID,MONTH,DATE,DEPATURE,DESTINATION,MONEY)VALUES (?,?,?,?,?,?) ";

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(
					"jdbc:postgresql:axiz_db",
					"axizuser",
					"axiz");

			presmt = con.prepareStatement(sql);

			presmt.setString(1, id);
			presmt.setString(2, month);
			presmt.setString(3, date);
			presmt.setString(4, depature);
			presmt.setString(5, destination);
			presmt.setInt(6, money);

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
