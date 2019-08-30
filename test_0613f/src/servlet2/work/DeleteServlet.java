package servlet2.work;

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

import entity.Work;
import test_0613f.work.DeleteDao;
import test_0613f.work.WorkDao;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/work/Delete")
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
		String[] work_id = request.getParameter("work_id").split(",");

		// セッションから情報を取得
		String id = (String) session.getAttribute("id");
		Integer year = (Integer) session.getAttribute("year");
		Integer month = (Integer) session.getAttribute("month");

		// チェックボックスにチェックがない？
		if (work_id.length == 1 && work_id[0].equals("")) {
			// home.jspへ遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/work/home.jsp");
			dispatch.forward(request, response);
		} else {
			// DAOの宣言
			DeleteDao delete = new DeleteDao();
			WorkDao work = new WorkDao();

			// 削除されたレコードを格納するリストの準備
			List<Work> deleteList = new ArrayList<>();

			// チェックされた分繰り返す
			for (String w : work_id) {
				// work_idをInteger型に変換
				Integer workId = Integer.parseInt(w);

				// 該当の情報を削除用リストに追加
				deleteList.addAll(delete.findDeleteList(workId));

				// 該当の情報を削除
				delete.delete(workId);
			}

			// 削除後のリスト取得
			List<Work> list = work.findAllByMonthForId(id, year, month);

			// リクエストに情報をセット
			request.setAttribute("deleteList", deleteList);

			// セッションに情報をセット
			session.setAttribute("list", list);

			// deleteResult.jspに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/work/deleteResult.jsp");
			dispatch.forward(request, response);

		}
	}
}
