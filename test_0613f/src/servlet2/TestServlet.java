package servlet2;

import java.io.IOException;
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
@WebServlet("/Test")
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
		String month = request.getParameter("selectTest");

		TotalMDao tmd = new TotalMDao();
		List<TotalM> list = tmd.findAllByMonth(id, month);
		session.setAttribute("list", list);
		System.out.println(list);
		session.setAttribute("nolist", "登録されているデータがありません。");

		RequestDispatcher dispatch = request.getRequestDispatcher("/home.jsp");
		dispatch.forward(request, response);

		System.out.println("aaa");

	}

}
