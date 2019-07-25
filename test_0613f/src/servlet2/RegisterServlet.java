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
import test_0613f.InsertDao;
import test_0613f.TotalMDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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
		// TODO Auto-generated method stub
		doGet(request, response);

		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		String month = request.getParameter("month");
		String date = request.getParameter("date");
		String depature = request.getParameter("depature");
		String destination = request.getParameter("destination");
		String money1 = request.getParameter("money");

		int money = Integer.parseInt(money1);

		InsertDao IDao = new InsertDao();
		IDao.insert(id, month, date, depature, destination, money);

		HttpSession session = request.getSession();
		TotalMDao tmd = new TotalMDao();
		List<TotalM> list = tmd.findAllByMonth(id,month);
		session.setAttribute("list", list);

		RequestDispatcher dispatch = request.getRequestDispatcher("/home.jsp");
		dispatch.forward(request, response);

	}

}
