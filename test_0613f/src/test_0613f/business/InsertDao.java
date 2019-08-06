package test_0613f.business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import entity.TotalM;

public class InsertDao {
	public void insert(String id, Integer year, Integer month, Integer day,
			String transportation, String depature, String destination,
			String division, int money, String place, String purpose) {

		Connection con = null;
		PreparedStatement presmt = null;
		ResultSet rs = null;

		String sql = "INSERT INTO totalm (ID,YEAR,MONTH,DAY,TRANSPORTATION,DEPATURE,DESTINATION,DIVISION,MONEY,PLACE,PURPOSE)VALUES (?,?,?,?,?,?,?,?,?,?,?) ";

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
			presmt.setString(5, transportation);
			presmt.setString(6, depature);
			presmt.setString(7, destination);
			presmt.setString(8, division);
			presmt.setInt(9, money);
			presmt.setString(10, place);
			presmt.setString(11, purpose);

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
