package test_0613f;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.TotalM;

public class TotalMDao {


	public List<TotalM> findAllByMonth(String id, Integer year, Integer month) {

		List<TotalM> list = new ArrayList<TotalM>();

		// JDBCドライバ読み込み
		try {
			// PostgreSQLドライバの読み込み
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// データベースへの接続
		try (Connection conn = DriverManager.getConnection(
				"jdbc:postgresql:axiz_db",
				"axizuser",
				"axiz");) {
			PreparedStatement presmt = null;
			String sql = "SELECT * FROM totalm WHERE id = ? AND year = ? AND month = ? ORDER BY day, totalm_id";
			presmt = conn.prepareStatement(sql);
			presmt.setString(1, id);
			presmt.setInt(2, year);
			presmt.setInt(3, month);
			ResultSet rset = presmt.executeQuery();

			// データベースから取得した値がある間、
			while (rset.next()) {

				TotalM tom = new TotalM();

				tom.setId(rset.getString("id"));
				tom.setTotalM_id(rset.getInt("totalM_id"));
				tom.setYear(rset.getInt("year"));
				tom.setMonth(rset.getInt("month"));
				tom.setDay(rset.getInt("day"));
				tom.setTransportation(rset.getString("transportation"));
				tom.setDepature(rset.getString("depature"));
				tom.setDestination(rset.getString("destination"));
				tom.setDivision(rset.getString("division"));
				tom.setMoney(rset.getInt("money"));
				tom.setPlace(rset.getString("place"));
				tom.setPurpose(rset.getString("purpose"));
				list.add(tom);
				// while文で次のレコードの処理へ?

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return list;
	}

}
