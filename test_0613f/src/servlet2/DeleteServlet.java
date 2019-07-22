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
import test_0613f.DeleteDao;
import test_0613f.TotalMDao;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/Delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteServlet() {
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
		//TotalM tom = new TotalM();

		String id = "1";
		String[] totalM_id = request.getParameterValues("totalM_id");

		if (totalM_id == null) {

			//			TotalMDao tmd = new TotalMDao();
			//			List<TotalM> list = tmd.findAll(id);
			//			HttpSession session = request.getSession();
			//			session.setAttribute("list", list);

			RequestDispatcher dispatch = request.getRequestDispatcher("/home.jsp");
			dispatch.forward(request, response);

		} else {

			DeleteDao dd = new DeleteDao();

			for (String a : totalM_id) {
				int totalMId = Integer.parseInt(a);

				dd.delete(totalMId);

			}

			TotalMDao tmd = new TotalMDao();
			List<TotalM> list = tmd.findAll(id);

			HttpSession session = request.getSession();
			session.setAttribute("list", list);


			System.out.println(id);

			RequestDispatcher dispatch = request.getRequestDispatcher("/home.jsp");
			dispatch.forward(request, response);

		}
	}
}
