package servlet2;

import java.io.IOException;
import java.util.List;

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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		//TotalM tom = new TotalM();

		String id = (String) session.getAttribute("id");
		String[] totalM_id = request.getParameterValues("totalM_id");
		List<TotalM> mList = (List<TotalM>) session.getAttribute("list");



		if (totalM_id.length == 1 && totalM_id[0].equals("")) {

			//			TotalMDao tmd = new TotalMDao();
			//			List<TotalM> list = tmd.findAll(id);
			//			HttpSession session = request.getSession();
			//			session.setAttribute("list", list);

			response.sendRedirect("./home.jsp");
			//RequestDispatcher dispatch = request.getRequestDispatcher("/home.jsp");
			//dispatch.forward(request, response);

		} else {

			DeleteDao dd = new DeleteDao();

			for (String a : totalM_id) {
				int totalMId = Integer.parseInt(a);

				dd.delete(totalMId);

			}

			Integer m = mList.get(0).getMonth();

			TotalMDao tmd = new TotalMDao();
			List<TotalM> list = tmd.findAllByMonth(id,m);

			session.setAttribute("list", list);

			System.out.println(id);

			response.sendRedirect("./home.jsp");
			//RequestDispatcher dispatch = request.getRequestDispatcher("/home.jsp");
			//dispatch.forward(request, response);

		}
	}
}
