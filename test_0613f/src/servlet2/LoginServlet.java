package servlet2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.TotalM;
import entity.UserInfo;
import test_0613f.LoginUser;
import test_0613f.business.TotalMDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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

		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		Calendar cal = Calendar.getInstance();
		Integer year = cal.get(Calendar.YEAR);
		Integer month = cal.get(Calendar.MONTH) + 1;

		//String month = String.valueOf(m);

		LoginUser lu = new LoginUser();
		UserInfo ui = lu.findUser(id);

		boolean isLogin = (ui != null && id.equals(ui.getId()) &&
				pass.equals(ui.getPass()));
		HttpSession session = request.getSession();
		session.setAttribute("isLogin", isLogin);

		if (isLogin) {

			session.setAttribute("id", ui.getId());
			session.setAttribute("name", ui.getName());
			session.setAttribute("auth", ui.getAuth());

			if(ui.getAuth() == 1) {
				session.setAttribute("masterId", ui.getId());
				session.setAttribute("masterName", ui.getName());
				session.setAttribute("masterAuth", ui.getAuth());
			}

			TotalMDao tmd = new TotalMDao();

			List<TotalM> list = tmd.findAllByMonthForId(id, year, month, 0);

			//レコード数しゅとく
			int count = tmd.countRow(id,year, month);


			int size = list.size();

			List<String> divisionList = new ArrayList<>();

			for(int i = 0; i < size; i++) {
				String division = list.get(i).getDivision();
				if(division.equals("片道")) {
					division = "往復";
					divisionList.add(division);
				} else {
					division = "片道";
					divisionList.add(division);
				}
			}

			int number = (count + 5 - 1) / 5;
			session.setAttribute("currentpage", 1);
			session.setAttribute("year", year);
			session.setAttribute("month", month);
			session.setAttribute("list", list);
			session.setAttribute("size", size);
			session.setAttribute("number", number);

			session.setAttribute("divisionList", divisionList);
			session.setAttribute("nolist", "登録されているデータがありません。");

			RequestDispatcher dispatch = request.getRequestDispatcher("/menu.jsp");
			dispatch.forward(request, response);

		} else {
			request.setAttribute("error", "IDかパスワードが間違っています。\n再入力してください。");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}

	}

}
