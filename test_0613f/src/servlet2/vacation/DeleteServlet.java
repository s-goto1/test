package servlet2.vacation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Vacation;
import test_0613f.vacation.DeleteDao;
import test_0613f.vacation.VacationDao;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/vacation/Delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 文字化け回避
		request.setCharacterEncoding("UTF-8");

		// セッション取得の準備
		HttpSession session = request.getSession();

		// 入力値を取得（forEachで回しているため配列で受け取る）
		String[] vacation_id = request.getParameter("vacation_id").split(",");

		// セッションから情報を取得
		String id = (String) session.getAttribute("id");
		Integer year = (Integer) session.getAttribute("year");

		// チェックボックスにチェックがない？
		if (vacation_id.length == 1 && vacation_id[0].equals("")) {
			// home.jspへ遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/vacation/home.jsp");
			dispatch.forward(request, response);
		} else {
			// DAOの宣言
			DeleteDao delete = new DeleteDao();
			VacationDao vacation = new VacationDao();

			// 削除されたレコードを格納するリストの準備
			List<Vacation> deleteList = new ArrayList<>();

			// チェックされた分繰り返す
			for (String v : vacation_id) {
				// vacation_idをInteger型に変換
				Integer vacationId = Integer.parseInt(v);

				// 該当の情報を削除用リストに追加
				deleteList.addAll(delete.findDeleteList(vacationId));

				// 該当の情報を削除
				delete.delete(vacationId);
			}

			// 削除後のリスト取得
			List<Vacation> list = vacation.findAllByMonthForId(id, year, 0);

			// リクエストに情報をセット
			request.setAttribute("deleteList", deleteList);

			// セッションに情報をセット
			session.setAttribute("list", list);

			// deleteResult.jspに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/vacation/deleteResult.jsp");
			dispatch.forward(request, response);

		}
	}
}
