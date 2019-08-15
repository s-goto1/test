package test_0613f.vacation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Vacation;

public class DeleteDao {

	public List<Vacation> findDeleteList(Integer vacation_id) {
		// Vacation型のListの宣言
		List<Vacation> list = new ArrayList<>();

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
			String sql = "SELECT * FROM vacation WHERE vacation_id = ?";

			// SQL文をセット
			presmt = conn.prepareStatement(sql);

			// プレースホルダに埋め込み
			presmt.setInt(1, vacation_id);

			// SQL文実行
			ResultSet rset = presmt.executeQuery();

			// データベースから取得したレコード分必要な情報をセット
			while (rset.next()) {
				Vacation vacation = new Vacation();

				vacation.setVacation_id(rset.getInt("vacation_id"));
				vacation.setId(rset.getString("id"));
				vacation.setYear(rset.getInt("year"));
				vacation.setFromMonth(rset.getInt("from_month"));
				vacation.setFromDay(rset.getInt("from_day"));
				vacation.setToMonth(rset.getInt("to_month"));
				vacation.setToDay(rset.getInt("to_day"));
				vacation.setTotalDay(rset.getInt("total_day"));
				vacation.setDivision(rset.getString("division"));
				vacation.setReason(rset.getString("reason"));
				list.add(vacation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return list;
	}

	public void delete(Integer vacation_id) {
		Connection con = null;
		PreparedStatement presmt = null;

		String sql = "DELETE FROM vacation WHERE vacation_id = ?";

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(
					"jdbc:postgresql:axiz_db",
					"axizuser",
					"axiz");

			presmt = con.prepareStatement(sql);

			presmt.setInt(1, vacation_id);

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
