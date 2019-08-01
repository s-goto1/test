package test_0613f;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.TotalM;

public class DeleteDao {

	public List<TotalM> findDeleteList(int totalM_id) {
		// TotalM型のListの宣言
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
			// PreparedStatementの宣言
			PreparedStatement presmt = null;

			// SQL文
			String sql = "SELECT * FROM totalm WHERE totalm_id = ?";

			// SQL文をセット
			presmt = conn.prepareStatement(sql);

			// プレースホルダに埋め込み
			presmt.setInt(1, totalM_id);

			// SQL文実行
			ResultSet rset = presmt.executeQuery();

			// データベースから取得したレコード分必要な情報をセット
			while (rset.next()) {

				TotalM tom = new TotalM();

				tom.setId(rset.getString("id"));
				tom.setTotalM_id(rset.getInt("totalM_id"));
				tom.setYear(rset.getInt("year"));
				tom.setMonth(rset.getInt("month"));
				tom.setDay(rset.getInt("day"));
				tom.setTranspotation(rset.getString("transportation"));
				tom.setDepature(rset.getString("depature"));
				tom.setDestination(rset.getString("destination"));
				tom.setDivision(rset.getString("division"));
				tom.setMoney(rset.getInt("money"));
				tom.setPlace(rset.getString("place"));
				tom.setPurpose(rset.getString("purpose"));
				list.add(tom);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return list;
	}

	public void delete( int totalM_id) {

		Connection con = null;
		PreparedStatement presmt = null;
		ResultSet rs = null;

		String sql = "DELETE FROM totalm WHERE totalm_id = ?";

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(
					"jdbc:postgresql:axiz_db",
					"axizuser",
					"axiz");

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
