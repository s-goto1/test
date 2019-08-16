package servlet2.vacation;

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

import entity.Vacation;
import test_0613f.vacation.VacationDao;

/**
 * Servlet implementation class PagingServlet
 */
@WebServlet("/vacation/Paging")
public class PagingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PagingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け回避
		request.setCharacterEncoding("UTF-8");

		// セッション情報取得の準備
		HttpSession session = request.getSession();

		// リクエストから情報を取得
		String page = request.getParameter("page");

		// 型変換
		int currentPage = Integer.valueOf(page);
		Integer offset = Integer.valueOf(page) * 5 - 5;

		// セッションから情報を取得
		String id = (String) session.getAttribute("id");
		Integer auth = (Integer) session.getAttribute("auth");
		Integer year = (Integer) session.getAttribute("year");
		Integer fromMonth = (Integer) session.getAttribute("fromMonth");

		VacationDao dao = new VacationDao();

		// 権限が管理者？
		if(auth == 1) {
			// セッションから情報を取得
			List<String> idList = (List<String>) session.getAttribute("idList");
			List<String> nameList = (List<String>) session.getAttribute("nameList");

			// リストのサイズを取得
			int size = idList.size();

			// ページ数を取得
			int number = (size + 5 - 1) / 5;

			// String型のListの宣言
			List<String> idListLimit = new ArrayList<>();

			// 残り人数が5人以上？
			if(offset + 5 <= size) {
				// 1度に表示するユーザ数を制限
				idListLimit = idList.subList(offset, offset + 5);
			// 残り人数が5人未満？
			} else {
				// 1度に表示するユーザ数を制限
				idListLimit = idList.subList(offset, size);
			}

			// 今月分の規定数の社員の出張精算データを取得
			Map<String, List<Vacation>> map = idListLimit.stream()
					.collect(Collectors.toMap(
							s -> s,
							s -> dao.findAllByMonthForIdFromAdmin(s, year)));

			// セッションに情報をセット
			session.setAttribute("currentpage", 1);
			session.setAttribute("size", size);
			session.setAttribute("number", number);
			session.setAttribute("map", map);
			session.setAttribute("idList", idList);
			session.setAttribute("nameList", nameList);

			// homeAdmin.jspに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/vacation/homeAdmin.jsp");
			dispatch.forward(request, response);
		// 権限が担当者？
		} else {
			List<Vacation> list = dao.findAllByMonthForId(id, year, offset);

			int count = dao.countRow(id, year);

			int number = (count + 5 - 1) / 5;

			session.setAttribute("currentpage", currentPage);
			session.setAttribute("year", year);
			session.setAttribute("fromMonth", fromMonth);
			session.setAttribute("list", list);
			session.setAttribute("nolist", "登録されているデータがありません。");
			session.setAttribute("number", number);

			RequestDispatcher dispatch = request.getRequestDispatcher("/vacation/home.jsp");
			dispatch.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
