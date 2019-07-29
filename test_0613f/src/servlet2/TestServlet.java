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
 * Servlet implementation class TestServlet
 */
@WebServlet("/Month")
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String month = request.getParameter("month");

		Integer m= Integer.valueOf(month);

		TotalMDao tmd = new TotalMDao();

		List<TotalM> list = tmd.findAllByMonth(id, m);

		int size = list.size();

		List<String> divisionList = new ArrayList<>();

		for(int i = 0; i < size; i++) {
			String division = list.get(i).getDestination();
			if(division.equals("片道")) {
				division = "往復";
				divisionList.add(division);
			} else {
				division = "片道";
				divisionList.add(division);
			}
		}

		session.setAttribute("list", list);
		session.setAttribute("divisionList", divisionList);
		session.setAttribute("nolist", "登録されているデータがありません。");

		RequestDispatcher dispatch = request.getRequestDispatcher("/home.jsp");
		dispatch.forward(request, response);



	}

}
