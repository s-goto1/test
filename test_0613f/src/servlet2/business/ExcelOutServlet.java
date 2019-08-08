package servlet2.business;

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
import test_0613f.business.ExcelTest2;
import test_0613f.business.TotalMDao;
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

		GetPath gp = new GetPath();
		String INPUT_DIR = gp.getDesktopPath();

		//int offset = 0;
		int i = 0;
		int point = 0;
		int total = 0;
		int sheetNum = 0;

		List<TotalM> list =  totalM.findAllByMonthForIdFromAdmin(id, year, month);

		int size = list.size();

		if(size > 21) {
			point = size / 21;
		}

		List<Integer> totalList = new ArrayList<>();

		String fileNameAfter = "テストだよ.xls";

		for (TotalM totalm : list) {
			boolean rowReset = ext.excelOut(totalm, i++, INPUT_DIR, name, fileNameAfter, point, sheetNum);

			total += totalm.getMoney();

			point = 0;

			if(rowReset) {
				i = 0;

				sheetNum += 1;

				totalList.add(total);

				total = 0;
			}
		}

		totalList.add(total);

		if(size > 21) {
			point = size / 21;
		}

		size = 21 * (point + 1) - list.size();

		request.setAttribute("excel", INPUT_DIR +"\\" + fileNameAfter);
		request.setAttribute("list", list);
		request.setAttribute("point", point);
		request.setAttribute("size", size);
		request.setAttribute("total", totalList);

		RequestDispatcher dispatch = request.getRequestDispatcher("/business/excelOutResult.jsp");
		dispatch.forward(request, response);
	}

}
