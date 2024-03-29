package servlet2.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.TotalM;
import test_0613f.business.SearchDao;
import test_0613f.business.TotalMDao;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/business/Date")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
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
		doGet(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 文字化け防止
		request.setCharacterEncoding("UTF-8");

		// セッション情報取得の準備
		HttpSession session = request.getSession();

		// セッションから情報を取得
		String id = (String) session.getAttribute("id");
		Map<String, List<TotalM>> map = (Map<String, List<TotalM>>) session.getAttribute("map");

		boolean admin = request.getParameter("admin") != null;

		// 入力値を取得
		String month = request.getParameter("month");
		String year = request.getParameter("year");

		// 型変換
		Integer y = Integer.valueOf(year);
		Integer m = Integer.valueOf(month);

		// DAOの宣言
		TotalMDao tmd = new TotalMDao();
		SearchDao search = new SearchDao();

		// homeAdmin.jspから遷移した？
		if (admin) {
			// 一旦mapのセッションを空に
			session.removeAttribute("map");

			// 今月の出張清算データがある人物のIDを取得
			List<String> idList = search.findIdDistinct(y, m);

			// 今月の出張清算データがある人物の名前を取得
			List<String> nameList = search.findNameDistinct(y, m);

			// データが1件でもある？
			if (idList.size() > 0 && nameList.size() > 0) {
				// 今月分の全社員の出張精算データを取得
				map = idList.stream()
						.collect(Collectors.toMap(
								s -> s,
								s -> tmd.findAllByMonthForId(s, y, m, 0)));



				List<String> nameList2 = new ArrayList<String>();
				for (String key : map.keySet()) {

					nameList2.addAll(search.findNameById(key)); ;
				}

				// セッションに情報をセット
				session.setAttribute("map", map);
				session.setAttribute("nameList", nameList2);
				// データが1件もない？
			} else {
				// セッションに情報をセット
				session.setAttribute("nomap", "登録されているデータがありません。");
			}

			// セッションに情報をセット
			session.setAttribute("year", y);
			session.setAttribute("month", m);

			// homeAdmin.jspに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/business/homeAdmin.jsp");
			dispatch.forward(request, response);
			// home.jspから遷移した？
		} else {
			// 変更後のリスト取得
			List<TotalM> list = tmd.findAllByMonthForId(id, y, m, 0);

			// リストのレコード数取得
			int size = list.size();

			// ページングなしでのレコード数取得
			int count = tmd.countRow(id, y, m);

			// ページング設定
			int number = (count + 5 - 1) / 5;

			// String型のListオブジェクトを生成
			List<String> divisionList = new ArrayList<>();

			// リストのレコード分繰り返す
			for (int i = 0; i < size; i++) {
				// 区分を取得
				String division = list.get(i).getDivision();

				// 区分が片道？
				if (division.equals("片道")) {
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

			// セッションに情報をセット
			session.setAttribute("currentpage", 1);
			session.setAttribute("year", y);
			session.setAttribute("month", m);
			session.setAttribute("number", number);
			session.setAttribute("list", list);
			session.setAttribute("divisionList", divisionList);

			// home.jspに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/business/home.jsp");
			dispatch.forward(request, response);
		}
	}
}
