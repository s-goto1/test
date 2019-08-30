package servlet2.work;

import java.io.IOException;
import java.sql.Time;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Work;
import test_0613f.work.InsertDao;
import test_0613f.work.WorkDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/work/Register")
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
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		String id = request.getParameter("id");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String comeTime = request.getParameter("comeTime");
		String leaveTime = request.getParameter("leaveTime");
		String brakeTime = request.getParameter("brakeTime");
		String workTime = request.getParameter("workTime");
		String overTime = request.getParameter("overTime");
		String visit = request.getParameter("visit");
		String notes = request.getParameter("notes");
		String vacation = request.getParameter("vacation");

		Integer year = (Integer) session.getAttribute("year");


		Integer m = Integer.valueOf(month);
		Integer d = Integer.valueOf(day);
		Time ct = Time.valueOf(comeTime);
		Time lt = Time.valueOf(leaveTime);
		Time bt = Time.valueOf(brakeTime);
		Time wt = Time.valueOf(workTime);
		Time ot = Time.valueOf(overTime);
		Integer v = Integer.valueOf(visit);

		InsertDao insert = new InsertDao();
		WorkDao work = new WorkDao();

		insert.insert(id, year, m, d, ct, lt, bt, wt, ot, v, notes, vacation);

		List<Work> list = work.findAllByMonthForId(id, year, m);

		request.setAttribute("month", m);
		request.setAttribute("day", d);
		request.setAttribute("comeTime", ct);
		request.setAttribute("leaveTime", lt);
		request.setAttribute("brakeTime", bt);
		request.setAttribute("WorkTime", wt);
		request.setAttribute("overTime", ot);
		request.setAttribute("notes", notes);
		session.setAttribute("currentpage", 1);
		session.setAttribute("list", list);

		RequestDispatcher dispatch = request.getRequestDispatcher("/work/registerResult.jsp");
		dispatch.forward(request, response);
	}
}
