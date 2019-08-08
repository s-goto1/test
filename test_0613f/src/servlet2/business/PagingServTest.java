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
import test_0613f.business.TotalMDao;

/**
 * Servlet implementation class PagingServTest
 */
@WebServlet("/business/PagingServTest")
public class PagingServTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PagingServTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		// リクエストを取得
		String page = request.getParameter("page");

		int currentPage = Integer.valueOf(page);
		Integer offset = Integer.valueOf(page) * 5 - 5;

		// セッションから情報を取得
		String id = (String) session.getAttribute("id");
		Integer auth = (Integer) session.getAttribute("auth");
		Integer month = (Integer) session.getAttribute("month");
		Integer year = (Integer) session.getAttribute("year");

//		Integer y = Integer.valueOf(year);
//		Integer m = Integer.valueOf(month);

		TotalMDao tmd = new TotalMDao();

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

			// 今月分の全社員の出張精算データを取得
			Map<String, List<TotalM>> map = idListLimit.stream()
					.collect(Collectors.toMap(
							s -> s,
							s -> tmd.findAllByMonthForIdFromAdmin(s, year, month)));

			// セッションに情報をセット
			session.setAttribute("currentpage", 1);
			session.setAttribute("size", size);
			session.setAttribute("number", number);
			session.setAttribute("map", map);
			session.setAttribute("idList", idList);
			session.setAttribute("nameList", nameList);

			// homeAdmin.jspに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/business/homeAdmin.jsp");
			dispatch.forward(request, response);
		// 権限が担当者？
		} else {
			List<TotalM> list = tmd.findAllByMonthForId(id, year, month, offset);

			int count = tmd.countRow(id, year, month);

			List<String> divisionList = new ArrayList<>();

			int size = list.size();

			for (int i = 0; i < size; i++) {
				String division = list.get(i).getDivision();
				if (division.equals("片道")) {
					division = "往復";
					divisionList.add(division);
				} else {
					division = "片道";
					divisionList.add(division);
				}
			}

			int number = (count + 5 - 1) / 5;

			session.setAttribute("currentpage", currentPage);
			session.setAttribute("year", year);
			session.setAttribute("month", month);
			session.setAttribute("list", list);
			session.setAttribute("divisionList", divisionList);
			session.setAttribute("nolist", "登録されているデータがありません。");
			session.setAttribute("number", number);

			RequestDispatcher dispatch = request.getRequestDispatcher("/business/home.jsp");
			dispatch.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
