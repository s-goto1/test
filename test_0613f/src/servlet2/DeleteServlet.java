package servlet2;

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

import entity.TotalM;
import test_0613f.DeleteDao;
import test_0613f.TotalMDao;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/business/Delete")
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 文字化け回避
		request.setCharacterEncoding("UTF-8");

		// セッション取得の準備
		HttpSession session = request.getSession();
		//TotalM tom = new TotalM();

		// 入力値を取得（forEachで回しているため配列で受け取る）
		String[] totalM_id = request.getParameter("totalM_id").split(",");

		// セッションから情報を取得
		String id = (String) session.getAttribute("id");
		Integer year = (Integer) session.getAttribute("year");
		List<TotalM> mList = (List<TotalM>) session.getAttribute("list");

		// チェックボックスにチェックがない？
		if (totalM_id.length == 1 && totalM_id[0].equals("")) {

			//			TotalMDao tmd = new TotalMDao();
			//			List<TotalM> list = tmd.findAll(id);
			//			HttpSession session = request.getSession();
			//			session.setAttribute("list", list);

			// home.jspへ遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/home.jsp");
			dispatch.forward(request, response);

		} else {
			// DAOの宣言
			DeleteDao dd = new DeleteDao();
			TotalMDao tmd = new TotalMDao();

			// 削除されたレコードを格納するリストの準備
			List<TotalM> deleteList = new ArrayList<>();

			// チェックされた分繰り返す
			for (String a : totalM_id) {
				// totalM_idをint型に変換
				int totalMId = Integer.parseInt(a);

				// 該当の情報を削除用リストに追加
				deleteList.addAll(dd.findDeleteList(totalMId));

				// 該当の情報を削除
				dd.delete(totalMId);
			}

			// 削除したデータの月を取得
			Integer m = mList.get(0).getMonth();

			// 削除後のリスト取得
			List<TotalM> list = tmd.findAllByMonth(id, year, m);

			// リストのレコード数取得
			int size = list.size();

			// String型のListオブジェクトを生成
			List<String> divisionList = new ArrayList<>();

			// リストのレコード分繰り返す
			for(int i = 0; i < size; i++) {
				// 区分を取得
				String division = list.get(i).getDivision();

				// 区分が片道？
				if(division.equals("片道")) {
					// 往復をセット
					division = "往復";
					divisionList.add(division);
				// 区分が往復？
				} else {
					// 片道をセット
					division = "片道";
					divisionList.add(division);
				}
			}

			// リクエストに情報をセット
			request.setAttribute("deleteList", deleteList);

			// セッションに情報をセット
			session.setAttribute("list", list);
			session.setAttribute("divisionList", divisionList);

			// deleteResult.jspに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/business/deleteResult.jsp");
			dispatch.forward(request, response);

		}
	}
}
