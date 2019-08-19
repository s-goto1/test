package servlet2.vacation;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Vacation;
import test_0613f.vacation.ExcelOutDao;
import test_0613f.vacation.VacationDao;
import util.GetPath;

/**
 * Servlet implementation class ExcelOutServlet
 */
@WebServlet("/vacation/ExcelOut")
public class ExcelOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExcelOutServlet() {
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
		String id = (String) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		Integer year = (Integer) session.getAttribute("year");

		ExcelOutDao excelOut = new ExcelOutDao();
		VacationDao vacation = new VacationDao();

		GetPath gp = new GetPath();
		String INPUT_DIR = gp.getDesktopPath();

		int sheetNum = 0;

		List<Vacation> list = vacation.findAllByYearForIdFromAdmin(id, year);

		int size = list.size();

		String fileNameAfter = "休暇届_出力済.xls";

		for (Vacation vacance : list) {
			excelOut.excelOut(vacance, INPUT_DIR, name, fileNameAfter, sheetNum, size);

			sheetNum += 1;
		}

		request.setAttribute("excel", INPUT_DIR +"\\" + fileNameAfter);
		request.setAttribute("list", list);

		RequestDispatcher dispatch = request.getRequestDispatcher("/vacation/excelOutResult.jsp");
		dispatch.forward(request, response);
	}

}
