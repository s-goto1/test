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
import test_0613f.TotalMDao;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String page = request.getParameter("page");
		int currentPage = Integer.valueOf(page);

		Integer offset = Integer.valueOf(page) * 5 - 4;

		// セッションから情報を取得
		String id = (String) session.getAttribute("id");
		Integer auth = 2;

		// 入力値を取得
		Integer month = (Integer) session.getAttribute("month");
		Integer year = (Integer) session.getAttribute("year");

//		Integer y = Integer.valueOf(year);
//		Integer m = Integer.valueOf(month);

		TotalMDao tmd = new TotalMDao();

		List<TotalM> list = tmd.findAllByMonth(id, year, month, offset);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
