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
import test_0613f.ExcelTest2;
import test_0613f.TotalMDao;
import util.GetPath;

/**
 * Servlet implementation class ExcelOutServlet
 */
@WebServlet("/business/ExcelOut")
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
		Integer month = (Integer) session.getAttribute("month");

		ExcelTest2 ext = new ExcelTest2();
		TotalMDao totalM = new TotalMDao();

		List<TotalM> list = totalM.findAllByMonthForIdFromAdmin(id, year, month);

		GetPath gp = new GetPath();
		String INPUT_DIR = gp.getDesktopPath();

		int i = 0;
		int total = 0;
		int size = 20 - list.size();
		String fileNameAfter = "テストだよ.xls";

		for (TotalM totalm : list) {
			ext.excelOut(totalm, i++, INPUT_DIR, name, fileNameAfter);

			total += totalm.getMoney();
		}
///aaaaaaaaaaaa
		request.setAttribute("excel", 	INPUT_DIR +"\\" + fileNameAfter);

		request.setAttribute("size", size);
		request.setAttribute("total", total);

		RequestDispatcher dispatch = request.getRequestDispatcher("/business/excelOutResult.jsp");
		dispatch.forward(request, response);
	}

}
