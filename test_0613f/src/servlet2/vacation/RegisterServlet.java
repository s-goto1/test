package servlet2.vacation;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Vacation;
import test_0613f.vacation.InsertDao;
import test_0613f.vacation.VacationDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/vacation/Register")
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
		String fromMonth = request.getParameter("fromMonth");
		String fromDay = request.getParameter("fromDay");
		String toMonth = request.getParameter("toMonth");
		String toDay = request.getParameter("toDay");
		String division = request.getParameter("division");
		String reason = request.getParameter("reason");

		Integer year = (Integer) session.getAttribute("year");
		Integer fm = Integer.valueOf(fromMonth);
		Integer fd = Integer.valueOf(fromDay);
		Integer tm = Integer.valueOf(toMonth);
		Integer td = Integer.valueOf(toDay);

		Integer totalDay = 0;

		InsertDao insert = new InsertDao();
		VacationDao vacation = new VacationDao();

		if(td - fd >= 0) {
			totalDay = td - fd + 1;
		} else {
			// オブジェクト生成
			LocalDate localDate = LocalDate.of(year, fm, fd);

			// 日数を取得
			int lastDay = localDate.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();

			// 月末は28日？
			if(lastDay == 28) {
				totalDay = 28 + td - fd + 1;
			// 月末は29日？
			} else if(lastDay == 29) {
				totalDay = 29 + td - fd + 1;
			// 月末は30日？
			} else if(lastDay == 30) {
				totalDay = 30 + td - fd + 1;
			// 月末は31日？
			} else {
				totalDay = 31 + td - fd + 1;
			}
		}

		insert.insert(id, year, fm, fd, tm, td, totalDay, division, reason);

		List<Vacation> list = vacation.findAllByYearForId(id, year, 0);

		request.setAttribute("fromMonth", fm);
		request.setAttribute("fromDay", fd);
		request.setAttribute("toMonth", tm);
		request.setAttribute("toDay", td);
		request.setAttribute("totalDay", totalDay);
		request.setAttribute("division", division);
		request.setAttribute("reason", reason);
		session.setAttribute("currentpage", 1);
		session.setAttribute("list", list);

		RequestDispatcher dispatch = request.getRequestDispatcher("/vacation/registerResult.jsp");
		dispatch.forward(request, response);
	}
}
