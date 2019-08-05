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
import test_0613f.InsertDao;
import test_0613f.TotalMDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/business/Register")
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
		String day = request.getParameter("day");
		String transportation = request.getParameter("transportation");
		String depature = request.getParameter("depature");
		String destination = request.getParameter("destination");
		String division = request.getParameter("division");
		String money1 = request.getParameter("money");
		String place = request.getParameter("place");
		String purpose = request.getParameter("purpose");

		HttpSession session = request.getSession();

		Integer year = (Integer) session.getAttribute("year");
		int money = Integer.parseInt(money1);
		Integer m= Integer.valueOf(month);
		Integer d= Integer.valueOf(day);

		InsertDao IDao = new InsertDao();
		IDao.insert(id, year, m, d, transportation, depature, destination,
				division, money, place, purpose);

		TotalMDao tmd = new TotalMDao();

		List<TotalM> list = tmd.findAllByMonth(id, year, m,1);

		int size = list.size();

		List<String> divisionList = new ArrayList<>();

		for(int i = 0; i < size; i++) {
			String divi = list.get(i).getDivision();
			if(divi.equals("片道")) {
				divi = "往復";
				divisionList.add(divi);
			} else {
				divi = "片道";
				divisionList.add(divi);
			}
		}

		request.setAttribute("month", m);
		request.setAttribute("day", d);
		request.setAttribute("transportation", transportation);
		request.setAttribute("depature", depature);
		request.setAttribute("destination", destination);
		request.setAttribute("division", division);
		request.setAttribute("money", money);
		request.setAttribute("place", place);
		request.setAttribute("purpose", purpose);
		session.setAttribute("list", list);
		session.setAttribute("divisionList", divisionList);

		RequestDispatcher dispatch = request.getRequestDispatcher("/business/registerResult.jsp");
		dispatch.forward(request, response);

	}

}
