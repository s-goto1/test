package servlet2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/Update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
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

		String date[] = request.getParameterValues("date");
		String depature[] = request.getParameterValues("depature");
//		String destination = request.getParameter("destination");
//		String money1 = request.getParameter("money");
//		String totalM_id1 = request.getParameter("totalM_id");

//		List<String> uplist = new ArrayList<String>();

		String testList [][]= new String[2][];
		testList[0] = date;
		testList[1] = depature;

		System.out.println(testList[0][0]);
		System.out.println(testList[0][1]);



//		List<TotalM> totalM =
//		Collections.addAll(uplist, date);

//		System.out.println(uplist.get(0));

		//		int totalM_id = Integer.parseInt(totalM_id1);
		//		int money = Integer.parseInt(money1);

		//		UpdateDao ud = new UpdateDao();
		//ud.update(date, depature, destination, money, totalM_id);

		RequestDispatcher dispatch = request.getRequestDispatcher("/home.jsp");
		dispatch.forward(request, response);
	}

}
