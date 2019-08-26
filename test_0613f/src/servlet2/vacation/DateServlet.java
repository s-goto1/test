package servlet2.vacation;

import java.io.IOException;
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

import entity.Vacation;
import test_0613f.vacation.SearchDao;
import test_0613f.vacation.VacationDao;

/**
 * Servlet implementation class DateServlet
 */
@WebServlet("/vacation/Date")
public class DateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け防止
		request.setCharacterEncoding("UTF-8");

		// セッション情報取得の準備
		HttpSession session = request.getSession();

		// セッションから情報を取得
		String id = (String) session.getAttribute("id");
		Map<String, List<Vacation>> map = (Map<String, List<Vacation>>) session.getAttribute("map");

		// 入力値を取得
		String year = request.getParameter("year");

		// 型変換
		Integer y = Integer.valueOf(year);

		// DAOの宣言
		VacationDao vacation = new VacationDao();
		SearchDao search = new SearchDao();

		// homeAdmin.jspから遷移した？
		if(map != null) {
			// 一旦mapのセッションを空に
			session.setAttribute("map", "");

			// 今年の休暇申請データがある人物のIDを取得
			List<String> idList = search.findIdDistinct(y);

			// 今年の休暇申請データがある人物の名前を取得
			List<String> nameList = search.findNameDistinct(y);

			// データが1件でもある？
			if(idList.size() > 0 && nameList.size() > 0) {
				// 今年分の全社員の休暇申請データを取得
				map = idList.stream()
						.collect(Collectors.toMap(
								s -> s,
								s -> vacation.findAllByYearForId(s, y, 0)));

				// セッションに情報をセット
				session.setAttribute("map", map);
				session.setAttribute("nameList", nameList);
			// データが1件もない？
			} else {
				// セッションに情報をセット
				session.setAttribute("nomap", "登録されているデータがありません。");
			}

			// セッションに情報をセット
			session.setAttribute("year", y);

			// homeAdmin.jspに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/vacation/homeAdmin.jsp");
			dispatch.forward(request, response);
		// home.jspから遷移した？
		} else {
			// 変更後のリスト取得
			List<Vacation> list = vacation.findAllByYearForId(id, y, 0);

			// ページングなしでのレコード数取得
			int count = vacation.countRow(id, y);

			// ページング設定
			int number = (count + 5 - 1) / 5;

			// セッションに情報をセット
			session.setAttribute("currentpage", 1);
			session.setAttribute("year", y);
			session.setAttribute("number", number);
			session.setAttribute("list", list);

			// home.jspに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/vacation/home.jsp");
			dispatch.forward(request, response);
		}
	}
}
