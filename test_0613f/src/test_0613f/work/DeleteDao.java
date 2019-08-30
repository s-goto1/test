package test_0613f.work;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Work;

public class DeleteDao {

	public List<Work> findDeleteList(Integer work_id) {
		// Vacation型のListの宣言
		List<Work> list = new ArrayList<>();

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
			String sql = "SELECT * FROM work WHERE work_id = ?";

			// SQL文をセット
			presmt = conn.prepareStatement(sql);

			// プレースホルダに埋め込み
			presmt.setInt(1, work_id);

			// SQL文実行
			ResultSet rset = presmt.executeQuery();

			// データベースから取得したレコード分必要な情報をセット
			while (rset.next()) {
				Work work = new Work();

				work.setWork_id(rset.getInt("work_id"));
				work.setId(rset.getString("id"));
				work.setYear(rset.getInt("year"));
				work.setMonth(rset.getInt("month"));
				work.setDay(rset.getInt("day"));
				work.setComeTime(rset.getTime("come_time"));
				work.setLeaveTime(rset.getTime("leave_time"));
				work.setBrakeTime(rset.getTime("brake_time"));
				work.setWorkTime(rset.getTime("work_time"));
				work.setOverTime(rset.getTime("over_time"));
				work.setNotes(rset.getString("notes"));
				work.setVacation(rset.getString("vacation"));
				list.add(work);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DTOクラスのインスタンスのListを返す
		return list;
	}

	public void delete(Integer work_id) {
		Connection con = null;
		PreparedStatement presmt = null;

		String sql = "DELETE FROM work WHERE work_id = ?";

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(
					"jdbc:postgresql:axiz_db",
					"axizuser",
					"axiz");

			presmt = con.prepareStatement(sql);

			presmt.setInt(1, work_id);

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
